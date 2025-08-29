package org.top.springwithdbhospital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="patient_t")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name_f", nullable = false)
    private String name;

    @Column(name="lastname_f", nullable = false)
    private String lastname;

    @Column(name="diagnosis_f", nullable = false)
    private String diagnosis;

    @Column(name="age_f", nullable = false)
    private Integer age;

    // связь с сущностью (таблицей) докторов
    @ManyToOne
    @JoinColumn(name="doctor_id", nullable = false)
    @JsonIgnore
    private Doctor doctor;

    public Patient() {}

    public Doctor getDoctor() {
        return doctor;
    }

    public Integer getAge() {
        return age;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getLastname() {
        return lastname;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
