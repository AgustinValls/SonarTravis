package api.entities;

public class ControlCalidad {

    private String id;

    private String texto;

    private Boolean valoracion;

    public ControlCalidad (String texto, Boolean valoracion){
        this.texto = texto;
        this.valoracion = valoracion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Boolean getValoracion() {
        return valoracion;
    }

    public void setValoracion(Boolean valoracion) {
        this.valoracion = valoracion;
    }

    @Override
    public String toString() {
        return "ControlCalidad{" +
                "id='" + id + '\'' +
                ", texto='" + texto + '\'' +
                ", valoracion=" + valoracion +
                '}';
    }
}
