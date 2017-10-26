# OptimizeAirbnb <br>
A SpringBoot Web Application That Optimizes An Airbnb Listing <br>

For The Capital One Software Engineering Summit 2nd Round Challenge <br>

### Progress:
Deliverable 1: Visualize the data - FINISHED </br>
Deliverable 2: Price estimation - FINISHED </br>
Deliverable 3: Bookings optimization - IN PROGRESS </br>

Bonus 1: Animate - NOT STARTED </br>
Bonus 2: Investment - NOT STARTED </br>
Bonus 2: Popularity - NOT STARTED </br>

### Description:
Lorem Ipsum

### How Data Flows:
**Overview:** This application respects the MVC design pattern (Model View Controller) and common OO design patterns (Single Responsibilty Principle, Encapsulation, etc.). Data is seeded to the MongoDB database that stores entries as nested objects with "leaves" being objects children to the parent object (In this case the parent node is the Listing class). DAO classes are data access classes meaning their only responsibility is to get data from the database based on queries I write in Mongo QUery Language. DAO's then pass the results to the Service layer where calculations are made and that data is processed to make conclusions. Next that data is passed to the Controllers classes that are the interface between the user and and underlying operations the application can perform. Finally, it is passed by the controllers in a ModelMap to the view rendered by the Thymeleaf templating engine. This allows us to display data dynamically using placeholders. </br>

**In Short:** </br>
MongoDB Database -> ListingDAO (extending Spring MongoRepository) -> ListingService (Implementation) -> ListingController -> Homepage (rendered with Thymeleaf)

**Why This Is Important:** </br>
This allows each layer to be detangled from other layer that have different jobs. This makes debugging much easier and keeps everything organized. Problems that occur in the Controller for example can now be easily retraced to a deeper layer down the line and fixed quickly.

## Skills Utilized </br>
-MVC Layered Application Design </br>
-Layered Unit Testing </br>
-Java 8 </br>
-Spring Framework (Spring Boot)</br>
-Spring Data </br>
-MongoDB </br>
-Google Charts </br>
-Git Version Control </br>

## Challenges Encountered </br>
-No matter how much a program crashes you just have to get back up, look at the error, collect yourself, and
give it another shot. It took me 6-7 hours and 30+ crashes on a beautiful Sunday to get MongoDB just to
persist simple POJO's with Spring Data's MongoDB Repository interface. Every run is a new chance to improve. </br>

-Importance of accounting for null: The database seeding process was really frustrating with many crashes due to
#ERROR cells in the spreadsheet and null values I ran ReGex's on causing Spring to blow up. It took a lot of adjusting. </br>

-Never worked with mongoDB before so it was a struggle transitioning from an understing of relational database tables
to the idea of representing objects in a tree-like pattern (in this case the central 'Listing' class is the parent node) </br>

-Frontend is not my forte so it was interesting figuring out how to layout the application </br>

## Things Learned From This Project </br>
-Using MongoDB and Spring Data MongoDB, previous to this project I had only worked with RDBMS systems like H2 </br>

-How to seed a database </br>

-Using Git with IntelliJ. I was really uncomfortable with UNIX commands and Git but now I feel a lot more
comfortable on the command line starting MongoDB, navigating around, staging commits, and pushing to remotes </br>

-Importance of algorithim efficiency: I've never worked with a set of data this large and all the talk about
Big O notation never really hit me until I saw how the idea of efficiency can have so much of a time impact on
operations. Having *thousands* upon *thousands* of listings makes data processing time crucial.</br>

-NoSQL means "Not Only SQL", not literally no SQL... </br>

-Using Google Charts to display the data dynamically </br>

## What I Don't Like About My Project </br>
-There are a lot of places where things are ineffecient, hacked together, and unsustainable. I'd like to fix
these moving forward and have marked them with TODO's </br>

## Known Errors </br>
-I may have only imported part of the excel sheet but I feel like the numbers are off on some charts. I believe
it is because my computer can't open the full excel document so it only loads part of it (I get that message sometimes
from Excel). Either way no matter the size of the data the DatabaseSeeder still will work with an excel file of any size
and nothing will change besides the numbers the charts show on the frontend (as long as column names match up to what's
in the class)</br>

-Some charts are draw conclusions that can't totally be made exactly. Like the top 10 names in San Francisco bar chart...
in retrospect I wonder what if a host has multiple listings? (which is likely) Numbers need to be adjusted based off of
that. I will go back and correct this.</br>

-The neighborhood enum is what is used to find people's neighborhood based on what latitude and longitude is passed in. Some neighborhood locations don't match up because of minor descrepencies between what I pulled from Google Maps and the literal location in the excel spreadsheet. I'm still thinking of what would be the best solution here.</br>

## Known Bugs </br>

-The Price Estimation page does not release redirect attribute carrying result back to the page that called POST on its own URI. Review this and release the attributes if they exist in the GET method.

## Built With

* [Spring](https://spring.io/) - The Web Framework Used
* [MongoDB](https://www.mongodb.com/) - NoSQL Database
* [Intellij](https://www.jetbrains.com/idea/) - IDE
* [Google Charts](https://developers.google.com/chart/) - Google Charts For Displaying Data

## Acknowledgments
* [Giflib Hibernate by TeamTreehouse](https://github.com/treehouse/giflib-hibernate) - Visual Asset Files and Design Scaffold
* [Michael Weinberger](https://github.com/mwein99) - Footer Design Code
