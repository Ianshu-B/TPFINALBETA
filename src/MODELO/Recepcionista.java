package MODELO;

import ENUMS.ROL;
import ENUMS.estadoHabitacion;
import EXCEPTIONS.*;
import INTERFACE.IJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public final class Recepcionista extends Usuario implements IJson {
    private int ID;
    private static int contador=1;
    private boolean puedeReservar;
    private boolean puedeCheckIN;
    private boolean puedeCheckOUT;
    private static HashMap<Integer,Reserva> reservas;
    private static HashMap<Integer,Reserva>reservaPendiente;


    public Recepcionista(String nombre, String documento, ROL rol, String username, String password ) {
        super(nombre, documento, rol, username, password);
        this.ID = contador++;
        this.puedeCheckIN=false;
        this.puedeCheckOUT=false;
        this.puedeReservar=false;
        this.reservas=new HashMap<>();
        this.reservaPendiente=new HashMap<>();
    }
    public Recepcionista() {
        super("", "", ROL.RECEPCIONISTA, "", "");
        this.ID = 0;
    }



    public HashMap<Integer, Reserva> getReservas() {
        return reservas;
    }

    public HashMap<Integer, Reserva> getReservaPendiente() {
        return reservaPendiente;
    }

    public void setReservas(HashMap<Integer, Reserva> reservas) {
        this.reservas = reservas;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isPuedeCheckIN() {
        return puedeCheckIN;
    }
    public boolean isPuedeCheckOUT() {
        return puedeCheckOUT;
    }

    public boolean isPuedeReservar() {
        return puedeReservar;
    }

    @Override
    public String toString() {
        return "Recepcionista{" +
                super.toString() + "\n" +
                "ID='" + ID + "\n"  +
                '}';
    }
    public JSONObject toJson() throws JSONException //toJson de recepcionista, no de reservas
    {
        JSONObject jsonObject = super.toJson(); //Paso todo lo de su clase Padre que es Persona y sigo completando con los atributos especificos
        try {
            jsonObject.put("ID",this.ID);

        }catch (JSONException e)
        {
            e.printStackTrace();
        }

        return jsonObject;

    }
    public static Recepcionista fromJson(JSONObject jsonObject) throws JSONException //fromJson de recepcionista, no de reservas
    {
        Recepcionista aux = new Recepcionista();
        try {
            aux.setNombre(jsonObject.getString("nombre"));
            aux.setDocumento(jsonObject.getString("documento"));
            aux.setRol(ROL.valueOf(jsonObject.getString("rol")));
            aux.setUsername(jsonObject.getString("username"));
            aux.setPassword(jsonObject.getString("password"));
            aux.setID(jsonObject.getInt("ID"));

        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        return aux;
    }
    //Metodos para cargar collections desde Json
    public static void devolverReservasPendientes(JSONArray jsonArray) throws JSONException {
        try { //Faltaria verificar que no esten vacios pero no se si funciona unicamente con null
            for (int i = 0; i < jsonArray.length(); i++) {
                Reserva r = Reserva.fromJson(jsonArray.getJSONObject(i)); //Paso el jsonObject y lo casteo a uno de reserva
                reservaPendiente.put(r.getIdReserva(), r); //Lo vuelvo a meter en su lista correspondiente
            }
        }catch (JSONException e)
        {
            e.printStackTrace();
        }

    }

    public static void devolverReservasConfirmadas(JSONArray jsonArray) throws JSONException {
        try { //Faltaria verificar que no esten vacios pero no se si funciona unicamente con null
            for (int i = 0; i < jsonArray.length(); i++) {
                Reserva r = Reserva.fromJson(jsonArray.getJSONObject(i)); //Paso el jsonObject y lo casteo a uno de reserva
                reservas.put(r.getIdReserva(), r); //Lo vuelvo a meter en su lista correspondiente mediante el documento
            }
        }catch (JSONException e)
        {
            e.printStackTrace();
        }

    }
    //------------------------------------------------------------------------------------------------------
    //Metodos para actualizar el contenido de JSON luego de una operacion
    public static void guardarReservasPendientes() throws JSONException
    {
        JSONArray jsonArray = new JSONArray();
        try {
            for(Reserva r : reservaPendiente.values())
            {
                jsonArray.put(r.toJson());
            }
            JsonUtiles.grabarUnJson(jsonArray,"ReservasPendientes.json");
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        //Basicamente recorro las listas correspondientes y voy actualizando los Json que ya existen para guardar o actualizar la informacion que contiene el JSON.
        //Lo hice para que en caso de que se coonfirme una reserva pendiente, se borre el estado del JSON a vacio y no vuelva a almacenar en la collection
        //la reserva que ya antes se confirmo.

    }
    public  void guardarReservasConfirmadas() throws JSONException
    {
        JSONArray jsonArray = new JSONArray();
        try {
            for(Reserva r : reservas.values())
            {
                jsonArray.put(r.toJson());
            }
            JsonUtiles.grabarUnJson(jsonArray,"ReservasConfirmadas.json");
        }catch (JSONException e)
        {
            e.printStackTrace();
        }

    }
    //--------------------------------------------------------------------------------------




    // 2 metodos que van a habilitar al recepcionista a hacer reservas o checkIn
    //reciben un valor por parametros desde el metodo otorgarPermisos en administrador
    //si le da true, pueden hacer esas actividades.

    public void habilitarReserva(boolean valor){
        this.puedeReservar=valor;
    }
    public void habilitarCheckIn(boolean valor){

        this.puedeCheckIN=valor;
    }
    public void habilitarCheckOut(boolean valor){

        this.puedeCheckOUT=valor;
    }



//AGREGAR VERIFICACIONES
public static String cargarReservaPendiente(Habitaciones habitacion, Pasajero pasajero, Date fechaInicio, Date fechaFin, Boolean estado, int cantidadPersonas, ArrayList<String> extras) throws elementoRepetidoException, JSONException {
    //Creamos una reserva pendiente
    Reserva pendiente = new Reserva(estado, fechaFin, fechaInicio, habitacion, pasajero, cantidadPersonas, extras);
    pendiente.setIdReserva(contador++);

    pendiente.setEstado(false); // Pendiente = false (aún no confirmada)
    if (reservaPendiente.containsKey(pendiente.getIdReserva())) {
        throw new elementoRepetidoException("Ya existe una reserva con el ID " + pendiente.getIdReserva());
    }

    reservaPendiente.put(pendiente.getIdReserva(), pendiente);
    guardarReservasPendientes();
    return "Reserva cargada correctamente";
}

    public String mostrarReservasPendientes() throws listaExtrasVaciaException {
        StringBuilder sb = new StringBuilder();

        if (reservaPendiente.isEmpty()) {
            sb.append("⚠️ No hay reservas pendientes actualmente.\n");
        } else {
            sb.append("📋 Reservas Pendientes:\n");
            sb.append("----------------------------\n");

            for (Reserva reserva : reservaPendiente.values()) {
                sb.append("🆔 ID: ").append(reserva.getIdReserva()).append("\n");
                sb.append("🧍 Pasajero: ").append(reserva.getPasajero().getNombre()).append("\n");
                sb.append("🏨 Habitación: ").append(reserva.getHabitacion().getNumeroHabitacion()).append("\n");
                sb.append("📅 Desde: ").append(reserva.getFechaInicio()).append("\n");
                sb.append("📅 Hasta: ").append(reserva.getFechaFin()).append("\n");
                sb.append("👥 Personas: ").append(reserva.getCantidadPersonas()).append("\n");
                sb.append("Estado: ").append(reserva.isEstado() ? "Activa" : "Pendiente").append("\n");
                sb.append("Extras: ").append(reserva.listarExtras()).append("\n");
                sb.append("Costo de reserva: ").append(reserva.getCostoReserva()).append("\n");
                sb.append("----------------------------\n");
            }
        }

        return sb.toString();
    }




    //metodo para hacer reservas, recibe todos los valores de una reserva, y antes de crearla
    //verificamos que tenga permiso, que la fecha de fin no sea anterior a la fecha de inicio y
    //que las fechas no se superpongan con otras reservas
    //si esto se cumple se crea la reserva y se la guarda en la coleccion pertinente

    public boolean realizarReserva(int idReserva) throws sinPermisoParaReservaExpection, FechaInvalidaExpection, HabitacionYaRervadaExpection, JSONException,HabitacionNoReservableExpection{

        if (!puedeReservar) {

            throw new sinPermisoParaReservaExpection("No tienes permiso para realizar una reserva");
        }

        Reserva reserva = reservaPendiente.get(idReserva);

            if (reserva == null) {
            throw new FechaInvalidaExpection("La reserva pendiente con ese ID no existe");
          }

        if (reserva.getFechaFin().before(reserva.getFechaInicio())) {
            throw new FechaInvalidaExpection  ("La fecha de fin no puede ser anterior a la fecha de inicio");
        }

        if (reserva.getHabitacion().getEstadoHabitacion() == estadoHabitacion.MANTENIMIENTO) {
            throw new HabitacionNoReservableExpection("La habitación no se puede reservar porque está en mantenimiento");
        }

        for (Reserva r : reservas.values()) {
            if (r.getHabitacion().equals(reserva.getHabitacion())) {

                if (r.isEstado() && fechasSeSuperponen(reserva.getFechaInicio(), reserva.getFechaFin(), r.getFechaInicio(), r.getFechaFin())) {
                    throw new HabitacionYaRervadaExpection("La habitacion ya fue reservada para esas fechas");
                }


            }
        }

        reservas.put(idReserva, reserva);
        reserva.setEstado(true);

        reserva.getHabitacion().setEstadoHabitacion(estadoHabitacion.RESERVADA);
        reservaPendiente.remove(idReserva);

        guardarReservasPendientes();

        guardarReservasConfirmadas();

        return true;
    }

    //metodo para hacer un check in segun documento de pasajero, verificamos si tenemos los permisos
    //que la fecha en la q se hace el check in corresponda al incio de su reserva y que el documento coincida
    //con algun pasajero que haya hecho reserva

    public String realizarCheckIn(String documento) throws sinPermisoParaCheckInExpection, DocumentoNoCoincideExpection {
        if(!puedeCheckIN){
            throw new sinPermisoParaCheckInExpection("No tienes permiso para realizar un check in");
        }


        LocalDate hoy = LocalDate.now();
        boolean encontrado = false;



        for (Reserva r : reservas.values()) {
            if (r.getPasajero().getDocumento().equals(documento)) {
                encontrado = true;

                // Convertimos Date a LocalDate para comparar correctamente
                LocalDate fechaInicio = r.getFechaInicio().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

                if (!fechaInicio.equals(hoy)) {
                    throw new FechaDeCheckInInvalidaExpection("No es la fecha correspondiente al inicio de la reserva");
                }

                r.setCheckIn(true);
                r.getHabitacion().setEstadoHabitacion(estadoHabitacion.OCUPADA);

                return "✅ Check-in realizado con éxito para el pasajero con documento: " + documento;
            }
        }

        throw new DocumentoNoCoincideExpection("El documento no coincide con ninguna reserva existente");

    }


    //METODO CHECKOUT
        public String realizarCheckOut(String documento)
            throws sinPermisoParaCheckInExpection, DocumentoNoCoincideExpection {

        if (!puedeCheckIN) {
            throw new sinPermisoParaCheckInExpection("No tienes permiso para realizar un check-out");
        }

        for (Reserva r : reservas.values()) {
            if (r.getPasajero().getDocumento().equals(documento)) {
                if (!r.isCheckIn()) {
                    return "No se puede hacer check-out, el pasajero aún no realizó el check-in.";
                }

                r.setCheckOut(true);
                r.getHabitacion().setEstadoHabitacion(estadoHabitacion.LIBRE);
                r.setEstado(false); // la reserva ya finalizó

                return "Check-out realizado con éxito para el pasajero con documento: " + documento;
            }
        }

        throw new DocumentoNoCoincideExpection("El documento no coincide con ninguna reserva existente");
    }

    //metodo para verificar superposicion de fechas
    private boolean fechasSeSuperponen(Date inicio1, Date fin1, Date inicio2, Date fin2) {
        return inicio1.before(fin2) && fin1.after(inicio2);
    }

    
    @Override
    public JSONArray backup() throws JSONException, listaUsuariosVacioException // METODO ESPECIFICO PARA LA CLASE GENERICA
    {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = super.backup();
        try {
            jsonObject.put("ID",this.ID);
            jsonArray.put(jsonObject);
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        return jsonArray;

    }


    public boolean cancelarReserva(int id) throws reservaYaCanceladaExpection, JSONException {

        Reserva r=buscarReservaXId(id);

        if(!r.isEstado()){
            throw new reservaYaCanceladaExpection("La reserva ya fue cancelada");
        }

        reservas.remove(id);

        r.getHabitacion().setEstadoHabitacion(estadoHabitacion.LIBRE);
        guardarReservasConfirmadas();
        return true;
    }


    public Reserva buscarReservaXId(int id){

        Reserva aux;

        if(reservas.containsKey(id)){
                aux=reservas.get(id);

                return aux;
            }
        return null;
        }


    public String cambiarEstado(Habitaciones habitacion, estadoHabitacion estado) throws HabitacionYaRervadaExpection{

        if(habitacion==null){
            throw new HabitacionNulaExpection("La habitacion no existe");
        }
        habitacion.setEstadoHabitacion(estado);
        return "Estado cambiado con exito a: "+estado;
    }

}
