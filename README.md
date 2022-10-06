# Critter Chronologer Project 

This project is part of the Udacity Java Web Developer Nanoedegree, showcasing the use of Spring with some basic JPA features.
The idea behind is to show how Java supports modelling business objects and their efficient persistence to a database, mysql in this case. 
Critter Chronologer a Software as a Service application that provides a scheduling interface for a small business that takes care of animals. 
This Spring Boot project will allow users to create pets, owners, and employees, and then schedule events for employees to provide services for pets.

## Getting Started

* [http://localhost:8082/test](http://localhost:8082/test) should return "Critter Starter installed successfully"
* [Postman Query Collection](`src/main/resource/Udacity.postman_collection.json`) contains a number of queries that you should be able to run (after importing the query collection into your postman)

### Save Customer Service
Create a new customer with a POST-Request to [http://localhost:8082/user/customer](http://localhost:8082/user/customer) containing payload/body below.
Service responds with the persisted customer entity/object having a unique id (and some empty fields).
```
{
"name": "Alex",
"phoneNumber": "1234567890"
}
```

### Save pet service
Create a new pet with a POST-Request to [http://localhost:8082/pet](http://localhost:8082/pet) containing payload/body below.
Service responds with the persisted pet entity/object having a unique id.
```
{
  "type": "CAT",
  "name": "Kilo",
  "birthDate": "2019-12-16T04:43:57.995Z",
  "notes": "HI KILO"
, "ownerId": "1"
}
``` 

### Save employee service
Create a new employee with a POST-Request to [http://localhost:8082/user/employee](http://localhost:8082/user/employee) 
containing payload/body below.
Service responds with the persisted employee entity/object having a unique id (and some empty fields).
```
{
  "name": "Hannah",
  "skills": ["PETTING", "FEEDING"]
}
``` 

### Get all customers/pets/employees
* GET-Gequest to [http://localhost:8082/user/customer](http://localhost:8082/user/customer)
shows the list of existing customers.
 
* GET-Gequest to [http://localhost:8082/pet/](http://localhost:8082/pet/)
shows the list of existing pets.

* GET-Gequest to [http://localhost:8082/user/employee](http://localhost:8082/user/employee)
shows the list of existing employees.

### Get customer/pet/employee by id
* A parameterized GET-Request to [http://localhost:8082/user/customer/1](http://localhost:8082/user/customer/1) 
responds with the customer having id=1. 

* A parameterized GET-Request to [http://localhost:8082/pet/2](http://localhost:8082/pet/2)
  responds with the pet having id=2 (parameterized as PathVariable).

* A parameterized GET-Request to [http://localhost:8082/user/employee/3](http://localhost:8082/user/employee/3)
responds with the employee identified by id=3 (parameterized as PathVariable).


### Get customer by pet and get pets by customer 
* A parameterized GET-Request to [http://localhost:8082/user/customer/pet/2](http://localhost:8082/user/customer/pet/2) 
responds with the customer of pet with id=2 (parameterized as PathVariable).
 
* A parameterized GET-Request to [http://localhost:8082/pet/owner/1](http://localhost:8082/pet/owner/1)
responds with a list of pets owned by customer with id=1 (parameterized as PathVariable).

### Add employee schedule and check employee availability for a given date
* Add a schedule to an existing employee (with id=1 as PathVariable) using a parameterized PUT-Request to
 [http://localhost:8082/user/employee/3](http://localhost:8082/user/employee/3)
 containing payload/body below.
 Service responds with void, respectively no response in case of success. 
```
{
    ["MONDAY", "TUESDAY", "FRIDAY"]
}
``` 

* You can check the availability for certain services (like feeding or petting) on a certain date with a GET-Request to
 [http://localhost:8082/user/employee/availability](http://localhost:8082/user/employee/availability) 
 containing the payload/body below.
 Service responds with the employee object/entity that provides the specified services on the given date. 
```
{
"date": "2019-12-17",
"skills": ["PETTING", "FEEDING"]
}
```

### Create schedule and get schedules (all or by customer/pet/employee)
* Create a schedule (i.e. a meeting between a number of employees and pets for a set of activities) 
  by using a POST-Request to [localhost:8082/schedule](localhost:8082/schedule) 
  containing the payload below.
  Service responds with the persisted schedule entity/object having a unique id.
```
{
    "employeeIds": [3],
    "petIds": [2],
    "date": "2020-08-02",
    "activities": ["FEEDING", "PETTING"]
}
```

* Get list of all existing schedules via GET-Request to [localhost:8082/schedule](localhost:8082/schedule)

* Find schedule by customer with a certain id via parameterized GET-Request 
  [http://localhost:8082/schedule/customer/1](http://localhost:8082/schedule/customer/1)
  where pathvariable represents the id (in this case for the customer with id=1)

* Find schedule by pet via with a certain id via parameterized GET-Request
  [http://localhost:8082/schedule/pet/2](http://localhost:8082/schedule/pet/2)
  where pathvariable represents the id (in this case for the pet with id=2)

* Find schedule by employee via with a certain id via parameterized GET-Request
  [http://localhost:8082/schedule/employee/3](http://localhost:8082/schedule/employee/3)
  where pathvariable represents the id (in this case for the employee with id=3)

## ToDo

## Dependencies

* [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download) (or Ultimate) recommended 
* [Java SE Development Kit 8+](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Maven](https://maven.apache.org/download.cgi)
* [MySQL Server 8](https://dev.mysql.com/downloads/mysql/) (or another standalone SQL instance)
* [Postman](https://www.getpostman.com/downloads/)

Part of this project involves configuring a Spring application to connect to an external data source. Before beginning this project, you must install a database to connect to. Here are [instructions for installing MySQL 8](https://dev.mysql.com/doc/refman/8.0/en/installing.html).

You should install the Server and Connector/J, but it is also convenient to install the Documentation and Workbench.

Alternately, you may wish to run MySQL in a docker container, using [these instructions](https://hub.docker.com/_/mysql/).

After installing the Server, you will need to create a user that your application will use to perform operations on the server. You should create a user that has all permissions on localhost using the sql command found [here](https://dev.mysql.com/doc/refman/8.0/en/creating-accounts.html).

Another SQL database may be used if desired, but do not use the H2 in-memory database as your primary datasource.

## Installation

1. Clone or download this repository.
2. Open IntelliJ IDEA.
3. In IDEA, select `File` -> `Open` and navigate to the `critter` directory within this repository. Select that directory to open.
4. The project should open in IDEA. In the project structure, navigate to `src/main/java/com.udacity.jdnd.course3.critter`. 
5. Within that directory, click on CritterApplication.java and select `Run` -> `Debug CritterApplication`. 
6. Open a browser and navigate to the url: [http://localhost:8082/test](http://localhost:8082/test)

You should see the message "Critter Starter installed successfully" in your browser.

## Testing

Once you have completed the above installation, you should also be able to run the included unit tests to verify basic functionality as you complete it. To run unit tests:

1. Within your project in IDEA, Navigate to `src/test/java/com.udacity.jdnd.course3.critter`.
2. Within that directory, click on `CritterFunctionalTest.java` and select `Run` -> `Run CritterFunctionalTest`.

A window should open showing you the test executions. All 9 tests should fail and if you click on them they will show `java.lang.UnsupportedOperationeException` as the cause.

As you complete the objectives of this project, you will be able to verify progress by re-running these tests.

### Tested Conditions
Tests will pass under the following conditions:

* `testCreateCustomer` - **UserController.saveCustomer** returns a saved customer matching the request
* `testCreateEmployee` - **UserController.saveEmployee** returns a saved employee matching the request
* `testAddPetsToCustomer` - **PetController.getPetsByOwner** returns a saved pet with the same id and name as the one saved with **UserController.savePet** for a given owner
* `testFindPetsByOwner` - **PetController.getPetsByOwner** returns all pets saved for that owner.
* `testFindOwnerByPet` - **UserController.getOwnerByPet** returns the saved owner used to create the pet.
* `testChangeEmployeeAvailability` - **UserController.getEmployee** returns an employee with the same availability as set for that employee by **UserControler.setAvailability**
* `testFindEmployeesByServiceAndTime` - **UserController.findEmployeesForService** returns all saved employees that have the requested availability and skills and none that do not
* `testSchedulePetsForServiceWithEmployee` - **ScheduleController.createSchedule** returns a saved schedule matching the requested activities, pets, employees, and date
* `testFindScheduleByEntities` - **ScheduleController.getScheduleForEmployee** returns all saved schedules containing that employee. **ScheduleController.getScheduleForPet** returns all saved schedules for that pet. **ScheduleController.getScheduleForCustomer** returns all saved schedules for any pets belonging to that owner.

### Postman
In addition to the included unit tests, a Postman collection has been provided. 

1. Open Postman.
2. Select the `Import` button.
3. Import the file found in this repository under `src/main/resource/Udacity.postman_collection.json`
4. Expand the Udacity folder in postman.

Each entry in this collection contains information in its `Body` tab if necessary and all requests should function for a completed project. Depending on your key generation strategy, you may need to edit the specific ids in these requests for your particular project.

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - Framework providing dependency injection, web framework, data binding, resource management, transaction management, and more.
* [Google Guava](https://github.com/google/guava) - A set of core libraries used in this project for their collections utilities.
* [H2 Database Engine](https://www.h2database.com/html/main.html) - An in-memory database used in this project to run unit tests.
* [MySQL Connector/J](https://www.mysql.com/products/connector/) - JDBC Drivers to allow Java to connect to MySQL Server

## License

This project is licensed under the MIT License - see the [LICENSE.md]()
