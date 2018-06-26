package inn.controller;

import inn.model.Staff;
import inn.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.time.format.DateTimeFormatter;

@Component
public class Util {

    private UserService userService;

    @Autowired
    Util(UserService userService) {
        this.userService = userService;
    }

    boolean isStaff(HttpSession session) {
        val id = session.getAttribute("id");
        if (id == null) return false;
        val user = userService.findById((Integer) id).get();
        return user instanceof Staff;
    }

    DateTimeFormatter datetimeFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

}
