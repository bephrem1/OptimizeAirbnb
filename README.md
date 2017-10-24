# OptimizeAirbnb <br>
A SpringBoot Web Application That Optimizes An Airbnb Listing <br>

For The Capital One Software Engineering Summit 2nd Round Challenge <br>

### Description:
Lorem Ipsum

## Skills Utilized </br>
-MVC Layered Application Design </br>
-Layered Unit Testing </br>
-Java 8 </br>
-Spring Framework (Spring Boot)</br>
-Spring Security </br>
-Spring Data </br>
-MongoDB </br>
-Git Version Control </br>

## Challenges Encountered </br>
-No matter how much a program crashes you just have to get back up, look at the error, collect yourself, and
give it another shot. It took me 6-7 hours and 30+ crashes on a beautiful Sunday to get MongoDB just to
persist simple POJO's with Spring Data's MongoDB Repository interface. Every run is a new chance to improve. </br>

-Importance of accounting for null: The database seeding process was really frustrating with many crashes due to
#ERROR cells in the spreadsheet and null values I ran ReGex's on causing Spring to blow up. It took a lot of adjusting. </br>

-Never worked with mongoDB before so it was a struggle transitioning from an understing of relational database tables
to the idea of representing objects in a tree-like pattern (in this case the central 'Listing' class is the parent node) </br>

## Things Learned From This Project </br>
-Using MongoDB and Spring Data MongoDB, previous to this project I had only worked with RDBMS systems like H2 </br>

-How to seed a database and set-up data transfer objects (DTO's) </br>

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

## Built With

* [Spring](https://spring.io/) - The Web Framework Used
* [MongoDB](https://www.mongodb.com/) - NoSQL Database
* [Intellij](https://www.jetbrains.com/idea/) - IDE
* [Google Charts](https://developers.google.com/chart/) - Google Charts For Displaying Data

## Acknowledgments
