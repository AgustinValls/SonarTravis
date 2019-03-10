package api;

import api.entities.LineaRegular;
import api.apiControllers.ConductorApiController;
import api.apiControllers.ControlCalidadApiController;
import api.apiControllers.AutobusApiController;
import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.dtos.ConductorDto;
import api.dtos.ControlCalidadDto;
import api.dtos.AutobusDto;
import api.exceptions.NotFoundException;
import api.exceptions.ArgumentNotValidException;
import api.exceptions.RequestInvalidException;

import http.HttpRequest;
import http.HttpResponse;
import http.HttpStatus;

import org.apache.logging.log4j.LogManager;

public class Dispatcher {

    static{
        DaoFactory.setFactory(new DaoMemoryFactory());
        LogManager.getLogger(Dispatcher.class).debug("  create DaoMemoryFactory");
    }

    private ConductorApiController conductorApiController = new ConductorApiController();

    private ControlCalidadApiController controlCalidadApiController = new ControlCalidadApiController();

    private AutobusApiController autobusApiController = new AutobusApiController();

    public void submit(HttpRequest request, HttpResponse response) {
        String ERROR_MESSAGE = "{'error':'%S'}";
        try {
            switch (request.getMethod()) {
                case POST:
                    this.doPost(request, response);
                    break;
                case GET:
                    this.doGet(request, response);
                    break;
                case PUT:
                    this.doPut(request);
                    break;
                case PATCH:
                    this.doPatch(request);
                    break;
                case DELETE:
                    this.doDelete(request);
                    break;
                default:
                    throw new RequestInvalidException("method error: " + request.getMethod());
            }
        } catch (ArgumentNotValidException | RequestInvalidException exception) {
            response.setBody(String.format(ERROR_MESSAGE, exception.getMessage()));
            response.setStatus(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException exception) {
            response.setBody(String.format(ERROR_MESSAGE, exception.getMessage()));
            response.setStatus(HttpStatus.NOT_FOUND);
        } catch (Exception exception) {  // Unexpected
            LogManager.getLogger(Dispatcher.class).error("HTTP UNEXPECTED ERROR: " + exception);
            exception.printStackTrace();
            response.setBody(String.format(ERROR_MESSAGE, exception));
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void doPost(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(ConductorApiController.CONDUCTORES)) {
            response.setBody(this.conductorApiController.create((ConductorDto) request.getBody()));
        } else if(request.isEqualsPath(ControlCalidadApiController.CONTROLCALIDADES)) {
            response.setBody(this.controlCalidadApiController.create((ControlCalidadDto) request.getBody()));
        } else if(request.isEqualsPath(AutobusApiController.AUTOBUSES)) {
            response.setBody(this.autobusApiController.create((AutobusDto) request.getBody()));
        } else {
            throw new RequestInvalidException("method error: " + request.getMethod());
        }
    }

    private void doPut(HttpRequest request) {
        if (request.isEqualsPath(ConductorApiController.CONDUCTORES + ConductorApiController.ID_ID)) {
            this.conductorApiController.update(request.getPath(1), (ConductorDto) request.getBody());
        } else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

    private void doGet(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(ControlCalidadApiController.CONTROLCALIDADES)) {
            response.setBody(this.controlCalidadApiController.readAll());
        } else {
            throw new RequestInvalidException("method error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

    private void doPatch(HttpRequest request) {
        if (request.isEqualsPath(AutobusApiController.AUTOBUSES + AutobusApiController.ID_ID + AutobusApiController.LINEAREGULAR)) {
            this.autobusApiController.updateLineaRegular(request.getPath(1), (LineaRegular) request.getBody());
        } else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

    private void doDelete(HttpRequest request) {
        if (request.isEqualsPath(ConductorApiController.CONDUCTORES + ConductorApiController.ID_ID)) {
            this.conductorApiController.delete(request.getPath(1));
        } else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }
}