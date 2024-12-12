package med.voll.api.controller;

import med.voll.api.domain.address.Address;
import med.voll.api.domain.address.DataAddress;
import med.voll.api.domain.doctor.*;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DataDoctorRegister> dataDoctorRegisterJSON;

    @Autowired
    private JacksonTester<DataResponseDoctor> dataResponseDoctorJSON;

    @MockitoBean
    private DoctorRepository doctorRepository;

    @Test
    @DisplayName("It should return HTTP 400 when the request has invalid data.")
    @WithMockUser
    void doctorRegisterScenery1() throws Exception {
        var response = mockMvc
                .perform(post("/doctor"))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("It should return HTTP 200 when the request receives a valid JSON.")
    @WithMockUser
    void doctorRegisterScenery2() throws Exception {
        var dataDoctorRegister = new DataDoctorRegister(
                "Medico",
                "medico@voll.med",
                "61999999999",
                "123456",
                Specialty.CARDIOLOGY,
                addressData());

        when(doctorRepository.save(any())).thenReturn(new Doctor(dataDoctorRegister));

        var response = mockMvc
                .perform(post("/doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataDoctorRegisterJSON.write(dataDoctorRegister).getJson()))
                .andReturn().getResponse();

        var dataResponseDoctor = new DataResponseDoctor(
                null,
                dataDoctorRegister.name(),
                dataDoctorRegister.email(),
                dataDoctorRegister.cellphoneNumber(),
                dataDoctorRegister.identityCard(),
                dataDoctorRegister.address()
        );

        var expectedJson  = dataResponseDoctorJSON.write(dataResponseDoctor).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }


    private DataAddress addressData() {
        return new DataAddress(
                "street x",
                "district y",
                "Buenos Aires",
                "345",
                "1"
        );
    }

}