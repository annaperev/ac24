package de.autoscout24.listingreport.service;

import de.autoscout24.listingreport.entity.Contact;
import de.autoscout24.listingreport.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UploadCsvServiceTest {

    @Autowired
    UploadCsvService uploadCsvService;

    @Autowired
    ContactRepository contactRepository;

    @BeforeEach
    void clearDb() {
        contactRepository.deleteAll();
    }

    @Test
    void uploadContact() {
        List<Contact> initialContacts = Arrays.asList(
                new Contact(1244l, LocalDate.of(2021, 06, 20)),
                new Contact(1085l, LocalDate.of(2021, 06, 19)),
                new Contact(1288l, LocalDate.of(2021, 06, 18)));
        uploadCsvService.uploadContact(initialContacts);
        List<Contact> contactsFromDb = contactRepository.findAll();
        assertEquals(contactsFromDb.size(), 3);

        contactsFromDb.forEach(contact -> {
            assertTrue(initialContacts.stream().anyMatch(initialContact ->
                    initialContact.getListingId().equals(contact.getListingId()) &&
                            initialContact.getContactDate().equals(contact.getContactDate())
            ));
        });
    }

    //TODO add uploadListing
}