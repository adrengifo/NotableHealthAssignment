package com.NotableHealth.service;


import com.NotableHealth.repositories.PatientRepository;
import com.NotableHealth.models.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    public Patient createNewPatient(Patient newPatient) throws Exception {
        Patient existingPatient = patientRepository.findByPatient(newPatient.getFirstName(), newPatient.getLastName(), newPatient.getDob());

        if (existingPatient != null) {
            throw new Exception("Patient already exists");
        }
        return patientRepository.save(newPatient);
    }

}
