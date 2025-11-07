package MODELO;
import ENUMS.estadoHabitacion;
import ENUMS.tamanioHabitacion;
import EXCEPTIONS.elementoNuloException;
import EXCEPTIONS.pasajeroRepetidoException;
import INTERFACE.ItoJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Objects;

public abstract class Habitaciones implements ItoJson {
    protected int numeroHabitacion;
    protected estadoHabitacion estadoHabitacion;
    protected tamanioHabitacion tamanioHabitacion;
    protected HashSet<Pasajero> listaOcupantesHabitacion;

    public Habitaciones(int numeroHabitacion, ENUMS.estadoHabitacion estadoHabitacion, ENUMS.tamanioHabitacion tamanioHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
        this.estadoHabitacion = estadoHabitacion;
        this.tamanioHabitacion = tamanioHabitacion;
        this.listaOcupantesHabitacion = new HashSet<>();
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public ENUMS.estadoHabitacion getEstadoHabitacion() {
        return estadoHabitacion;
    }

    public void setEstadoHabitacion(ENUMS.estadoHabitacion estadoHabitacion) {
        this.estadoHabitacion = estadoHabitacion;
    }

    public ENUMS.tamanioHabitacion getTamanioHabitacion() {
        return tamanioHabitacion;
    }

    public void setTamanioHabitacion(ENUMS.tamanioHabitacion tamanioHabitacion) {
        this.tamanioHabitacion = tamanioHabitacion;
    }

    public String agregarOcupante(Pasajero p) throws elementoNuloException, pasajeroRepetidoException //El retorno se puede cambiar por un bool en caso de necesitar
    {
        StringBuilder sb = new StringBuilder();
        if(p == null)
        {
            throw new elementoNuloException("No se permite ingresar pasajeros nulos a la habitacion!");
        }

        if(listaOcupantesHabitacion.add(p))
        {
            sb.append("Pasajero agregado correctamente a la habitacion!").append("\n");
        }else
        {
            throw new pasajeroRepetidoException("Pasajero ya existente en la lista de la habitacion!");
        }
        return sb.toString();

    }
    public String listarOcupantes()
    {
        StringBuilder sb = new StringBuilder();
        for(Pasajero p : listaOcupantesHabitacion)
        {
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }

    public Pasajero buscarPasajeroHabitacion(Pasajero p) throws elementoNuloException
    {
        if(p == null)
        {
            throw  new elementoNuloException("Se ingreso un pasajero nulo a buscar!");
        }else
        {
            for(Pasajero pp : listaOcupantesHabitacion)
            {
                if(pp.equals(p))
                {
                    return pp;
                }
            }
        }
        return null;
    }
    public String eliminarPasajeroHabitacion(Pasajero p) throws elementoNuloException
    {
        StringBuilder sb = new StringBuilder();
        Pasajero pAux = buscarPasajeroHabitacion(p);
        if(pAux != null)
        {
            listaOcupantesHabitacion.remove(pAux);
            sb.append("Pasajero eliminado correctamente de la habitacion!").append("\n");
        }else
        {
            sb.append("No se pudo eliminar el pasajero de la habitacion!").append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Habitaciones{" + "\n" +
                "numeroHabitacion=" + numeroHabitacion + "\n" +
                ", estadoHabitacion=" + estadoHabitacion + "\n" +
                ", tamanioHabitacion=" + tamanioHabitacion + "\n" +
                ", listaOcupantesHabitacion=" + listarOcupantes() + "\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Habitaciones that)) return false;
        return numeroHabitacion == that.numeroHabitacion;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numeroHabitacion);
    }
    @Override
    public JSONArray backup() throws JSONException
    {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArrayAux = new JSONArray();
        JSONArray jsonArrayAuxFinal = new JSONArray();
        try {
            jsonObject.put("numeroHabitacion",this.numeroHabitacion);
            jsonObject.put("estadoHabitacion",this.estadoHabitacion);
            jsonObject.put("tamanioHabitacion",this.tamanioHabitacion);
            for(Pasajero p : listaOcupantesHabitacion)
            {
                jsonArrayAux.put(p.toJson());
            }
            jsonObject.put("listaOcupantesHabitacion",jsonArrayAux);
            jsonArrayAuxFinal.put(jsonObject);

        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        return jsonArrayAuxFinal;

    }
}

