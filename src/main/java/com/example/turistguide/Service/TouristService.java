package com.example.turistguide.Service;

import com.example.turistguide.Model.TouristAttraction;
import com.example.turistguide.Repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {
    private final TouristRepository repository;

    public TouristService(TouristRepository repository){
        this.repository = repository;
    }

    public List<TouristAttraction> getAttractions(){
        return repository.getAttractions();
    }

    public TouristAttraction getAttractionByName(String name){
        return repository.getAttractionByName(name);
    }

    public String addAttraction(TouristAttraction touristAttraction){
        return repository.addAttraction(touristAttraction);
    }

    public String updateAttraction(TouristAttraction touristAttraction){
        return repository.updateAttraction(touristAttraction);
    }

    public String deleteAttraction(String name){
        return repository.deleteAttraction(name);
    }
}
