package com.benyamephrem.config;

import com.benyamephrem.dao.ListingDao;
import com.benyamephrem.model.Listing;
import com.coreoz.windmill.Windmill;
import com.coreoz.windmill.files.FileSource;
import com.coreoz.windmill.imports.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.util.stream.Stream;

@Component //So that spring picks up this class as a component
public class DatabaseSeeder implements CommandLineRunner{

    @Autowired
    private ListingDao listingDao;

    @Override
    public void run(String... args) throws Exception {

        String filePath = "listings.xlsx";

        //Seeding the database with the organized data to run queries later on
        try (Stream<Row> rowStream = Windmill.parse(FileSource.of(new FileInputStream(filePath)))) {
            rowStream
                    // Skip the header row that contains the column names
                    .skip(1)
                    .forEach(row -> {
                        Listing listing = new Listing(
                                row.cell("listing_id").asString(),
                                row.cell("host_id").asString(),

                        );


                        listingDao.save(listing);
                    });
        }


    }
}


//
//***Example syntax from Windmill Library for reference***
//
//try (Stream<Row> rowStream = Windmill.parse(FileSource.of(new FileInputStream("myFile.xlsx")))) {
//    rowStream
//        // Skip the header row that contains the column names
//        .skip(1)
//        .forEach(row -> {
//           System.out.println(
//              "row n°" + row.rowIndex()
//                  + " column 'User login' value : " + row.cell("User login").asString()
//                  + " column n°3 number value : " + row.cell(2).asDouble().value() // index is zero-based
//           );
//        });
//}

