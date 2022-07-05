package com.NotableHealth.repositories;

import com.NotableHealth.models.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Object> {

    @Query(value = "Select * from patients where id= :id", nativeQuery = true)
    Patient findById(String id);

    List<Patient> findAll();

    @Query(value = "SELECT * from patients WHERE first_name = :firstName and last_name = :lastName and dob = :dob", nativeQuery = true)
    Patient findByPatient(String firstName, String lastName, String dob);
}
