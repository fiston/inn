package inn.controller;

import inn.model.Customer;
import inn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String createUser(RedirectAttributes redirectAttributes,
                             @ModelAttribute("user") Customer user, BindingResult result) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", result.getAllErrors());
            redirectAttributes.addFlashAttribute("alertClass", "danger");
            return "redirect:/register";
        } else {
            redirectAttributes.addFlashAttribute("message", "注册成功！");
            redirectAttributes.addFlashAttribute("alertClass", "success");
            userService.save(user);
            return "redirect:/login";
        }
    }

}
