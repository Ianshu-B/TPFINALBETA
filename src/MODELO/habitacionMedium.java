package MODELO;

import ENUMS.estadoHabitacion;
import ENUMS.tamanioHabitacion;

public class habitacionMedium extends Habitaciones{
    private boolean cajaSeguridad;

    public habitacionMedium(int numeroHabitacion, ENUMS.estadoHabitacion estadoHabitacion, ENUMS.tamanioHabitacion tamanioHabitacion, boolean cajaSeguridad) {
        super(numeroHabitacion, estadoHabitacion, tamanioHabitacion);
        this.cajaSeguridad = cajaSeguridad;
    }
    public habitacionMedium() {
        super(0, ENUMS.estadoHabitacion.LIBRE, ENUMS.tamanioHabitacion.GRANDE); //Por defecto
        this.cajaSeguridad = false; //Poor defecto
    }

    public boolean isCajaSeguridad() { //Supongo que el is es por el tipo de atributo
        return cajaSeguridad;
    }

    public void setCajaSeguridad(boolean cajaSeguridad) {  //Supongo que el is es por el tipo de atributo
        this.cajaSeguridad = cajaSeguridad;
    }

    @Override
    public String toString() {
        return "habitacionMedium{" + "\n" +
                "cajaSeguridad=" + cajaSeguridad + "\n" +
                '}';
    }

    //FALTAN METODOS ESPECIFICOS
}
