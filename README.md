# OptimizeAirbnb <br>
A SpringBoot Web Application That Optimizes An Airbnb Listing Based On A Given Dataset From The San Francisco Area with Tens of Thousands of Listings<br>

For The Capital One Software Engineering Summit 2nd Round Challenge 2017 <br>

School: University of Maryland College Park </br>
Year: Class of 2021 (Freshman) </br>

Date Project Started From Scratch: 10/23/17 </br>
Date Deliverables + Bonus Finished: 10/30/17 </br>

**Note:** Only 2,000 listings have been loaded so far...will continue to load listings when I get more free-time. This application will still work no matter how many (or little) listings are in the backend though (but may throw divide by zero errors if neighborhood that hasn't been loaded yet is queried for an income estimate or other calculation etc.)

### Progress:
Deliverable 1: Visualize the data - FINISHED </br>
Deliverable 2: Price estimation - FINISHED </br>
Deliverable 3: Bookings optimization - FINISHED </br>

Bonus 1: Animate - FINISHED </br>
Bonus 2: Investment - FINISHED </br>
Bonus 2: Popularity - FINISHED </br>

Other Necessities: </br>
Input Validation & Security- NOT STARTED </br>
DAO Unit Tests - NOT STARTED </br>
Service Unit Tests - NOT STARTED </br>
Contoller Unit Tests - NOT STARTED </br>
Cache Database Results - NOT STARTED </br>
Polish UI/UX - NOT STARTED </br>
Clean Up Verbose Code + Code Efficiency Checks - NOT STARTED </br>

### Short Tour:
**Homepage:** </br>
It offers navigation buttons to all of the tools provided by this application.

<a href="https://ibb.co/i0irVm"><img src="https://preview.ibb.co/jHsUGR/Screen_Shot_2017_10_30_at_7_05_19_PM.png" alt="Screen_Shot_2017_10_30_at_7_05_19_PM" border="0"></a>

**Visualize:** </br>
This page displays the 3 charts required as the first deliverable of the challenge. Three charts are provided: </br>
1.) A 3D pie chart displaying amount of listings per neighborhood </br>
2.) A count of the most frequent first names appearing on Airbnb listings in San Francisco </br>
3.) A bar chart displaying the most common property types in San Francisco and listing counts per property type </br>

<a href="https://ibb.co/iVfYbR"><img src="https://preview.ibb.co/dDK4i6/Screen_Shot_2017_10_30_at_7_05_43_PM.png" alt="Screen_Shot_2017_10_30_at_7_05_43_PM" border="0"></a>

**Income Estimator:** </br>
Given a latitude and longitude this tool will first find the closest neighborhood that you live in using the Haversine method and then find the average revenue that each property type in that neighborhood makes on average weekly. This is then displayed to the user in a simple bar chart as what they can expect to make if they listed in the area with each respective property type (Ex: House, Apartment, Condo)

<a href="https://ibb.co/miFui6"><img src="https://preview.ibb.co/c6GObR/Screen_Shot_2017_10_30_at_7_42_38_PM.png" alt="Screen_Shot_2017_10_30_at_7_42_38_PM" border="0"></a>

**Price Optimizer:** </br>
Given a latitude and longitude this tool will calculate the optimal price that your listing should be. It takes into account the highest performing listings in the neighborhood and takes the average of the top 20% best performing listings for that given sector and recommends an appropriate price so that your listing can achieve the same results.

<a href="https://ibb.co/fMoBwR"><img src="https://preview.ibb.co/eoDdbR/Screen_Shot_2017_10_30_at_7_05_52_PM.png" alt="Screen_Shot_2017_10_30_at_7_05_52_PM" border="0"></a>

**Best Neighborhoods:** </br>
This tool allows you to supply the latitude and longitude of your neighborhood and see it's comparison against the top 10 best rated neighborhoods in San Francisco. It displays a chart of the top 10 and adds an extra bar for your neighborhood score so you can make a comparison.

<a href="https://ibb.co/neCMwR"><img src="https://preview.ibb.co/h99ei6/Screen_Shot_2017_10_30_at_7_05_59_PM.png" alt="Screen_Shot_2017_10_30_at_7_05_59_PM" border="0"></a>

