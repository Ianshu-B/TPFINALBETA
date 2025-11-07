package MODELO;

import ENUMS.estadoHabitacion;
import ENUMS.tamanioHabitacion;
import INTERFACE.ItoJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class habitacionEstandar extends Habitaciones implements ItoJson {
    //Sin atributos especificos
    public habitacionEstandar(int numeroHabitacion, ENUMS.estadoHabitacion estadoHabitacion, ENUMS.tamanioHabitacion tamanioHabitacion) {
        super(numeroHabitacion, estadoHabitacion, tamanioHabitacion);
        this.costoHabitacion=getCostoHabitacion();
    }
    public habitacionEstandar() {
        super(0, ENUMS.estadoHabitacion.LIBRE, ENUMS.tamanioHabitacion.GRANDE); //Por defecto
    }

    @Override
    public String toString() {
        return "habitacionEstandar{" +
                super.toString() + "\n" +

                '}';
    }
    //FALTAN LOS METODOS ESPECIFICOS

    @Override
    public JSONArray backup() throws JSONException
    {
        JSONArray jsonArray = super.backup();
        return jsonArray;
    }
}
