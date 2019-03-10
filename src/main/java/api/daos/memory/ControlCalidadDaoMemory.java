package api.daos.memory;

import java.util.HashMap;
import api.entities.ControlCalidad;
import api.daos.ControlCalidadDao;

public class ControlCalidadDaoMemory extends GenericDaoMemory<ControlCalidad> implements ControlCalidadDao {

    public ControlCalidadDaoMemory() {
        super(new HashMap<>());
    }

    @Override
    public String getId(ControlCalidad controlCalidad) {
        return controlCalidad.getId();
    }

    @Override
    public void setId(ControlCalidad controlCalidad, String id) {
        controlCalidad.setId(id);
    }
}

