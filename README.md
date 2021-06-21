# SpringBoot RestfulAPI using swagger,H2Database and JPA

Need only Docker. From root directory run:
```
docker build -t autoscout24/reportsapp .
docker run -p 9001:9001 autoscout24/reportsapp
```

Or

Need JVM, IntelliJ Idea, maven. From Idea run: 
```
de/autoscout24/listingreport/ListingReportApplication.java
```

About 
* To simplify development process I use h2 in-memory base, it could be replaced to postgres
* To build the reports, cvs files should be uploaded first
* Upload end-points save csv files to h2 db
* Report end-points build reports based on data from db tables LISTING and CONTACTS


H2 Databse  http://localhost:9001/h2/
JDBC URL:jdbc:h2:mem:testdb, User Name: sa, Password: 

Restful endpoints with swagger http://localhost:9001/swagger-ui.html



**Upload Csv:**

PUT 
```
http://localhost:9001/api/listingsCsv
http://localhost:9001/api/contactsCsv
```

**API Reports:**

GET
```
http://localhost:9001/api/averageListingSellingPricePerSellerType
http://localhost:9001/api/percentualDistributionOfAvailableCarsByMake
http://localhost:9001/api/averagePriceOfMostContactedListings
http://localhost:9001/api/topFiveMostContactedListingsPerMonth
```


**Thymeleaf Reports:**

GET
```
http://localhost:9001/report/averageListingSellingPricePerSellerType
http://localhost:9001/report/percentualDistributionOfAvailableCarsByMake
http://localhost:9001/report/averagePriceOfMostContactedListings
```

Response percentualDistributionOfAvailableCarsByMake Example:
```
[
    {
        "percent": 16.33,
        "make": "Mercedes-Benz"
    },
    {
        "percent": 16,
        "make": "Toyota"
    },
    {
        "percent": 14,
        "make": "Renault"
    }
]
```