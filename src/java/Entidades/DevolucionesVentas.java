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
public class DevolucionesVentas {

    //Atributos

    private int numero_devolucion;
    private int numero_factura;
    private int numero_producto;
    private Date fecha_realizacion;
    private Date fecha_limite;
    private String motivo_devolucion;
    private String aceptacion_devolucion;
    private int id_trabajador;
    private boolean existe;
    
    //Constructores
        public DevolucionesVentas(int numero_devolucion, int numero_factura,int numero_producto ,Date fecha_realizacion,Date fecha_limite ,String motivo_devolucion, String aceptacion_devolucion, int id_trabajador) {
        this.numero_devolucion = numero_devolucion;
        this.numero_factura = numero_factura;
        this.numero_producto = numero_producto;
        this.fecha_realizacion = fecha_realizacion;
        this.fecha_limite = fecha_limite;
        this.motivo_devolucion = motivo_devolucion;
        this.aceptacion_devolucion = aceptacion_devolucion;
        this.id_trabajador = id_trabajador;
        this.existe = true;
    }
        public DevolucionesVentas() {
       
    }
    
    //Gets y Sets
    public int getNumero_devolucion() {
        return numero_devolucion;
    }

    public void setNumero_devolucion(int numero_devolucion) {
        this.numero_devolucion = numero_devolucion;
    }

    public int getNumero_factura() {
        return numero_factura;
    }

    public void setNumero_factura(int numero_factura) {
        this.numero_factura = numero_factura;
    }

    public Date getFecha_realizacion() {
        return fecha_realizacion;
    }

    public void setFecha_realizacion(Date fecha_realizacion) {
        this.fecha_realizacion = fecha_realizacion;
    }
  public Date getFecha_limite() {
        return fecha_limite;
    }

    public void setFecha_limite(Date fecha_limite) {
        this.fecha_limite = fecha_limite;
    }

    public String getMotivo_devolucion() {
        return motivo_devolucion;
    }

    public void setMotivo_devolucion(String motivo_devolucion) {
        this.motivo_devolucion = motivo_devolucion;
    }

    public String getAceptacion_devolucion() {
        return aceptacion_devolucion;
    }

    public void setAceptacion_devolucion(String aceptacion_devolucion) {
        this.aceptacion_devolucion = aceptacion_devolucion;
    }

    public int getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(int id_trabajador) {
        this.id_trabajador = id_trabajador;
    }
    

    public int getNumero_producto() {
        return numero_producto;
    }

    public void setNumero_producto(int numero_producto) {
        this.numero_producto = numero_producto;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
}
