package com.example.turistguide.Controller;

import com.example.turistguide.Model.TouristAttraction;
import com.example.turistguide.Repository.TouristRepository;
import com.example.turistguide.Service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/attractions")
public class TouristController {

    private final TouristService service;

    public TouristController(TouristService service){
     this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TouristAttraction>> getAttractions(){
        return new ResponseEntity<>(service.getAttractions(), HttpStatus.OK);
    }





}
