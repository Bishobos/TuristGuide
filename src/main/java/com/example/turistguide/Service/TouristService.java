package com.example.turistguide.Service;

import com.example.turistguide.Model.TouristAttraction;
import com.example.turistguide.Repository.TouristRepository;
import com.example.turistguide.Repository.TouristRepositorySQL;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class TouristService {
    private final TouristRepositorySQL repository;

    public TouristService(TouristRepositorySQL repository){
        this.repository = repository;
    }

    public List<TouristAttraction> getAttractions(){
        return repository.getAttractions();
    }

    public TouristAttraction getAttractionByName(String name){
        if(name==null){
            throw new RuntimeException();
        }
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
