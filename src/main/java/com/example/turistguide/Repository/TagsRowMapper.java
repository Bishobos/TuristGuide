package com.example.turistguide.Repository;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagsRowMapper implements RowMapper<List<String>> {
    @Override
    public List<String> mapRow(ResultSet resultSet, int rowNum) throws SQLException{
        List<String> results = new ArrayList<>();
        do {
            results.add(resultSet.getString("tags"));
        } while(resultSet.next());

        return results;
    }
}
