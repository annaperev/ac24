package de.autoscout24.listingreport.dto;

public interface MostContactedListingsPerMonthProjection {
    String getMonth();

    Integer getRanking();

    Long getListingId();

    String getMake();

    Double getSellingPrice();

    Integer getTotalAmountOfContacts();
}
