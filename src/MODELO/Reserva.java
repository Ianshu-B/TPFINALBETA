package MODELO;

import ENUMS.estadoHabitacion;
import EXCEPTIONS.reservaYaCanceladaExpection;

import java.util.Date;
import java.util.Objects;

public class Reserva {

    private static int contadorID=1;
    private int idReserva;
    private Habitaciones habitacion;
    private Pasajero pasajero;
    private Date fechaInicio;
    private Date fechaFin;
    private boolean estado;
    private int cantidadPersonas;



    public Reserva(boolean estado, Date fechaFin, Date fechaInicio, Habitaciones habitacion, int idReserva, Pasajero pasajero,int cantidadPersonas) {
        this.estado = true;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.habitacion = habitacion;
        this.idReserva = idReserva;
        this.pasajero = pasajero;
        this.cantidadPersonas=cantidadPersonas;

        habitacion.setEstadoHabitacion(estadoHabitacion.RESERVADA);
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

    public String cancelarReserva() throws reservaYaCanceladaExpection {

        if(estado == false){
            throw new reservaYaCanceladaExpection("La reserva ya estaba cancelada");
        }
        estado=false;
        habitacion.setEstadoHabitacion(estadoHabitacion.LIBRE);
        return "Reserva cancelada con exito";
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
}
