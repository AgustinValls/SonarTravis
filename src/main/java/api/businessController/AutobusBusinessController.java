package api.businessController;

import api.exceptions.NotFoundException;
import api.entities.LineaRegular;
import api.daos.DaoFactory;
import api.dtos.AutobusDto;
import api.entities.Autobus;

public class AutobusBusinessController {

    public String create(AutobusDto autobusDto) {
        Autobus autobus = new Autobus (autobusDto.getCapacidad(), null);
        DaoFactory.getFactory().getAutobusDao().save(autobus);
        return autobus.getId();
    }

    public void updateLineaRegular(LineaRegular lineaRegular,String autobusId) {
        Autobus autobus  = DaoFactory.getFactory().getAutobusDao().read(autobusId)
                .orElseThrow(() -> new NotFoundException("Autobus (" + autobusId + ")"));
        autobus.setLineaRegular(lineaRegular);
        DaoFactory.getFactory().getAutobusDao().save(autobus);
    }
}