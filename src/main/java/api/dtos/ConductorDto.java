package api.dtos;

public class ConductorDto {

    private String nombre;

    private String telefono;

    public ConductorDto(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public String toString() {
        return "ConductorDto{" +
                "nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}