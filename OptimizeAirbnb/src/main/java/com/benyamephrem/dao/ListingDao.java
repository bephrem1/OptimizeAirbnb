package com.benyamephrem.dao;

import com.benyamephrem.model.Listing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ListingDao extends MongoRepository<Listing, String> {

    Listing findById(String id);

}
