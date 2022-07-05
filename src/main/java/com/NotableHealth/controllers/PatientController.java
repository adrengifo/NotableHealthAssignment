package com.NotableHealth.controllers;

import com.NotableHealth.repositories.PatientRepository;
import com.NotableHealth.models.Patient;
import com.NotableHealth.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(value = "/patient")
public class PatientController {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PatientService patientService;

    Logger logger = LoggerFactory.getLogger(PatientController.class);

    @GetMapping("/{patientId}")
    public ResponseEntity<Patient> getPatientById(
            @PathVariable String patientId) {
        Patient patient = patientRepository.findById(patientId);
        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> allPatients = patientRepository.findAll();
        return new ResponseEntity<>(allPatients, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> createPatient(@RequestBody Patient patient) {
        try{
            Patient newPatient = patientService.createNewPatient(patient);
            return new ResponseEntity<>(newPatient, HttpStatus.CREATED);
        } catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
