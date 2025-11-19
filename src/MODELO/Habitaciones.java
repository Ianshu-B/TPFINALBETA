package MODELO;
import ENUMS.estadoHabitacion;
import ENUMS.tamanioHabitacion;
import EXCEPTIONS.elementoNuloException;
import EXCEPTIONS.pasajeroRepetidoException;
import INTERFACE.IJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Objects;

public abstract class Habitaciones implements IJson {
    protected int numeroHabitacion;
    protected estadoHabitacion estadoHabitacion;
    protected tamanioHabitacion tamanioHabitacion;
    protected HashSet<Pasajero> listaOcupantesHabitacion;
    protected double costoHabitacion;

    public Habitaciones(int numeroHabitacion, estadoHabitacion estadoHabitacion, tamanioHabitacion tamanioHabitacion ) {
        this.numeroHabitacion = numeroHabitacion;
        this.estadoHabitacion = estadoHabitacion;
        this.tamanioHabitacion = tamanioHabitacion;
        this.listaOcupantesHabitacion = new HashSet<>();
        this.costoHabitacion=100;
    }

    public double getCostoHabitacion() {
        return costoHabitacion;
    }

    public void setCostoHabitacion(double costoHabitacion) {
        this.costoHabitacion = costoHabitacion;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public estadoHabitacion getEstadoHabitacion() {
        return estadoHabitacion;
    }

    public void setEstadoHabitacion(estadoHabitacion estadoHabitacion) {
        this.estadoHabitacion = estadoHabitacion;
    }

    public tamanioHabitacion getTamanioHabitacion() {
        return tamanioHabitacion;
    }

    public void setTamanioHabitacion(tamanioHabitacion tamanioHabitacion) {
        this.tamanioHabitacion = tamanioHabitacion;
    }

    public void setListaOcupantesHabitacion(HashSet<Pasajero> listaOcupantesHabitacion) {
        this.listaOcupantesHabitacion = listaOcupantesHabitacion;
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
        return "------------------------------------------\n" +
                "Nº Habitacion:   " + this.numeroHabitacion + "\n" +
                "Estado:          " + this.estadoHabitacion + "\n" +
                "Tamaño:          " + this.tamanioHabitacion + "\n" +
                "Costo Base:      $" + this.costoHabitacion + "\n" +
                "Ocupantes:       " + listarOcupantes() + "\n" +
                "------------------------------------------\n";
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


    public JSONObject toJson(){
        JSONArray aux=new JSONArray();
        JSONObject object=new JSONObject();
          try {
              object.put("numeroHabitacion",this.numeroHabitacion);
              object.put("estadoHabitacion",this.estadoHabitacion.toString());
              object.put("tamanioHabitacion",this.tamanioHabitacion.toString());
              object.put("costoHabitacion",this.costoHabitacion);
              object.put("tipo", this.getClass().getSimpleName());


              for (Pasajero p:listaOcupantesHabitacion){
                  aux.put(p.toJson());
              }

              object.put("listaOcupantesHabitacion",aux);
          } catch (JSONException e) {
              throw new RuntimeException(e);
          }
        return object;
    }


    public static Habitaciones fromJson(JSONObject object) throws JSONException {

        String tipo = object.getString("tipo");
        Habitaciones aux;

        switch (tipo) {

            case "habitacionEstandar":
                aux = new habitacionEstandar();
                break;
            case "habitacionMedium":
                aux = new habitacionMedium();
            case "habitacionPremium":
                aux = new habitacionPremium();

            case "habitacionDeluxe":
                aux = new habitacionDeluxe();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + tipo);
        }

        aux.setNumeroHabitacion(object.getInt("numeroHabitacion"));
        aux.setCostoHabitacion(object.getDouble("costoHabitacion"));
        aux.setTamanioHabitacion(ENUMS.tamanioHabitacion.valueOf(object.getString("tamanioHabitacion")));
        aux.setEstadoHabitacion(ENUMS.estadoHabitacion.valueOf(object.getString("estadoHabitacion")));

        JSONArray arreglo=object.getJSONArray("listaOcupantesHabitacion");
        HashSet<Pasajero>ocupantes=new HashSet<>();
        for(int i=0;i<arreglo.length();i++){
            ocupantes.add(Pasajero.fromJson(arreglo.getJSONObject(i)));
        }
        aux.setListaOcupantesHabitacion(ocupantes);
        return aux;
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

