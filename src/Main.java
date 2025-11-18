import ENUMS.ROL;
import ENUMS.estadoHabitacion;
import ENUMS.tamanioHabitacion;
import EXCEPTIONS.*;
import GESTORA.gestoraHotel;
import MODELO.*;
import GESTORA.*;
import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String msj;
        Administrador administrador1 = Administrador.getAdmin();
        Recepcionista recepcionista1=new Recepcionista("Juan","44.563.345",ROL.RECEPCIONISTA,"JuanRecep123","contraseña123");
        Recepcionista recepcionista2=new Recepcionista("Pedro","24.332.245",ROL.RECEPCIONISTA,"PedroHotel","pizzaYcoca");
        gestoraHotel<Persona>gestoraHotel=new gestoraHotel<>();
        gestoraHotel<Recepcionista>gestoraHotel1=new gestoraHotel<>();
        gestoraHotel<Habitaciones>gestoraHabitacion=new gestoraHotel<>();
        gestoraHabitaciones gestoraHabitaciones = new gestoraHabitaciones();
        habitacionPremium h1=new habitacionPremium(0, estadoHabitacion.LIBRE, tamanioHabitacion.GRANDE);
        try {
            gestoraHabitaciones.agregarHabitacion(h1);
        } catch (elementoNuloException | elementoInsertadoException | elementoRepetidoException e) {
            System.out.println(e.getMessage());
        }
        Pasajero p1=new Pasajero();
        Date hoy = new Date();
        Date fin = new Date(hoy.getTime() + (3 * 24 * 60 * 60 * 1000));
        Recepcionista r1=new Recepcionista("valentin","4345",ROL.RECEPCIONISTA,"valentindona","123v");

        System.out.println(recepcionista1.isPuedeReservar());

