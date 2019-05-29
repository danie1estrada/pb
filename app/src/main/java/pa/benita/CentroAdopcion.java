package pa.benita;

public class CentroAdopcion {

    public String clave_cadopcion, nombre_ca, direccion_ca, comentarios;
    public int tiempo_eo, capacidad;

    public CentroAdopcion(String clave_cadopcion, String nombre_ca, String direccion_ca, String comentarios, int tiempo_eo, int capacidad) {
        this.clave_cadopcion = clave_cadopcion;
        this.nombre_ca = nombre_ca;
        this.direccion_ca = direccion_ca;
        this.comentarios = comentarios;
        this.tiempo_eo = tiempo_eo;
        this.capacidad = capacidad;
    }
}
