package MODELO;

import ENUMS.ROL;
import EXCEPTIONS.*;
import INTERFACE.ItoJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class Recepcionista extends Usuario implements ItoJson {
    private int ID;
    private int contador=1;
    private boolean puedeReservar;
    private boolean puedeCheckIN;
    private HashMap<String,Reserva> reservas;


    public Recepcionista(String nombre, String documento, ROL rol, String username, String password ) {
        super(nombre, documento, rol, username, password);
        this.ID = contador++;
        this.puedeCheckIN=false;
        this.puedeReservar=false;
        this.reservas=new HashMap<>();
    }
    public Recepcionista() {
        super("", "", ROL.RECEPCIONISTA, "", "");
        this.ID = 0;
    }

    public HashMap<String, Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(HashMap<String, Reserva> reservas) {
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
    public JSONObject toJson() throws JSONException
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
    //FALTAN METODOS ESPECIFICOS


    // 2 metodos que van a habilitar al recepcionista a hacer reservas o checkIn
    //reciben un valor por parametros desde el metodo otorgarPermisos en administrador
    //si le da true, pueden hacer esas actividades.

    public void habilitarReserva(boolean valor){
        this.puedeReservar=valor;
    }
    public void habilitarCheckIn(boolean valor){
        this.puedeCheckIN=valor;
    }

    //metodo para hacer reservas, recibe todos los valores de una reserva, y antes de crearla
    //verificamos que tenga permiso, que la fecha de fin no sea anterior a la fecha de inicio y
    //que las fechas no se superpongan con otras reservas
    //si esto se cumple se crea la reserva y se la guarda en la coleccion pertinente
    public boolean realizarReserva(Habitaciones habitacion, Pasajero pasajero, Date fechaInico, Date fechaFin, Boolean estado, int cantidadPersonas) throws sinPermisoParaReservaExpection, FechaInvalidaExpection, HabitacionYaRervadaExpection {
        if (puedeReservar==false){
        throw new sinPermisoParaReservaExpection("No tienes permiso para realizar una reserva");
        }

        if(fechaFin.before(fechaInico)){
            throw new FechaInvalidaExpection("La fecha del fin de la reserva no puede ser anterior a la feceha de inicio");
        }

        for(Reserva r:reservas.values()){

            if(r.getHabitacion().equals(habitacion)){

                if(r.isEstado() && fechasSeSuperponen(fechaInico,fechaFin,r.getFechaInicio(),r.getFechaFin())){
                    throw new HabitacionYaRervadaExpection("La habitacion ya fue reservada");

                }
            }
        }

        Reserva reserva=new Reserva(estado,fechaFin,fechaInico,habitacion,pasajero,cantidadPersonas);
        reservas.put(pasajero.getDocumento(),reserva);
        return true;
    }

    //metodo para hacer un check in segun documento de pasajero, verificamos si tenemos los permisos
    //que la fecha en la q se hace el check in corresponda al incio de su reserva y que el documento coincida
    //con algun pasajero que haya hecho reserva
    public String realizarCheckIn(String documento) throws sinPermisoParaCheckInExpection, DocumentoNoCoincideExpection {
        if(puedeCheckIN==false){
            throw new sinPermisoParaCheckInExpection("No tienes permiso para realizar un check in");
        }


        LocalDate hoy = LocalDate.now();

        for(Reserva r:reservas.values()){
            if(!r.getFechaInicio().equals(hoy))
                throw new FechaDeCheckInInvalidaExpection("Fecha no correspondiente al check in");
        }


        boolean flag=false;

        for (Reserva r: reservas.values()){
            if(r.getPasajero().getDocumento().equals(documento)){
                r.setCheckIn(true);
                flag=true;
            }
        }
        if(!flag){
            throw new DocumentoNoCoincideExpection("El documento no coincide con una reserva existente");
        }


     return "Check In realizado con exito para el pasajero con documento"+documento;
    }

    //metodo para verificar superposicion de fechas
    private boolean fechasSeSuperponen(Date inicio1, Date fin1, Date inicio2, Date fin2) {
        return inicio1.before(fin2) && fin1.after(inicio2);
    }
    @Override
    public JSONArray backup() throws JSONException // METODO ESPECIFICO PARA LA CLASE GENERICA
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


}
