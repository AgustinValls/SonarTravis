package api.dtos;

import api.entities.ControlCalidad;

public class ControlCalidadIdReadAllDto {

    private String id;
    private String texto;
    private Boolean valoracion;

    public ControlCalidadIdReadAllDto (ControlCalidad controlCalidad){
        this.id = controlCalidad.getId();
        this.texto = controlCalidad.getTexto();
        this.valoracion = controlCalidad.getValoracion();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ControlCalidadIdReadAllDto{" +
                "id='" + id + '\'' +
                ", texto='" + texto + '\'' +
                ", valoracion=" + valoracion +
                '}';
    }
}