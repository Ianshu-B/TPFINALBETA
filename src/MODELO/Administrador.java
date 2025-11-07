package MODELO;

import ENUMS.ROL;
import EXCEPTIONS.elementoNuloException;
import INTERFACE.ItoJson;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashSet;

import static ENUMS.ROL.RECEPCIONISTA;

public class Administrador extends Usuario implements ItoJson {
    private HashSet<Usuario> listaUsuariosCreados;
    public Administrador(String nombre, String documento, ROL rol, String username, String password) {
        super(nombre, documento, rol, username, password);
        this.listaUsuariosCreados = new HashSet<>();
    }
    public Administrador() { //COONSTRUCTOR VACIO
        super("", "", ROL.ADMINISTRADOR, "", "");
        this.listaUsuariosCreados = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Administrador{" +
                super.toString() + "\n" +
                '}';
    }
    //METODO agregarUsuarioLista
    public String agregarUsuarioLista(Usuario u) throws elementoNuloException
    {
        StringBuilder sb = new StringBuilder();
        Usuario aux = buscarUsuario(u);
        if(aux==null)
        {
            if(u ==null)
            {
                throw new elementoNuloException("No se permiten elementos nulos!");
            }
            listaUsuariosCreados.add(u);
            sb.append("Elemento agregado correctamente a a lista de Usuarios del sistema!");
        }else
        {
            throw new elementoNuloException("El elemento ya se encuentra en la lista!");
        }

        return sb.toString();
    }
    public String listarUsuariosCreados()
    {
        StringBuilder sb = new StringBuilder();
        for(Usuario u : listaUsuariosCreados)
        {
            sb.append(u.toString()).append("\n");
        }
        return sb.toString();
    }
    public Usuario buscarUsuario(Usuario u)
    {
        for(Usuario uu : listaUsuariosCreados)
        {
            if (uu.equals(u))
            {
                return uu;
            }
        }
        return null;
    }

    public String eliminarUsuario(Usuario u) throws elementoNuloException
    {
        StringBuilder sb = new StringBuilder();
        Usuario aux = buscarUsuario(u);
        if(aux == null)
        {
            throw  new elementoNuloException("No se encontro el usuario a eliminar dentro de la lista!");
        }
        if(listaUsuariosCreados.remove(aux))
        {
            sb.append("Usuario eliminado correctamente!").append("\n");
        }else
        {
            sb.append("No se pudo eliminar el usuario ingresado!").append("\n");
        }
        return sb.toString();

    }

    //METODOS CREARUSUARIO
    public Pasajero crearPasajero(String nombre, String documento, ROL rol, String username, String password, String origen,String domicilio) throws elementoNuloException
    {
        if(nombre == null || documento == null | username == null | password == null | origen == null | documento == null)
        {
            throw  new elementoNuloException("Elemento o mas de un elemento nulo ingresado. No esta permitido");
        }
        return new Pasajero(nombre,documento,rol,username,password,origen,domicilio);
    }
    public Recepcionista crearRecepcionista(String nombre, String documento, ROL rol, String username, String password) throws elementoNuloException
    {
        if(nombre == null || documento == null | username == null | password == null)
        {
            throw new elementoNuloException("Elemento o mas de un elemento nulo ingresado. No esta permitido");
        }
        return new Recepcionista(nombre, documento, rol, username, password);
    }
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //METODO BACKUP IMPLEMENTANDO UNA INTERFAZ
    /*
    @Override
    public JSONArray backup()
    {
        JSONArray jsonArray = new JSONArray();
        try {
            for(Usuario u : listaUsuariosCreados)
            {
                jsonArray.put(u.toJson());
            }

        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        return jsonArray;
    }

     */
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //METODO BACKUP SOLUCIONADO, CONSULTAR CON EQUIPO O PROFE JIJIJ

    @Override
    public JSONArray backup()
    {
        JSONArray jsonArrayRecepcionistas = new JSONArray();
        JSONArray jsonArrayPasajeros = new JSONArray();
        JSONArray totalElementosJsonArray = new JSONArray();
        try {
            for(Usuario u : listaUsuariosCreados)
            {
                if(u instanceof Recepcionista)
                {
                    jsonArrayRecepcionistas.put(u.toJson());
                }else
                {
                    jsonArrayPasajeros.put(u.toJson());
                }

            }

            JsonUtiles.grabarUnJson(jsonArrayRecepcionistas,"RecepcionistaRespaldo.json");
            JsonUtiles.grabarUnJson(jsonArrayPasajeros,"PasajeroRespaldo.json");
            //No me gusta grabar el JSON dentro de un metodo pero es lo unico que se me ocurrio
            //Si se les ocurre algo y quieren cambiarlo, mandenle >:D

        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        //Simplemente para retornar algo en caso de que no sea valido no retornar nada, retorno el total de elementos.
            try {
                for(Usuario u : listaUsuariosCreados)
                {
                    totalElementosJsonArray.put(u.toJson());
                }
        }catch (JSONException e)
            {
                e.printStackTrace();
            }
        return totalElementosJsonArray;
    }
    //FALTA METODO ASIGNAR PERMISOS QUE NO ME ACUERDO QUE FUNCION CUMPLE EXACTAMENTE

    public void otorgarPermisosReserva(Recepcionista recepcionista){

        recepcionista.habilitarReserva(true);
    }

    public void otorgarPermisosCheckIn(Recepcionista recepcionista){
        recepcionista.habilitarCheckIn(true);
    }



}
