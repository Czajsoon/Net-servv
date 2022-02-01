package hospital.hospital.user.controllers;

import hospital.hospital.user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.PathVariable;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntTest {

@Autowired
private MockMvc mvc;
@Autowired
private UserRepository userRepository;


    @Test
    void users() throws Exception{
        RequestBuilder request= MockMvcRequestBuilders.get("/api/users");
        MvcResult result =mvc.perform(request).andReturn();
        assertEquals(0, result.getResponse().getContentLength());
    }


}