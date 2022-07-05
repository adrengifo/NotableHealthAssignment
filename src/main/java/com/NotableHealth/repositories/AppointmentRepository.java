package com.NotableHealth.repositories;

import com.NotableHealth.models.Appointment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Object> {

    List<Appointment> findAll();

    @Query(value = "Select * From appointments where id= :id", nativeQuery = true)
    Appointment findById(String id);

    @Modifying
    @Query(value = "Delete From appointments where id= :id", nativeQuery = true)
    void deleteById(String id);

    @Query(value = "Select * from appointments where doctor_id= :id", nativeQuery = true)
    List<Appointment> findByDoctorId(String id);

    @Query(value = "Select * FROM appointments where doctor_id= :doctorId and DATE(start_time) = :date", nativeQuery = true)
    List<Appointment> findByDoctorIdAndDate(String doctorId, String date);

    @Query(value = "Select * FROM appointments where doctor_id= :doctorId and start_time = :dateTime", nativeQuery = true)
    List<Appointment> findByDoctorIdAndDateTime(String doctorId, Date dateTime);
}
