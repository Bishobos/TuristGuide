package com.example.turistguide.Repository;

import com.example.turistguide.Model.TouristAttraction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttractionRowMapper implements RowMapper<TouristAttraction> {
    @Override
    public TouristAttraction mapRow(ResultSet resultSet, int rowNum) throws SQLException{
        return new TouristAttraction(
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getString("location"));
    }

}


/*
unpack tags here instead of in TouristAttraction, then clean up TouristAttraction.
 */