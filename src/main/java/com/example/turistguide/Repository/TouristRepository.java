package com.example.turistguide.Repository;

import com.example.turistguide.Model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    private List<TouristAttraction> attractions = new ArrayList<>();

    public List<TouristAttraction> getAttractions(){
        return attractions;
    }
}
