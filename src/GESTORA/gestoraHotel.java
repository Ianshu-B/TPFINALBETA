package GESTORA;

import EXCEPTIONS.elementoBorradoException;
import EXCEPTIONS.elementoInsertadoException;
import EXCEPTIONS.elementoNuloException;
import INTERFACE.ItoJson;
import org.json.JSONArray;

import java.util.ArrayList;

public class gestoraHotel <T> {
    private ArrayList<T> listaGeneral;

    public gestoraHotel() {
        this.listaGeneral = new ArrayList<>();
    }

    //AGREGAR
    public String agregarElemento(T t) throws elementoNuloException, elementoInsertadoException
    {
        StringBuilder sb = new StringBuilder();
        if(t == null)
        {
            throw new elementoNuloException("No se permite agregar elementos nulos al Hotel!");
        }
        if(listaGeneral.add(t))
        {
            sb.append("Elemento agregado correctamente!").append("\n");
        }else
        {
            throw new elementoInsertadoException("No se pudo insertar el elemento!");
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
        throw new elementoNuloException("ERROR. ELEMENTO NULO!");
        }
        if(listaGeneral.remove(aux))
        {
            sb.append("Elemento borrado correctamente!").append("\n");
        }else
        {
            throw new elementoBorradoException("ERROR. no se pudo borrar el elemento!");
        }
        return sb.toString();
    }

    /*
    @Override
    public JSONArray backup()
    {

    }

     */


}
