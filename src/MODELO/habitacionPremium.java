package MODELO;

import INTERFACE.IJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class habitacionPremium extends Habitaciones implements IJson {

    public habitacionPremium(int numeroHabitacion, ENUMS.estadoHabitacion estadoHabitacion, ENUMS.tamanioHabitacion tamanioHabitacion) {
        super(numeroHabitacion, estadoHabitacion, tamanioHabitacion);
        this.costoHabitacion=getCostoHabitacion() * 4;
    }
    public habitacionPremium() {
        super(0, ENUMS.estadoHabitacion.LIBRE, ENUMS.tamanioHabitacion.GRANDE); //Por defecto
        this.costoHabitacion=getCostoHabitacion() * 4;
    }



    @Override
    public String toString() {
        return "habitacionPremium{" +
                super.toString();
    }
    //FALTAN METODOS ESPECIFICOS
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
