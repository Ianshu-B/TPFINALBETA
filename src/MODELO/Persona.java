package MODELO;

import EXCEPTIONS.listaUsuariosVacioException;
import INTERFACE.IJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public abstract class Persona implements IJson {
    protected String nombre;
    protected String documento;

    public Persona(String nombre, String documento) {
        this.nombre = nombre;
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    //EQUALS SOBREESCRITO POR DNI
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(documento, persona.documento);
    }
    //HASHCODE SOBREESCRITO POR DNI
    @Override
    public int hashCode() {
        return Objects.hash(documento);
    }

    @Override
    public String toString() {
        return "Nombre:    " + this.nombre + "\n" +
                "Documento: " + this.documento;
    }


    public JSONObject toJson() throws JSONException
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("nombre",this.nombre);
            jsonObject.put("documento",this.documento);
        }catch (JSONException e)
        {
            e.printStackTrace();
        }

        return jsonObject;

    }
    @Override
    public JSONArray backup() throws JSONException, listaUsuariosVacioException //ESPECIFICO PARA LA CLASE GENERICA
    {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            jsonObject.put("nombre",this.nombre);
            jsonObject.put("documento",this.documento);
            jsonArray.put(jsonObject);
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        return jsonArray;
    }




}
