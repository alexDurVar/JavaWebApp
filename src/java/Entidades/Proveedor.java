/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Telyman
 */
public class Proveedor {

    //Atributos
    private int num_proveedor;
    private String nombre_org;
    private String correo;
    private String direccion;
    private String telefono;
    private boolean existe;
    
    //Constructores
        public Proveedor(int num_proveedor, String nombre_org, String correo, String direccion, String telefono) {
        this.num_proveedor = num_proveedor;
        this.nombre_org = nombre_org;
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.existe = true;
    }
        
        public Proveedor() {
        
    }
    
    //Gets y Sets
    public int getNum_proveedor() {
        return num_proveedor;
    }

    public void setNum_proveedor(int num_proveedor) {
        this.num_proveedor = num_proveedor;
    }

    public String getNombre_org() {
        return nombre_org;
    }

    public void setNombre_org(String nombre_org) {
        this.nombre_org = nombre_org;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    
    
}
