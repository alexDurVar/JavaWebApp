/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Steven
 */
public class Producto {

     //Atributos
    private int numero_producto;
    private String nombre_producto;
    private String tipo_producto;
    private float precio_venta;
    private int cantidad;
    private float impuesto;
    private float descuento;
    private String descripcion;
    private String garantia;
    private String disponibilidad;
    private int num_proveedor;
    private float precio_compra;
    private float precio_total;
    private boolean existe;
    
    //Constructores
        public Producto(int numero_producto, String nombre_producto, String tipo_producto, float precio_venta, int cantidad, float impuesto, float descuento, String descripcion, String garantia, String disponibilidad, int num_proveedor,float precio_compra,float precio_total) {
        this.numero_producto = numero_producto;
        this.nombre_producto = nombre_producto;
        this.tipo_producto = tipo_producto;
        this.precio_venta = precio_venta;
        this.cantidad = cantidad;
        this.impuesto = impuesto;
        this.descuento = descuento;
        this.descripcion = descripcion;
        this.garantia = garantia;
        this.disponibilidad = disponibilidad;
        this.num_proveedor = num_proveedor;
        this.precio_compra = precio_compra;
        this.precio_total = precio_total;
        this.existe = true;
    }
        
        public Producto() {
        
    }
    
    
    //Gets y Sets
    
    public int getNumero_producto() {
        return numero_producto;
    }

    public void setNumero_producto(int numero_producto) {
        this.numero_producto = numero_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getTipo_producto() {
        return tipo_producto;
    }

    public void setTipo_producto(String tipo_producto) {
        this.tipo_producto = tipo_producto;
    }

    public float getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(float precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(float impuesto) {
        this.impuesto = impuesto;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public int getNum_proveedor() {
        return num_proveedor;
    }

    public void setNum_proveedor(int num_proveedor) {
        this.num_proveedor = num_proveedor;
    }
    

    public float getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(float precio_compra) {
        this.precio_compra = precio_compra;
    }

    public float getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(float precio_total) {
        this.precio_total = precio_total;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    
}
