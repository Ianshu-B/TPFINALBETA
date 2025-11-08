package MODELO;

import java.util.Date;
import java.util.Objects;

public class reservaPendiente {
    private String nombrePasajero;
    private Habitaciones habitacion;
    private Date fechaComienzo;
    private Date fechaFinal;
    private int cantPersonas;

    public reservaPendiente(String nombrePasajero, Habitaciones habitacion, Date fechaComienzo, Date fechaFinal, int cantPersonas) {
        this.nombrePasajero = nombrePasajero;
        this.habitacion = habitacion;
        this.fechaComienzo = fechaComienzo;
        this.fechaFinal = fechaFinal;
        this.cantPersonas = cantPersonas;
    }
    //No recuerdo como hacer el constructor vacio de esto jijijijijijijijij porque son objetos de otras clases


    public String getNombrePasajero() {
        return nombrePasajero;
    }

    public void setNombrePasajero(String nombrePasajero) {
        this.nombrePasajero = nombrePasajero;
    }

    public Habitaciones getHabitaciones() {
        return habitacion;
    }

    public void setHabitaciones(Habitaciones habitaciones) {
        this.habitacion = habitaciones;
    }

    public Date getFechaComienzo() {
        return fechaComienzo;
    }

    public void setFechaComienzo(Date fechaComienzo) {
        this.fechaComienzo = fechaComienzo;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public int getCantPersonas() {
        return cantPersonas;
    }

    public void setCantPersonas(int cantPersonas) {
        this.cantPersonas = cantPersonas;
    }

    @Override
    public boolean equals(Object o) { //EL EQUALS ESTA SOBREESCRRITO POR NMRO HABITACION Y FECHA INICIO Y FIN
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        reservaPendiente that = (reservaPendiente) o;
        return Objects.equals(habitacion, that.habitacion) && Objects.equals(fechaComienzo, that.fechaComienzo) && Objects.equals(fechaFinal, that.fechaFinal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(habitacion);
    }

    @Override
    public String toString() {
        return "reservaPendiente{" + "\n" +
                "nombreSolicitante=" + nombrePasajero + "\n" +
                ", habitaciones=" + habitacion + "\n" +
                ", fechaComienzo=" + fechaComienzo + "\n" +
                ", fechaFinal=" + fechaFinal + "\n" +
                ", cantPersonas=" + cantPersonas + "\n" +
                '}';
    }
}
