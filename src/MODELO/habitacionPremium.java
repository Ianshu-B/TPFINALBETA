package MODELO;

import ENUMS.estadoHabitacion;
import ENUMS.tamanioHabitacion;

public class habitacionPremium extends Habitaciones{
    private boolean miniBar;

    public habitacionPremium(int numeroHabitacion, ENUMS.estadoHabitacion estadoHabitacion, ENUMS.tamanioHabitacion tamanioHabitacion, boolean miniBar) {
        super(numeroHabitacion, estadoHabitacion, tamanioHabitacion);
        this.miniBar = miniBar;
    }
    public habitacionPremium() {
        super(0, ENUMS.estadoHabitacion.LIBRE, ENUMS.tamanioHabitacion.GRANDE); //Por defecto
        this.miniBar = false; //Por defecto
    }

    public boolean isMiniBar() { //Supongo que el is es por el tipo de atributo
        return miniBar;
    }

    public void setMiniBar(boolean miniBar) { //Supongo que el is es por el tipo de atributo
        this.miniBar = miniBar;
    }

    @Override
    public String toString() {
        return "habitacionPremium{" +
                super.toString() + "\n" +
                "miniBar=" + miniBar + "\n" +
                '}';
    }
    //FALTAN METODOS ESPECIFICOS
}
