package de.autoscout24.listingreport.entity;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LISTING")
public class Listing {
    @Id
    @CsvBindByName
    private Long id;
    @CsvBindByName
    private String make;
    @CsvBindByName
    private BigDecimal price;
    @CsvBindByName
    private BigDecimal mileage;
    @CsvBindByName(column = "seller_type")
    private String sellerType;
}
