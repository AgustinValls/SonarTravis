package api;

import api.apiControllers.AutobusApiController;
import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.dtos.AutobusDto;
import api.entities.Conductor;

import api.entities.LineaRegular;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AutobusIT {

    @BeforeAll
    static void before() {
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

    @Test
    void testCreateAutobus() {
        this.createAutobus();
    }

    @Test
    void testAutobusInvalidRequest() {
        HttpRequest request = HttpRequest.builder(AutobusApiController.AUTOBUSES + "/invalid").body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateConductorWithoutConductorDto() {
        HttpRequest request = HttpRequest.builder(AutobusApiController.AUTOBUSES).body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testUpdateAutobusNotFoundException() {
        LineaRegular lineaRegular = LineaRegular.NACIONAL;
        String id = this.createAutobus(lineaRegular);
        HttpRequest request = HttpRequest.builder(AutobusApiController.AUTOBUSES).path(AutobusApiController.ID_ID)
                .expandPath(id + "ERRORINYECTADO").path(AutobusApiController.LINEAREGULAR).body(LineaRegular.NACIONAL).patch();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    void testUpdateLineaRegular(){
        LineaRegular lineaRegular = LineaRegular.INTERURBANA;
        String id = this.createAutobus(lineaRegular);
        HttpRequest request = HttpRequest.builder(AutobusApiController.AUTOBUSES).path(AutobusApiController.ID_ID)
                .expandPath(id).path(AutobusApiController.LINEAREGULAR).body(lineaRegular).patch();
        new Client().submit(request);
        lineaRegular = LineaRegular.PROVINCIAL;
        id = this.createAutobus(lineaRegular);
        request = HttpRequest.builder(AutobusApiController.AUTOBUSES).path(AutobusApiController.ID_ID)
                .expandPath(id).path(AutobusApiController.LINEAREGULAR).body(lineaRegular).patch();
        new Client().submit(request);
    }

    private String createAutobus() {
        Conductor conductor = new Conductor("Isabel Garcia", "656534365");
        HttpRequest request = HttpRequest.builder(AutobusApiController.AUTOBUSES).body(new AutobusDto(45, conductor)).post();
        return (String) new Client().submit(request).getBody();
    }

    private String createAutobus(LineaRegular lineaRegular) {
        Conductor conductor = new Conductor("Alberto Perez Sanz", "693733845");
        HttpRequest request = HttpRequest.builder(AutobusApiController.AUTOBUSES).body(new AutobusDto(45, conductor, lineaRegular)).post();
        return (String) new Client().submit(request).getBody();
    }
}
