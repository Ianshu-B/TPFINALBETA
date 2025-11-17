package MODELO;

import INTERFACE.IJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class habitacionMedium extends Habitaciones implements IJson {
    private boolean cajaSeguridad;
    private boolean desayunoBuffet;
    private boolean roomService;

    public habitacionMedium(int numeroHabitacion, ENUMS.estadoHabitacion estadoHabitacion, ENUMS.tamanioHabitacion tamanioHabitacion) {
        super(numeroHabitacion, estadoHabitacion, tamanioHabitacion);
        this.cajaSeguridad = false;
        this.desayunoBuffet = false;
        this.roomService = false;
        this.costoHabitacion=getCostoHabitacion()*2;
    }
    public habitacionMedium() {
        super(0, ENUMS.estadoHabitacion.LIBRE, ENUMS.tamanioHabitacion.GRANDE); //Por defecto
        this.cajaSeguridad = false; //Poor defecto
    }

    public boolean isCajaSeguridad() { //Supongo que el is es por el tipo de atributo
        return cajaSeguridad;
    }

    public void setCajaSeguridad(boolean cajaSeguridad) {  //Supongo que el is es por el tipo de atributo
        this.cajaSeguridad = cajaSeguridad;
    }

    public boolean isDesayunoBuffet() {
        return desayunoBuffet;
    }

    public void setDesayunoBuffet(boolean desayunoBuffet) {
        this.desayunoBuffet = desayunoBuffet;
    }

    public boolean isRoomService() {
        return roomService;
    }

    public void setRoomService(boolean roomService) {
        this.roomService = roomService;
    }

    @Override
    public String toString() {
        return "habitacionMedium{" + "\n" +
                "cajaSeguridad=" + cajaSeguridad + "\n" +
                '}';
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
}
