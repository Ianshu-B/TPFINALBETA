package MODELO;

import ENUMS.estadoHabitacion;
import EXCEPTIONS.reservaYaCanceladaExpection;
import INTERFACE.ItoJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Objects;

public class Reserva implements ItoJson {

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

    @Override
    public JSONArray backup() {

        JSONArray arregloReserva=new JSONArray();
        JSONObject object=new JSONObject();
        try {
            object.put("Estado",this.estado);
            object.put("Fecha de fin",this.fechaFin);
            object.put("Fecha de Inicio",this.fechaInicio);
            object.put("Habitacion",this.habitacion);
            object.put("Pasajero",this.pasajero);
            object.put("Cantidad Personas",this.cantidadPersonas);
            object.put("ID",this.idReserva);
            object.put("Costo: ",this.costoReserva);
            arregloReserva.put(object);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return arregloReserva;
    }

    public double calcularCostoReserva(){

        long diasTotales= (fechaFin.getTime() - fechaInicio.getTime()) / (1000 * 60 * 60 * 24);

        double extraHidromasaje=100;
        double extraJacuzzi=100;
        double extraCajaSeguridad=50;
        double extraMiniBar=70;
        double precio_base=diasTotales * habitacion.getCostoHabitacion();

        if(this.habitacion instanceof habitacionDeluxe){

            habitacionDeluxe habitacion1=(habitacionDeluxe) habitacion;
            if(habitacion1.isJacuzzi()){
                precio_base=precio_base+extraJacuzzi;
                if(habitacion1.isHidromasaje()){
                    precio_base=precio_base+extraHidromasaje;
                }
            } else if (this.habitacion instanceof habitacionMedium) {
                habitacionMedium habitacion2=(habitacionMedium) habitacion;
                if(habitacion2.isCajaSeguridad()){
                    precio_base=precio_base+extraCajaSeguridad;
                }
            } else if (this.habitacion instanceof habitacionPremium) {
                habitacionPremium habitacion3=(habitacionPremium) habitacion;
                if(habitacion3.isMiniBar()){
                    precio_base=precio_base+extraMiniBar;
                }
            }
        }

        return precio_base;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "cantidadPersonas=" + cantidadPersonas +
                ", idReserva=" + idReserva +
                ", habitacion=" + habitacion +
                ", pasajero=" + pasajero +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", estado=" + estado +
                ", costoReserva=" + costoReserva +
                '}';
    }
}
