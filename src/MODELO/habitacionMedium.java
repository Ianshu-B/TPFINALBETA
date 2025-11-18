package MODELO;

import INTERFACE.IJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class habitacionMedium extends Habitaciones implements IJson {

    public habitacionMedium(int numeroHabitacion, ENUMS.estadoHabitacion estadoHabitacion, ENUMS.tamanioHabitacion tamanioHabitacion) {
        super(numeroHabitacion, estadoHabitacion, tamanioHabitacion);
        this.costoHabitacion=getCostoHabitacion()*2;
    }
    public habitacionMedium() {
        super(0, ENUMS.estadoHabitacion.LIBRE, ENUMS.tamanioHabitacion.GRANDE); //Por defecto
        this.costoHabitacion=getCostoHabitacion()*2;
    }



    @Override
    public String toString() {
        return "habitacionMedium{" +
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
