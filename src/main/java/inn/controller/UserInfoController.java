package inn.controller;

import inn.model.Customer;
import inn.model.Reservation;
import inn.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

@Controller
public class UserInfoController {

    private UserService userService;

    @Autowired
    public UserInfoController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userinfo")
    public String showUserInfo(Model model, HttpSession session) {
        val user = (Customer) userService.findById((Integer) session.getAttribute("id")).get();
        model.addAttribute("user", user);
        val reservations = user.getReservations();
        reservations.sort(Comparator.comparing(Reservation::getId));
        model.addAttribute("reservations", reservations);
        model.addAttribute("formatter", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return "userinfo";
    }

}
