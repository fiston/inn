package inn.controller;

import inn.model.Customer;
import inn.model.Reservation;
import inn.service.ReservationService;
import inn.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

@Controller
public class ReservationController {

    private String[] capacities = {"不限", "1", "2", "3"};

    private UserService userService;
    private ReservationService reservationService;

    @Autowired
    public ReservationController(UserService userService, ReservationService reservationService) {
        this.userService = userService;
        this.reservationService = reservationService;
    }

    @GetMapping("/reserve")
    public String showQueryForm(Model model) {
        model.addAttribute("capacities", capacities);
        return "reservation";
    }

    @PostMapping("/reserve")
    public String showAvailableRooms(Model model, RedirectAttributes redirectAttributes, @RequestParam String capacity,
                                     @RequestParam String startDate, @RequestParam String endDate) {
        val parseResult = parseStartAndEndDate(startDate, endDate);
        if (parseResult.errorMessage != null) {
            redirectAttributes.addFlashAttribute("message", parseResult.errorMessage);
            redirectAttributes.addFlashAttribute("alertClass", "danger");
            return "redirect:/reserve";
        }
        int cap;
        try {
            cap = Integer.parseInt(capacity);
        } catch (NumberFormatException e) {
            cap = 0;
        }
        val map = reservationService.vacantRoomTypesAndNumbers(parseResult.startDate, parseResult.endDate, cap);
        model.addAttribute("entries", map);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("capacity", capacity);
        model.addAttribute("capacities", capacities);
        return "reservation";
    }

    @PostMapping("/reserve/{roomTypeId}")
    public String reverseRoom(RedirectAttributes redirectAttributes, HttpSession session, @PathVariable int roomTypeId,
                              @RequestParam String startDate, @RequestParam String endDate) {
        val userId = session.getAttribute("id");
        if (userId == null) {
            redirectAttributes.addFlashAttribute("message", "预订客房请先登录！");
            redirectAttributes.addFlashAttribute("alertClass", "danger");
            return "redirect:/login";
        }
        val user = userService.findById((Integer) userId).get();
        if (!(user instanceof Customer)) {
            redirectAttributes.addFlashAttribute("message", "不可使用管理员账号预订客房！");
            redirectAttributes.addFlashAttribute("alertClass", "danger");
            return "redirect:/reserve";
        }
        val parseResult = parseStartAndEndDate(startDate, endDate);
        if (parseResult.errorMessage != null) {
            redirectAttributes.addFlashAttribute("message", parseResult.errorMessage);
            redirectAttributes.addFlashAttribute("alertClass", "danger");
            return "redirect:/reserve";
        }
        val reservation = new Reservation();
        val roomType = reservationService.findRoomTypeById(roomTypeId).get();
        val start = parseResult.startDate;
        val end = parseResult.endDate;
        float charge = roomType.getPrice() * ChronoUnit.DAYS.between(start, end);
        if (((Customer) user).getIsVip()) charge *= 0.9;
        reservation.setCustomer((Customer) user);
        reservation.setRoomType(roomType);
        reservation.setStartDate(start);
        reservation.setEndDate(end);
        reservation.setCharge(charge);
        reservation.setReservationTime(LocalDateTime.now());
        reservationService.saveReservation(reservation);
        redirectAttributes.addFlashAttribute("message", "预订成功！");
        redirectAttributes.addFlashAttribute("alertClass", "success");
        return "redirect:/userinfo";
    }

    private class ParseResult {
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String errorMessage;

        private ParseResult(LocalDate startDate, LocalDate endDate, String errorMessage) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.errorMessage = errorMessage;
        }
    }

    private ParseResult parseStartAndEndDate(String startDate, String endDate) {
        LocalDate start, end;
        try {
            start = LocalDate.parse(startDate);
            end = LocalDate.parse(endDate);
        } catch (DateTimeParseException e) {
            return new ParseResult(null, null, "日期格式不正确！");
        }
        if (!start.isBefore(end)) {
            return new ParseResult(null, null, "入住日期必须早于退房日期！");
        }
        if (start.isBefore(LocalDate.now())) {
            return new ParseResult(null, null, "入住日期不能早于今日！");
        }
        return new ParseResult(start, end, null);
    }

}
