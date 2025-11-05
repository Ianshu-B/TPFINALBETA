package MODELO;

import ENUMS.ROL;

import static ENUMS.ROL.RECEPCIONISTA;

public class Administrador extends Usuario{
    public Administrador(String nombre, String documento, ROL rol, String username, String password) {
        super(nombre, documento, rol, username, password);
    }
    public Administrador() { //COONSTRUCTOR VACIO
        super("", "", ROL.ADMINISTRADOR, "", "");
    }

    @Override
    public String toString() {
        return "Administrador{" +
                super.toString() + "\n" +
                '}';
    }

    //FALTAN METODOS
    public Pasajero crearPasajero(String nombre, String documento, ROL rol, String username, String password, String origen,String domicilio)
    {
        return new Pasajero(nombre,documento,rol,username,password,origen,domicilio);
    }
    


}
