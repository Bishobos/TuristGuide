package com.example.turistguide.Model;

public class TouristAttraction {
    private String name;
    private String description;

    public TouristAttraction(String name, String description) {
     this.name = name;
     this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
