package inn.ServiceTest;


import inn.model.Customer;
import inn.model.Person;
import inn.model.Staff;
import inn.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testSave() {
        Customer customer = userService.newCustomer();
        customer.setUsername("wyuc");
        customer.setPassword("123456");
        customer.setIsVip(Boolean.FALSE);
        customer.setEmailAddress("wyuc@zju.edu.cn");
        customer.setGender("M");
        customer.setIdentityNumber("330881222334538876");
        customer.setPhoneNumber("1234567891");
        customer.setRealName("Wang yucheng");
        userService.save(customer);
    }


    @Test
    public void testFindById() {
        Customer customer = userService.newCustomer();
        customer.setUsername("wyuc1");
        customer.setPassword("123456");
        customer.setIsVip(Boolean.FALSE);
        customer.setEmailAddress("wyuc@zju.edu.cn");
        customer.setGender("M");
        customer.setIdentityNumber("330881222334538876");
        customer.setPhoneNumber("1234567891");
        customer.setRealName("Wang yucheng");
        userService.save(customer);
        Assert.assertEquals(userService.findById(customer.getId()).get().getUsername(), "wyuc1");
    }

    @Test
    public void testExsistsByUserName() {
        Customer customer = userService.newCustomer();
        customer.setUsername("wyuc2");
        customer.setPassword("123456");
        customer.setIsVip(Boolean.FALSE);
        customer.setEmailAddress("wyuc@zju.edu.cn");
        customer.setGender("M");
        customer.setIdentityNumber("330881222334538876");
        customer.setPhoneNumber("1234567891");
        customer.setRealName("Wang yucheng");
        userService.save(customer);
        Assert.assertEquals(userService.existsByUsername("cy"), false);
        Assert.assertEquals(userService.existsByUsername("wyuc2"), true);
    }

    @Test
    public void testAuthenticate() {
        Customer customer = userService.newCustomer();
        customer.setUsername("wyuc3");
        customer.setPassword("123456");
        customer.setIsVip(Boolean.FALSE);
        customer.setEmailAddress("wyuc@zju.edu.cn");
        customer.setGender("M");
        customer.setIdentityNumber("330881222334538876");
        customer.setPhoneNumber("1234567891");
        customer.setRealName("Wang yucheng");
        userService.save(customer);
        Assert.assertEquals(userService.authenticate("wyuc3", "123456").get().getUsername(), "wyuc3");
    }
}
