
# Medical analytical prescription

Medical Analytical Prescription is a tool that aims to make the process of finding a suitable doctor and receiving a prescription more efficient and convenient.

With this project, you can input your symptoms and receive information about doctors that match your needs, including their specialty, experience, sex, and price. This helps you make an informed decision about which doctor to visit.

In addition, Medical Analytical Prescription also allows you to book appointments and receive prescriptions online. This eliminates the need for in-person visits to the doctor's office, making the process more convenient and accessible.

Finally, Medical Analytical Prescription also provides a centralized location to keep track of your appointments and prescriptions, so you have all your medical information in one place.

Overall, Medical Analytical Prescription is a pet project designed to make the healthcare experience more efficient and accessible for everyone.


## Links to related microservices

 - [med_application](https://github.com/arthurcjinpa/medical_application)
 - [med_prescription](https://github.com/arthurcjinpa/medical_prescription)
 - [med_eureka](https://github.com/arthurcjinpa/medical_eureka)


## Environment Variables

To run med_application service, you will need to add the following environment variables to your .env file

`URL`

`POSTGRES_DB`

`POSTGRES_USER`

`POSTGRES_PASSWORD`

And additionally for med_prescription

`URL`

`MYSQL_DATABASE`

`MYSQL_USER`

`MYSQL_PASSWORD`

`MYSQL_ROOT_PASSWORD`
## How to run Locally

- Clone three of my microservices

- Insert your credentials for projects and databases in .env file and spring properties

- Launch Docker

- Run command for docker-compose

```bash
  docker-compose -f docker-compose.yml up --build
```

Show a list of all Docker containers 

```bash
  docker ps -a
```

In case if Docker doesn't work because of wsl, try this command in powershell

```bash
  wsl --update
```

or this (keep in mind that this command removes all docker containers and data)

```bash
 wsl --terminate docker-desktop
 wsl --terminate docker-desktop-data
```
## Step by step process of getting an application as a user and API Reference

#### Choose your symptoms from the list by calling 

```http
  GET /prescription/symptom/list
```

#### Paste your symptoms as request param by calling 

```http
  GET /prescription/specialty?headache,jaw pain,glare from lights
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `symptoms`      | `List<String>` | **Required**. Input symptoms |

You will get a Data Transfer Object of available doctors with the right specialty (including their ids, names, prices, available time) and symptoms

#### After this, if you want to make an appointment with a doctor, you should get an application by calling

```http
  POST /application/add
```
and the body of your POST request would look like this:

    {
    "context": "i feel sick",
    "symptoms": [
        "headache",
        "eye redness",
        "glare from lights"
    ],
    "doctorId": 1,
    "specialty": "OPHTHALMOLOGIST",
    "userEmail": "nicop@mail.com",
    "chosenTime": "2023-02-01T17:00:00+02:00",
    "approved": true
    }

#### This request goes right to the prescription microservice and call there

```http
  POST /prescription/confirmation
```

This will create a Prescription in PostgreSQL prescription's database and an Application in MySQL application's database, and also add an application to the user's history and prescription to the doctor's one

#### After this, if you wish to see your applications as a user you could call

```http
  GET /application/user?email=nicop@mail.com
```
or other showAll requests.



## Tech Stack

**Frameworks:** Spring, Hibernate, Junit, Mockito, Liquibase, Drools

**Databases:** MySQL, PostgreSQL

**Microservices:** Feign, Eureka

**Tools:** Github, Trello, Dbdiagram, Postman, Docker


## Roadmap

- Integrate security

- Refactor part of the code

- Create load-balancer (Nginx or HAProxy) or integrate client-side service discovery

- Integrate Swagger

- Add logs

- Integrate Java Mail Api

- Add Quartz Scheduler


## Authors

Please, feel free to message me if you encounter any kind of problem or have something to add/edit!
- [@arthurcjinpa](https://www.github.com/arthurcjinpa)

