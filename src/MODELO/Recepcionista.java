package MODELO;

import ENUMS.ROL;
import ENUMS.estadoHabitacion;
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
    private static int contador=1;
    private boolean puedeReservar;
    private boolean puedeCheckIN;
    private HashMap<String,Reserva> reservas;
    private HashMap<Integer,Reserva>reservaPendiente;


    public Recepcionista(String nombre, String documento, ROL rol, String username, String password ) {
        super(nombre, documento, rol, username, password);
        this.ID = contador++;
        this.puedeCheckIN=false;
        this.puedeReservar=false;
        this.reservas=new HashMap<>();
        this.reservaPendiente=new HashMap<>();
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



//AGREGAR VERIFICACIONES
public void cargarReservaPendiente(Habitaciones habitacion, Pasajero pasajero,
                                   Date fechaInicio, Date fechaFin,
                                   Boolean estado, int cantidadPersonas) {
    //Creamos una reserva pendiente
    Reserva pendiente = new Reserva(estado, fechaFin, fechaInicio, habitacion, pasajero, cantidadPersonas);
    pendiente.setEstado(false); // Pendiente = false (aún no confirmada)
    reservaPendiente.put(pendiente.getIdReserva(), pendiente);
}

    public String mostrarReservasPendientes() {
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
                sb.append("----------------------------\n");
            }
        }

        return sb.toString();
    }




    //metodo para hacer reservas, recibe todos los valores de una reserva, y antes de crearla
    //verificamos que tenga permiso, que la fecha de fin no sea anterior a la fecha de inicio y
    //que las fechas no se superpongan con otras reservas
    //si esto se cumple se crea la reserva y se la guarda en la coleccion pertinente

    public boolean realizarReserva(int idReserva) throws sinPermisoParaReservaExpection, FechaInvalidaExpection, HabitacionYaRervadaExpection {
        if (!puedeReservar){
        throw new sinPermisoParaReservaExpection("No tienes permiso para realizar una reserva");
        }

        Reserva reserva=reservaPendiente.get(idReserva);

        if (reserva == null) {
            throw new FechaInvalidaExpection("La reserva pendiente con ese ID no existe");
        }

        if (reserva.getFechaFin().before(reserva.getFechaInicio())) {
            throw new FechaInvalidaExpection("La fecha de fin no puede ser anterior a la fecha de inicio");
        }

        for(Reserva r:reservas.values()){
            if(r.getHabitacion().equals(reserva.getHabitacion())){
                if(r.isEstado() && fechasSeSuperponen(reserva.getFechaInicio(),reserva.getFechaFin(),r.getFechaInicio(),r.getFechaFin())){
                    throw new HabitacionYaRervadaExpection("La habitacion ya fue reservada");

                }
            }
        }


        reservas.put(reserva.getPasajero().getDocumento(),reserva);
        reserva.setEstado(true);
        reserva.getHabitacion().setEstadoHabitacion(estadoHabitacion.RESERVADA);
        reservaPendiente.remove(idReserva);
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
