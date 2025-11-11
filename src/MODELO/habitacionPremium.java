package MODELO;

import ENUMS.estadoHabitacion;
import ENUMS.tamanioHabitacion;
import INTERFACE.ItoJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class habitacionPremium extends Habitaciones implements ItoJson {
    private boolean miniBar;
    private boolean spa;
    private boolean vistaMar;

    public habitacionPremium(int numeroHabitacion, ENUMS.estadoHabitacion estadoHabitacion, ENUMS.tamanioHabitacion tamanioHabitacion, boolean miniBar, boolean spa, boolean vistaMar) {
        super(numeroHabitacion, estadoHabitacion, tamanioHabitacion);
        this.miniBar = miniBar;
        this.spa = spa;
        this.vistaMar = vistaMar;
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
}
