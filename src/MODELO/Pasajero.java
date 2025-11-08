package MODELO;

import ENUMS.ROL;
import INTERFACE.ItoJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Pasajero extends Usuario implements ItoJson {
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
    //FALTAN METODOS
    @Override
    public JSONArray backup() throws JSONException //ESPECIFICO PARA LA CLASE GENERICA
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

    public void solicitarReserva(Habitaciones habitacion, Pasajero pasajero, Date fechaInico, Date fechaFin, Boolean estado, int cantidadPersonas){

        Recepcionista r=Administrador.getAdmin().obtenerRecepcionista();
        r.cargarReservaPendiente( habitacion, pasajero,  fechaInico,  fechaFin,  estado,  cantidadPersonas);
    }

}
