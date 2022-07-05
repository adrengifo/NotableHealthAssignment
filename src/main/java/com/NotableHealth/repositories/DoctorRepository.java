package com.NotableHealth.repositories;

import com.NotableHealth.models.Doctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Object> {
    @Query(value = "Select * from Doctors where id= :id", nativeQuery = true)
    Doctor findById(String id);


    List<Doctor> findAll();
}
