package org.top.springwithdbhospital.api;

import jakarta.persistence.Column;

public class PatientCreateData {
    private String name;
    private String lastname;
    private String diagnosis;
    private Integer age;
    private Integer doctorId;

    public PatientCreateData() {}

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
