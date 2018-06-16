package inn.controller;

import inn.service.ReservationService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Controller
public class ReservationController {

    private String[] capacities = {"不限", "1", "2", "3"};
    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reserve")
    public String showQueryForm(Model model) {
        model.addAttribute("capacities", capacities);
        return "reservation";
    }

    @PostMapping("/reserve")
    public String showAvailableRooms(Model model, RedirectAttributes redirectAttributes,
                                     @RequestParam("startDate") String startDate,
                                     @RequestParam("endDate") String endDate,
                                     @RequestParam("capacity") String capacity) {
        LocalDate start, end;
        try {
            start = LocalDate.parse(startDate);
            end = LocalDate.parse(endDate);
        } catch (DateTimeParseException e) {
            redirectAttributes.addFlashAttribute("message", "日期格式不正确！");
            redirectAttributes.addFlashAttribute("alertClass", "danger");
            return "redirect:/reserve";
        }
        int cap;
        try {
            cap = Integer.parseInt(capacity);
        } catch (NumberFormatException e) {
            cap = 0;
        }
        val map = reservationService.vacantRoomNumbers(start, end, cap);
        model.addAttribute("entries", map.entrySet());
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("capacity", capacity);
        model.addAttribute("capacities", capacities);
        return "reservation";
    }

}
