package inn.controller;

import inn.model.Person;
import inn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String createSession(RedirectAttributes redirectAttributes,
                                @RequestParam("username") String username, @RequestParam("password") String password) {
        Person user = userService.authenticate(username, password);
        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误！");
            redirectAttributes.addFlashAttribute("alertClass", "danger");
            return "redirect:/login";
        } else {
            redirectAttributes.addFlashAttribute("message", "登录成功！");
            redirectAttributes.addFlashAttribute("alertClass", "success");
            return "redirect:/";
        }
    }

}
