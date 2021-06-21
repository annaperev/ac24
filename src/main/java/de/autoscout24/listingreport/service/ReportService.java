package de.autoscout24.listingreport.service;

import de.autoscout24.listingreport.dto.BaseReportProjection;
import de.autoscout24.listingreport.dto.MostContactedListingsPerMonthProjection;
import de.autoscout24.listingreport.repository.ReportRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    ReportRepositoryCustom reportRepositoryCustom;

    public ReportService() {
    }

    public List<BaseReportProjection> averageListingSellingPricePerSellerType() {
        return reportRepositoryCustom.getReportAveragePriceBySellerType();
    }

    public List<BaseReportProjection> percentualDistributionOfAvailableCarsByMake() {
        return reportRepositoryCustom.getReportDistributionByMark();
    }

    public Long averagePriceOfMostContactedListings() {
        return Math.round(reportRepositoryCustom.getAveragePriceOfMostContactedListings());
    }

    public List<MostContactedListingsPerMonthProjection> topFiveMostContactedListingsPerMonth() {
        return reportRepositoryCustom.topFiveMostContactedListingsPerMonth();
    }
}
