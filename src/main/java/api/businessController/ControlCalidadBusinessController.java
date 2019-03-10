package api.businessController;

import api.daos.DaoFactory;
import api.dtos.ControlCalidadDto;
import api.entities.ControlCalidad;
import api.dtos.ControlCalidadIdReadAllDto;

import java.util.List;
import java.util.ArrayList;

public class ControlCalidadBusinessController {

    public String create(ControlCalidadDto controlCalidadDto) {
        ControlCalidad controlCalidad = new ControlCalidad(controlCalidadDto.getTexto(), null);
        DaoFactory.getFactory().getControlCalidadDao().save(controlCalidad);
        return controlCalidad.getId();
    }

    public List<ControlCalidadIdReadAllDto> readAll() {
        List<ControlCalidad> controlCalidades = DaoFactory.getFactory().getControlCalidadDao().findAll();
        List<ControlCalidadIdReadAllDto> controlCalidadIdReadAllDto = new ArrayList<>();
        for (ControlCalidad  controlCalidad  : controlCalidades ) {
            controlCalidadIdReadAllDto .add(new ControlCalidadIdReadAllDto (controlCalidad));
        }
        return controlCalidadIdReadAllDto;
    }
}