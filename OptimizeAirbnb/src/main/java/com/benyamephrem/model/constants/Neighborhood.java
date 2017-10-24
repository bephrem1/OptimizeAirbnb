package com.benyamephrem.model.constants;

public enum Neighborhood {

    BAYVIEW("Bayview", 37.730416, -122.384424),
    BERNAL_HEIGHTS("Bernal Heights", 37.741162, -122.417838),
    CASTRO("Castro/Upper Market", 37.763776, -122.434034),
    UPPER_MARKET("Castro/Upper Market", 37.763776, -122.434034),
    CHINATOWN("Chinatown", 37.794138, -122.407791),
    CROCKER_AMAZON("Crocker Amazon", 37.711952, -122.437584),
    DIAMOND_HEIGHTS("Diamond Heights", 37.742372, -122.442477),
    DOWNTOWN("Downtown/Civic Center", 37.781553, -122.415643),
    CIVIC_CENTER("Downtown/Civic Center", 37.781553, -122.415643),
    EXCELSIOR("Excelsior", 37.724415, -122.42723),
    FINANCIAL_DISTRICT("Financial District", 37.794574, -122.399944),
    GLEN_PARK("Glen Park", 37.737772, -122.432104),
    GOLDEN_GATE_PARK("Golden Gate Park", 37.769421, -122.486214),
    HAIGHT_ASHBURY("Haight Ashbury", 37.76922, -122.448139),
    INNER_RICHMOND("Inner Richmond", 37.77987, -122.464746),
    INNER_SUNSET("Inner Sunset", 37.760737, -122.467954),
    LAKESHORE("Lakeshore", 37.723317, -122.488679),
    MARINA("Marina", 37.803667, -122.436815),
    MISSION("Mission", 37.759865, -122.414798),
    NOB_HILL("Nob Hill", 37.793014, -122.416113),
    NOE_VALLEY("Noe Valley", 37.750238, -122.433703),
    NORTH_BEACH("North Beach", 37.806053, -122.410331),
    OCEAN_VIEW("Ocean View", 37.714183, -122.456632),
    OUTER_MISSION("Outer Mission", 37.715678, -122.445791),
    OUTER_RICHMOND("Outer Richmond", 37.777677, -122.49531),
    OUTER_SUNSET("Outer Sunset", 37.755445, -122.494069),
    PACIFIC_HEIGHTS("Pacific Heights", 37.792515, -122.438231),
    PARKSIDE("Parkside", 37.733048, -122.478567),
    POTRERO_HILL("Potrero Hill", 37.760493, -122.400869),
    PRESIDIO("Presidio", 37.798874, -122.466194),
    PRESIDIO_HEIGHTS("Presidio Heights", 37.788913, -122.453801),
    RUSSIAN_HILL("Russian Hill", 37.801096, -122.419556),
    SEACLIFF("Seacliff", 37.785941, -122.4907),
    SOUTH_OF_MARKET("South of Market", 37.778519, -122.405639),
    TREASURE_ISLAND("Treasure Island/YBI", 37.823552, -122.370648),
    YBI("Treasure Island/YBI", 37.823552, -122.370648),
    TWIN_PEAKS("Twin Peaks", 37.75251, -122.447568),
    VISITACION_VALLEY("Visitacion Valley", 37.717175, -122.404251),
    WEST_OF_TWIN_PEAKS("West of Twin Peaks", 37.740561, -122.458885),
    WESTERN_ADDITION("Western Addition", 37.7822, -122.4342);

    private String name;
    private double latitude;
    private double longitude;

    Neighborhood(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

