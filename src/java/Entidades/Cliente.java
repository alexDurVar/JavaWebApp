
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Steven
 */
public class Cliente extends Persona{

    public Cliente(int id_cliente, String nombre, String apellidos, String telefono, String residencia) {
        super(nombre, apellidos, telefono, residencia);
        this.id_cliente = id_cliente;
    }
     public Cliente() {
        super();
        this.id_cliente = 0;
      
    }

    
    public Cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
   //Atributos
    private int id_cliente;


    
    //Gets y Sets
     public int getId_cliente() {    
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }


    
}
