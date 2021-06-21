package de.autoscout24.listingreport.controller;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import de.autoscout24.listingreport.entity.Contact;
import de.autoscout24.listingreport.entity.Listing;
import de.autoscout24.listingreport.exception.CsvUploadException;
import de.autoscout24.listingreport.service.UploadCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@RequestMapping("/api")
@RestController
public class UploadController {

    @Autowired
    UploadCsvService uploadCsvService;

    @PutMapping("/listingsCsv")
    public void uploadListingsCsv(@RequestParam MultipartFile file) {
        if (file.isEmpty()) {
            throw new CsvUploadException("File is empty");
        } else {
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                CsvToBean<Listing> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Listing.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();
                List<Listing> listings = csvToBean.parse();
                uploadCsvService.uploadListing(listings);
            } catch (IOException e) {
                throw new CsvUploadException("Csv validation");
            }
        }
    }

    @PutMapping("/contactsCsv")
    public void uploadContactsCsv(@RequestParam MultipartFile file) {
        if (file.isEmpty()) {
            throw new CsvUploadException("File is empty");
        } else {
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                CsvToBean<Contact> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Contact.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();
                List<Contact> contacts = csvToBean.parse();
                uploadCsvService.uploadContact(contacts);
            } catch (IOException e) {
                throw new CsvUploadException("Csv validation");
            }
        }
    }
}
