package com.NotableHealth.models;

public enum AppointmentType {

    NEWPATIENT("New Patient"),
    FOLLOWUP("Follow Up");

    private final String appointmentType;

    AppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getString() {
        return appointmentType;
    }
}
