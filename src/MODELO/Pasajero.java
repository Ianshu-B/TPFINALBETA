package MODELO;

import ENUMS.ROL;

public class Pasajero extends Usuario{
    private String origen; //PAIS DE LA PERSONA
    private String domicilio;

    public Pasajero(String nombre, String documento, ROL rol, String username, String password, String origen, String domicilio) {
        super(nombre, documento, rol, username, password);
        this.origen = origen;
        this.domicilio = domicilio;
    }
    public Pasajero() {
        super("", "", ROL.PASAJERO, "", "");
        this.origen = "";
        this.domicilio = "";
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return "Pasajero{" +
                super.toString() + "\n" +
                "origen='" + origen + "\n" +
                ", domicilio='" + domicilio + "\n" +
                '}';
    }
    //FALTAN METODOS
}
