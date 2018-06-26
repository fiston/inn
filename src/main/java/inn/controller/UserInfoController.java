package inn.controller;

import inn.model.Customer;
import inn.model.Reservation;
import inn.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Comparator;

@Controller
public class UserInfoController {

    private UserService userService;
    private Util util;

    @Autowired
    public UserInfoController(UserService userService, Util util) {
        this.userService = userService;
        this.util = util;
    }

    @GetMapping("/userinfo")
    public String showUserInfo(Model model, HttpSession session) {
        val id = (Integer) session.getAttribute("id");
        addAttributesToModel(model, id);
        return "userinfo";
    }

    @GetMapping("/userinfo/{id}")
    public String inspectUserInfo(Model model, RedirectAttributes redirectAttributes,
                                  HttpSession session, @PathVariable int id) {
        if (!util.isStaff(session)) {
            redirectAttributes.addFlashAttribute("message", "只有管理员账号可以访问此页面！");
            redirectAttributes.addFlashAttribute("alertClass", "danger");
            return "redirect:/";
        }
        addAttributesToModel(model, id);
        return "userinfo";
    }

    private void addAttributesToModel(Model model, int userId) {
        val user = (Customer) userService.findById(userId).get();
        val reservations = user.getReservations();
        reservations.sort(Comparator.comparing(Reservation::getId));
        model.addAttribute("user", user);
        model.addAttribute("reservations", reservations);
        model.addAttribute("formatter", util.datetimeFormatter());
    }

}
