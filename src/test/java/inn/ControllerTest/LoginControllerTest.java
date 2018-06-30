package inn.ControllerTest;

import inn.Application;
import inn.controller.LoginController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

@RunWith(SpringRunner.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTest {
    @MockBean
    private LoginController loginController;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testLoginGet() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/login")).andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testLoginPost() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/login").param("username", "wyuc").param("password", "123456")).andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testLogoutDelete() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/logout")).andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }


}
