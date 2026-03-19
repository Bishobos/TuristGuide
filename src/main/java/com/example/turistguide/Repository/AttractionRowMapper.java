package com.example.turistguide.Repository;

import com.example.turistguide.Model.TouristAttraction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttractionRowMapper implements RowMapper<TouristAttraction> {
    @Override
    public TouristAttraction mapRow(ResultSet resultSet, int rowNum) throws SQLException{
        TouristAttraction result = new TouristAttraction(
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getString("location"));

        return addTags(resultSet, result);
    }

    private TouristAttraction addTags(ResultSet resultSet, TouristAttraction attraction) throws SQLException{
        do{
            attraction.addTag(resultSet.getString("tags"));
        } while(resultSet.next());

        return attraction;
    }

}
