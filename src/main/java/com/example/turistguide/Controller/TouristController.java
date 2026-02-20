package com.example.turistguide.Controller;

import com.example.turistguide.Model.TouristAttraction;
import com.example.turistguide.Repository.TouristRepository;
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
    public ResponseEntity<TouristAttraction> getAttractionByName(@PathVariable String name){
        return new ResponseEntity<>(service.getAttractionByName(name), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addAttraction(@RequestBody TouristAttraction touristAttraction){

        return new ResponseEntity<>(service.addAttraction(touristAttraction), HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateAtraction(@RequestBody TouristAttraction touristAttraction){

        return new ResponseEntity<>(service.updateAttraction(touristAttraction), HttpStatus.OK);
    }

    @PostMapping("/delete/{name}")
    public ResponseEntity<String> deleteAttraction(@PathVariable String name){
        return new ResponseEntity<>(service.deleteAttraction(name), HttpStatus.OK);
    }

    @GetMapping("/{name}/tags")
    public String getAttractionsTag(@PathVariable String name, Model model){
        TouristAttraction attraction = service.getAttractionByName(name);
            model.addAttribute("attraction",attraction);
            model.addAttribute("tags",attraction.getTags());
        return "tags";
    }

}
