//package com.benyamephrem.config;
//
//import com.benyamephrem.dao.ListingDao;
//import com.benyamephrem.model.Listing;
//import com.benyamephrem.model.leafs.*;
//import com.coreoz.windmill.Windmill;
//import com.coreoz.windmill.files.FileSource;
//import com.coreoz.windmill.imports.Row;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.io.FileInputStream;
//import java.util.stream.Stream;
//
//@Component //So that spring picks up this class as a component
//public class DatabaseSeeder implements CommandLineRunner{
//
//    @Autowired
//    private ListingDao listingDao;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        String filePath = "listings.xlsx";
//
//        //TODO:be I don't like how awkward this database is seeded...revise later (adjusting the sheet to account for null...I don't like it) revise please
//        //Seeding the database with the organized data to run queries on later
//        //When you query weekly price make sure to filter saying to make sure it does not == 0 since that is just no input
//        try (Stream<Row> rowStream = Windmill.parse(FileSource.of(new FileInputStream(filePath)))) {
//            rowStream
//                    // Skip the header row that contains the column names
//                    .skip(1)
//                    .forEach(row -> {
//                        Listing listing = new Listing(
//                                row.cell("listing_id").asString(),
//                                row.cell("host_id").asString(),
//                                new ListingInfo(
//                                        row.cell("listing_url").asString(),
//                                        row.cell("host_url").asString(),
//                                        row.cell("title").asString(),
//                                        row.cell("summary").asString(),
//                                        row.cell("space").asString(),
//                                        row.cell("description").asString(),
//                                        row.cell("neighborhood_overview").asString(),
//                                        row.cell("host_name").asString(),
//                                        row.cell("host_since").asString(),
//                                        row.cell("host_about").asString(),
//                                        row.cell("host_is_superhost").asString(),
//                                        Integer.parseInt(row.cell("host_listings_count").asString())
//
//                                ),
//                                new ListingLocation(
//                                        Double.parseDouble(row.cell("latitude").asString()),
//                                        Double.parseDouble(row.cell("longitude").asString()),
//                                        row.cell("neighborhood").asString(),
//                                        row.cell("street").asString(),
//                                        row.cell("city").asString(),
//                                        row.cell("state").asString(),
//                                        row.cell("zipcode").asString()
//                                ),
//                                new PropertyLogistics(
//                                        row.cell("property_type").asString(),
//                                        row.cell("room_type").asString(),
//                                        Integer.parseInt(row.cell("accommodates").asString()),
//                                        Integer.parseInt(row.cell("guests_included").asString()),
//                                        Double.parseDouble(row.cell("bathrooms").asString()),
//                                        Integer.parseInt(row.cell("bedrooms").asString()),
//                                        Integer.parseInt(row.cell("beds").asString()),
//                                        row.cell("bed_type").asString()
//                                ),
//                                new ListingFinancials(
//                                        Double.parseDouble(row.cell("price").asString().replaceAll("$", "")),
//                                        Double.parseDouble(row.cell("weekly_price").asString().replaceAll("$", "")),
//                                        Double.parseDouble(row.cell("monthly_price").asString().replaceAll("$", "")),
//                                        Double.parseDouble(row.cell("cleaning_fee").asString().replaceAll("$", "")),
//                                        Double.parseDouble(row.cell("extra_people").asString().replaceAll("$", "")),
//                                        Double.parseDouble(row.cell("security_deposit").asString().replaceAll("$", ""))
//                                ),
//                                Integer.parseInt(row.cell("minimum_nights").asString()),
//                                Integer.parseInt(row.cell("maximum_nights").asString()),
//                                Integer.parseInt(row.cell("availability_30").asString()),
//                                Integer.parseInt(row.cell("availability_60").asString()),
//                                Integer.parseInt(row.cell("availability_90").asString()),
//                                Integer.parseInt(row.cell("availability_365").asString()),
//                                new ListingReviewStats(
//                                        Integer.parseInt(row.cell("number_of_reviews").asString()),
//                                        Double.parseDouble(row.cell("reviews_per_month").asString()),
//                                        Integer.parseInt(row.cell("review_scores_accuracy").asString()),
//                                        Integer.parseInt(row.cell("review_scores_cleanliness").asString()),
//                                        Integer.parseInt(row.cell("review_scores_checkin").asString()),
//                                        Integer.parseInt(row.cell("review_scores_communication").asString()),
//                                        Integer.parseInt(row.cell("review_scores_location").asString()),
//                                        Integer.parseInt(row.cell("review_scores_value").asString()),
//                                        Integer.parseInt(row.cell("review_scores_rating").asString())
//                                )
//                        );
//
//                        //Persist the constructed listing object
//                        listingDao.save(listing);
//                    });
//        }
//
//
//    }
//}
//
//
////
////***Example syntax from Windmill Library for reference***
////
////try (Stream<Row> rowStream = Windmill.parse(FileSource.of(new FileInputStream("myFile.xlsx")))) {
////    rowStream
////        // Skip the header row that contains the column names
////        .skip(1)
////        .forEach(row -> {
////           System.out.println(
////              "row n°" + row.rowIndex()
////                  + " column 'User login' value : " + row.cell("User login").asString()
////                  + " column n°3 number value : " + row.cell(2).asDouble().value() // index is zero-based
////           );
////        });
////}
//
