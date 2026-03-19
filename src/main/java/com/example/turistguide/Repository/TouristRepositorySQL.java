package com.example.turistguide.Repository;

import com.example.turistguide.Model.TouristAttraction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepositorySQL {
    private final JdbcTemplate jdbcTemplate;

    public TouristRepositorySQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public TouristAttraction getAttractionByName(String name){
        String sqlAttraction = String.format("select attractions.name as name, " +
                "attractions.location as location, " +
                "attractions.description as description, " +
                "tags.name as tags " +
                "from attractions_tags join attractions " +
                "on attractions.attraction_id = attractions_tags.attraction_id " +
                "and attractions.name ='%s' " +
                "join tags " +
                "on tags.tags_id = attractions_tags.tags_id", name);

        return jdbcTemplate.query(sqlAttraction, new AttractionRowMapper()).getFirst();
    }

    public List<TouristAttraction> getAttractions(){
        String sqlNames = "select name from attractions";

        List<String> names = jdbcTemplate.query(sqlNames, new SingleColoumnRowMapper());

        List<TouristAttraction> results = new ArrayList<>();

        for(String name: names){
            results.add(getAttractionByName(name));
        }

        return results;
    }



}
