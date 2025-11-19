package MODELO;

import ENUMS.ROL;
import EXCEPTIONS.elementoNuloException;
import EXCEPTIONS.elementoRepetidoException;
import EXCEPTIONS.listaUsuariosVacioException;
import INTERFACE.IJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public final class Pasajero extends Usuario implements IJson {
    private String origen; //PAIS DE LA PERSONA
    private String domicilio;

    public Pasajero(String nombre, String documento,  String username, String password, String origen, String domicilio) {
        super(nombre, documento,ROL.PASAJERO, username, password);
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
    public JSONObject toJson() throws JSONException
    {
        JSONObject jsonObject = super.toJson(); //Paso todo lo de su clase Padre que es Persona y sigo completando con los atributos especificos
        try {
            jsonObject.put("origen",this.origen);
            jsonObject.put("domicilio",this.domicilio);

        }catch (JSONException e)
        {
            e.printStackTrace();
        }

        return jsonObject;

    }
    public static Pasajero fromJson(JSONObject jsonObject) throws JSONException
    {
        Pasajero aux = new Pasajero();
        try {
            aux.setNombre(jsonObject.getString("nombre"));
            aux.setDocumento(jsonObject.getString("documento"));
            aux.setRol(ROL.valueOf(jsonObject.getString("rol")));
            aux.setUsername(jsonObject.getString("username"));
            aux.setPassword(jsonObject.getString("password"));
            aux.setDomicilio(jsonObject.getString("domicilio"));
            aux.setOrigen(jsonObject.getString("origen"));

        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        return aux;
    }
    //FALTAN METODOS
    @Override
    public JSONArray backup() throws JSONException, listaUsuariosVacioException //ESPECIFICO PARA LA CLASE GENERICA
    {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = super.backup();
        try {
            jsonObject.put("origen",this.origen);
            jsonObject.put("domicilio",this.domicilio);
            jsonArray.put(jsonObject);
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        return jsonArray;

    }

    public String solicitarReserva(Habitaciones habitacion, Pasajero pasajero, Date fechaInico, Date fechaFin, Boolean estado, int cantidadPersonas, ArrayList<String> extras) throws elementoRepetidoException, JSONException {


        String msj = Recepcionista.cargarReservaPendiente(habitacion, pasajero,  fechaInico,  fechaFin,  estado,  cantidadPersonas, extras);

        return msj;
    }

}
