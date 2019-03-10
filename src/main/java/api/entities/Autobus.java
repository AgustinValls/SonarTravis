package api.entities;

import java.util.Set;
import java.util.HashSet;

public class Autobus {

    private String id;
    private int capacidad;
    private Conductor conductor;
    private LineaRegular lineaRegular;
    private Set<Pasajero> pasajeros;

    public Autobus(int capacidad, Conductor conductor){
        this.capacidad = capacidad;
        this.conductor = conductor;
    }

    public Autobus(int capacidad, Conductor conductor, Set<Pasajero> pasajeros){
        this(capacidad, conductor);
        this.pasajeros = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLineaRegular(LineaRegular lineaRegular) {
        this.lineaRegular = lineaRegular;
    }

    @Override
    public String toString() {
        return "Autobus{" +
                "id='" + id + '\'' +
                ", capacidad=" + capacidad +
                ", conductor=" + conductor +
                ", lineaRegular=" + lineaRegular +
                ", pasajeros=" + pasajeros +
                '}';
    }
}