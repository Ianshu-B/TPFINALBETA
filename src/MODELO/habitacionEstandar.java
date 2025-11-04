package MODELO;

import ENUMS.estadoHabitacion;
import ENUMS.tamanioHabitacion;

public class habitacionEstandar extends Habitaciones{
    //Sin atributos especificos
    public habitacionEstandar(int numeroHabitacion, ENUMS.estadoHabitacion estadoHabitacion, ENUMS.tamanioHabitacion tamanioHabitacion) {
        super(numeroHabitacion, estadoHabitacion, tamanioHabitacion);
    }
    public habitacionEstandar() {
        super(0, ENUMS.estadoHabitacion.LIBRE, ENUMS.tamanioHabitacion.GRANDE); //Por defecto
    }

    @Override
    public String toString() {
        return "habitacionEstandar{" +
                super.toString() + "\n" +

                '}';
    }
    //FALTAN LOS METODOS ESPECIFICOS
}
