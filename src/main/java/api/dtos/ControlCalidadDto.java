package api.dtos;

public class ControlCalidadDto {

    private String texto;

    private Boolean valoracion;

    public ControlCalidadDto(String texto, Boolean valoracion){
        this.texto  = texto ;
        this.valoracion = valoracion;
    }

    public String getTexto() {
        return texto;
    }

    public Boolean getValoracion() {
        return valoracion;
    }

    @Override
    public String toString() {
        return "ControlCalidadDto{" +
                "texto='" + texto + '\'' +
                ", valoracion=" + valoracion +
                '}';
    }
}