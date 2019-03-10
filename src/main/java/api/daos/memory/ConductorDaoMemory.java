package api.daos.memory;

import java.util.HashMap;
import api.entities.Conductor;
import api.daos.ConductorDao;

public class ConductorDaoMemory extends GenericDaoMemory<Conductor> implements ConductorDao {

    public ConductorDaoMemory() {
        super(new HashMap<>());
    }

    @Override
    public String getId(Conductor conductor) {
        return conductor.getId();
    }

    @Override
    public void setId(Conductor conductor, String id) {
        conductor.setId(id);
    }
}

