package MODELO;

import ENUMS.ROL;
import EXCEPTIONS.*;
import INTERFACE.IJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.HashSet;

public final class  Administrador extends Usuario implements IJson {
    //NOS CREAMOS UNA UNICA INSTANCIA STATIC DE ADMIN PARA QUE PUEDA TENER ACCESESO CONSTANTE A TODO
    private static Administrador administradorUnico=new Administrador("ADMIN","44850150",ROL.ADMINISTRADOR,"ADMIN_HOTEL","PIZZA");

    private HashMap<String,Usuario> listaUsuariosCreados;

    private Administrador(String nombre, String documento, ROL rol, String username, String password) {
        super(nombre, documento, rol, username, password);
        this.listaUsuariosCreados = new HashMap<>();
        this.listaUsuariosCreados.put(this.documento,administradorUnico);
    }

    public Administrador() { //COONSTRUCTOR VACIO
        super("", "", ROL.ADMINISTRADOR, "", "");
        this.listaUsuariosCreados = new HashMap<>();
    }


    //ESTE METODO NOS DEVUELVE EL ADMIN UNICO QUE EXISTE
    public static Administrador getAdmin(){
        return administradorUnico;
    }

    //BUSCA UN RECEPCIONISTA PARA HACER UNA RESERVA CUADNO ES SOLICITADA
    //AGEGAR LOGICA PARA SABER QUE RECEPCIONISTA DEVOLVER.
    public Recepcionista obtenerRecepcionista(){

        for(Usuario u:listaUsuariosCreados.values()){
            if(u instanceof Recepcionista){
                Recepcionista r=(Recepcionista) u;
                return r;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return "Administrador{" +
                super.toString() + "\n" +
                '}';
    }
    //METODO agregarUsuarioLista
    public String agregarUsuarioLista(Usuario u) throws elementoNuloException {
        StringBuilder sb = new StringBuilder();
        if (u == null)
        {
            throw new elementoNuloException("No se permiten elementos nulos!");
        }
        Usuario aux = buscarUsuario(u);
        if(aux == null)
        {
            listaUsuariosCreados.put(u.getDocumento(),u);
            sb.append("Usuario agregado correctamente!");
        }else
        {
            throw new elementoNuloException("El usuario ya esta registrado!");
        }

        return sb.toString();
    }
    public  String listarUsuariosCreados()
    {
        StringBuilder sb = new StringBuilder();
        for(Usuario u : listaUsuariosCreados.values())
        {
            sb.append(u.toString()).append("\n");
        }
        return sb.toString();
    }
    public Usuario buscarUsuario(Usuario u)
    {
        if(u == null)
        {
            return null;
        }
        return listaUsuariosCreados.get(u.getDocumento()); //Te devuelve la key del usuario
    }
    public Usuario buscarXDocumento(String documento) throws elementoNuloException
    {
        Usuario aux;
        if(documento == null)
        {
            throw new elementoNuloException("Documento nulo. ERROR!");
        }
        if(listaUsuariosCreados.containsKey(documento))
        {
            aux = listaUsuariosCreados.get(documento);
            return aux;
        }
        return null;
    }

    public String eliminarUsuario(String documento) throws elementoNuloException
    {
        StringBuilder sb = new StringBuilder();
        Usuario aux = buscarXDocumento(documento);
        if(aux == null)
        {
            throw  new elementoNuloException("No se encontro el usuario a eliminar dentro de la lista!");
        }
        if(listaUsuariosCreados.remove(aux.getDocumento())!=null)
        {
            sb.append("Usuario eliminado correctamente!").append("\n");
        }else
        {
            sb.append("No se pudo eliminar el usuario ingresado!").append("\n");
        }
        return sb.toString();

    }

    //METODOS CREARUSUARIO
    public Pasajero crearPasajero(String nombre, String documento,String username, String password, String origen,String domicilio) throws elementoNuloException
    {
        if(nombre == null || documento == null | username == null | password == null | origen == null | documento == null)
        {
            throw  new elementoNuloException("Elemento o mas de un elemento nulo ingresado. No esta permitido");
        }
        return new Pasajero(nombre,documento,username,password,origen,domicilio);
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
public JSONObject toJson()  throws JSONException // toJson propio de admin
{
    JSONObject jsonObject = new JSONObject();
    try {
        jsonObject = super.toJson();

    }catch (JSONException e)
    {
        e.printStackTrace();
    }
    return  jsonObject;
}

public  Administrador fromJson(JSONObject jsonObject) throws JSONException //fromJson propio de admin
{
    //Utilizo this porque solo tenemos una unica instancia de administrador en el gestor.
    try {
        this.setNombre(jsonObject.getString("nombre"));
        this.setDocumento(jsonObject.getString("documento"));
        this.setRol(ROL.valueOf(jsonObject.getString("rol")));
        this.setUsername(jsonObject.getString("username"));
        this.setPassword(jsonObject.getString("password"));
    }catch (JSONException e)
    {
        e.printStackTrace();
    }
    return this; //Retorno this por el comentario de arriba.

}
    @Override
    public JSONArray backup() throws listaUsuariosVacioException
    {
        if(listaUsuariosCreados.isEmpty()){
            throw new listaUsuariosVacioException("Lista de usuarios vacía, no se creará backup.");
        }
        JSONArray jsonArrayRecepcionistas = new JSONArray();
        JSONArray jsonArrayPasajeros = new JSONArray();
        JSONArray totalElementosJsonArray = new JSONArray();
        try {
            for(Usuario u : listaUsuariosCreados.values())
            {
                if(u instanceof Recepcionista)
                {
                    jsonArrayRecepcionistas.put(u.toJson());
                }else if(u instanceof Pasajero)
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
                for(Usuario u : listaUsuariosCreados.values())
                {
                    totalElementosJsonArray.put(u.toJson());
                }
        }catch (JSONException e)
            {
                e.printStackTrace();
            }
        return totalElementosJsonArray;
    }
    public void backupReservas(HashMap<String, Reserva> reservas,HashMap<Integer,Reserva>reservaPendiente) throws listaReservasVaciaException
    {
        if(reservaPendiente.isEmpty() && reservas.isEmpty())
        {
            throw  new listaReservasVaciaException("No hay reservas ni reservas pendientes para realizar backup!");
        }
        JSONArray jsonArrayReservasPendientes = new JSONArray();
        JSONArray jsonArrayReservas = new JSONArray();

        try {
            //Reservas pendientes
            for(Reserva r : reservaPendiente.values())
            {
                jsonArrayReservasPendientes.put(r.toJson());

            }
            //Reservas coonfirmadas
            for(Reserva r : reservas.values())
            {
                jsonArrayReservas.put(r.toJson());

            }
            //Grabo las pendientes
            JsonUtiles.grabarUnJson(jsonArrayReservasPendientes,"ReservasPendientes.json");
            //Grabo las confirmadas
            JsonUtiles.grabarUnJson(jsonArrayReservas,"ReservasConfirmadas.json");

        }catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
//Persistencia de datos de Usuarios
    public void devolverDatosJson(JSONArray jsonArray) throws JSONException
    {
        Usuario aux; //Referencia de Usuario donde despues en el switch le voy a guardar un objeto concreto
        try {
            for(int i = 0; i < jsonArray.length();i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i); //Almaceno un objeto del JsonArray en un auxiliar
                ROL rol = ROL.valueOf(jsonObject.getString("rol")); //Casteo de ENUM a string el tipo de dato que tenga almacenado en rol desde el JSON
                switch (rol)
                {
                    case PASAJERO:
                        Pasajero p = Pasajero.fromJson(jsonObject);
                        listaUsuariosCreados.put(p.getDocumento(),p); //Reconstruyo la collection de la clase desde el Json
                        break;
                    case RECEPCIONISTA:
                        Recepcionista r = Recepcionista.fromJson(jsonObject);
                        listaUsuariosCreados.put(r.getDocumento(),r); //Reconstruyo la collection de la clase desde el Json
                        break;
                    case ADMINISTRADOR:
                        Administrador a = Administrador.getAdmin(); // Guardo el retorno de getAdmin
                        a = a.fromJson(jsonObject);
                        aux = a;
                        listaUsuariosCreados.put(aux.getDocumento(),aux); //Reconstruyo la collection de la clase desde el Json
                        break;

                    default:
                        throw new IllegalArgumentException("Elemento desconocido!"); //Esto lo saque del segundo parcial teorico de progra

                }

                //Los fromJson los hice estaticos para que devuelva un objeto ya completo. Salvo admin obviamente porque queremos una unica instancia de ese objeto.

            }

        }catch (JSONException e)
        {
            e.printStackTrace();
        }

    }


    /*
    public Administrador fromJson(JSONObject jsonObject) throws JSONException
    {
        Administrador aux = new Administrador();
        try {
               aux.setNombre();

        }catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

     */
    //FALTA METODO ASIGNAR PERMISOS QUE NO ME ACUERDO QUE FUNCION CUMPLE EXACTAMENTE

    public String otorgarPermisosReserva(Recepcionista recepcionista){

        recepcionista.habilitarReserva(true);
        return "Permisos otorgados";
    }

    public String otorgarPermisosCheckIn(Recepcionista recepcionista){

        recepcionista.habilitarCheckIn(true);
        return "Permisos para check in otorgados";
    }

    //TERMINAR ACA Y EN RECEPCIONISTA
    public String otorgarPermisosCheckOut(Recepcionista recepcionista){
        recepcionista.habilitarCheckOut(true);
        return "Permisos para check out otorgados";

    }


    public Usuario buscarXUserName(String userName) throws UsuarioNoEncontradoException {
        if (userName == null || userName.isEmpty()) {
            throw new UsuarioNoEncontradoException("El nombre de usuario ingresado es nulo o vacío.");
        }

        for (Usuario u : listaUsuariosCreados.values()) {
            if (u.getUsername().equalsIgnoreCase(userName)) {
                return u;
            }
        }

        throw new UsuarioNoEncontradoException("No se encontró un usuario con el nombre de usuario: " + userName);
    }

    public Usuario buscarUsuarioLogin(String username, String password) throws UsuarioNoEncontradoException {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            throw new UsuarioNoEncontradoException("Debe ingresar usuario y contraseña.");
        }

        for (Usuario u : listaUsuariosCreados.values()) {
            if (u.getUsername().equalsIgnoreCase(username) && u.getPassword().equals(password)) {
                return u;
            }
        }

        throw new UsuarioNoEncontradoException("Usuario o contraseña incorrectos.");
    }

    public Recepcionista buscarRecepcionista(String nombre) throws RecepcionistaNoEncontradoException {
        if (nombre == null || nombre.isEmpty()) {
            throw new RecepcionistaNoEncontradoException("El nombre del recepcionista no puede ser nulo o vacío.");
        }

        for (Usuario u : listaUsuariosCreados.values()) {
            if (u instanceof Recepcionista && u.getUsername().equalsIgnoreCase(nombre)) {
                return (Recepcionista) u;
            }
        }

        throw new RecepcionistaNoEncontradoException("No se encontró un recepcionista con el nombre: " + nombre);
    }
}
