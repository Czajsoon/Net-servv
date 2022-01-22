package hospital.hospital.visit.controllers;


import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VisitControllerIntTest {

    @Autowired
    private MockMvc mvc;


    @Before
    public void init() throws Exception {
        RequestBuilder addUser = MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"password\":\"kuba3\",\n" +
                        "    \"username\":\"12345\",\n" +
                        "    \"name\":\"Jakub\",\n" +
                        "    \"surname\":\"Czajkowski\",\n" +
                        "    \"bornDate\":\"2000-02-20\",\n" +
                        "    \"identification\":\"99999\",\n" +
                        "    \"sex\":\"Man\",\n" +
                        "    \"roles\":[{\"id\":2}]\n" +
                        "}");
        mvc.perform(addUser);
        RequestBuilder addDoctor = MockMvcRequestBuilders.post("/api/doctor")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"password\":\"kuba123\",\n" +
                        "    \"name\":\"Jakub\",\n" +
                        "    \"username\":\"kuba\",\n" +
                        "    \"surname\":\"Czajkowski\",\n" +
                        "    \"bornDate\":\"2000-02-25\",\n" +
                        "    \"room\":1,\n" +
                        "    \"roles\":[{\"id\":3}],\n" +
                        "    \"specialisations\":[1,3]\n" +
                        "}");
        mvc.perform(addDoctor);
        RequestBuilder addVisit = MockMvcRequestBuilders.post("/api/visits")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"date\":\"2022-01-06T06:00:00.000Z\",\n" +
                        "    \"description\":\"jakis opis wizyty\",\n" +
                        "    \"visitType\":1,\n" +
                        "    \"doctor\":2,\n" +
                        "    \"user\":99999\n" +
                        "}");
        mvc.perform(addVisit).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(2)
    public void testInit() throws Exception {
        init();
    }

    @Test
    @After
    @Order(3)
    public void Visit() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/api/visits");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals(0,result.getResponse().getContentLength());
    }

    @Test
    @After
    @Order(4)
    public void visits() throws Exception {
        RequestBuilder addVisit1 = MockMvcRequestBuilders.post("/api/visits")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"date\":\"2022-01-06T06:00:00.000Z\",\n" +
                        "    \"description\":\"jakis opis wizyty\",\n" +
                        "    \"visitType\":1,\n" +
                        "    \"doctor\":2,\n" +
                        "    \"user\":99999\n" +
                        "}");
        MvcResult result = mvc.perform(addVisit1).andReturn();
        assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    @After
    @Order(5)
    public void visitsTaken() throws Exception {
        RequestBuilder addVisit = MockMvcRequestBuilders.post("/api/visits")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"date\":\"2022-01-06T06:00:00.000Z\",\n" +
                        "    \"description\":\"jakis opis wizyty\",\n" +
                        "    \"visitType\":1,\n" +
                        "    \"doctor\":2,\n" +
                        "    \"user\":99999\n" +
                        "}");
        mvc.perform(addVisit);
        RequestBuilder addVisit1 = MockMvcRequestBuilders.post("/api/visits")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"date\":\"2022-01-06T06:00:00.000Z\",\n" +
                        "    \"description\":\"jakis opis wizyty\",\n" +
                        "    \"visitType\":1,\n" +
                        "    \"doctor\":2,\n" +
                        "    \"user\":99999\n" +
                        "}");
        MvcResult result = mvc.perform(addVisit1).andReturn();
        assertEquals("Wizyta w tej godzinie jest niedostępna!",result.getResponse().getContentAsString());
    }

    @Test
    @After
    @Order(6)
    public void visitsDoctorNotFound() throws Exception {
        RequestBuilder addVisit1 = MockMvcRequestBuilders.post("/api/visits")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"date\":\"2022-01-06T06:00:00.000Z\",\n" +
                        "    \"description\":\"jakis opis wizyty\",\n" +
                        "    \"visitType\":1,\n" +
                        "    \"doctor\":3,\n" +
                        "    \"user\":99999\n" +
                        "}");
        MvcResult result = mvc.perform(addVisit1).andReturn();
        assertEquals(404,result.getResponse().getStatus());
    }

    @Test
    @After
    @Order(7)
    public void visitsUserNotFound() throws Exception {
        RequestBuilder addVisit1 = MockMvcRequestBuilders.post("/api/visits")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"date\":\"2022-01-06T06:00:00.000Z\",\n" +
                        "    \"description\":\"jakis opis wizyty\",\n" +
                        "    \"visitType\":1,\n" +
                        "    \"doctor\":2,\n" +
                        "    \"user\":99899\n" +
                        "}");
        MvcResult result = mvc.perform(addVisit1).andReturn();
        assertEquals(404,result.getResponse().getStatus());
    }

    @Test
    @After
    @Order(8)
    public void visitsVisitTypeNotFound() throws Exception {
        RequestBuilder addVisit1 = MockMvcRequestBuilders.post("/api/visits")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"date\":\"2022-01-06T06:00:00.000Z\",\n" +
                        "    \"description\":\"jakis opis wizyty\",\n" +
                        "    \"visitType\":3,\n" +
                        "    \"doctor\":2,\n" +
                        "    \"user\":99999\n" +
                        "}");
        MvcResult result = mvc.perform(addVisit1).andReturn();
        assertEquals(404,result.getResponse().getStatus());
    }

    @Test
    @After
    @Order(9)
    public void visitsVisitBeforeOpen() throws Exception {
        RequestBuilder addVisit = MockMvcRequestBuilders.post("/api/visits")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"date\":\"2022-01-06T05:00:00.000Z\",\n" +
                        "    \"description\":\"jakis opis wizyty\",\n" +
                        "    \"visitType\":1,\n" +
                        "    \"doctor\":2,\n" +
                        "    \"user\":99999\n" +
                        "}");
        MvcResult result = mvc.perform(addVisit).andReturn();
        assertEquals("Clinic is closed! Clinic is open 6:00-14:00",result.getResponse().getContentAsString());
    }

    @Test
    @Order(11)
    public void VisitChangeDate100() throws Exception {
        RequestBuilder editVisit = MockMvcRequestBuilders.put("/api/visits")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"id\":1,\n" +
                        "    \"date\":\"2022-01-06T07:00:00.000Z\"\n" +
                        "}");
        mvc.perform(editVisit).andExpect(MockMvcResultMatchers.content().string("Pomyślnie zmieniono date wizyty!"));
    }

    @Test
    @After
    @Order(1)
    public void VisitChangeDateNotFound() throws Exception {
        RequestBuilder editVisit = MockMvcRequestBuilders.put("/api/visits")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"id\":10,\n" +
                        "    \"date\":\"2022-01-06T07:00:00.000Z\"\n" +
                        "}");
        mvc.perform(editVisit).andExpect(MockMvcResultMatchers.content().string("Visit not Found!"));
    }

    @Test
    @After
    @Order(10)
    public void VisitChangeDateNotPossible() throws Exception {
        RequestBuilder editVisit = MockMvcRequestBuilders.put("/api/visits")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"id\":1,\n" +
                        "    \"date\":\"2022-01-06T06:00:00.000Z\"\n" +
                        "}");
        MvcResult result = mvc.perform(editVisit).andReturn();
        assertEquals("Najbliższa możliwa wizyta jest: ",result.getResponse().getContentAsString().substring(0,32));
    }

    @Test
    @After
    @Order(12)
    public void deleteVisit() throws Exception {
        RequestBuilder deleteVisit = MockMvcRequestBuilders.delete("/api/visits?visitId=1");
        mvc.perform(deleteVisit).andExpect(MockMvcResultMatchers.content().string("Wizyta została pomyślnie anulowana!")).andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    @After
    @Order(13)
    public void searchVisit() throws Exception {
        RequestBuilder getVisit = MockMvcRequestBuilders.get("/api/visits/receptionist?spec=Wszystkie");
        mvc.perform(getVisit).andExpect(MockMvcResultMatchers.status().is(200));
    }
}