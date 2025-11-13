package MODELO;

import ENUMS.estadoHabitacion;
import EXCEPTIONS.reservaYaCanceladaExpection;
import INTERFACE.IJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Objects;

public class Reserva implements IJson {

    private static int contadorID=1;
    private int idReserva;
    private Habitaciones habitacion;
    private Pasajero pasajero;
    private Date fechaInicio;
    private Date fechaFin;
    private boolean estado;
    private int cantidadPersonas;
    private double costoReserva;
    private boolean checkIn;
    private boolean checkOut;


    public Reserva(boolean estado, Date fechaFin, Date fechaInicio, Habitaciones habitacion, Pasajero pasajero,int cantidadPersonas) {
        // Validaciones básicas

        if (fechaInicio == null || fechaFin == null)
            throw new IllegalArgumentException("Las fechas de inicio y fin no pueden ser nulas");

        if (fechaFin.before(fechaInicio))
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");

        if (habitacion == null)
            throw new IllegalArgumentException("Debe asignarse una habitación a la reserva");

        if (pasajero == null)
            throw new IllegalArgumentException("Debe asignarse un pasajero a la reserva");

        if (cantidadPersonas <= 0)
            throw new IllegalArgumentException("La cantidad de personas debe ser mayor que 0");


        this.estado = true;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.habitacion = habitacion;
        this.idReserva = contadorID++;
        this.pasajero = pasajero;
        this.cantidadPersonas=cantidadPersonas;
        this.costoReserva=calcularCostoReserva();


        habitacion.setEstadoHabitacion(estadoHabitacion.RESERVADA);
    }


    public boolean isCheckIn() {
        return checkIn;
    }

    public void setCheckIn(boolean checkIn) {
        this.checkIn = checkIn;
    }

    public boolean isCheckOut() {
        return checkOut;
    }

    public void setCheckOut(boolean checkOut) {
        this.checkOut = checkOut;
    }

    public double getCostoReserva() {
        return costoReserva;
    }

    public void setCostoReserva(double costoReserva) {
        this.costoReserva = costoReserva;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Habitaciones getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitaciones habitacion) {
        this.habitacion = habitacion;
    }

    public int getIdReserva() {
        return idReserva;
    }


    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Reserva reserva)) return false;
        return Objects.equals(fechaInicio, reserva.fechaInicio) && Objects.equals(fechaFin, reserva.fechaFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaInicio, fechaFin);
    }


    @Override
    public JSONArray backup() {

        JSONArray arregloReserva=new JSONArray();
        JSONObject object=new JSONObject();
        try {
            object.put("idReserva", idReserva);
            object.put("estado", estado);
            object.put("fechaInicio", fechaInicio.toString());
            object.put("fechaFin", fechaFin.toString());
            object.put("habitacion", habitacion.toString());
            object.put("pasajero", pasajero.toString());
            object.put("cantidadPersonas", cantidadPersonas);
            object.put("costoReserva", costoReserva);
            object.put("checkIn", checkIn);
            object.put("checkOut", checkOut);
            arregloReserva.put(object);
        } catch (JSONException e) {
            throw new RuntimeException("Error al crear backup de reserva", e);
        }
        return arregloReserva;
    }



    //metodo para cancelar una reserva, si la reserva no fue ya cancelada
    public String cancelarReserva() throws reservaYaCanceladaExpection {

        if(!estado){
            throw new reservaYaCanceladaExpection("La reserva ya estaba cancelada");
        }
        this.estado=false;
        this.habitacion.setEstadoHabitacion(estadoHabitacion.LIBRE);
        return "Reserva cancelada con exito";
    }



    //metodo que calcula el costo de una reserva segun:
    //la cantidad de dias, tipo de habitacion y extras utilizados

    public double calcularCostoReserva(){

        long diasTotales= (fechaFin.getTime() - fechaInicio.getTime()) / (1000 * 60 * 60 * 24);

        if (diasTotales <= 0) diasTotales = 1; // Evita 0 días o negativos


        double precioBase = diasTotales * habitacion.getCostoHabitacion();

        //Extra según tipo de habitación

        if (habitacion instanceof habitacionDeluxe habitacion1) {
            if (habitacion1.isJacuzzi()) precioBase += 100;

            if (habitacion1.isHidromasaje()) precioBase += 100;

        } else if (habitacion instanceof habitacionMedium habitacion2) {

            if (habitacion2.isCajaSeguridad()) precioBase += 50;

        } else if (habitacion instanceof habitacionPremium habitacion3) {

            if (habitacion3.isMiniBar()) precioBase += 70;

        }

        return precioBase;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + idReserva +
                ", pasajero=" + pasajero +
                ", habitacion=" + habitacion +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", cantidadPersonas=" + cantidadPersonas +
                ", costo=" + costoReserva +
                ", estado=" + (estado ? "Activa" : "Cancelada") +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                '}';
    }
}
