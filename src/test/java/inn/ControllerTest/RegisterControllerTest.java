package inn.ControllerTest;


import inn.controller.LoginController;
import inn.controller.ManagementController;
import inn.controller.RegisterController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(RegisterController.class)
public class RegisterControllerTest {

    @MockBean
    private ManagementController managementController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRegisterGet() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/register")).andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testRegisterPost() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/register").param("user", "user")).andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

}
