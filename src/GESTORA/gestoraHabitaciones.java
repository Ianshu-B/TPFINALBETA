package GESTORA;

import EXCEPTIONS.*;
import INTERFACE.IJson;
import MODELO.*;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashSet;

public class gestoraHabitaciones implements IJson {
    private HashSet<Habitaciones> listaHabitaciones;

    public gestoraHabitaciones() {
        this.listaHabitaciones = new HashSet<>();
    }

    //AGREGAR
    public String agregarHabitacion(Habitaciones h) throws elementoNuloException, elementoInsertadoException, elementoRepetidoException{
        StringBuilder sb = new StringBuilder();
        if(h == null)
        {
            throw new elementoNuloException("No se permite agregar elementos nulos a las habitaciones!");
        }
        if(listaHabitaciones.contains(h))
        {
            throw new elementoRepetidoException("La habitacion ya se encuentra en la lista!");
        }
        if(listaHabitaciones.add(h))
        {
            sb.append("Habitacion agregada correctamente a la lista de HABITACIONES!").append("\n");
        }else
        {
            throw new elementoInsertadoException("No se pudo insertar la habitacion a la lista!");
        }
        return sb.toString();
    }

    //LISTAR
    public String listarHabitaciones(){
        StringBuilder sb = new StringBuilder();

        for(Habitaciones h : listaHabitaciones){
            sb.append(h.toString()).append("\n");
        }
        return sb.toString();
    }

    //BUSCAR
    public Habitaciones buscarHabitacion(Habitaciones habitacion){
        for(Habitaciones h : listaHabitaciones){
            if(h.equals(habitacion)){
                return h;
            }
        }
        return null;
    }

    //BUSCAR POR NRO HABITACION
    public Habitaciones buscarHabitacion(int nroHabitacion) throws NumeroNegativoException{
        if(nroHabitacion < 0){
            throw new NumeroNegativoException("Numero de habitacion no valido");
        }

        for(Habitaciones h : listaHabitaciones){
            if(h.getNumeroHabitacion() == nroHabitacion){
                return h;
            }
        }
        return null;
    }

    //ELIMINAR
    public String eliminarHabitacion(int nroHabitacion) throws elementoNuloException, elementoBorradoException, NumeroNegativoException{
        StringBuilder sb = new StringBuilder();
        if(nroHabitacion < 0){
            throw new NumeroNegativoException("Numero de habitacion negativo.");
        }
        Habitaciones aux = buscarHabitacion(nroHabitacion);
        if(aux == null)
        {
            throw new elementoNuloException("La habitacion a eliminar es nula!!.!");
        }
        if(listaHabitaciones.remove(aux))
        {
            sb.append("Habitacion borrada correctamente de la lista del HOTEL!").append("\n");
        }else
        {
            throw new elementoBorradoException("ERROR. no se pudo borrar la habitacion de la lista!");
        }
        return sb.toString();
    }

    @Override
    public JSONArray backup() throws JSONException, listaUsuariosVacioException //TOJSON de la generica
    {
        if(listaHabitaciones.isEmpty()){
            throw new listaUsuariosVacioException("Lista de habitaciones vacía, no se creará backup.");
        }

        JSONArray jsonArray = new JSONArray();
        try {
            for(Habitaciones h : listaHabitaciones)
            {
                jsonArray.put(h.backup());
            }
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        return jsonArray;
    }
}
