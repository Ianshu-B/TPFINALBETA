package MODELO;

import ENUMS.estadoHabitacion;
import ENUMS.tamanioHabitacion;
import INTERFACE.ItoJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class habitacionDeluxe extends Habitaciones implements ItoJson {

    private boolean jacuzzi; //True tiene, false no tiene. NO SE ME OCURRIO OTRA COSA AAAAAAAAA
    private boolean hidromasaje; //True tiene, false no tiene. NO SE ME OCURRIO OTRA COSA AAAAAAAAA

    public habitacionDeluxe(int numeroHabitacion, ENUMS.estadoHabitacion estadoHabitacion, ENUMS.tamanioHabitacion tamanioHabitacion, boolean jacuzzi, boolean hidromasaje) {
        super(numeroHabitacion, estadoHabitacion, tamanioHabitacion);
        this.jacuzzi = jacuzzi;
        this.hidromasaje = hidromasaje;
    }
    public habitacionDeluxe() {
        super(0, ENUMS.estadoHabitacion.LIBRE, ENUMS.tamanioHabitacion.GRANDE);
        this.jacuzzi = true; //Por defecto
        this.hidromasaje = true; //Por defecto
    }

    public boolean isJacuzzi() { //Supongo que el is es por el tipo de atributo
        return jacuzzi;
    }

    public void setJacuzzi(boolean jacuzzi) {
        this.jacuzzi = jacuzzi;
    }

    public boolean isHidromasaje() { //Supongo que el is es por el tipo de atributo
        return hidromasaje;
    }

    public void setHidromasaje(boolean hidromasaje) {
        this.hidromasaje = hidromasaje;
    }

    @Override
    public String toString() {
        return "habitacionDeluxe{" + "\n" +
                super.toString() + "\n" +
                "jacuzzi=" + jacuzzi + "\n" +
                ", hidromasaje=" + hidromasaje + "\n" +
                '}';
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
}
