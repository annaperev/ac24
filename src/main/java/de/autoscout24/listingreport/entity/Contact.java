package de.autoscout24.listingreport.entity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import de.autoscout24.listingreport.util.LocalDateConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "CONTACTS")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CsvBindByName(column = "listing_id", required = true)
    private Long listingId;
    @CsvCustomBindByName(converter = LocalDateConverter.class, required = true, column = "contact_date")
    private LocalDate contactDate;

    public Contact(Long listingId, LocalDate contactDate) {
        this.listingId = listingId;
        this.contactDate = contactDate;
    }
}