<a href="https://ibb.co/fmTTbR"><img src="https://preview.ibb.co/dEtuGR/Screen_Shot_2017_10_30_at_7_51_45_PM.png" alt="Screen_Shot_2017_10_30_at_7_51_45_PM" border="0"></a>

**Investment Calculator:** </br>
This tool takes asks for an investment amount and an aggression percentage (1 being extremely aggressive and 100 being completely conservative) and recommends the best sectors and the amount to put in each sector to get maximum ROI. Certain edge cases are buggy for this tool and they will be continued to be worked on.

<a href="https://ibb.co/hwdTbR"><img src="https://preview.ibb.co/ceHzi6/Screen_Shot_2017_10_30_at_7_06_07_PM.png" alt="Screen_Shot_2017_10_30_at_7_06_07_PM" border="0"></a>

<a href="https://ibb.co/iYsobR"><img src="https://preview.ibb.co/dGVKi6/Screen_Shot_2017_10_30_at_7_06_44_PM.png" alt="Screen_Shot_2017_10_30_at_7_06_44_PM" border="0"></a>

### How To Run: </br>
1.) Import this application into IntelliJ with 'git clone' command </br>
2.) Ensure MongoDB is installed on your machine </br>
3.) Start mongo with 'mongod' command </br>
4.) (On First Run) Uncomment DatabaseSeeder class in config package </br>
5.) Hit run (green play button) </br>
6.) Visit localhost:8080 and the application should be up </br>
7.) Comment DatabaseSeeder class as local database is already populated. </br>
[Visualize Your MongoDB Entries With RoboMongo](https://robomongo.org/). </br>

Or Application is Live At: https://optimize-airbnb.herokuapp.com/


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
-Gradle Dependency Management </br>
-Deploying On Heoku </br>

## Challenges Encountered </br>
-No matter how much a program crashes you just have to get back up, look at the error, collect yourself, and
give it another shot. It took me 6-7 hours and 30+ crashes on a beautiful Sunday to get MongoDB just to
persist simple POJO's with Spring Data's MongoDB Repository interface. Every run is a new chance to improve. </br>

-Importance of accounting for null: The database seeding process was really frustrating with many crashes due to
#ERROR cells in the spreadsheet and null values I ran ReGex's on causing Spring to blow up. It took a lot of adjusting. </br>

-Never worked with mongoDB before so it was a struggle transitioning from an understing of relational database tables
to the idea of representing objects in a tree-like pattern (in this case the central 'Listing' class is the parent node) </br>

-Frontend is not my forte so it was interesting figuring out how to layout the application. Many elements are visually bland and I feel like it doesn't do justice to the complexity behind the scenes. </br>

-Depolying to Heroku sounded easy at first (and is for a static application) but turned into a 10 hours orderal where I had wrong buildscripts, git ignored files that Heroku wanted, mistyped remotes, had to learn how to hook up mongo with heroku...it was frustrating. I do now understand how to deploy a static or dynamic spring boot application on Heroku and the idea of remotes and local commits makes alot more sense now.

### The Deploying Nightmare </br>
-I have **never** deployed an application to a remote source so after some searching of the internets I found a simple solution...Heroku. It uses git and in just four words (git push heroku master) I can push changes and deploy an application to the world in the click of a button. Little did I know that it would be wayyyyyyyyyy harder than I thought. </br>

Challenge 1: The first challenge I faced was understanding what a remote was. I know git pretty solid but the idea of remote repositories had never really clicked for me. But now it is so simple...it's literally just a remote code-base you push commits to, not any different from me pushing to github. I just had to add my heroku app as a remote in IntelliJ VCS settings.

Challenge 2: The second challenge I faced setting the correct buildpack. I had no idea what these were and I tried switching my project over to a java buildpack and making my project use Maven instead of Gradle (because the Heroku guide only showed how to use Maven to deploy). Little did I know that there already was a gradle buildpack to use on my application.

Challenge 3 **(the 12 hour nightmare)**: Once the static site was up it was time to connect the database and seed it. This literally drove me insane. I could not focus on anything but making this work because run after run my application would crash. Hour after hour no progress. Logs showed nothing on first glance so I looked a ton of other places that didn't matter. Finally I logically realized that it must be in the Database Loader lambda that an error is happening. I pulled up the tail of the logs, took a very close look, line by line, and there it was..."java.lang.OutOfMemoryError: GC overhead limit exceeded at line 30 in DatabaseLoader.java". I realized that the FileInput object was trying to be created on a HUGE excel file and the application crashed to avoid a fatal Heap overflow. I made the excel file smaller with less entries and voilà...the database came to life with 1,998 documents...after hours...and hours...and hours of......nothing. I am currently finishing the seeding process and testing every part of the application for functionality.

## Things Learned From This Project </br>
-Using MongoDB and Spring Data MongoDB, previous to this project I had only worked with RDBMS systems like H2 </br>

-How to seed a database </br>

-Using Git with IntelliJ. I was really uncomfortable with UNIX commands and Git but now I feel a lot more
comfortable on the command line starting MongoDB, navigating around, staging commits, and pushing to remotes </br>

-Importance of algorithim efficiency: I've never worked with a set of data this large and all the talk about
Big O notation never really hit me until I saw how the idea of efficiency can have so much of a time impact on
operations. Having *thousands* upon *thousands* of listings makes data processing time crucial.</br>

-Breaking Problems Down: Many tasks that I either didn't know how to do or wanted to find the best way to do required me to pull out a notepad and write every step I needed to do in plain English. This process of solving the problem by hand before coding anything gives me more confidence now to solve complex problems as I can just learn how to solve the mini-steps I don't know and do the mini-steps I do know. </br>

-NoSQL means "Not Only SQL", not literally no SQL... </br>

-Using Google Charts to display the data dynamically </br>

-Sifting through logs. This is **so** important and could have saved me 12 hours of troubleshooting upon deploying of the application. The logs are essentially the application talking to you and if you aren't "listening" then it is near impossible to quickly pinpoint an issue. Once I got comfortable reading verbose logs from Heroku I found the GC overhead limit problem quickly and solved it.</br>

## What I Don't Like About My Project </br>
-There are a lot of places where things are ineffecient, hacked together, and unsustainable. There are places with heavy/repeated database calls that can be moved around, many verbose expressions that can be simplified, etc. I'd like to fix these moving forward and have marked them with TODO's </br>

## Known Errors </br>
-I may have only imported part of the excel sheet but I feel like the numbers are off on some charts. I believe
it is because my computer can't open the full excel document so it only loads part of it (I get that message sometimes
from Excel). Either way no matter the size of the data the DatabaseSeeder still will work with an excel file of any size
and nothing will change besides the numbers the charts show on the frontend (as long as column names match up to what's
in the class) </br>

