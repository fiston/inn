package inn.ControllerTest;


import inn.controller.ManagementController;
import inn.controller.ReservationController;
import inn.controller.UserInfoController;
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
@WebMvcTest(UserInfoController.class)
public class UserInfoControllerTest {

    @MockBean
    private ManagementController managementController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testUserinfoGet() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/userinfo")).andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testUserinfoGetid() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/userinfo/{id}").param("id", 123)).andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }


}
