package MODELO;

import ENUMS.ROL;
import EXCEPTIONS.elementoNuloException;

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

    //METODOS CREARUSUARIO
    public Pasajero crearPasajero(String nombre, String documento, ROL rol, String username, String password, String origen,String domicilio) throws elementoNuloException
    {
        if(nombre == null || documento == null | username == null | password == null | origen == null | documento == null)
        {
            throw  new elementoNuloException("Elemento o mas de un elemento nulo ingresado. No esta permitido");
        }
        return new Pasajero(nombre,documento,rol,username,password,origen,domicilio);
    }
    public Recepcionista crearRecepcionista(String nombre, String documento, ROL rol, String username, String password, String ID) throws elementoNuloException
    {
        if(nombre == null || documento == null | username == null | password == null | ID == null)
        {
            throw new elementoNuloException("Elemento o mas de un elemento nulo ingresado. No esta permitido");
        }
        return new Recepcionista(nombre, documento, rol, username, password, ID);
    }
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------



}
