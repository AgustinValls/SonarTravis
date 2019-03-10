package api.daos.memory;

import java.util.HashMap;
import api.entities.Autobus;
import api.daos.AutobusDao;

public class AutobusDaoMemory extends GenericDaoMemory<Autobus > implements AutobusDao {

    public AutobusDaoMemory() {
        super(new HashMap<>());
    }

    @Override
    public String getId(Autobus  autobus ) {
        return autobus .getId();
    }

    @Override
    public void setId(Autobus autobus , String id) {
        autobus.setId(id);
    }
}

