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
    JSONArray jsonArray = super.backup();
    try {
        jsonObject.put("cajaSeguridad",this.cajaSeguridad);
        jsonArray.put(jsonArray);
    }catch (JSONException e)
    {
        e.printStackTrace();
    }
    return jsonArray;
}


    public JSONObject toJson(){

        JSONObject object=super.toJson();

        try {
            object.put("cajaSeguridad",this.cajaSeguridad);
            object.put("desayunoBuffet",this.desayunoBuffet);
            object.put("roomService",this.roomService);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return object;
    }

}
