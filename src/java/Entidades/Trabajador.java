/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Telyman
 */
public class Trabajador extends Persona{

    public Trabajador(int id_trabajador, String nombre, String apellidos, String telefono, String residencia) {
        super(nombre, apellidos, telefono, residencia);
        this.id_trabajador = id_trabajador;
    }
     public Trabajador() {
        super();
       
    }

    //Atributos
    public Trabajador(int id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

    private int id_trabajador;

    //Gets y Sets
    
    public int getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(int id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

   
    
}
