package com.NotableHealth.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    UUID id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false, foreignKey = @ForeignKey(name = "fk_patient_id"))
    @Type(type = "org.hibernate.type.UUIDCharType")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false, foreignKey = @ForeignKey(name = "fk_doctor_id"))
    @Type(type = "org.hibernate.type.UUIDCharType")
    private Doctor doctor;

    @JsonProperty(value = "startTime")
    private Date startTime;

    @JsonProperty(value = "endTime")
    private Date endTime;

    @Enumerated(EnumType.STRING)
    @JsonProperty(value = "appointmentType")
    private AppointmentType appointmentType;
}
