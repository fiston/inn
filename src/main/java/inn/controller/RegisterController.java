package inn.controller;

import inn.model.Customer;
import inn.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public Customer populateUser() {
        return userService.newCustomer();
    }

    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String createUser(Model model, RedirectAttributes redirectAttributes,
                             @Valid @ModelAttribute("user") Customer user, BindingResult result) {
        if (result.hasErrors()) {
            val message = new StringBuilder();
            for (FieldError error : result.getFieldErrors()) {
                message.append(error.getField());
                message.append("：");
                message.append(error.getDefaultMessage());
                message.append("。");
            }
            model.addAttribute("message", message.toString());
            model.addAttribute("alertClass", "danger");
            return "register";
        }
        if (userService.existsByUsername(user.getUsername())) {
            model.addAttribute("message", "用户名已被注册！");
            model.addAttribute("alertClass", "danger");
            return "register";
        }
        user.setIsVip(false);
        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "注册成功！");
        redirectAttributes.addFlashAttribute("alertClass", "success");
        return "redirect:/login";
    }

}
