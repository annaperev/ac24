package de.autoscout24.listingreport.controller;

import de.autoscout24.listingreport.dto.BaseReportProjection;
import de.autoscout24.listingreport.dto.MostContactedListingsPerMonthProjection;
import de.autoscout24.listingreport.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping("/averageListingSellingPricePerSellerType")
    public List<BaseReportProjection> averageListingSellingPricePerSellerType() {
        return reportService.averageListingSellingPricePerSellerType();
    }

    @GetMapping("/percentualDistributionOfAvailableCarsByMake")
    public List<BaseReportProjection> percentualDistributionOfAvailableCarsByMake() {
        return reportService.percentualDistributionOfAvailableCarsByMake();
    }

    @GetMapping("/averagePriceOfMostContactedListings")
    public Long averagePriceOfMostContactedListings() {
        return reportService.averagePriceOfMostContactedListings();
    }

    @GetMapping("/topFiveMostContactedListingsPerMonth")
    public List<MostContactedListingsPerMonthProjection> topFiveMostContactedListingsPerMonth() {
        return reportService.topFiveMostContactedListingsPerMonth();
    }

}
