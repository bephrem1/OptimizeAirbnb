package com.benyamephrem.dao;

import com.benyamephrem.model.Listing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ListingDao extends MongoRepository<Listing, String> {

    List<Listing> findAll();

    Listing findById(String id);

    //Lets us filter down into data tree to query listingLocation variable's neighborhood value...'?0' passes in the parameter
    //input for querying
    @Query(value = "{listingLocation.neighborhood:?0}")
    List<Listing> findByNeighborhood(String neighborhood);

    @Query(value = "{propertyLogistics.propertyType:?0}")
    List<Listing> findByPropertyType(String propertyType);

}