-Some charts are draw conclusions that can't totally be made exactly. Like the top 10 names in San Francisco bar chart...
in retrospect I wonder what if a host has multiple listings? (which is likely) Numbers need to be adjusted based off of
that. I will go back and correct this and these are marked by TODO's</br>

-The neighborhood enum is what is used to find people's neighborhood based on what latitude and longitude is passed in. Some neighborhood locations don't match up because of minor descrepencies between what I pulled from Google Maps and the literal location in the excel spreadsheet. I'm still thinking of what would be the best solution here but either way the match is very close or exact in most cases. </br>

-The breakeven calculation on the Investment Calculator is slightly faulty. Currently fixing the calculation to take into account investment put into each sector instead of just sampling and adding up weekly income from the sector list.

## Built With

* [Spring](https://spring.io/) - The Web Framework Used
* [MongoDB](https://www.mongodb.com/) - NoSQL Database
* [Intellij](https://www.jetbrains.com/idea/) - IDE
* [Google Charts](https://developers.google.com/chart/) - Google Charts For Displaying Data

## Acknowledgments
* [Giflib Hibernate by TeamTreehouse](https://github.com/treehouse/giflib-hibernate) - Visual Asset Files and Design Scaffold
* [Michael Weinberger](https://github.com/mwein99) - Footer Design Code
* [Flaticon](https://www.flaticon.com/) - Icons
