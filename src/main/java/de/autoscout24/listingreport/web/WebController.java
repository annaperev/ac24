package de.autoscout24.listingreport.web;

import de.autoscout24.listingreport.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/report")
public class WebController {

    @Autowired
    ReportService reportService;

    @RequestMapping(value = "/averageListingSellingPricePerSellerType", method = RequestMethod.GET)
    public String averageListingSellingPricePerSellerType(Model model) {
        model.addAttribute("list", reportService.averageListingSellingPricePerSellerType());
        return "averageListingSellingPricePerSellerType";
    }

    @RequestMapping(value = "/percentualDistributionOfAvailableCarsByMake", method = RequestMethod.GET)
    public String percentualDistributionOfAvailableCarsByMake(Model model) {
        model.addAttribute("list", reportService.percentualDistributionOfAvailableCarsByMake());
        return "percentualDistributionOfAvailableCarsByMake";
    }

    @RequestMapping(value = "/averagePriceOfMostContactedListings", method = RequestMethod.GET)
    public String averagePriceOfMostContactedListings(Model model) {
        model.addAttribute("list", reportService.averagePriceOfMostContactedListings());
        return "averagePriceOfMostContactedListings";
    }
}
