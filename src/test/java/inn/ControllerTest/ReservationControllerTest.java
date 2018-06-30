package inn.ControllerTest;


import inn.controller.ManagementController;
import inn.controller.RegisterController;
import inn.controller.ReservationController;
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
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @MockBean
    private ManagementController managementController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testReserveGet() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/reserve")).andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testReservePost() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/reserve").param("reservation", "reservation")).andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }




}
