package inn.controller;

import inn.model.Staff;
import inn.service.ReservationService;
import inn.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class ManagementController {

    private UserService userService;
    private ReservationService reservationService;

    @Autowired
    public ManagementController(UserService userService, ReservationService reservationService) {
        this.userService = userService;
        this.reservationService = reservationService;
    }

    @GetMapping("/management")
    public String showReservations(Model model) {
        val reservations = reservationService.findAllReservations();
        model.addAttribute("reservations", reservations);
        model.addAttribute("formatter", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return "management";
    }

    @GetMapping("/checkin")
    public String showCheckin(Model model, HttpSession session) {
        val staff = (Staff) userService.findById((Integer) session.getAttribute("id")).get();
        val reservations = reservationService.checkinReservations(LocalDate.now());
        model.addAttribute("reservations", reservations);
        model.addAttribute("staffId", staff.getId());
        return "checkin";
    }

    @GetMapping("/checkout")
    public String showCheckout(Model model, HttpSession session) {
        val staff = (Staff) userService.findById((Integer) session.getAttribute("id")).get();
        val reservations = reservationService.checkoutReservations(LocalDate.now());
        model.addAttribute("reservations", reservations);
        model.addAttribute("staffId", staff.getId());
        return "checkout";
    }

}
