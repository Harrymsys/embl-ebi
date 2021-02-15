# embl-ebi
### This project contains the microservice for the person data service in embl-ebi

# Technical Stack 

#### Microservice - Spring Boot (Version - 2.3.3)
#### DataStore - Apache Derby (Version - 10.15.1.3)
#### Junit - SpringBoot Mockmvc and Junit (Version - 5) 
#### Jacaco - Test Coverage (Version - 0.8.5) [We have **83.5 % test coverage** for this project (check the code coverage section)]
#### Docker - (Version - 20.10.2)
#### UML Diagram - (ObjectAid) 
#### Java - (Version - 11) 
#### Build Tool - Maven - (Version - 3.6.3)

# 1) Class Diagram for this project 
![](https://github.com/Harrymsys/embl-ebi/blob/main/outputs/classdiagram_png.png)

# 2) API Documentation for the person data service in embl-ebi

**API Description : **: API to store the person data 

**API URL**: http://localhost:8080/person/store

**API Method Type**: POST

**API Content Type**: application/json

**Response**: application/json

![](https://github.com/Harrymsys/embl-ebi/blob/main/outputs/Store.PNG)

**Sample Input**: 

                      [{
                       "firstName": "Bill",
                       "lastName": "Gates",
                       "age": "65",
                       "favouriteColour": "Blue"
                       },
                       {
                       "firstName": "Sundar",
                       "lastName": "Pichai",
                       "age": "50",
                       "favouriteColour": "Red"
                       }]

**Sample Response**: 
                      {
                          "personIds": [
                              13,
                              14,
                              15
                          ]
                      }
                      
                      where the 13, 14, 15 are the unique Ids created for the added data 

**Possible Status Codes **: 
                     200 - Success 
                     500 - Internal Server Error 
                     General HTTP Errors like 400, 415 etc., 
                     
**Possible Cases **:           
                 1) Store single or multiple person data 
                 2) Validation on firstName, lastName, age and favouriteColour field 
                            firstName >= 3 and firstName <= 60 (It is configurable in constants file as of now - we can move this to property file)
                            lastName >= 3 and lastName <= 60 (It is configurable in constants file as of now - we can move this to property file)
                            favouriteColour >= 3 and favouriteColour <= 60 (It is configurable in constants file as of now - we can move this to property file)
                            age >= 1 (It is configurable in constants file as of now - we can move this to property file)
                            
**API Number : **: 2

**API URL**: http://localhost:8080/person/get/{personId}

**API Method Type**: GET

**Response**: application/json

![](https://github.com/Harrymsys/embl-ebi/blob/main/outputs/GetById.PNG)

**Sample Input**: 

                      [{
                       "firstName": "Bill",
                       "lastName": "Gates",
                       "age": "65",
                       "favouriteColour": "Blue"
                       },
                       {
                       "firstName": "Sundar",
                       "lastName": "Pichai",
                       "age": "50",
                       "favouriteColour": "Red"
                       }]

**Sample Response**: 
                      {
                          "personIds": [
                              13,
                              14,
                              15
                          ]
                      }
                      
                      where the 13, 14, 15 are the unique Ids created for the added data 

**Possible Status Codes **: 
                     200 - Success 
                     500 - Internal Server Error 
                     General HTTP Errors like 400, 415 etc., 
                     
**Possible Cases **:           
                 1) Store single or multiple person data 
                 2) Validation on firstName, lastName, age and favouriteColour field 
                            firstName >= 3 and firstName <= 60 (It is configurable in constants file as of now - we can move this to property file)
                            lastName >= 3 and lastName <= 60 (It is configurable in constants file as of now - we can move this to property file)
                            favouriteColour >= 3 and favouriteColour <= 60 (It is configurable in constants file as of now - we can move this to property file)
                            age >= 1 (It is configurable in constants file as of now - we can move this to property file)
                            
                                                  