try {
    administrador1.agregarUsuarioLista(recepcionista1);
    administrador1.agregarUsuarioLista(recepcionista2);
} catch (elementoNuloException e) {
    throw new RuntimeException(e);
}

        //----------------------------------------------------------------------------------------//

        //A PARTIR DE ACA COMIENZA EL MENU INTERACTIVO, FALTA REVISAR ALGUNAS COSAS
        //Y CHECKEAR EL USO DEL INSTANCE OF EN EL MAIN

        Scanner sc=new Scanner(System.in);
        System.out.println("MENU FINAL"); //DESPUES BORRAR
        while(true) {

            System.out.println("\n=== BIENVENIDO AL  HOTEl ===");
            System.out.println("1. Crear cuenta ");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("Opción: ");

            try {


                int opcion = sc.nextInt();
                sc.nextLine();

                if (opcion == 1) {
                    System.out.println("Ingrese sus datos: ");
                    System.out.println("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.println("Documento: ");
                    String documento = sc.nextLine();
                    System.out.println("Pais de origen: ");
                    String nacionalidad = sc.nextLine();
                    System.out.println("Domicilio: ");
                    String domicilio = sc.nextLine();
                    System.out.println("Ingrese un nombre de usuario: ");
                    String userName = sc.nextLine();
                    System.out.println("Ingrese una contraseña: ");
                    String contrasena = sc.nextLine();

                    Pasajero nuevoPasajero = new Pasajero(nombre, documento, userName, contrasena, nacionalidad, domicilio);
                    try {
                        administrador1.agregarUsuarioLista(nuevoPasajero);
                    } catch (elementoNuloException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (opcion == 2) {

                    //ACA COMIENZA EL MENU SI EL USUARIO YA ESTABA CREADO
                    try {
                        System.out.println("Ingrese su nombre de usuario: ");
                        String user = sc.nextLine();
                        System.out.println("Ingrese su contraseña");
                        String password = sc.nextLine();


                        Usuario usuario = administrador1.buscarUsuarioLogin(user, password);
                        System.out.println("Iniciaste sesión como: " + usuario.getUsername());

                        boolean continuar = true;


                        while (continuar) {
                            if (usuario instanceof Administrador) {
                                try {
                                    String JsonAux = JsonUtiles.leer("totalBackup");
                                    //leerUnJson devuelve un tokener, mientras que leer devuelve un String.
                                    //no se si esta bien, pero funciona sorprendentemente
                                    JSONArray Array = new JSONArray(JsonAux);
                                    administrador1.devolverDatosJson(Array);

                                }catch (JSONException e)
                                {
                                    System.out.println(e.getMessage());
                                }

                                System.out.println("\n--- MENÚ ADMIN ---");
                                System.out.println("1: Listar Usuario");
                                System.out.println("2: Buscar un Usuario");
                                System.out.println("3: Eliminar un Usuario");
                                System.out.println("4: Crear Pasajero");
                                System.out.println("5: Crear Recepcionista");
                                System.out.println("6: Crear habitacion");
                                System.out.println("7: Listar habitaciones");
                                System.out.println("8: Buscar habitacion");
                                System.out.println("9: Eliminar habitacion");
                                System.out.println("10: Otorgar permisos Checkin");
                                System.out.println("11: Otorgar permisos CheckOut");
                                System.out.println("12: Hacer un BackUp");
                                System.out.println("13: Otorgar permisos Reserva");
                                System.out.println("14: Salir");

                                int op = sc.nextInt();
                                sc.nextLine();
                                try {
                                    switch (op) {
                                        case 1:


                                            System.out.println(administrador1.listarUsuariosCreados());
                                            break;

                                        case 2:
                                            System.out.println("Ingrese el nombre de usuario a buscar: ");
                                            String nombre = sc.nextLine();
                                            Usuario aux = administrador1.buscarXUserName(nombre);
                                            System.out.println(aux);
                                            break;
                                        case 3:
                                            System.out.println("Ingrese el nombre de usuario a eliminar: ");
                                            nombre = sc.nextLine();
                                            msj = administrador1.eliminarUsuario(nombre);
                                            System.out.println(msj);
                                            break;

                                        case 4:
                                            String nombre1, documento, username, password1, origen, domicilio;
                                            System.out.println("CREANDO PASAJERO");
                                            System.out.println("Ingrese el nombre del PASAJERO :");
                                            nombre1 = sc.nextLine();
                                            System.out.println("Ingrese el documento del PASAJERO :");
                                            documento = sc.next();
                                            System.out.println("Ingrese el username del PASAJERO :");
                                            username = sc.next();
                                            System.out.println("Ingrese la contrasenia del PASAJERO : ");
                                            password1 = sc.next();
                                            sc.nextLine(); //Limpio el salto de linea
                                            System.out.println("Ingrese el origen del PASAJERO :");
                                            origen = sc.nextLine();
                                            System.out.println("Ingrese el domicilio del PASAJERO :");
                                            domicilio = sc.nextLine();
                                            Pasajero pasajero = administrador1.crearPasajero(nombre1, documento, username, password1, origen, domicilio);
                                            if (pasajero != null) {
                                                System.out.println("Pasajero creado correctamente!");
                                                msj = administrador1.agregarUsuarioLista(pasajero);
                                                System.out.println(msj);
                                                msj = gestoraHotel.agregarElemento(pasajero);
                                                System.out.println(msj);
                                                System.out.println("Listando la informacion del PASAJERO creado :");
                                                System.out.println(pasajero.toString());
                                            } else {
                                                System.out.println("Hubo un error al crear al pasajero!");
                                            }

                                            break;

                                        case 5:


                                            System.out.println("CREANDO RECEPCIONISTA");
                                            System.out.println("Ingrese el nombre del RECEPCIONISTA :");
                                            nombre = sc.next();
                                            System.out.println("Ingrese el documento del RECEPCIONISTA :");
                                            documento = sc.next();
                                            System.out.println("Ingrese el username del RECEPCIONISTA :");
                                            username = sc.next();
                                            System.out.println("Ingrese la contrasenia del RECEPCIONISTA : ");
                                            password = sc.next();
                                            Recepcionista recepcionista = administrador1.crearRecepcionista(nombre, documento, ROL.RECEPCIONISTA, username, password);
                                            if (recepcionista != null) {
                                                System.out.println("Recepcionista creado correctamente!");
                                                msj = administrador1.agregarUsuarioLista(recepcionista);
                                                System.out.println(msj);
                                                msj = gestoraHotel1.agregarElemento(recepcionista);
                                                System.out.println(msj);
                                                System.out.println("Listando la informacion del RECEPCIONISTA creado :");
                                                System.out.println(recepcionista.toString());

                                            } else {
                                                System.out.println("Hubo un error al crear al Recepcionista!");
                                            }

                                            break;

                                        case 6:

                                            System.out.println("Que tipo de habitacion vas a crear?");
                                            System.out.println("1: Habitacion Estandar");
                                            System.out.println("2: Habitacion Medium");
                                            System.out.println("3: Habitacion Premium");
                                            System.out.println("4: Habitacion Deluxe");
                                            int numHabitacion;
                                            String tamanioHab;


                                            opcion = sc.nextInt();
                                            if(opcion == 1){
                                                System.out.println("ESTANDAR");
                                                System.out.println("Numero de habitacion: ");
                                                numHabitacion = sc.nextInt();
                                                sc.nextLine();
                                                System.out.println("Tamanio de la habitacion: PEQUENIA, MEDIANO o GRANDE");
                                                tamanioHab = sc.nextLine();
                                                habitacionEstandar habitacionEstandar = new habitacionEstandar(numHabitacion, estadoHabitacion.LIBRE, tamanioHabitacion.valueOf(tamanioHab.toUpperCase()));
                                                msj = gestoraHabitaciones.agregarHabitacion(habitacionEstandar);
                                                System.out.println(msj);

                                            } else if (opcion == 2) {
                                                System.out.println("MEDIUM");
                                                System.out.println("Numero de habitacion: ");
                                                numHabitacion = sc.nextInt();
                                                sc.nextLine();
                                                System.out.println("Tamanio de la habitacion: PEQUENIA, MEDIANO o GRANDE");
                                                tamanioHab = sc.nextLine();

                                                habitacionMedium habitacionMedium = new habitacionMedium(numHabitacion, estadoHabitacion.LIBRE, tamanioHabitacion.valueOf(tamanioHab.toUpperCase()));
                                                msj = gestoraHabitaciones.agregarHabitacion(habitacionMedium);
                                                System.out.println(msj);

                                            } else if (opcion == 3) {
                                                System.out.println("PREMIUM");
                                                System.out.println("Numero de habitacion: ");
                                                numHabitacion = sc.nextInt();
                                                sc.nextLine();
                                                System.out.println("Tamanio de la habitacion: PEQUENIA, MEDIANO o GRANDE");
                                                tamanioHab = sc.nextLine();

                                                habitacionPremium habitacionPremium = new habitacionPremium(numHabitacion, estadoHabitacion.LIBRE, tamanioHabitacion.valueOf(tamanioHab.toUpperCase()));
                                                msj = gestoraHabitaciones.agregarHabitacion(habitacionPremium);
                                                System.out.println(msj);

                                            } else if (opcion == 4) {
                                                System.out.println("DELUXE");
                                                System.out.println("Numero de habitacion: ");
                                                numHabitacion = sc.nextInt();
                                                sc.nextLine();
                                                System.out.println("Tamanio de la habitacion: PEQUENIA, MEDIANO o GRANDE");
                                                tamanioHab = sc.nextLine();

                                                habitacionDeluxe habitacionDeluxe = new habitacionDeluxe(numHabitacion, estadoHabitacion.LIBRE, tamanioHabitacion.valueOf(tamanioHab.toUpperCase()));
                                                msj = gestoraHabitaciones.agregarHabitacion(habitacionDeluxe);
                                                System.out.println(msj);
                                            }else{
                                                System.out.println("Opcion incorrecta.");
                                            }
                                            break;

                                        case 7:
                                            System.out.println(gestoraHabitaciones.listarHabitaciones());
                                            break;

                                        case 8:
                                            System.out.println("Ingresa el numero de habitacion:");
                                            int nroHabitacion = sc.nextInt();
                                            Habitaciones h = gestoraHabitaciones.buscarHabitacion(nroHabitacion);
                                            System.out.println(h.toString());
                                            break;

                                        case 9:
                                            System.out.println("Ingresa el numero de la habitacion a eliminar: ");
                                            nroHabitacion = sc.nextInt();
                                            msj = gestoraHabitaciones.eliminarHabitacion(nroHabitacion);
                                            System.out.println(msj);
                                            break;

                                        case 10:
                                            System.out.println("Ingrese el usuario del recepcionsta a otorgar permisos para CHECKIN: ");
                                            msj = sc.nextLine();
                                            Recepcionista r = administrador1.buscarRecepcionista(msj);
                                            System.out.println(administrador1.otorgarPermisosCheckIn(r));
                                            break;

                                        case 11:
                                            System.out.println("Ingrese el usuario del recepcionsta a otorgar permisos para CHECKOUT: ");
                                            msj = sc.nextLine();
                                            Recepcionista rr = administrador1.buscarRecepcionista(msj);
                                            System.out.println(administrador1.otorgarPermisosCheckOut(rr));
                                            break;

                                        case 12:
                                            System.out.println("GENERANDO RESPALDO DE LOS USUARIOS...");
                                            JSONArray jsonTotal = administrador1.backup();
                                            JsonUtiles.grabarUnJson(jsonTotal,"totalBackup.json");
                                            System.out.println("Backup realizado correctamente");
                                            break;


                                        case 13:
                                            System.out.println("Ingrese el usuario del recepcionista a otorgar permisos para realizar Reserva: ");
                                            msj = sc.nextLine();
                                            Recepcionista rrr = administrador1.buscarRecepcionista(msj);
                                            System.out.println(administrador1.otorgarPermisosReserva(rrr));
                                            break;
                                        case 14:
                                            System.out.println("Saliendo del menu...");

                                            jsonTotal = administrador1.backup();
                                            JsonUtiles.grabarUnJson(jsonTotal, "totalBackup.json");

                                            JSONArray jsonHabitaciones = gestoraHabitaciones.backup();
                                            JsonUtiles.grabarUnJson(jsonHabitaciones, "habitacionesBackup.json");

                                            continuar = false;

                                            break;
                                        default:
                                            System.out.println("Opcion incorrecta!");
                                    }
                                } catch (elementoNuloException | FechaInvalidaExpection | elementoInsertadoException |
                                         elementoRepetidoException | listaUsuariosVacioException | JSONException |
                                         elementoBorradoException e) {
                                    System.out.println(e.getMessage());
                                } catch (IllegalArgumentException e){
                                    System.out.println("Tamanio de habitacion no valido.");
                                }

                            } else if (usuario instanceof Pasajero) {
                                Pasajero pasajero = (Pasajero) usuario;


                                System.out.println("\n--- MENÚ PASAJERO ---");
                                System.out.println("1: Crear Reserva");
                                System.out.println("2: Salir");

                                int op = sc.nextInt();
                                sc.nextLine();

                                try {
                                    switch (op) {

                                        case 1:
                                   /*
                                   System.out.println("Ingrese el nombre del socilicitante de la reserva :");
                                   String nombre =  sc.nextLine();

                                    */
                                            System.out.println("Habitaciones disponibles: ");
                                            System.out.println(gestoraHabitaciones.listarHabitaciones());
                                            System.out.println("Ingrese el numero de habitacion que desea reservar");
                                            int numeroHabitacion = sc.nextInt();
                                            sc.nextLine();
                                            Habitaciones habitacion = gestoraHabitacion.buscarHabitacionXnumero(numeroHabitacion);

                                            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                                            Date fechaInicio = null;
                                            Date fechaFin = null;

                                            System.out.print("Ingrese fecha de inicio (dd/MM/yyyy): ");
                                            String strInicio = sc.nextLine();
                                            fechaInicio = formato.parse(strInicio);

                                            System.out.print("Ingrese fecha de fin (dd/MM/yyyy): ");
                                            String strFin = sc.nextLine();
                                            fechaFin = formato.parse(strFin);

                                            //AGREGAR EXPECTION
                                            System.out.println("Ingrese la cantidad de personas en la reserva: ");
                                            int cantPersonas = sc.nextInt();

                                            pasajero.solicitarReserva(habitacion, pasajero, fechaInicio, fechaFin, true, cantPersonas);
                                            break;
                                        case 2:
                                            System.out.println("Saliendo del menu...");
                                            continuar = false;
                                            break;
                                        default:
                                            System.out.println("Opcion incorrecta!");
                                            break;
                                    }

                                }catch (NumeroNegativoException e) {
                                    System.out.println(e.getMessage());
                                } catch (ParseException e) {
                                    throw new RuntimeException(e);
                                }

                            } else if (usuario instanceof Recepcionista) {
                                Recepcionista recepcionista = (Recepcionista) usuario;

                                System.out.println("\n--- MENÚ RECEPCIONISTA ---");
                                System.out.println("1: Realizar CheckIn");
                                System.out.println("2: Realizar CheckOut");
                                System.out.println("3: Verificar Reservas Pendientes");
                                System.out.println("4: Realizar una Reserva Pendiente");
                                System.out.println("5: Salir");

                                int op = sc.nextInt();

                                switch (op) {

                                    case 1:
                                        System.out.println("Ingrese el documento del pasajero para realizar su checkin: ");
                                        String doc = sc.nextLine();

                                        recepcionista.realizarCheckIn(doc);
                                        break;
                                    case 2:
                                        System.out.println("Ingrese el documento del pasajero para realizar su checkout: ");
                                        String doc1 = sc.nextLine();

                                        recepcionista.realizarCheckOut(doc1);
                                        break;

                                    case 3:
                                        System.out.println(recepcionista.mostrarReservasPendientes());
                                        break;

                                    case 4:
                                        System.out.println("Ingresar el ID de la reserva a realizar: ");
                                        int id = sc.nextInt();
                                        recepcionista.realizarReserva(id);
                                        break;

                                    case 5:
                                        System.out.println("Saliendo...");
                                        continuar = false;
                                        break;
                                    default:
                                        System.out.println("Opcion incorrecta!");
                                        break;
                                }


                            }

                        }
                    } catch (UsuarioNoEncontradoException | RecepcionistaNoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (opcion == 3) {
                    System.out.println("SALIENDO DEL SISTEMA");

                    break;
                }
            }catch (InputMismatchException e)
            {
                System.out.println("ERROR. INGRESE CUALQUIER DIGITO MOSTRADO EN PANTALLA!");
                sc.nextLine(); //Limpio el input para que no se cree un bucle infinito, casi me explota la pc
            }


            }



    }
}