import ENUMS.ROL;
import EXCEPTIONS.elementoNuloException;
import MODELO.Administrador;
import MODELO.Pasajero;
import MODELO.Recepcionista;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Administrador administrador1 = new Administrador("Ian","4545A", ROL.ADMINISTRADOR,"adminIan","454545");
    try {
        //TESTEANDO CREAR USUARIO
        Scanner sc = new Scanner(System.in);
        int rta;
        String msj;
    do {
    System.out.println("1. Crear pasajero, 2.Crear Recepcionista. Digite su respuesta :");
    rta = sc.nextInt();
    if(rta!=1 && rta!=2)
    {
        System.out.println("Opcion incorrecta, ingrese nuevamente!");
    }
        }while (rta!=1 && rta!=2);

        switch (rta)
        {
            case 1 :
                String nombre,documento,username,password,origen,domicilio;
                System.out.println("CREANDO PASAJERO");
                System.out.println("Ingrese el nombre del PASAJERO :");
                nombre = sc.next();
                System.out.println("Ingrese el documento del PASAJERO :");
                documento = sc.next();
                System.out.println("Ingrese el username del PASAJERO :");
                username = sc.next();
                System.out.println("Ingrese la contrasenia del PASAJERO : ");
                password = sc.next();
                System.out.println("Ingrese el origen del PASAJERO :");
                origen = sc.next();
                System.out.println("Ingrese el domicilio del PASAJERO :");
                domicilio = sc.next();
                Pasajero pasajero = administrador1.crearPasajero(nombre,documento,ROL.PASAJERO,username,password,origen,domicilio);
                if(pasajero != null)
                {
                    System.out.println("Pasajero creado correctamente!");
                    System.out.println("Listando la informacion del PASAJERO creado :");
                    System.out.println(pasajero.toString());
                }else
                {
                    System.out.println("Hubo un error al crear al pasajero!");
                }
                break;
            case  2 :
                String ID;
                System.out.println("CREANDO RECEPCIONISTA");
                System.out.println("Ingrese el nombre del RECEPCIONISTA :");
                nombre = sc.next();
                System.out.println("Ingrese el documento del RECEPCIONISTA :");
                documento = sc.next();
                System.out.println("Ingrese el username del RECEPCIONISTA :");
                username = sc.next();
                System.out.println("Ingrese la contrasenia del RECEPCIONISTA : ");
                password = sc.next();
                System.out.println("Ingrese el id del RECEPCIONISTA");
                ID = sc.next();
                Recepcionista recepcionista = administrador1.crearRecepcionista(nombre,documento,ROL.RECEPCIONISTA,username,password,ID);
                if(recepcionista != null)
                {
                    System.out.println("Recepcionista creado correctamente!");
                    System.out.println("Listando la informacion del RECEPCIONISTA creado :");
                    System.out.println(recepcionista.toString());
                }else
                {
                    System.out.println("Hubo un error al crear al Recepcionista!");
                }
                break;
            default:
                System.out.println("Opcion incorrecta!");
        }

    }catch (elementoNuloException e)
    {
        System.out.println(e.getMessage());
    }

    }
}