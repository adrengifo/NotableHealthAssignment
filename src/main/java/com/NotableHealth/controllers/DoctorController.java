package com.NotableHealth.controllers;

import com.NotableHealth.repositories.DoctorRepository;
import com.NotableHealth.models.Doctor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(value = "/doctor")
public class DoctorController {
    @Autowired
    DoctorRepository doctorRepository;

    Logger logger = LoggerFactory.getLogger(DoctorController.class);

    @GetMapping("/all")
    public ResponseEntity<List<Doctor>> getDoctorById() {
        List<Doctor> doctor = doctorRepository.findAll();
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable String doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId);
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        Doctor createdDoctor = doctorRepository.save(doctor);
        return new ResponseEntity<>(createdDoctor, HttpStatus.CREATED);
    }
}
