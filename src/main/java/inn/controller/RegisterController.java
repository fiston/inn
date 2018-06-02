package inn.controller;

import inn.model.Customer;
import inn.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private PersonRepository personRepository;

    @ModelAttribute("user")
    public Customer customer() {
        return new Customer();
    }

    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String createUser(@ModelAttribute("user") Customer user) {
        personRepository.save(user);
        return "index";
    }

}
