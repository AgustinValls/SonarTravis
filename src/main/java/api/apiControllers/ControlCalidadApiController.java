package api.apiControllers;

import api.dtos.ControlCalidadIdReadAllDto;
import api.businessController.ControlCalidadBusinessController;
import api.dtos.ControlCalidadDto;
import api.exceptions.ArgumentNotValidException;
import java.util.List;

public class ControlCalidadApiController {

    public static final String CONTROLCALIDADES = "/controlCalidades";

    private ControlCalidadBusinessController controlCalidadBusinessController = new ControlCalidadBusinessController();

    public String create(ControlCalidadDto controlCalidadDto) {
        this.validate(controlCalidadDto, "controlCalidadDto");
        this.validate(controlCalidadDto.getTexto(), "ControlCalidadDto nombre");
        this.validate(controlCalidadDto.getValoracion(), "ControlCalidadDto valoracion");
        return this.controlCalidadBusinessController.create(controlCalidadDto);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is NULL");
        }
    }

    public List<ControlCalidadIdReadAllDto> readAll() {
        return this.controlCalidadBusinessController.readAll();
    }
}