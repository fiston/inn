package inn.ControllerTest;


import ch.qos.logback.core.encoder.EchoEncoder;
import inn.controller.LoginController;
import inn.controller.ManagementController;
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
@WebMvcTest(LoginController.class)
public class ManagementControllerTest {
    @MockBean
    private ManagementController managementController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testManagementGet() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/management")).andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testCheckinGet() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/checkin")).andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }


    @Test
    public void testCheckoutGet() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/checkout")).andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testReservationsPost() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/reservations").param("id", "123")).andReturn();
        System.out.print(result.getResponse().getContentAsString());
    }

    @Test
    public void testReservationsDelete() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/reservations").param("id", "123")).andReturn();
        System.out.print(result.getResponse().getContentAsString());
    }
}
