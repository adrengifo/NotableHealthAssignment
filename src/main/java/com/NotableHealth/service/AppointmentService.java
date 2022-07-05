package com.NotableHealth.service;

import com.NotableHealth.repositories.AppointmentRepository;
import com.NotableHealth.repositories.PatientRepository;
import com.NotableHealth.repositories.DoctorRepository;
import com.NotableHealth.models.Appointment;
import com.NotableHealth.models.Doctor;
import com.NotableHealth.models.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorRepository doctorRepository;


    public void appointmentValidations(Appointment appointment, Doctor doctor) throws Exception {
        //check if time is within right interval
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(appointment.getStartTime());
        int minute = calendar.get(Calendar.MINUTE);
        if (minute % 15 != 0) {
            throw new Exception("Appointment must start at a 15 min interval");
        }

        //Check if doc exists
        if (doctor == null) {
            throw new Exception("Doctor does not exist");
        }
        //Find if doc has more appt at that time
        Date dateTime = appointment.getStartTime();
        List<Appointment> conflictingAppts = appointmentRepository.findByDoctorIdAndDateTime(doctor.getId().toString(), dateTime);
        if (conflictingAppts.size() >= 3) {
            throw new Exception("Doctor has no availability for the current date and time");
        }
    }

    public Appointment createNewAppointment(Appointment appointmentRequest, String doctorId) throws Exception {
        Doctor doctor = doctorRepository.findById(doctorId);
        appointmentValidations(appointmentRequest, doctor);
        Patient patient = appointmentRequest.getPatient();
        if (patientRepository.findByPatient(
                patient.getFirstName(), patient.getLastName(), patient.getDob())
                == null) {
            patientRepository.save(patient);
        }
        appointmentRequest.setDoctor(doctor);
        appointmentRequest.setPatient(patient);
        return appointmentRepository.save(appointmentRequest);
    }

    public void deleteAppointment(String appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }
}
