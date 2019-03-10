package api.businessController;

import api.exceptions.NotFoundException;
import api.daos.DaoFactory;
import api.dtos.ConductorDto;
import api.entities.Conductor;

public class ConductorBusinessController {

    public String create(ConductorDto conductorDto) {
        Conductor conductor = new Conductor(conductorDto.getNombre(), null);
        DaoFactory.getFactory().getConductorDao().save(conductor);
        return conductor.getId();
    }
    public void update(String id, ConductorDto conductorDto) {
        Conductor conductor = DaoFactory.getFactory().getConductorDao().read(id)
                .orElseThrow(() -> new NotFoundException("Conductor id: " + id));
        conductor.setNombre(conductorDto.getNombre());
        conductor.setTelefono(conductorDto.getTelefono());
        DaoFactory.getFactory().getConductorDao().save(conductor);
    }

    public void delete(String id) {
        DaoFactory.getFactory().getConductorDao().deleteById(id);
    }
}
