package MODELO;

import ENUMS.ROL;
import INTERFACE.ItoJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public abstract class Usuario extends Persona implements ItoJson {
    protected ROL rol;
    protected String username;
    protected String password;

    public Usuario(String nombre, String documento, ROL rol, String username, String password) {
        super(nombre, documento);
        this.rol = rol;
        this.username = username;
        this.password = password;
    }
    public Usuario() {
        super("", "");
        this.rol = ROL.PASAJERO; //POR DEFECTO
        this.username = "";
        this.password = "";
    }

    public ROL getRol() {
        return rol;
    }

    public void setRol(ROL rol) {
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ROL retornarRol() //Metodo retornarRol para gestionar los multiples usuarios sin filtrar por instanceOf constantemente. Nos sirve a futuro
    {
        return rol;
    }

    //FALTA QUIZAS SOBREESCRIBIR EL EQUALS POR PASSWORD ?? PERO TEORICAMENTE YA COMPARTE EL EQUALS DE SU SUPERCLASE QUE ES PERSONA.


    @Override
    public String toString() {
        return "Usuario{" + "\n" +
                super.toString() + "\n" +
                "rol=" + rol + "\n" +
                ", username='" + username + "\n" +
                ", password='" + password  + "\n" +
                '}';
    }

    public JSONObject toJson() throws JSONException
    {
        JSONObject jsonObject = super.toJson(); //Paso todo lo de su clase Padre que es Persona y sigo completando con los atributos especificos
        try {
            jsonObject.put("rol",this.rol);
            jsonObject.put("username",this.username);
            jsonObject.put("password",this.password);
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        return jsonObject;

    }

    @Override
    public JSONArray backup() throws JSONException //METODO ESPECIFICO PARA LA CLASE GENERICA
    {
        JSONArray jsonArray = super.backup();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("rol",this.rol);
            jsonObject.put("username",this.username);
            jsonObject.put("password",this.password);
            jsonArray.put(jsonObject);
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        return jsonArray;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Usuario usuario)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(password, usuario.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), password);
    }




}
