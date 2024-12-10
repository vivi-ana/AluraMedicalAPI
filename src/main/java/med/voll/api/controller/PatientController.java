package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * REST controller for managing patients.
 * Provides endpoints for registering, updating, deleting, and retrieving patients.
 */
@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    PatientService patientService;

    /**
     * Retrieves a paginated list of patients.
     * @param pageable the pagination information
     * @return the response entity containing the paginated list of patients
     */
    @GetMapping
    public ResponseEntity<Page<DataPatientList>> patientList (@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return ResponseEntity.ok(patientService.getAllPatients(pageable));
    }

    /**
     * Registers a new patient.
     * @param dataPatientRegister the data to register a new patient
     * @param uriComponentsBuilder the URI components builder
     * @return the response entity containing the registered patient's data and the URI of the new resource
     */
    @PostMapping
    @Transactional
    public ResponseEntity<DataResponsePatient> patientRegister (@RequestBody @Valid DataPatientRegister dataPatientRegister, UriComponentsBuilder uriComponentsBuilder) {
        DataResponsePatient dataResponsePatient = patientService.registerPatient(dataPatientRegister);
        URI url = uriComponentsBuilder.path("/patient/{id}").buildAndExpand(dataResponsePatient.id()).toUri();
        return ResponseEntity.created(url).body(dataResponsePatient);
    }

    /**
     * Updates a patient's information.
     * @param dataPatientUpdate the data to update the patient's information
     * @return the response entity containing the updated patient's data
     */
    @PutMapping
    @Transactional
    public ResponseEntity patientUpdate (@RequestBody @Valid DataPatientUpdate dataPatientUpdate) {
        return ResponseEntity.ok(patientService.updatePatient(dataPatientUpdate));
    }

    /**
     * Marks a patient as inactive (logical delete).
     * @param id the ID of the patient to be deleted
     * @return the response entity with no content status
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity patientDelete(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves the detailed data of a specific patient.
     * @param id the ID of the patient to be retrieved
     * @return the response entity containing the patient's data
     */
    @GetMapping("/{id}")
    public ResponseEntity<DataResponsePatient> patientDataReturn (@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }
}