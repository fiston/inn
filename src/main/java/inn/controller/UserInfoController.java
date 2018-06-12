package inn.controller;

import inn.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserInfoController {

    private UserService userService;

    @Autowired
    public UserInfoController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userinfo")
    public String showUserInfo(Model model, HttpSession session) {
        val user = userService.findById((Integer) session.getAttribute("id")).get();
        model.addAttribute("user", user);
        return "userinfo";
    }

}
