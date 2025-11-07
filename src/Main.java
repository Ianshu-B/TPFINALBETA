import ENUMS.ROL;
import EXCEPTIONS.elementoNuloException;
import MODELO.Administrador;
import MODELO.JsonUtiles;
import MODELO.Pasajero;
import MODELO.Recepcionista;
import org.json.JSONArray;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String msj;
        Administrador administrador1 = new Administrador("Ian","4545A", ROL.ADMINISTRADOR,"adminIan","454545");
    try {
        //TESTEANDO CREAR USUARIO
        boolean continuar = true;
        Scanner sc = new Scanner(System.in);
        while (continuar) {
            int rta;
            do {
                System.out.println("1. Crear pasajero, 2.Crear Recepcionista. Digite su respuesta :");
                rta = sc.nextInt();
                sc.nextLine(); //Limpio el salto de linea
                if (rta != 1 && rta != 2) {
                    System.out.println("Opcion incorrecta, ingrese nuevamente!");
                }
            } while (rta != 1 && rta != 2);

            switch (rta) {
                case 1:
                    String nombre, documento, username, password, origen, domicilio;
                    System.out.println("CREANDO PASAJERO");
                    System.out.println("Ingrese el nombre del PASAJERO :");
                    nombre = sc.nextLine();
                    System.out.println("Ingrese el documento del PASAJERO :");
                    documento = sc.next();
                    System.out.println("Ingrese el username del PASAJERO :");
                    username = sc.next();
                    System.out.println("Ingrese la contrasenia del PASAJERO : ");
                    password = sc.next();
                    sc.nextLine(); //Limpio el salto de linea
                    System.out.println("Ingrese el origen del PASAJERO :");
                    origen = sc.nextLine();
                    System.out.println("Ingrese el domicilio del PASAJERO :");
                    domicilio = sc.nextLine();
                    Pasajero pasajero = administrador1.crearPasajero(nombre, documento, ROL.PASAJERO, username, password, origen, domicilio);
                    if (pasajero != null) {
                        System.out.println("Pasajero creado correctamente!");
                        msj = administrador1.agregarUsuarioLista(pasajero);
                        System.out.println(msj);
                        System.out.println("Listando la informacion del PASAJERO creado :");
                        System.out.println(pasajero.toString());
                        /*
                        System.out.println("REALIZANDO BACKUP DEL PASAJERO :");
                        JSONArray JsonPasajero = new JSONArray();
                        JsonPasajero = administrador1.backup();
                        JsonUtiles.grabarUnJson(JsonPasajero, "BackupPasajero.json");
                        System.out.println("BACKUP CREADO CORRECTAMENTE!");

                         */
                    } else {
                        System.out.println("Hubo un error al crear al pasajero!");
                    }
                    break;
                case 2:
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
                    Recepcionista recepcionista = administrador1.crearRecepcionista(nombre, documento, ROL.RECEPCIONISTA, username, password, ID);
                    if (recepcionista != null) {
                        System.out.println("Recepcionista creado correctamente!");
                        msj = administrador1.agregarUsuarioLista(recepcionista);
                        System.out.println(msj);
                        System.out.println("Listando la informacion del RECEPCIONISTA creado :");
                        System.out.println(recepcionista.toString());
                        /*
                        System.out.println("REALIZANDO BACKUP DEL RECEPCIONISTA :");
                        JSONArray JsonRecepcionista = new JSONArray();
                        JsonRecepcionista = administrador1.backup();
                        JsonUtiles.grabarUnJson(JsonRecepcionista, "BackupRecepcionista.json");
                        System.out.println("BACKUP CREADO CORRECTAMENTE!");

                         */
                    } else {
                        System.out.println("Hubo un error al crear al Recepcionista!");
                    }
                    break;
                default:
                    System.out.println("Opcion incorrecta!");
            }
            String respuesta;
            System.out.println("Si quiere crear otro usuario digite 's'. De lo contrario, digite 'n'");
            respuesta = sc.next().toLowerCase();
            if(!respuesta.equals("s"))
            {
                continuar = false;
                System.out.println("SALIENDO DEL SISTEMA");
            }

        }

        System.out.println("LISTANDO TOTAL DE USUARIOS CREADOS");
        msj = administrador1.listarUsuariosCreados();
        System.out.println(msj);
        System.out.println("GENERANDO BACKUPS DE LOS USUARIOS CREADOS");
        JSONArray jsonArrayTotalElementos = new JSONArray();
        jsonArrayTotalElementos = administrador1.backup(); //Guardo el total de elementos en un JSON. No se que tan necesario sea o si esta bien pero lo dejo por las dudas
        JsonUtiles.grabarUnJson(jsonArrayTotalElementos, "TotalElementos.json");

    }catch (elementoNuloException e)
    {
        System.out.println(e.getMessage());
    }

    }
    //pruebacomit
}