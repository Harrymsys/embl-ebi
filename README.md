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

# 1) Class Diagram for this project **(For Image clarity click on the image to open in new tab / clone it)**
![](https://github.com/Harrymsys/embl-ebi/blob/main/outputs/classdiagram_png.png)

# 2) API Documentation for the person data service in embl-ebi

### API Number : 1

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
                            
### API Number : 2

**API URL**: http://localhost:8080/person/get/{personId}

**API Method Type**: GET

**Response**: application/json

![](https://github.com/Harrymsys/embl-ebi/blob/main/outputs/GetById.PNG)

**Sample Input**: 

                     http://localhost:8080/person/get/5

**Sample Response**: 
                      {
    "personId": 5,
    "firstName": "Sundar",
    "lastName": "Pichai",
    "age": 50,
    "favouriteColour": "Red"
}
                      
                      

**Possible Status Codes **: 
                     200 - Success 
                     500 - Internal Server Error 
                     General HTTP Errors like 400, 415 etc., 
                     
**Possible Cases **:           
                 1) Get the person data, If the personId is valid and available in datastore
                 2) Validation errors on the personId 
                 
                 
### API Number : 3

**API URL**: http://localhost:8080/person/get?sort={sort_field}&from={from_value}&size={data_required_count}

**API Method Type**: GET

**Response**: application/json

i) Get all with default sort (sort by firstName), pagination (0, 1000)

![](https://github.com/Harrymsys/embl-ebi/blob/main/outputs/GetAll.PNG)

ii) Get all with sort fields like (sort by firstName / lastName / age / favouriteColour)

![](https://github.com/Harrymsys/embl-ebi/blob/main/outputs/GetAllWithSorting.PNG)

iii) Get all with sort fields like (sort by firstName / lastName / age / favouriteColour) and Pagination (from and size to be provided) 

![](https://github.com/Harrymsys/embl-ebi/blob/main/outputs/GetAllWithSortingAndPagination.PNG)

**Sample Input**: 

                     http://localhost:8080/person/get?sort=firstName&from=0&size=2

**Sample Response**: 
               {
                  "total": 2,
                  "data": [
                      {
                          "personId": 16,
                          "firstName": "Bill",
                          "lastName": "Gates",
                          "age": 65,
                          "favouriteColour": "Blue"
                      },
                      {
                          "personId": 18,
                          "firstName": "EFG",
                          "lastName": "Keynes",
                          "age": 2,
                          "favouriteColour": "red"
                      }
                  ]
              }
                      
                      

**Possible Status Codes **: 
                     200 - Success 
                     500 - Internal Server Error 
                     General HTTP Errors like 400, 415 etc., 
                     
**Possible Cases **:           
                 1) Get all the person data
                 2) Validation on sort fields (providing the non available fields) 
                 3) Sorting on different fields  
                 4) Pagination 
                 5) Sorting with Pagination 
                                                  
### API Number : 4

**API Description : **: API to Update the person data 

**API URL**: http://localhost:8080/person/update

**API Method Type**: PUT

**API Content Type**: application/json

**Response**: application/json

![](https://github.com/Harrymsys/embl-ebi/blob/main/outputs/Update.PNG)

**Sample Input**: 


                       {
                       "firstName": "BillGates",
                       "lastName": "Microsoft",
                       "age": "66",
                       "favouriteColour": "red",
                       "personId" : 2
                       }

**Sample Response**: 
                      Person data is updated successfully to the data store, personId : 2

**Possible Status Codes **: 
                     200 - Success 
                     500 - Internal Server Error 
                     General HTTP Errors like 400, 415 etc., 
                     
**Possible Cases **:           
                 1) Update single data 
                 2) Validation on firstName, lastName, age and favouriteColour field 
                            firstName >= 3 and firstName <= 60 (It is configurable in constants file as of now - we can move this to property file)
                            lastName >= 3 and lastName <= 60 (It is configurable in constants file as of now - we can move this to property file)
                            favouriteColour >= 3 and favouriteColour <= 60 (It is configurable in constants file as of now - we can move this to property file)
                            age >= 1 (It is configurable in constants file as of now - we can move this to property file)
                 3) Validation on personId field
                 

### API Number : 5

**API URL**: http://localhost:8080/person/delete/{personId}

**API Method Type**: DELETE

**Response**: application/json

![](https://github.com/Harrymsys/embl-ebi/blob/main/outputs/deletebyid.PNG)

**Sample Input**: 

                     http://localhost:8080/person/delete/5

**Sample Response**: 
                     Person data is deleted successfully from the data store, personId : 16
                      
                      

**Possible Status Codes **: 
                     200 - Success 
                     500 - Internal Server Error 
                     General HTTP Errors like 400, 415 etc., 
                     
**Possible Cases **:           
                 1) Delete the person data, If the personId is valid and available in datastore
                 2) Validation errors on the personId 
                 
### API Number : 6

**API URL**: http://localhost:8080/person/deleteall

**API Method Type**: DELETE

**Response**: application/json

![](https://github.com/Harrymsys/embl-ebi/blob/main/outputs/deleteall.PNG)

**Sample Input**: 

                     http://localhost:8080/person/deleteall

**Sample Response**: 
                     All the Person data's are deleted successfully from the data store
                      
                      

**Possible Status Codes **: 
                     200 - Success 
                     500 - Internal Server Error 
                     General HTTP Errors like 400, 415 etc., 
                     
**Possible Cases **:           
                 1) Delete all the person data
                 2) Validation errors on the personId 
                 
# 3) Test Coverage Report 

# We have 83.5% test coverage report in this project currently 

![](https://github.com/Harrymsys/embl-ebi/blob/main/outputs/testcoveragereport.PNG)

# We have 27 tests with possible cases for the above use case

![](https://github.com/Harrymsys/embl-ebi/blob/main/outputs/testcase1.PNG)

# 4) Deployment 

## Prerequisite 

1) Docker should be installed in the machine for container deployment
2) Maven should be installed in the machine for building the project 
3) Git for clone 

## Build the project (Its already built - Try If you are interested in building again) 

1) Go to the pom.xml folder and run the maven command like below 

mvn clean install 

## Docker Deployment 

1) Clone this project repository : git clone https://github.com/Harrymsys/embl-ebi.git (Currently added as public repository - I will remove it after you guys downloaded this project) 
2) Go to the Docker file location : 

![](https://github.com/Harrymsys/embl-ebi/blob/main/outputs/DockerfileLocation.PNG)

3) Build the docker image 

![](https://github.com/Harrymsys/embl-ebi/blob/main/outputs/builddockerimage.PNG)

4) Run the docker image 

![](https://github.com/Harrymsys/embl-ebi/blob/main/outputs/runningdocker.PNG)



