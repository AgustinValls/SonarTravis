package api.entities;

import java.time.LocalDate;

public class Pasajero {

    private String id;
    private LocalDate fechaViaje;
    private Boolean discapacidad;

    public Pasajero(LocalDate fechaViaje, Boolean discapacidad){
        this.fechaViaje = fechaViaje;
        this.discapacidad = discapacidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFechaViaje() {
        return fechaViaje;
    }

    public void setFechaViaje(LocalDate fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public Boolean getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(Boolean discapacidad) {
        this.discapacidad = discapacidad;
    }

    @Override
    public String toString() {
        return "Pasajero{" +
                "id='" + id + '\'' +
                ", fechaViaje=" + fechaViaje +
                ", discapacidad=" + discapacidad +
                '}';
    }
}
