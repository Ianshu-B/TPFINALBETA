package GESTORA;

import EXCEPTIONS.listaReservasVaciaException;
import EXCEPTIONS.reservaNulaException;
import MODELO.reservaPendiente;

import java.util.HashSet;

public class gestoraReservas {
    private HashSet<reservaPendiente> listaReservasPendientes;

    public gestoraReservas(HashSet<reservaPendiente> listaReservasPendientes) {
        this.listaReservasPendientes = new HashSet<>();
    }

    public String agregarSolicitudReserva(reservaPendiente rp)throws reservaNulaException
    {
        StringBuilder sb = new StringBuilder();
        if(rp == null)
        {
            throw new reservaNulaException("No se permiten reservas nulas!");
        } //Hice un hashSet para ahorrarnos la verificacion de solicitud de reserva Repetida pero no se si se necesita igual para el tp
        if(listaReservasPendientes.add(rp))
        {
        sb.append("Solicitud de reserva ingresada correctamente!").append("\n");
        }
        else
        {
            sb.append("No se pudo ingresar la solicitud de reserva!").append("\n");
        }
        return sb.toString();
    }

    public reservaPendiente buscarSolicitudReserva(reservaPendiente rp) throws reservaNulaException
    {
        if(rp == null)
        {
            throw  new reservaNulaException("No se puede buscar una solicitud de reserva nula!");
        }
        for(reservaPendiente rpp : listaReservasPendientes)
        {
            if(rpp.equals(rp))
            {
                return rpp;
            }
        }
        return null;
    }

    public String listarReservasPendientes() throws listaReservasVaciaException
    {
        StringBuilder sb = new StringBuilder();
        if(listaReservasPendientes.isEmpty())
        {
        throw new listaReservasVaciaException("No hay reservas pendientes en este momento!");
        }
        for(reservaPendiente rp : listaReservasPendientes)
        {
            sb.append(rp.toString()).append("\n");
        }
        return sb.toString();
    }

    //ME FALTARIA EL METODO ELIMINAR RESERVA PENDIENTE POR EQUALS DE USERNAME DE PASAJERO QUIZAS
}
