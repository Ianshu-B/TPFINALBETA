package MODELO;

import ENUMS.ROL;

public class Recepcionista extends Usuario {
    private String ID;

    public Recepcionista(String nombre, String documento, ROL rol, String username, String password, String ID) {
        super(nombre, documento, rol, username, password);
        this.ID = ID;
    }
    public Recepcionista() {
        super("", "", ROL.RECEPCIONISTA, "", "");
        this.ID = "";
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Recepcionista{" +
                super.toString() + "\n" +
                "ID='" + ID + "\n"  +
                '}';
    }

    //FALTAN METODOS ESPECIFICOS
}
