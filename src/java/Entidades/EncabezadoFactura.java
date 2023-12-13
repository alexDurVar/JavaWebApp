/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.sql.Date;

/**
 *
 * @author Telyman
 */
public class EncabezadoFactura {
   
    //Atributos
    private int numero_factura;
    private int id_trabajador;
    private Date fecha;
    private float impuesto;
    private float monto_a_pagar;
    private int id_cliente;
    private String nombre;
    private String estado;
    private float subtotal;
    
    private boolean existe;
    
    //Cosntructores
        public EncabezadoFactura(int numero_factura, int id_trabajador, Date fecha, float impuesto, float monto_a_pagar, int id_cliente, String estado, float subtotal) {
        this.numero_factura = numero_factura;
        this.id_trabajador = id_trabajador;
        this.fecha = fecha;
        this.impuesto = impuesto;
        this.monto_a_pagar = monto_a_pagar;
        this.id_cliente = id_cliente;
        this.estado = estado;
        this.subtotal = subtotal;
        this.existe = true;
    }
    public EncabezadoFactura() {
      this.numero_factura = 0;
        this.id_trabajador = 0;
       
        this.impuesto = 0;
        this.monto_a_pagar = 0;
        this.id_cliente = 0;
        this.estado = "";
    }

    
    //Gets y Sets
     public int getNumero_factura() {
        return numero_factura;
    }
     public boolean isExiste() {
        return existe;
    }

    public void setNumero_factura(int numero_factura) {
        this.numero_factura = numero_factura;
    }
    
    public void setExiste(boolean  existe){
        this.existe = existe;
    }

    public int getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(int id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public float getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(float impuesto) {
        this.impuesto = impuesto;
    }

    public float getMonto_a_pagar() {
        return monto_a_pagar;
    }

    public void setMonto_a_pagar(float monto_a_pagar) {
        this.monto_a_pagar = monto_a_pagar;
    }
    

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }
}
