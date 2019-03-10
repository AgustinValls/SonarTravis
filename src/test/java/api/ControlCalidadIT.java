package api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;

import api.apiControllers.ControlCalidadApiController;

import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;

import api.dtos.ControlCalidadDto;
import api.dtos.ControlCalidadIdReadAllDto;

import java.util.List;

public class ControlCalidadIT {

    @BeforeAll
    static void before() {
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

    @Test
    void testCreateControlCalidad() {
        this.createControlCalidad();
    }

    private String createControlCalidad() {
        HttpRequest request = HttpRequest.builder(ControlCalidadApiController.CONTROLCALIDADES)
                .body(new ControlCalidadDto("Servicio puntual. Satisfactorio", true)).post();
        return (String) new Client().submit(request).getBody();
    }

    @Test
    private String createControlCalidad(String texto) {
        ControlCalidadDto controlCalidadDto = new ControlCalidadDto(texto, true);
        HttpRequest request = HttpRequest.builder(ControlCalidadApiController.CONTROLCALIDADES).body(controlCalidadDto).post();
        return (String) new Client().submit(request).getBody();
    }

    @Test
    void testConductorInvalidRequest() {
        HttpRequest request = HttpRequest.builder(ControlCalidadApiController.CONTROLCALIDADES + "/invalid").body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateConductorWithoutConductorDto() {
        HttpRequest request = HttpRequest.builder(ControlCalidadApiController.CONTROLCALIDADES).body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateConductorWithoutConductorDtoName() {
        HttpRequest request = HttpRequest.builder(ControlCalidadApiController.CONTROLCALIDADES)
                .body(new ControlCalidadDto(null, null)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testReadAll() {
        for (int i = 0; i < 8; i++) {
            this.createControlCalidad("Hora de llegada puntual " +i);
        }
        HttpRequest request = HttpRequest.builder(ControlCalidadApiController.CONTROLCALIDADES).get();
        List<ControlCalidadIdReadAllDto> themes = (List<ControlCalidadIdReadAllDto>) new Client().submit(request).getBody();
        assertTrue(themes.size()>=8);
    }
}