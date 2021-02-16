Coverage: Currently at 71.8% on the src/main.



# To Do List Project

This is a simple To-Do list project that enables the user to interact with a webpage and perform simple CRUD functionality.  
This project is to explore technologies such as SpringBoot,Javascript and HTMLto deepen the understanding of the topic  
The project document folder contains my supporting documents such as my risk assessment, UML and ERD,PPT and screen shots of my Kanban and Test coverage  with repect to the project.  


## Getting Started

The below instructions will Allow you to set up a version of the project running in a local environment for developing and testing purposes.  
1. clone and fork this repository
2. open in IDE ,preferebly using spring tool suite, as a maven/spring projectj  
3. Open the command line in the folder where the .jar file is located
4. Run the command `java -jar <filename>` and this should start up the application on a local tomcat server 
5. Go to `localhost:8080/Homepage.html` for the Home page   
6. To test out the application you can use postman to test the URLS or you can directly interact with the application and validate the responces using the console 
7. To vies the database structure and test out the databases you can go to localhost:8080/h2-console
8. to close the application simply use ctrl + C in your CMD to spin down the application




### Prerequisites


  
* [Spring](https://spring.io/tools)   
* [Java](https://www.java.com/en/download/)  
* [Maven](https://maven.apache.org/index.html)   
* [Git](https://git-scm.com/downloads)  
* [Postman](https://www.postman.com/downloads/)

### Installing

A step by step guide using examples to show you how to get the development environment running  
**_Step One:_** In your IDE, once you have forked or cloned the repo down, import existing maven/spring project  
**_Step Two:_** Browse the root directory and select your folder  
**_Step Three:_** Import the folder to your IDE  


This project should be then imported into your IDE, if you wish to customise the database options, they can be found, `src/main/resources` in the application.properties files. 
  
if the import is successful you'll be able deployment the application locally

# Testing 

## Running the tests

### Unit Testing
this tests the individual methods of the program to make sure they are correct and outputting what we expect 

### Integration Tests
Integration testing with JUnit 5 allows us to see if the HTTP endpoints in our application work with
various CRUD -based HTTP requests. This makes use of the Controller layer, which can be 'mocked' so
that we can test the outcomes of sending data to each HTTP endpoint.


### Adding static analysis Tests
SonarQube - Used to make sure that best practice, bugs,security and smells can be identified and  elimated as much as possible
 

### User-Acceptance Testing  
Using Selenium we test the front end of the website - there will also extent reports auto-generated in the `ToDoList/target/TestReports` folder  



## Deployment

1. Open Cmd in folder where the jar file is located  
2. The jar file is already generated for the current build but if you make changes and re make the jar in the future you will need to   
3. Run the command ```java -jar SpringTDL-0.0.1-SNAPSHOT```  on your cmd in the directory that contains the project 





## Built With
* [Maven](https://maven.apache.org/) - Dependency Management
*Mvn - clean and Mvn - package were used to build the jat fie for the project 


## Authors
*[Waleed Tahir](https://github.com/awatahirqa)

## Acknowledgements  
*Massive thank you to my trainers who have supported me every step of the processs
* [Nicholas Johnson](https://github.com/nickrstewarttds)
* [Vinesh Ghela](https://github.com/vineshghela) 
*[Aswene Sivaraj](https://github.com/Asivaraj-QA) 
* [Savannah Vaithilingam](https://github.com/savannahvaith)
* [Alan Davis](https://github.com/MorickClive)  

## License  
[BootStrap](https://github.com/twbs/bootstrap/blob/v4.0.0/LICENSE)
