package com.example.turistguide.Controller;

import com.example.turistguide.Model.AttractionTags;
import com.example.turistguide.Model.TouristAttraction;
import com.example.turistguide.Service.TouristService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TouristController.class)
class TouristControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TouristService touristService;

    private TouristAttraction mockAttraction1 = new TouristAttraction("name1", "description1", "location1", List.of("GRATIS"));
    private TouristAttraction mockAttraction2 = new TouristAttraction("name2", "description2", "location2", List.of("GRATIS"));
    private TouristAttraction mockAttraction3 = new TouristAttraction("name3", "description3", "location3", List.of("GRATIS"));
    private final List<TouristAttraction> mockAttractions = new ArrayList<>() {
        {
            add(mockAttraction1);
            add(mockAttraction2);
            add(mockAttraction3);
        }
    };
    private TouristAttraction emptyAttraction = new TouristAttraction();


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void getAttractionsWithBody() throws Exception{
        when(touristService.getAttractions()).thenReturn(mockAttractions);

        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk())
                .andExpect(view().name("show-attractions"))
                .andExpect(model().attributeExists("attractions"))
                .andExpect(model().attribute("attractions", mockAttractions));

        verify(touristService).getAttractions();
    }

    @Test
    void getAttractionsNoBody() throws Exception{
        when(touristService.getAttractions()).thenReturn(null);

        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk())
                .andExpect(view().name("show-attractions"))
                .andExpect(model().attributeDoesNotExist("show-attractions"));

        verify(touristService).getAttractions();
    }

    @Test
    void getAttractionByName() {
    }

    @Test
    void addAttraction() throws Exception{
        mockMvc.perform(get("/attractions/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration-form"))
                .andExpect(model().attributeExists("attraction"))
                .andExpect(model().attributeExists("tags"))
                .andExpect(model().attribute("attraction", emptyAttraction))
                .andExpect(model().attribute("tags", AttractionTags.values()));
    }

    //can't figure out how to get it to work with the PathVariable
/*
    @Test
    void editAttraction() throws Exception{
        mockMvc.perform(get("/attractions/{name}/edit")
                        .param("name", "name2"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit-attraction"))
                .andExpect(model().attributeExists("attraction"))
                .andExpect(model().attributeExists("tags"))
                .andExpect(model().attribute("attraction", mockAttraction2))
                .andExpect(model().attribute("tags", AttractionTags.values()));
        verify(touristService).getAttractionByName("name2");
    }
*/

    @Test
    void addSomething() throws Exception{
        mockMvc.perform(post("/attractions/save")
                .contentType("application/x-www-form-urlencoded")
                .param("name", "name1")
                .param("description", "description1")
                .param("location", "location1")
                .param("tags", AttractionTags.values()[0].toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/attractions"));

        ArgumentCaptor<TouristAttraction> captor = ArgumentCaptor.forClass(TouristAttraction.class);
        verify(touristService).addAttraction(captor.capture());

        TouristAttraction saved = captor.getValue();
        assertEquals("name1", saved.getName());
        assertEquals("description1", saved.getDescription());
        assertEquals("location1", saved.getLocation());
        assertEquals(List.of(AttractionTags.values()[0].toString()), saved.getTags());
    }
    @Test
    void addNothing() throws Exception{
        mockMvc.perform(post("/attractions/save")
                        .contentType("application/x-www-form-urlencoded")
                        .param("tags", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/attractions"));

        ArgumentCaptor<TouristAttraction> captor = ArgumentCaptor.forClass(TouristAttraction.class);
        verify(touristService).addAttraction(captor.capture());

        TouristAttraction saved = captor.getValue();
        assertNull(saved.getName());
        assertNull(saved.getDescription());
        assertNull(saved.getLocation());
        assertEquals(List.of(), saved.getTags());
    }

    @Test
    void updateAttraction() throws Exception{
        mockMvc.perform(post("/attractions/update")
                        .contentType("application/x-www-form-urlencoded")
                        .param("name", "name2")
                        .param("description", "description2")
                        .param("location", "location2")
                        .param("tags", AttractionTags.values()[0].toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/attractions"));

        ArgumentCaptor<TouristAttraction> captor = ArgumentCaptor.forClass(TouristAttraction.class);
        verify(touristService).updateAttraction(captor.capture());

        TouristAttraction saved = captor.getValue();
        assertEquals("name2", saved.getName());
        assertEquals("description2", saved.getDescription());
        assertEquals("location2", saved.getLocation());
        assertEquals(List.of(AttractionTags.values()[0].toString()), saved.getTags());
    }

    @Test
    void deleteAttraction() {
    }

    @Test
    void getAttractionsTag() {
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