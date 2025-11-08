package GESTORA;

import EXCEPTIONS.elementoBorradoException;
import EXCEPTIONS.elementoInsertadoException;
import EXCEPTIONS.elementoNuloException;
import EXCEPTIONS.elementoRepetidoException;
import INTERFACE.ItoJson;
import MODELO.Habitaciones;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class gestoraHotel <T extends ItoJson> implements ItoJson {
    private ArrayList<T> listaGeneral;

    public gestoraHotel() {
        this.listaGeneral = new ArrayList<>();
    }

    //AGREGAR
    public String agregarElemento(T t) throws elementoNuloException, elementoInsertadoException, elementoRepetidoException
    {
        StringBuilder sb = new StringBuilder();
        if(t == null)
        {
            throw new elementoNuloException("No se permite agregar elementos nulos al Hotel!");
        }
        if(listaGeneral.contains(t))
        {
            throw new elementoRepetidoException("El elemento ya se encuentra en la lista!");
        }
        if(listaGeneral.add(t))
        {
            sb.append("Elemento agregado correctamente a la lista del HOTEL!").append("\n");
        }else
        {
            throw new elementoInsertadoException("No se pudo insertar el elemento a la lista del HOTEL!");
        }
        return sb.toString();

    }
    //LISTAR
    public String listarElementos()
    {
        StringBuilder sb = new StringBuilder();
        for(T t : listaGeneral)
        {
            sb.append(t.toString()).append("\n");
        }
        return sb.toString();
    }

    //BUSCAR
    public T buscarElemento(T t)
    {
        for(T tt : listaGeneral)
        {
            if(tt.equals(t))
            {
                return tt;
            }
        }
        return null;
    }

    //ELIMINAR
    public String eliminarElemento(T t) throws elementoNuloException, elementoBorradoException
    {
        StringBuilder sb = new StringBuilder();
        T aux = buscarElemento(t);
        if(aux == null)
        {
        throw new elementoNuloException("El elemento a eliminar es nulo!!.!");
        }
        if(listaGeneral.remove(aux))
        {
            sb.append("Elemento borrado correctamente de la lista del HOTEL!").append("\n");
        }else
        {
            throw new elementoBorradoException("ERROR. no se pudo borrar el elemento de la lista del HOTEL!");
        }
        return sb.toString();
    }


    @Override
    public JSONArray backup() throws JSONException //TOJSON de la generica
    {
        JSONArray jsonArray = new JSONArray();
        try {
            for(T t : listaGeneral)
            {
                jsonArray.put(t.backup());
            }
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        return jsonArray;

    }
    //AGREGAR EXPECTIONS
    public Habitaciones buscarHabitacionXnumero(int numeroHabitacion){

        for(T e:listaGeneral){
            if (e instanceof Habitaciones){
                Habitaciones h=(Habitaciones)e;
                if(h.getNumeroHabitacion()==numeroHabitacion){
                    return h;
                }
            }
        }
        return null;
    }

}
