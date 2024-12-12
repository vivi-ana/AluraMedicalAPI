package med.voll.api.controller;

import med.voll.api.domain.appointment.AppointmentService;
import med.voll.api.domain.appointment.DataAppointmentDetail;
import med.voll.api.domain.appointment.DataAppointmentSchedule;
import med.voll.api.domain.doctor.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private JacksonTester<DataAppointmentSchedule> dataAppointmentScheduleJSON;

    @Autowired
    private JacksonTester<DataAppointmentDetail> dataAppointmentDetailJSON;

    @MockitoBean
    AppointmentService appointmentService;

    @Test
    @DisplayName("It should return HTTP 400 when the request has invalid data.")
    @WithMockUser
    void scheduleAppointmentScenery1() throws Exception {
        var response = mockMvc.perform((post("/appointments")))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("It should return HTTP 200 when the request receives a valid JSON.")
    @WithMockUser
    void scheduleAppointmentScenery2() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.CARDIOLOGY;
        var dataDetail = new DataAppointmentDetail(null, 2L, 5L, date);
        when(appointmentService.scheduleAppointment(any())).thenReturn(dataDetail);

        var response = mockMvc.perform((post("/appointments"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataAppointmentScheduleJSON.write(
                                new DataAppointmentSchedule(2L, 5L, date, specialty)
                        ).getJson())
                )
                .andReturn().getResponse();

        var expectedJson = dataAppointmentDetailJSON.write(dataDetail).getJson();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }
}