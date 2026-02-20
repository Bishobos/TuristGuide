package com.example.turistguide.Controller;

import com.example.turistguide.Model.TouristAttraction;
import com.example.turistguide.Service.TouristService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Array;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(TouristController.class)
class TouristControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TouristService touristService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldShowAttractions() throws Exception{
        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk())
                .andExpect(view().name("show-attractions"));
    }
/*
    @Test
    void getAttractionsTag() throws Exception{
        String attractionName ="Tivoli";
        List<String> tags = Array.set("Børnevenlig", "En populær forlystelsespark");
        TouristAttraction attraction = new TouristAttraction(attractionName,"En sjov park", "København",tags);
        mockMvc.perform(get("/attractions/{name}/tags", attractionName))
                .andExpect(status().isOk())
                .andExpect(view().name("tags"));

    }

 */
}