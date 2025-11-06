package MODELO;

import ENUMS.ROL;
import org.json.JSONException;
import org.json.JSONObject;

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
    public JSONObject toJson() throws JSONException
    {
        JSONObject jsonObject = super.toJson(); //Paso todo lo de su clase Padre que es Persona y sigo completando con los atributos especificos
        try {
            jsonObject.put("ID",this.ID);

        }catch (JSONException e)
        {
            e.printStackTrace();
        }

        return jsonObject;

    }
    //FALTAN METODOS ESPECIFICOS
}
