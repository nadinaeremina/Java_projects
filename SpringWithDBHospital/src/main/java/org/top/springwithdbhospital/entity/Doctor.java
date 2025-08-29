package org.top.springwithdbhospital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="doctor_t")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name_f", nullable = false)
    private String name;

    @Column(name="lastname_f", nullable = false)
    private String lastname;

    @Column(name="speciality_f", nullable = false)
    private String speciality;

    @Column(name="age_f", nullable = false)
    private Integer age;

    // связь с сущностью (таблицей) докторов
    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private Set<Patient> patients;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getSpeciality() {
        return speciality;
    }

    public Integer getAge() {
        return age;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
