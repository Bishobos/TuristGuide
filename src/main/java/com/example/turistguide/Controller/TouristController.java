package com.example.turistguide.Controller;

import com.example.turistguide.Model.AttractionTags;
import com.example.turistguide.Model.TouristAttraction;
import com.example.turistguide.Service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/attractions")
public class TouristController {

    private final TouristService service;

    public TouristController(TouristService service) {
        this.service = service;
    }


    @GetMapping
    public String getAttractions(Model model) {
        List<TouristAttraction> attractions = service.getAttractions();
        model.addAttribute("attractions", attractions);
        return "show-attractions";
    }

    @GetMapping("/{name}")
    public String getAttractionByName(@PathVariable String name, Model model){
        TouristAttraction attraction = service.getAttractionByName(name);
        model.addAttribute("attraction", attraction);
        return "name-path";
    }



    @GetMapping("/add")
    public String addAttraction(Model model){
        TouristAttraction attraction = new TouristAttraction();
        model.addAttribute("attraction", attraction);
        model.addAttribute("tags", AttractionTags.values());
        return "registration-form";
    }

    @GetMapping("/{name}/edit")
    public String editAttraction(@PathVariable String name, Model model){
        TouristAttraction attraction = service.getAttractionByName(name);
        model.addAttribute("attraction", attraction);
        model.addAttribute("tags", AttractionTags.values());
        return "edit-attraction";
    }

    @PostMapping("/save")
    public String add(@ModelAttribute TouristAttraction touristAttraction){
        service.addAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    @PostMapping("/update")
    public String updateAttraction(@ModelAttribute TouristAttraction touristAttraction){
        service.updateAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    @PostMapping("/delete/{name}")
    public String deleteAttraction(@PathVariable String name){
        service.deleteAttraction(name);
        return "redirect:/attractions";
    }

    @GetMapping("/{name}/tags")
    public String getAttractionsTag(@PathVariable String name, Model model){
        TouristAttraction attraction = service.getAttractionByName(name);
            model.addAttribute("attraction",attraction);
            model.addAttribute("tags",attraction.getTags());
        return "tags";
    }

}
