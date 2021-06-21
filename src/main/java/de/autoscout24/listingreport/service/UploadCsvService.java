package de.autoscout24.listingreport.service;

import de.autoscout24.listingreport.entity.Contact;
import de.autoscout24.listingreport.entity.Listing;
import de.autoscout24.listingreport.repository.ContactRepository;
import de.autoscout24.listingreport.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadCsvService {
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    ListingRepository listingRepository;

    public void uploadContact(List<Contact> dtos) {
        contactRepository.deleteAll();
        contactRepository.saveAll(dtos);
    }

    public void uploadListing(List<Listing> dtos) {
        listingRepository.deleteAll();
        listingRepository.saveAll(dtos);
    }

}

