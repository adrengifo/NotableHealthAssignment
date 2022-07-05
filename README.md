# NotableHealth

To run:
    - Ensure you have a mySql server running on port 3306
    - user = root
    - password = Password

```
./gradlew bootRun
```

## Endpoints:
### Appointments
- Gets all appointments
  - GET: localhost:8080/appointment/all
- Get appointment by docter:
  - GET: localhost:8080/appointment/doctor/{doctorId}
- Get appointment by doctor and date:
  - GET: localhost:8080/appointment/doctor/{doctorId}/{date}
- Delete appointment
  - DELETE: localhost:8080/appointment/{appointmentId}
- Create new appointment
  - POST: localhost:8080/appointment/create/{doctorId}
  - Sample request body:
    - ```
      {
        "patient": {
            "id": "d9208c88-faa3-11ec-b939-0242ac120002",
            "first_name": "Bowman",
            "last_name": "Dwayne",
            "dob": "01/01/1999"
        },
        "startTime": "2022-07-30T01:30:56.000+00:00",
        "endTime": "2022-07-30T02:27:56.000+00:00",
        "appointmentType": "NEWPATIENT"
      }
      ```

### Doctors:
- Gets all Doctors:
  - GET: http://localhost:8080/doctor/all
- Get Doctor by Id:
  - GET:  http://localhost:8080/doctor/{doctor_id}
- Create new doctor:
  - POST: http://localhost:8080/doctor/
    - Sample request:
      - ```
        {
           "firstName": "Test",
           "lastName": "test"
        }
        ```

### Patient:
- Get all patients:
  - GET: http://localhost:8080/patient/all
- Get patient by id:
  - http://localhost:8080/patient/{patient_id}
- Create new patient:
  - POST: 
  - Sample request:
    - ```
      {
         "first_name": "Lowe",
         "last_name": "Damon",
         "dob": "01/01/1980"
      }
      ```