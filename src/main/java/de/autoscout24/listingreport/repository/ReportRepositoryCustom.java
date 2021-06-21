package de.autoscout24.listingreport.repository;

import de.autoscout24.listingreport.dto.BaseReportProjection;
import de.autoscout24.listingreport.dto.MostContactedListingsPerMonthProjection;
import de.autoscout24.listingreport.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepositoryCustom extends JpaRepository<Listing, Long> {

    @Query(nativeQuery = true,
            value = "SELECT l.seller_type as name, " +
                    "       Cast(Avg(l.price) AS INT) as number " +
                    "FROM   listing l " +
                    "GROUP  BY l.seller_type ")
    List<BaseReportProjection> getReportAveragePriceBySellerType();

    @Query(nativeQuery = true,
            value = "WITH total " +
                    "     AS (SELECT Count(*) AS total " +
                    "         FROM   listing) " +
                    "SELECT TOP 30 PERCENT  l.make                                      AS name, " +
                    "             Round(Cast(Count(l.id) AS DECIMAL) / total * 100, 2) AS number " +
                    "FROM   listing L, " +
                    "       total " +
                    "GROUP  BY l.make " +
                    "ORDER  BY number DESC, name ")
    List<BaseReportProjection> getReportDistributionByMark();


    @Query(nativeQuery = true,
            value = "SELECT Avg(price) " +
                    "FROM   (SELECT TOP 30 PERCENT l.id                  AS listing_id, " +
                    "                              Count(c.contact_date) AS contact_number, " +
                    "                              l.price " +
                    "        FROM   contacts c " +
                    "               JOIN listing l " +
                    "                      ON l.id = c.listing_id " +
                    "        GROUP  BY l.id " +
                    "        ORDER  BY contact_number DESC) ")
    Double getAveragePriceOfMostContactedListings();

    @Query(nativeQuery = true,
            value = "SELECT month, " +
                    "       ranking, " +
                    "       listing_id AS listingId, " +
                    "       make, " +
                    "       price AS sellingPrice, " +
                    "       mileage, " +
                    "       count AS totalAmountOfContacts " +
                    "FROM   (SELECT *, " +
                    "               Row_number() " +
                    "                 OVER ( " +
                    "                   partition BY month) AS ranking " +
                    "        FROM   (SELECT c.listing_id, " +
                    "                       l.make, " +
                    "                       l.price, " +
                    "                       l.mileage, " +
                    "                       Formatdatetime(c.contact_date, 'yyyy-MM') AS month, " +
                    "                       Count (c.contact_date)                    AS count, " +
                    "                FROM   listing l " +
                    "                       JOIN contacts c " +
                    "                         ON c.listing_id = l.id " +
                    "                GROUP  BY c.listing_id, " +
                    "                          month " +
                    "                ORDER  BY month, " +
                    "                          listing_id, " +
                    "                          count DESC)) AS x " +
                    "WHERE  ranking < 6 ")
    List<MostContactedListingsPerMonthProjection> topFiveMostContactedListingsPerMonth();
}
