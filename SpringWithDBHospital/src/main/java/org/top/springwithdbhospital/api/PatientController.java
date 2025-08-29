package org.top.springwithdbhospital.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.top.springwithdbhospital.entity.Doctor;
import org.top.springwithdbhospital.entity.Patient;
import org.top.springwithdbhospital.repository.DoctorRepository;
import org.top.springwithdbhospital.repository.PatientRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("patient")
public class PatientController {
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public PatientController(PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @GetMapping
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    @PostMapping
    public Patient create(@RequestBody PatientCreateData patientCreateData) {
        // заполним данные пациента
        Patient patient = new Patient();
        patient.setName(patientCreateData.getName());
        patient.setLastname(patientCreateData.getLastname());
        patient.setDiagnosis(patientCreateData.getDiagnosis());
        patient.setAge(patientCreateData.getAge());
        // для доктора заполним только id и установим данного пациента
        Doctor patientDoctor = new Doctor();
        patientDoctor.setId(patientCreateData.getDoctorId());
        patient.setDoctor(patientDoctor);
        // сохранить в БД
        patientRepository.save(patient);
        // вернуть созданный заказ
        return patient;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return ResponseEntity.ok().body("Успешно удалено!");
        }
        return ResponseEntity.badRequest().body("Нет такого id!");
    }

    @PatchMapping("{id}")
    public ResponseEntity<String> edit(@PathVariable Integer id,
                                       @RequestBody PatientCreateData editPatient) {

        Patient newPatient = new Patient();

        if (patientRepository.existsById(id)) {
            newPatient.setName(editPatient.getName());
            newPatient.setLastname(editPatient.getLastname());
            newPatient.setDiagnosis(editPatient.getDiagnosis());
            newPatient.setAge(editPatient.getAge());
            int doctorId = editPatient.getDoctorId();
            Optional<Doctor> doctorOptional = doctorRepository.findById(editPatient.getDoctorId());
            Doctor doctor = doctorOptional.get();
            newPatient.setDoctor(doctor);
            patientRepository.deleteById(id);
            patientRepository.save(newPatient);
            return ResponseEntity.ok().body("Успешно отредактировано!");
        }
        return ResponseEntity.badRequest().body("Нет такого id!");
    }

    @GetMapping("{id}")
    public Optional<Patient> getPatientById(@PathVariable Integer id) {
        return patientRepository.findById(id);
    }
}
