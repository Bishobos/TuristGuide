package com.example.turistguide.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TouristAttraction {
    private String name;
    private String description;
    private String location;
    private List<String> tags = new ArrayList<>();

    public TouristAttraction(String name, String description) {
     this.name = name;
     this.description = description;
    }

    public TouristAttraction(String name, String description, String location, List<String> tags) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.tags = tags;
    }

    public TouristAttraction(){}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }



    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TouristAttraction that = (TouristAttraction) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
