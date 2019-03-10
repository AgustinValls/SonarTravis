package api.dtos;

import api.entities.Conductor;
import api.entities.LineaRegular;

public class AutobusDto {

    private int capacidad;

    private Conductor conductor;

    private LineaRegular  lineaRegular;

    public AutobusDto(int capacidad, Conductor conductor){
        this.capacidad = capacidad;
        this.conductor = conductor;
        this.lineaRegular = LineaRegular.URBANA;
    }

    public AutobusDto(int capacidad, Conductor conductor, LineaRegular lineaRegular){
        this.capacidad = capacidad;
        this.conductor = conductor;
        this.lineaRegular = lineaRegular;
    }

    public int getCapacidad() {
        return capacidad;
    }

    @Override
    public String toString() {
        return "AutobusDto{" +
                "capacidad=" + capacidad +
                ", conductor=" + conductor +
                '}';
    }
}