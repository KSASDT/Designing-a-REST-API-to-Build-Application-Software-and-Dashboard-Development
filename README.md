# Designing-a-REST-API-to-Build-Application-Software-and-Dashboard-Development

<img src="rest pi.jpg" >

Description
--------------
This is the summer internship project done by me in the year 2017 at CaLeLa(Blue Anchor Private Limited) Bandra, Mumbai. The main 
objective is to create REST API using Spring MVC that connects the back-end data sources to the client application and developed 
the dashboard for CaLeLa users. The dashboard includes the applications such as task list, event calendar, alerts and notifications,
contact list, note etc.

File and Directory Structure:
---------------------------

Files includes:
            
			
               UserManagement.rar                     : REST API built in Java
               UserManaggingController.java           : MVC for REST in java
               Task_list.html                         : Task list application for Dashboard
               Login.html                             : User login interface
               Calender.html                          : Calendar for Calela user
               Page_notes.html                        : To add notes 
               Page_contactList.html                  : Contact list for user
               Notify.html                            : Notification to user


      
      

Functional description of the project is as follows:
----------------------------------------------------

(i) Registration is offered. Then a registered user logs into the site (mycalela.com)

(ii) REST API is created to integrate with back-end infrastructure

(iii) The dashboard is display to user on which different applications are developed

(iv) The user may preview the page on a range of browsers


Languages and Tools Used:
-------------------------

1) Eclipse Java EE IDE
2) Sublime Text Editor
3) HTML
4) CSS
5) JavaScript
6) Maven
7) Tomcat
8) MongoDB
9) JSON


RESTful API:
------------
A RESTful API is an application program interface (API) that uses HTTP requests to GET, PUT, POST and DELETE data. 
Developing RESTful service in spring is no different that spring mvc(model and view controller) framework. The technologies 
stack being used for creating REST API is as follows:

(i)  Eclipse IDE neon

(ii) Maven 3.1(project management tool)

(iii) Spring 1.4.7 RELEASE

(iv) JRE 1.8

(v)  Tomcat 7.1



Methodology:
------------

Now to develop REST API following steps has been performed: 

(i)  Generated the new maven project

(ii) Add the dependencies like web dependencies, Actuators(used for managing applications), Dev tool(for Spring) and spring mvc plugin to the pom.xml file

(iii) Inside main package created all the models such as Users, User Authentication, User Setting, Activity log, Address info, Email info, Firm info, Image info, OTP, Password reset link, session info, phone info etc.

(iv) Set up rest based controller that is mainly for making connection between client and server using simple HTTP

(v)  Retrieved all users by using "GET" method of HTTP that is used to retrieve data from specified resource

(vi) Used "POST" method to create user that submit data to specified resource and for updating used "PUT" method and to delete user used "DELETE" method

(vii) Now for testing purpose either use an external client POSTMAN (An extension from CHROME) or test it from eclipse IDE by creating REST client of the application from tomcat.


Dashboard Development:
----------------------
Created the dashboard for CaLeLa that build a responsive UI. The applications were built on the dashboard are as follows:

(i) Task List: It displays all running applications and services with their Progress status and it is created by using html, CSS and added functionality to task list using JavaScript.

(ii) 	Event Calendar: An event calendar is used by professionals to monitor events and set the agenda.

(iii) Alerts and Notifications: This can be used to notify the user about something special: danger, success, information or warning.

(iv)  Note: Let the user to store anything in one place.

(v)  Contact List: Developed the contact list in grid format for better interaction among people.


Results:
-------
The REST API has created that supports general purpose application development. All HTTP requests (GET, PUT, DELETE, and PUSH) 
implemented successfully for every use case.  At the front-end development, I have created the dashboard functions such as task 
lists, event calendar, Notification and alerts, contact list, note etc. 
  
