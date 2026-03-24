package com.example.turistguide.Repository;

import com.example.turistguide.Model.TouristAttraction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TouristRepositorySQL {
    private final JdbcTemplate jdbcTemplate;

    public TouristRepositorySQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public TouristAttraction getAttractionByName(String name){
        String sqlAttraction = ("select attractions.name as name, " +
                "attractions.location as location, " +
                "attractions.description as description, " +
                "tags.name as tags " +
                "from attractions_tags join attractions " +
                "on attractions.attraction_id = attractions_tags.attraction_id " +
                "and attractions.name = ? " +
                "join tags " +
                "on tags.tags_id = attractions_tags.tags_id");

        return jdbcTemplate.query(sqlAttraction, new AttractionRowMapper(), name).getFirst();
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

    public String addAttraction(TouristAttraction touristAttraction){
        String sqlAttraction = String.format("insert into attractions (name, location, description) " +
                "values ('%s', '%s', '%s'); ", touristAttraction.getName(),
                touristAttraction.getLocation(),
                touristAttraction.getDescription());

        String sqlTags = String.format("insert into attractions_tags (attraction_id, tags_id) " +
                "select a.attraction_id, t.tags_id " +
                "from ( " +
                "select attraction_id from attractions where name = '%s') as a " +
                "cross join ( " +
                "select tags_id from tags where name in (%s)) as t;", touristAttraction.getName(),
                formatTags(touristAttraction.getTags()));


        jdbcTemplate.update(sqlAttraction);
        jdbcTemplate.update(sqlTags);

        return "Attraction added";
    }

    public String updateAttraction(TouristAttraction touristAttraction){
        System.out.println(formatTags(touristAttraction.getTags()));
        String sqlUpdate = String.format("update attractions " +
                "Set location = '%s', description = '%s' " +
                "where attractions.name = '%s';", touristAttraction.getLocation(),
                touristAttraction.getDescription(),
                touristAttraction.getName());

        String sqlDeleteAll = "delete from attractions_tags where attraction_id=(" +
                "select attraction_id from attractions where name = ?);";

        String sqlInsertTags = String.format("insert into attractions_tags (attraction_id, tags_id) " +
                "select a.attraction_id, t.tags_id " +
                "from (" +
                "select attraction_id from attractions where name = '%s' " +
                ") as a " +
                "cross join( " +
                "select tags_id from tags where name in (%s) " +
                ") as t;", touristAttraction.getName(),
                formatTags(touristAttraction.getTags()));


        jdbcTemplate.update(sqlUpdate);

        jdbcTemplate.update(sqlDeleteAll, touristAttraction.getName());
        jdbcTemplate.update(sqlInsertTags);

        return "Attraction updated";
    }

    public String deleteAttraction(String name){
        String sqlDelete = "delete from attractions where attractions.name = ?";

        jdbcTemplate.update(sqlDelete, name);

        return name + "deleted";
    }

    private String formatTags(List<String> tags){
        if(tags.isEmpty()){
            return "";
        }
        StringBuilder formatted = new StringBuilder("'" + tags.getFirst() + "'");
        if(tags.size()>1){
            for (int i = 1; i< tags.size(); i++){
                formatted.append(", '").append(tags.get(i)).append("'");
            }
        }
        return formatted.toString();
    }



}
