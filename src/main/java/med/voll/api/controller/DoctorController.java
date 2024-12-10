package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * REST controller for managing doctors.
 * Provides endpoints for registering, updating, deleting, and retrieving doctors.
 */
@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    /**
     * Registers a new doctor.
     * @param doctorRegister the data to register a new doctor
     * @param uriComponentsBuilder the URI components builder
     * @return the response entity containing the registered doctor's data and the URI of the new resource
     */
    @PostMapping
    public ResponseEntity<DataResponseDoctor> doctorRegister(@RequestBody @Valid DataDoctorRegister doctorRegister, UriComponentsBuilder uriComponentsBuilder) {
        DataResponseDoctor dataResponseDoctor = doctorService.registerDoctor(doctorRegister);
        URI url = uriComponentsBuilder.path("/doctor/{id}").buildAndExpand(dataResponseDoctor.id()).toUri();
        return ResponseEntity.created(url).body(dataResponseDoctor);
    }

    /**
     * Retrieves a paginated list of active doctors.
     * @param pageable the pagination information
     * @return the response entity containing the paginated list of active doctors
     */
    @GetMapping
    public ResponseEntity<Page<DataDoctorList>> doctorList(@PageableDefault(size = 2, sort = "name") Pageable pageable) {
        return ResponseEntity.ok(doctorService.getAllActiveDoctors(pageable));

    }

    /**
     * Updates a doctor's information.
     * @param doctorUpdate the data to update the doctor's information
     * @return the response entity containing the updated doctor's data
     */
    @PutMapping
    @Transactional
    public ResponseEntity doctorUpdate (@RequestBody @Valid DataDoctorUpdate doctorUpdate) {
        return ResponseEntity.ok(doctorService.updateDoctor(doctorUpdate));
    }

    /**
     * Marks a doctor as inactive (logical delete).
     * @param id the ID of the doctor to be deleted
     * @return the response entity with no content status
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity doctorDelete (@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves the detailed data of a specific doctor.
     * @param id the ID of the doctor to be retrieved
     * @return the response entity containing the doctor's data
     */
    @GetMapping("/{id}")
    public ResponseEntity<DataResponseDoctor> doctorDataReturn (@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }
}