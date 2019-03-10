package api.apiControllers;

import api.businessController.ConductorBusinessController;
import api.dtos.ConductorDto;
import api.exceptions.ArgumentNotValidException;

public class ConductorApiController {

    public static final String CONDUCTORES = "/conductores";

    public static final String ID_ID = "/{id}";

    private ConductorBusinessController conductorBusinessController = new ConductorBusinessController();

    public String create(ConductorDto conductorDto) {
        this.validate(conductorDto, "conductorDto");
        this.validate(conductorDto.getNombre(), "ConductorDto Nombre");
        return this.conductorBusinessController.create(conductorDto);
    }

    public void update(String id, ConductorDto conductorDto) {
        this.validate(conductorDto, "conductorDto");
        this.validate(conductorDto.getNombre(), "conductorDto Nombre");
        this.conductorBusinessController.update(id, conductorDto);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is NULL");
        }
    }

    public void delete(String id) {
        this.conductorBusinessController.delete(id);
    }
}