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
}
