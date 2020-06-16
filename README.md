# Student Course Survey Web Application
## Summary:
This web application is a project used to explore several concepts within application design as well as Java enterprise development!
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
## Application Architecture

## Application Walkthrough
![GitHub Logo](https://github.com/Jdonz94/Course-Survey-WebApp/blob/master/Screenshots%20of%20Web%20App/Page1.PNG)

- When the student navigates to the website, this is the landing page that they are greeted with, where they are initially asked to input their Course, Instructor and the semester/year that they are taking this course. From here, they are prompted with the first questions that require them to input their responses along a range of answers via a radio button selection. 
