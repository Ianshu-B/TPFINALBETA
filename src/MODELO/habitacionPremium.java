package MODELO;

import INTERFACE.IJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class habitacionPremium extends Habitaciones implements IJson {
    private boolean miniBar;
    private boolean spa;
    private boolean vistaMar;

    public habitacionPremium(int numeroHabitacion, ENUMS.estadoHabitacion estadoHabitacion, ENUMS.tamanioHabitacion tamanioHabitacion) {
        super(numeroHabitacion, estadoHabitacion, tamanioHabitacion);
        this.miniBar = false;
        this.spa = false;
        this.vistaMar = false;
    }
    public habitacionPremium() {
        super(0, ENUMS.estadoHabitacion.LIBRE, ENUMS.tamanioHabitacion.GRANDE); //Por defecto
        this.miniBar = false; //Por defecto
        this.costoHabitacion=getCostoHabitacion() * 4;
    }

    public boolean isMiniBar() { //Supongo que el is es por el tipo de atributo
        return miniBar;
    }

    public void setMiniBar(boolean miniBar) { //Supongo que el is es por el tipo de atributo
        this.miniBar = miniBar;
    }

    public boolean isSpa() {
        return spa;
    }

    public void setSpa(boolean spa) {
        this.spa = spa;
    }

    public boolean isVistaMar() {
        return vistaMar;
    }

    public void setVistaMar(boolean vistaMar) {
        this.vistaMar = vistaMar;
    }

    @Override
    public String toString() {
        return "habitacionPremium{" +
                super.toString() + "\n" +
                "miniBar=" + miniBar + "\n" +
                '}';
    }
    //FALTAN METODOS ESPECIFICOS
    @Override
    public JSONArray backup() throws JSONException
    {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = super.backup();
        try {
            jsonObject.put("miniBar",this.miniBar);
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
            object.put("miniBar",this.miniBar);
            object.put("spa",this.spa);
            object.put("vistaMar",this.vistaMar);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return object;
    }
}
