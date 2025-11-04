package MODELO;

import ENUMS.ROL;

public abstract class Usuario extends Persona {
    protected ROL rol;
    protected String username;
    protected String password;

    public Usuario(String nombre, String documento, ROL rol, String username, String password) {
        super(nombre, documento);
        this.rol = rol;
        this.username = username;
        this.password = password;
    }
    public Usuario() {
        super("", "");
        this.rol = ROL.PASAJERO; //POR DEFECTO
        this.username = "";
        this.password = "";
    }

    public ROL getRol() {
        return rol;
    }

    public void setRol(ROL rol) {
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ROL retornarRol() //Metodo retornarRol para gestionar los multiples usuarios sin filtrar por instanceOf constantemente. Nos sirve a futuro
    {
        return rol;
    }

    //FALTA QUIZAS SOBREESCRIBIR EL EQUALS POR PASSWORD ?? PERO TEORICAMENTE YA COMPARTE EL EQUALS DE SU SUPERCLASE QUE ES PERSONA.


    @Override
    public String toString() {
        return "Usuario{" + "\n" +
                super.toString() + "\n" +
                "rol=" + rol + "\n" +
                ", username='" + username + "\n" +
                ", password='" + password  + "\n" +
                '}';
    }

    //FALTA EL TOJSON
    //FALTA METODOS

}
