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
    private String ID;
    private boolean puedeReservar;
    private boolean puedeCheckIN;
    private HashMap<String,Reserva> reservas;


    public Recepcionista(String nombre, String documento, ROL rol, String username, String password, String ID) {
        super(nombre, documento, rol, username, password);
        this.ID = ID;
        this.puedeCheckIN=false;
        this.puedeReservar=false;
        this.reservas=new HashMap<>();
    }
    public Recepcionista() {
        super("", "", ROL.RECEPCIONISTA, "", "");
        this.ID = "";
    }

    public HashMap<String, Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(HashMap<String, Reserva> reservas) {
        this.reservas = reservas;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public void habilitarReserva(boolean valor){
        this.puedeReservar=valor;
    }
    public void habilitarCheckIn(boolean valor){
        this.puedeCheckIN=valor;
    }

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
