package api.apiControllers;

import api.entities.LineaRegular;
import api.businessController.AutobusBusinessController;
import api.dtos.AutobusDto;
import api.exceptions.ArgumentNotValidException;

public class AutobusApiController {

    public static final String AUTOBUSES = "/autobuses";

    public static final String ID_ID = "/{id}";

    public static final String LINEAREGULAR = "/lineaRegular";

    private AutobusBusinessController autobusBusinessController = new AutobusBusinessController();

    public String create(AutobusDto autobusDto) {
        this.validate(autobusDto, "autobusDto");
        this.validate(autobusDto.getCapacidad(), "AutobusDto capacidad");
        return this.autobusBusinessController.create(autobusDto);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is NULL");
        }
    }

    public void updateLineaRegular(String autobusId, LineaRegular lineaRegular){
        this.validate(lineaRegular, "lineaRegular");
        this.autobusBusinessController.updateLineaRegular(lineaRegular, autobusId);
    }
}
