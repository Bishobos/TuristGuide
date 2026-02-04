package com.example.turistguide.Repository;

import com.example.turistguide.Model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    private List<TouristAttraction> attractions = new ArrayList<>();

    public TouristRepository(){
        populate();
    }

    private void populate(){
        attractions.add(new TouristAttraction("Tivoli", "En populær forlystelsespark"));
        attractions.add(new TouristAttraction("Bakken", "Et samlingspunkt og forlystelsespark"));
        attractions.add(new TouristAttraction("Tårnet", "Et gammelt tårn"));
    }

    public List<TouristAttraction> getAttractions(){
        return attractions;
    }

    public TouristAttraction getAttractionByName(String name){

        for (TouristAttraction attraction : attractions){
            if (attraction.getName().equals(name)){
                return attraction;
            }
        }
        return null;

    }

    public String addAttraction(TouristAttraction touristAttraction){
        attractions.add(touristAttraction);
        return "New attraction added.";
    }

    public String updateAttraction(TouristAttraction touristAttraction){
        for (int i = 0; i < attractions.size(); i++) {
            if(attractions.get(i).equals(touristAttraction)){
                attractions.set(i, touristAttraction);
                return attractions.get(i).getName() + " updated!";
            }
        }
             return " Attraction not found :(";
    }

    public String deleteAttraction(String name){
        for (TouristAttraction attraction : attractions){
            if (attraction.getName().equals(name)){
                attractions.remove(attraction);
                return attraction.getName() + " deleted!";
            }
        }
        return " Attraction not found :(";
    }
}
