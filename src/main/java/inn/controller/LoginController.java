package inn.controller;

import inn.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

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
    public String createSession(Model model, RedirectAttributes redirectAttributes, HttpSession session,
                                @RequestParam("username") String username, @RequestParam("password") String password) {
        val user = userService.authenticate(username, password);
        if (!user.isPresent()) {
            model.addAttribute("message", "用户名或密码错误！");
            model.addAttribute("alertClass", "danger");
            return "login";
        }
        session.setAttribute("id", user.get().getId());
        session.setAttribute("username", user.get().getUsername());
        redirectAttributes.addFlashAttribute("message", "登录成功！");
        redirectAttributes.addFlashAttribute("alertClass", "success");
        return "redirect:/userinfo";
    }

    @DeleteMapping("/logout")
    public String destroySession(RedirectAttributes redirectAttributes, HttpSession session) {
        session.removeAttribute("id");
        session.removeAttribute("username");
        redirectAttributes.addFlashAttribute("message", "登出成功！");
        redirectAttributes.addFlashAttribute("alertClass", "success");
        return "redirect:/";
    }

}
