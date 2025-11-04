package MODELO;

import java.util.Objects;

public abstract class Persona {
    protected String nombre;
    protected String documento;

    public Persona(String nombre, String documento) {
        this.nombre = nombre;
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    //EQUALS SOBREESCRITO POR DNI
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(documento, persona.documento);
    }
    //HASHCODE SOBREESCRITO POR DNI
    @Override
    public int hashCode() {
        return Objects.hash(documento);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + "\n" +
                ", documento='" + documento + "\n" +
                '}';
    }
}
