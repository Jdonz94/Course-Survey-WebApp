# Student Course Survey Web Application
## Application Architecture
   ### Objective:
   The objective of this project is to create a web application that allows students to submit their thoughts on their classes so that staff can then review the results and have a good understanding of how their classes are being received and what value they provide to their students.
   
   ### Approach:
   The design of this web application would be made with respect to the __MVC__ pattern of design, in which we seek to isolate the user view from the logic and the data storage. This was the primary choice behind how the project would be executed. 
   
   
   ![User Flow](https://github.com/Jdonz94/Course-Survey-WebApp/blob/master/Architecture%20Diagrams/User%20Flow.svg)
   
   As the above diagram shows, the user view (web page that the user interacts with) is the first part of the equation. There are two things to consider here, the user either successfully completes the web page or they do not. We send the information that the user input to the view controller and the view controller then decides if the data is complete (success) or the user left off pieces of information. If they left off pieces, when they click the submit button, the page will not progress. However, if they did complete the webpage correctly then the results will be recorded and send to the database via the view controller's connection to the database via our JDBC driver.
   
   This is essentially the logic that carries through the project for all of the user input pages, it is a process of data collection (__view__), data validation (__controller__), and then storage (__data base__). 
   
   
   ![Faculty Flow](https://github.com/Jdonz94/Course-Survey-WebApp/blob/master/Architecture%20Diagrams/Faculty%20Flow.svg)
   
   The business logic, other than the validation and what to mark as required and not for the data input, lies with the statistics that are presented on the final page of the application that provides faculty with the ability to see the overall trends of the student answers.
   
   The design for this particular part of the project is a little more complicated than just the MVC elements of the web application itself because there needs to be a smart design around how the information that we gather from the view is stored in the database. For this project I chose to simply have a table in the database for each page that was presented to the user so that there could easily be statistics as granular as a specific page or question that appears on that page. 
   
   What happens here is that when the faculty accesses the final page of the web application, they will see a the landing page for the results, where it is initially blank but shows them all of the questions that the students were asked, when they scroll to the bottom they press a button that is labeled "Run Report" and they are presented with the statistics for all students who answered the entirety of the survey.
   
   How this works, is that the view controller for the results page uses it's JDBC to query the database for all of the answers within each page (which in this case each page has its own table). From there, there the controller calls a method that iterates through the result set that the database provided through our query and identifies what type of question the problem was ("Yes/No", "Strongly Agree... Strongly Disagree", etc...), and then it uses this information to identify the answers for that particular question number and provides the percentage of occurence for that particular answer. For example, if we have the question "I found this class to be worthwhile" and the answers are "Yes" or "No", the alorithm first identifies that the possible answers are yes and no, and then it tallies how many of each it received with respect to the total number of answers. So if we had 5 yes and 5 no, it would give us 50% for each, and this is what the faculty member would see on the results page. Below are some screenshots of the application that show exactly what this looks like and it may be a little easier to see how it plays out.
   
   
   
## Technologies used:
* __Frontend__
    * Java Server Faces Framework
      * JSF was useful for making use of pre-built components for the webpages that allowed for some of the grouping and structure of the webpages
  * HTML5
    * The bulk of the HTML that is present in the web pages was created via the JSF components, however some of the HTML was coded manually for instances where I did not want to use a pre-made JSF component.
  * CSS3
    * CSS was used to give the webpages a cohesive color scheme as well as to provide structure for the webpages with consideration to the different component types used on some of the pages

* __Backend__
  * Java EE
    * MVC design principles were applied to have a separation of the user view from the business logic and data storage
  * MySQL
    * MySQL was the database that was used to store and retrieve results from the completed surveys, this information was used in the business logic portion of the application to provide some basic insights into the distributions of the answers.
  * JDBC
    * Java Data Base Connection was used as the driver option to link the Java logic to the MySQL DB.
  * Glassfish 5 Web Server
    * Glassfish was used to allow for local hosting of the website for testing and to make sure that the code was producing the desired product if it was to be hosted in a live environment.

## Application Walkthrough
![Page 1](https://github.com/Jdonz94/Course-Survey-WebApp/blob/master/Screenshots%20of%20Web%20App/Page1.PNG)

- When the student navigates to the website, this is the landing page that they are greeted with, where they are initially asked to input their Course, Instructor and the semester/year that they are taking this course. From here, they are prompted with the first questions that require them to input their responses along a range of answers via a radio button selection. 

![Page 1 Completed](https://github.com/Jdonz94/Course-Survey-WebApp/blob/master/Screenshots%20of%20Web%20App/Page1_completed.PNG)

- This is an example of what the page looks like when it has been completed (navigation rules in the config file does check for completeness of the page before allowing the user to progress to the next page)

![Page 2](https://github.com/Jdonz94/Course-Survey-WebApp/blob/master/Screenshots%20of%20Web%20App/Page2_completed.PNG)
- This is the second page of the application that the user would see after they have successfully completed the first page of the application

![Page 3](https://github.com/Jdonz94/Course-Survey-WebApp/blob/master/Screenshots%20of%20Web%20App/page3_completed.PNG)
- This is the third page that the user would see after successful completion of the second page

![Page 4](https://github.com/Jdonz94/Course-Survey-WebApp/blob/master/Screenshots%20of%20Web%20App/page4_completed.PNG)
- This is the final page that the user would interact with, and it allows for the user to answer some free response questions that are recorded for further use but are not used in calculating the statistics that are shown on the results page

### Application results
![Results Page](https://github.com/Jdonz94/Course-Survey-WebApp/blob/master/Screenshots%20of%20Web%20App/resultspage.PNG)
![Results Page Cont.](https://github.com/Jdonz94/Course-Survey-WebApp/blob/master/Screenshots%20of%20Web%20App/resultspage2.PNG)

- The output of the program is the percentage of occurence for each answer per question number. This grants faculty who would be using the application the ability to see what the typical feeling is around the question. __This provides valuable insights into whether or not a class is meeting its objectives and why or why not.__
