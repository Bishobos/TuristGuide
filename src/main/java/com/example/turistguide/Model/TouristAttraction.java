package com.example.turistguide.Model;

import java.util.Objects;

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
