package MODELO;

import INTERFACE.IJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class habitacionDeluxe extends Habitaciones implements IJson {

    public habitacionDeluxe(int numeroHabitacion, ENUMS.estadoHabitacion estadoHabitacion, ENUMS.tamanioHabitacion tamanioHabitacion) {
        super(numeroHabitacion, estadoHabitacion, tamanioHabitacion);
        this.costoHabitacion=getCostoHabitacion() * 6;
    }
    public habitacionDeluxe() {
        super(0, ENUMS.estadoHabitacion.LIBRE, ENUMS.tamanioHabitacion.GRANDE);
        this.costoHabitacion=getCostoHabitacion() * 6;
    }

    @Override
    public String toString() {
        return "\n" +
                "🌟 HABITACIÓN DELUXE 🌟\n" +
                super.toString();
    }

    //Faltan los metodos especificos
    @Override
    public JSONArray backup() throws JSONException
    {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray = super.backup();
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public JSONObject toJson(){
        JSONObject object=super.toJson();

        return object;
    }
}
