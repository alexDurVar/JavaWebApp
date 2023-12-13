/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.util.logging.Logger;

/**
 *
 * @author Telyman
 */
public class Persona {

  

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getResidencia() {
        return residencia;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public Persona( String nombre, String apellidos, String telefono, String residencia) {
       
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.residencia = residencia;
        this.existe = true;
    }
      public Persona() {
       this.nombre = "";
        this.apellidos = "";
        this.telefono = "";
        this.residencia = "";
    }
    
    
   
    private String nombre;
    private String apellidos;
    private String telefono;
    private String residencia;
    private boolean existe;
    
}
