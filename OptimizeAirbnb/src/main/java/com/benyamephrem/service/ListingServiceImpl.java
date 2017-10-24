package com.benyamephrem.service;

import com.benyamephrem.dao.ListingDao;
import com.benyamephrem.model.Listing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ListingServiceImpl implements ListingService{
    @Autowired
    ListingDao listingDao;

    @Override
    public Listing findById(String id) {
        return listingDao.findById(id);
    }

    @Override
    public List<Listing> findByNeighborhood(String neighborhood) {
        return listingDao.findByNeighborhood(neighborhood);
    }

    @Override
    public int getListingCountForNeighborhood(String neighborhood) {
        List<Listing> matchedListings = findByNeighborhood(neighborhood);
        return matchedListings.size();
    }

    @Override
    public Map<String, Integer> getNeighborhoodToListingCountMap() {


        return null;
    }

}
