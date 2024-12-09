package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.appointment.AppointmentService;
import med.voll.api.domain.appointment.DataAppointmentCancel;
import med.voll.api.domain.appointment.DataAppointmentSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing appointments.
 */
@RestController
@RequestMapping("appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    /**
     * Schedules a new appointment.
     * @param dataAppointmentSchedule the appointment data to schedule
     * @return a ResponseEntity containing the scheduled appointment details
     */
    @PostMapping
    @Transactional
    public ResponseEntity scheduleAppointment (@RequestBody @Valid DataAppointmentSchedule dataAppointmentSchedule) {
        var appointmentDetail = appointmentService.scheduleAppointment(dataAppointmentSchedule);
        return ResponseEntity.ok(appointmentDetail);
    }

    /**
     * Cancels an existing appointment.
     * @param dataAppointmentCancel the appointment data to cancel
     * @return a ResponseEntity indicating the cancellation was successful
     */
    @DeleteMapping
    @Transactional
    public ResponseEntity cancelAppointment(@RequestBody @Valid DataAppointmentCancel dataAppointmentCancel) {
        appointmentService.cancelAppointment(dataAppointmentCancel);
        return ResponseEntity.noContent().build();
    }
}
