package de.autoscout24.listingreport.service;

import de.autoscout24.listingreport.entity.Contact;
import de.autoscout24.listingreport.entity.Listing;
import de.autoscout24.listingreport.repository.ContactRepository;
import de.autoscout24.listingreport.repository.ListingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ReportServiceTest {

    @Autowired
    ReportService reportService;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    ListingRepository listingRepository;

    @BeforeEach
    void clearDb() {
        contactRepository.deleteAll();
        listingRepository.deleteAll();
    }

    @Test
    void averagePriceOfMostContactedListings() {
        List<Listing> initialListings = Arrays.asList(
                new Listing(1244l, "Mazda", BigDecimal.valueOf(30000), BigDecimal.valueOf(2000), "other"),
                new Listing(1085l, "Audi", BigDecimal.valueOf(40000), BigDecimal.valueOf(8500), "private"),
                new Listing(1288l, "VW", BigDecimal.valueOf(50000), BigDecimal.valueOf(4500), "other"),
                new Listing(1231l, "Renault", BigDecimal.valueOf(34111), BigDecimal.valueOf(4500), "dealer"),
                new Listing(1215l, "Mazda", BigDecimal.valueOf(48868), BigDecimal.valueOf(500), "other"),
                new Listing(1095l, "Fiat", BigDecimal.valueOf(41177), BigDecimal.valueOf(3500), "private"),
                new Listing(1233l, "Audi", BigDecimal.valueOf(16250), BigDecimal.valueOf(4000), "other"),
                new Listing(1220l, "Fiat", BigDecimal.valueOf(16315), BigDecimal.valueOf(3000), "other"),
                new Listing(1188l, "BWM", BigDecimal.valueOf(25554), BigDecimal.valueOf(6500), "other"),
                new Listing(1257l, "Mercedes-Benz", BigDecimal.valueOf(2357), BigDecimal.valueOf(8000), "dealer"));
        listingRepository.saveAll(initialListings);

        List<Contact> initialContacts = Arrays.asList(
                new Contact(1244l, LocalDate.of(2021, 06, 20)),
                new Contact(1244l, LocalDate.of(2021, 06, 20)),
                new Contact(1244l, LocalDate.of(2021, 06, 20)),

                new Contact(1085l, LocalDate.of(2021, 06, 19)),
                new Contact(1085l, LocalDate.of(2021, 06, 19)),
                new Contact(1085l, LocalDate.of(2021, 06, 19)),

                new Contact(1288l, LocalDate.of(2021, 06, 18)),
                new Contact(1288l, LocalDate.of(2021, 06, 18)),

                new Contact(1231l, LocalDate.of(2021, 06, 18)),
                new Contact(1215l, LocalDate.of(2021, 06, 18)),
                new Contact(1095l, LocalDate.of(2021, 06, 18)),
                new Contact(1233l, LocalDate.of(2021, 06, 18)),
                new Contact(1220l, LocalDate.of(2021, 06, 18)),
                new Contact(1188l, LocalDate.of(2021, 06, 18)),
                new Contact(1257l, LocalDate.of(2021, 06, 18))
        );
        contactRepository.saveAll(initialContacts);
        assertEquals(40000l, reportService.averagePriceOfMostContactedListings());

    }
}