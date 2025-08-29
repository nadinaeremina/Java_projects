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
@RequestMapping("doctor")
public class DoctorController {
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public DoctorController(DoctorRepository doctorRepository,
                            PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @GetMapping
    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Doctor> getDoctorById(@PathVariable Integer id) {
        return doctorRepository.findById(id);
    }

    @GetMapping("{id}/patients")
    public List<Patient> getDoctorPatients(@PathVariable Integer id) {
        return patientRepository.findAllByDoctorId(id);
    }

    @PostMapping
    public Doctor addDoctor(@RequestBody DoctorCreateData doctorCreateData) {
        // заполним данные доктора
        Doctor doctor = new Doctor();
        doctor.setName(doctorCreateData.getName());
        doctor.setLastname(doctorCreateData.getLastname());
        doctor.setSpeciality(doctorCreateData.getSpeciality());
        doctor.setAge(doctorCreateData.getAge());
        // сохранить в БД
        doctorRepository.save(doctor);
        // вернуть созданный заказ
        return doctor;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return ResponseEntity.ok().body("Успешно удалено!");
        }
        return ResponseEntity.badRequest().body("Нет такого id!");
    }

    @PatchMapping("{id}")
    public ResponseEntity<String> edit(@PathVariable Integer id,
                                       @RequestBody DoctorCreateData editDoctor) {

        Doctor newDoctor = new Doctor();

        if (doctorRepository.existsById(id)) {
            newDoctor.setName(editDoctor.getName());
            newDoctor.setLastname(editDoctor.getLastname());
            newDoctor.setSpeciality(editDoctor.getSpeciality());
            newDoctor.setAge(editDoctor.getAge());
            doctorRepository.deleteById(id);
            doctorRepository.save(newDoctor);
            return ResponseEntity.ok().body("Успешно отредактировано!");
        }
        return ResponseEntity.badRequest().body("Нет такого id!");
    }
}
