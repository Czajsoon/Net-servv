package hospital.hospital.refferalAbsention.controllers;

import hospital.hospital.refferalAbsention.repository.RefferalAbsentionRepository;
import hospital.hospital.user.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.PostConstruct;

import static org.junit.jupiter.api.Assertions.*;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class RefferalAbsentionControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private RefferalAbsentionRepository refferalAbsentionRepository;

    @Before
    public void DodajRef() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.post("/api/refferalAbsention")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"startDate\":\"2000-02-25\",\n" +
                        "\"endDate\":\"2019-05-26\",\n" +
                        "\"description\":\"costamcosctam\",\n" +
                        "\"doctor\":\"1\",\n" +
                        "\"user\":\"1\",\n" +
                        "\"visit\":1" +
                        "}");
       mvc.perform(request);
       mvc.perform(request);
    }
    @Test
    public void testInit() throws Exception{
        DodajRef();
    }

 /*   @Test
    @After
    void refAbsDELETE() throws Exception{
        RequestBuilder request= MockMvcRequestBuilders.delete("/api/refferalAbsention/1");
        MvcResult result=mvc.perform(request).andReturn();
        assertEquals(200,result.getResponse().getStatus());

    }*/

    @Test
    @After
    void refAbs() throws Exception {
        RequestBuilder request= MockMvcRequestBuilders.get("/api/refferalAbsention");
        MvcResult result=mvc.perform(request).andReturn();
        assertEquals(0, result.getResponse().getContentLength());

    }

    @Test
    @After
    void ra() throws Exception {
        RequestBuilder request= MockMvcRequestBuilders.post("/api/refferalAbsention")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n"+
                        "\"startDate\":\"2000-02-25\",\n" +
                        "\"endDate\":\"2019-05-26\",\n" +
                        "\"description\":\"costamcosctam\",\n" +
                        "\"doctor\":\"1\",\n" +
                        "\"user\":\"1\",\n" +
                        "\"visit\":1"+
                            "}");
        MvcResult result= mvc.perform(request).andReturn();
        assertEquals(200,result.getResponse().getStatus());
    }





}