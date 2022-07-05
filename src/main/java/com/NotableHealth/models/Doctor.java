package com.NotableHealth.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    UUID id;

    @JsonProperty(value = "firstName")
    String firstName;

    @JsonProperty(value = "lastName")
    String lastName;

//    @OneToMany(mappedBy = "id")
//    private List<Appointment> appointments;
}
