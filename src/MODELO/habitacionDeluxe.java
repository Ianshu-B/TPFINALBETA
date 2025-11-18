package MODELO;

import INTERFACE.IJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class habitacionDeluxe extends Habitaciones implements IJson {

    public habitacionDeluxe(int numeroHabitacion, ENUMS.estadoHabitacion estadoHabitacion, ENUMS.tamanioHabitacion tamanioHabitacion) {
        super(numeroHabitacion, estadoHabitacion, tamanioHabitacion);
        this.costoHabitacion=getCostoHabitacion() * 6;
    }
    public habitacionDeluxe() {
        super(0, ENUMS.estadoHabitacion.LIBRE, ENUMS.tamanioHabitacion.GRANDE);
    }

    @Override
    public String toString() {
        return "habitacionDeluxe{" + "\n" +
                super.toString();
    }

    //Faltan los metodos especificos
    @Override
    public JSONArray backup() throws JSONException
    {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = super.backup();
        try {
            jsonObject.put("jacuzzi",this.jacuzzi);
            jsonObject.put("hidromasaje",this.hidromasaje);
            jsonArray.put(jsonObject);
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public JSONObject toJson(){
        JSONObject object=super.toJson();

        try {
            object.put("hidromasaje",this.hidromasaje);
            object.put("jacuzzi",this.jacuzzi);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
return object;
    }
}
