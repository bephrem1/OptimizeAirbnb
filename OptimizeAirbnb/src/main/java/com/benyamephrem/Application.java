package com.benyamephrem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//TODO:be Implement a loading bar to let the user know when things are loading, etc.
@SpringBootApplication
public class Application{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

// TODO: Animate: Make frontend look halfway decent
// TODO: Animate: Add an animation to your visualization.
// TODO: Investment: If I have $100 million to invest, where in San Francisco should I buy properties so I can maximize my returns with Airbnb? When will I break even?
// TODO: Popularity: Can you identify the neighborhood that averages the most positive reviews?
// TODO: Cache database responses to save unnecessary queries
// TODO: Consolidate excessive database queries
// TODO: Clean up code verbosity and use Java 8 lambdas where you can
// TODO: Write Unit Tests for DAO, Service, and Controller layers.