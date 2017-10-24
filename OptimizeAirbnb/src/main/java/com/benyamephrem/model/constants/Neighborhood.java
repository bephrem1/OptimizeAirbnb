package com.benyamephrem.model.constants;

public enum Neighborhood {

    BAYVIEW("Bayview", 37.730416, -122.384424),
    BERNAL_HEIGHTS("Bernal Heights", 37.741162, -122.417838),
    CASTRO("CASTRO/UPPER MARKET", 37.763776, -122.434034),
    UPPER_MARKET("CASTRO/UPPER MARKET", 37.763776, -122.434034),
    CHINATOWN("Chinatown", 37.794138, -122.407791),
    CROCKER_AMAZON("Crocker Amazon", 37.711952, -122.437584),
    DIAMOND_HEIGHTS("Diamond Heights", 37.742372, -122.442477),
    DOWNTOWN("Downtown/Civic Center", 37.781553, -122.415643),
    CIVIC_CENTER("Downtown/Civic Center", 37.781553, -122.415643),
    EXCELSIOR("EXCELSIOR", 37.724415, -122.42723),
    FINANCIAL_DISTRICT("FINANCIAL DISTRICT", 37.794574, -122.399944),
    GLEN_PARK("GLEN PARK", 37.737772, -122.432104),
    GOLDEN_GATE_PARK("GOLDEN GATE PARK", 37.769421, -122.486214),
    HAIGHT_ASHBURY("HAIGHT ASHBURY", 37.76922, -122.448139),
    INNER_RICHMOND("INNER RICHMOND", 37.77987, -122.464746),
    INNER_SUNSET("INNER SUNSET", 37.760737, -122.467954),
    LAKESHORE("LAKESHORE", 37.723317, -122.488679),
    MARINA("MARINA", 37.803667, -122.436815),
    MISSION("MISSION", 37.759865, -122.414798),
    NOB_HILL("NOB HILL", 37.793014, -122.416113),
    NOE_VALLEY("NOE VALLEY", 37.750238, -122.433703),
    NORTH_BEACH("NORTH BEACH", 37.806053, -122.410331),
    OCEAN_VIEW("OCEAN VIEW", 37.714183, -122.456632),
    OUTER_MISSION("OUTER MISSION", 37.715678, -122.445791),
    OUTER_RICHMOND("OUTER RICHMOND", 37.777677, -122.49531),
    OUTER_SUNSET("OUTER SUNSET", 37.755445, -122.494069),
    PACIFIC_HEIGHTS("PACIFIC HEIGHTS", 37.792515, -122.438231),
    PARKSIDE("PARKSIDE", 37.733048, -122.478567),
    POTRERO_HILL("POTRERO HILL", 37.760493, -122.400869),
    PRESIDIO("PRESIDIO", 37.798874, -122.466194),
    PRESIDIO_HEIGHTS("PRESIDIO HEIGHTS", 37.788913, -122.453801),
    RUSSIAN_HILL("RUSSIAN HILL", 37.801096, -122.419556),
    SEACLIFF("SEACLIFF", 37.785941, -122.4907),
    SOUTH_OF_MARKET("SOUTH OF MARKET", 37.778519, -122.405639),
    TREASURE_ISLAND("TREASURE ISLAND/YBI", 37.823552, -122.370648),
    YBI("TREASURE ISLAND/YBI", 37.823552, -122.370648),
    TWIN_PEAKS("TWIN PEAKS", 37.75251, -122.447568),
    VISITACION_VALLEY("VISITACION VALLEY", 37.717175, -122.404251),
    WEST_OF_TWIN_PEAKS("WEST OF TWIN PEAKS", 37.740561, -122.458885),
    WESTERN_ADDITION("WESTERN ADDITION", 37.7822, -122.4342);

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

