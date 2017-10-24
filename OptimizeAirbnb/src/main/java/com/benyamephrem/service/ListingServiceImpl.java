package com.benyamephrem.service;

import com.benyamephrem.dao.ListingDao;
import com.benyamephrem.model.Listing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListingServiceImpl implements ListingService{
    @Autowired
    ListingDao listingDao;

    @Override
    public Listing findById(String id) {
        return listingDao.findById(id);
    }
}
