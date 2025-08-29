package org.top.springwithdbhospital.api;

public class DoctorCreateData {
    private String name;
    private String lastname;
    private String speciality;
    private Integer age;

    public DoctorCreateData() {}

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
}
