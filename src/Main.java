import ENUMS.ROL;
import EXCEPTIONS.*;
import GESTORA.gestoraHotel;
import INTERFACE.ItoJson;
import MODELO.*;
import org.json.JSONArray;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        habitacionPremium h1=new habitacionPremium();
        Pasajero p1=new Pasajero();
        Date hoy = new Date();
        Date fin = new Date(hoy.getTime() + (3 * 24 * 60 * 60 * 1000));
        Recepcionista r1=new Recepcionista("valentin","4345",ROL.RECEPCIONISTA,"valentindona","123v");



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
        while(true){
            System.out.println("\n=== BIENVENIDO AL  HOTEl ===");
            System.out.println("1. Crear cuenta ");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("Opción: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            if (opcion==1){
                System.out.println("Ingrese sus datos: ");
                System.out.println("Nombre: ");
                String nombre=sc.nextLine();
                System.out.println("Documento: ");
                String documento=sc.nextLine();
                System.out.println("Pais de origen: ");
                String nacionalidad=sc.nextLine();
                System.out.println("Domicilio: ");
                String domicilio=sc.nextLine();
                System.out.println("Ingrese un nombre de usuario: ");
                String userName=sc.nextLine();
                System.out.println("Ingrese una contraseña: ");
                String contrasena=sc.nextLine();

                Pasajero nuevoPasajero=new Pasajero(nombre,documento,userName,contrasena,nacionalidad,domicilio);
                try {
                    administrador1.agregarUsuarioLista(nuevoPasajero);
                } catch (elementoNuloException e) {
                    System.out.println(e.getMessage());
                }
            } else if (opcion==2) {

                //ACA COMIENZA EL MENU SI EL USUARIO YA ESTABA CREADO
                try {
                System.out.println("Ingrese su nombre de usuario: ");
                String user=sc.nextLine();
                System.out.println("Ingrese su contraseña");
                String password=sc.nextLine();



                    Usuario usuario = administrador1.buscarUsuarioLogin(user, password);
                    System.out.println("Iniciaste sesión como: " + usuario.getUsername());

                    boolean continuar = true;


                    while (continuar) {
                        if (usuario instanceof Administrador) {


                            System.out.println("\n--- MENÚ ADMIN ---");
                            System.out.println("1: Listar Usuario");
                            System.out.println("2: Buscar un Usuario");
                            System.out.println("3: Eliminar un Usuario");
                            System.out.println("4: Crear Pasajero");
                            System.out.println("5: Crear Recepcionista");
                            System.out.println("6: Otorgar permisos Checkin");
                            System.out.println("7: Otorgar permisos CheckOut");
                            System.out.println("8: Hacer un BackUp");
                            System.out.println("9: Otorgar permisos Reserva");

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
                                        password = sc.next();
                                        sc.nextLine(); //Limpio el salto de linea
                                        System.out.println("Ingrese el origen del PASAJERO :");
                                        origen = sc.nextLine();
                                        System.out.println("Ingrese el domicilio del PASAJERO :");
                                        domicilio = sc.nextLine();
                                        Pasajero pasajero = administrador1.crearPasajero(nombre1, documento, username, password, origen, domicilio);
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
                                        System.out.println("Ingrese el usuario del recepcionsta a otorgar permisos para CHECKIN: ");
                                        msj = sc.nextLine();
                                        Recepcionista r = administrador1.buscarRecepcionista(msj);
                                        System.out.println(administrador1.otorgarPermisosCheckIn(r));
                                        break;

                                    case 7:
                                        System.out.println("Ingrese el usuario del recepcionsta a otorgar permisos para CHECKOUT: ");
                                        msj = sc.nextLine();
                                        Recepcionista rr = administrador1.buscarRecepcionista(msj);
                                        administrador1.otorgarPermisosCheckOut(rr);
                                        break;

                                    case 8:
                                        administrador1.backup();
                                        break;


                                    case 9:
                                        System.out.println("Ingrese el usuario del recepcionista a otorgar permisos para realizar Recerva: ");
                                        msj = sc.nextLine();
                                        Recepcionista rrr = administrador1.buscarRecepcionista(msj);
                                        administrador1.otorgarPermisosReserva(rrr);
                                        break;
                                }
                            } catch (elementoNuloException e) {
                                System.out.println(e.getMessage());
                            } catch (elementoInsertadoException | elementoRepetidoException e) {
                                throw new RuntimeException(e);
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
                                        System.out.println("Ingrese el numero de habitacion que desea reservar");
                                        int numeroHabitacion = sc.nextInt();
                                        sc.nextLine();
                                        Habitaciones habitacion = gestoraHotel.buscarHabitacionXnumero(numeroHabitacion);

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
                                }

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
                                    int id=sc.nextInt();
                                    recepcionista.realizarReserva(id);
                                    break;

                                case 5:
                                    System.out.println("Saliendo...");
                                    continuar = false;
                                    break;
                            }

                        }

                    }
                }catch (UsuarioNoEncontradoException | RecepcionistaNoEncontradoException e) {
                    System.out.println(e.getMessage());
                }
            }else if(opcion == 3)
            {
                System.out.println("SALIENDO DEL SISTEMA");
                break;
            }


        }


    }
}