package com.NotableHealth.controllers;

import com.NotableHealth.repositories.AppointmentRepository;
import com.NotableHealth.models.Appointment;
import com.NotableHealth.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping(value = "/appointment")
public class AppointmentsController {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    AppointmentService appointmentService;

    @RequestMapping(value = "/all")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> allAppointments = appointmentRepository.findAll();
        return new ResponseEntity<>(allAppointments, HttpStatus.OK);
    }

    @RequestMapping(value = "/doctor/{doctorId}")
    public ResponseEntity<List<Appointment>> getAllAppointmentsByDoctor(
            @PathVariable String doctorId) {
        List<Appointment> allAppointments = appointmentRepository.findByDoctorId(doctorId);
        return new ResponseEntity<>(allAppointments, HttpStatus.OK);
    }

    @RequestMapping(value = "/doctor/{doctorId}/{date}")
    public ResponseEntity<List<Appointment>> getAllAppointmentsByDoctorAndDate(
            @PathVariable String doctorId, @PathVariable String date) {
        List<Appointment> allAppointments = appointmentRepository.findByDoctorIdAndDate(doctorId, date);
        return new ResponseEntity<>(allAppointments, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(value = "/{appointmentId}")
    public ResponseEntity<Object> deleteAppointment(@PathVariable String appointmentId) {
        Appointment appt = appointmentRepository.findById(appointmentId);
        if (appt == null) {
            return new ResponseEntity<>(
                    "No Appointment found with the provided Appointment ID", HttpStatus.BAD_REQUEST);
        }
        appointmentService.deleteAppointment(appointmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/create/{doctorId}")
    public ResponseEntity<Object> createNewAppointment(
            @RequestBody Appointment appointment, @PathVariable String doctorId) {
        try {
            Appointment newAppointment = appointmentService.createNewAppointment(appointment, doctorId);
            return new ResponseEntity<>(newAppointment, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
