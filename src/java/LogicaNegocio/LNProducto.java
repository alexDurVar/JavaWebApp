/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaNegocio;

/**
 *
 * @author Telyman
 */


import AccesoDatos.ADProducto;

import Entidades.Cliente;
import Entidades.Producto;
import Entidades.Proveedor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class LNProducto {
    //Atributo
     private String _mensaje;
    
    public String getMensaje() {
        return _mensaje;
    }
   
    //Llamar a Acceso a Datos
    public int Insertar(Producto producto) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADProducto adproducto;
        try {
            adproducto = new ADProducto();
            id = adproducto.Insertar(producto);
            _mensaje = adproducto.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
    
    //Listar proveedores con RESULT SET
    public ResultSet ListarRegistros(String condicion, String orden) throws Exception{
        ResultSet resultado;
        ADProducto adproducto;
        try {
            adproducto= new ADProducto();
            resultado= adproducto.ListarRegistros(condicion, orden);
        } catch (Exception e) {
            throw  e;
        }
        return resultado;
    }
    //FIN Listar proveedor con RESULT SET
    
    //Listar clientes con la LISTA
    public List<Producto> ListarRegistros(String condicion) throws Exception{
        List<Producto> resultado = new ArrayList();
        ADProducto adprodcuto;
          try {
            adprodcuto = new ADProducto();
            resultado= adprodcuto.ListarRegistros(condicion);
        } catch (Exception e) {
            throw  e;
        }
        return resultado;
        
    }
    //FINListar producto con la LISTA
    
    //Obtener un registro
    public Producto ObtenerRegistro(String condicion) throws Exception{
        Producto resultado;
        ADProducto adproducto;
        try{
            adproducto= new ADProducto();
            resultado = adproducto.ObtenerRegistro(condicion);
            if(resultado.isExiste()){
                _mensaje = "Proveedor recuperado exitosamente";
                
            }else{
                _mensaje = "El proveedor no existe";
            }
        }catch(Exception e){
            throw e;
        }
        return resultado;
    }
    
    //Obtener registro FIN
    
        public int Modificar(Producto producto) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADProducto adproducto;
        try {
            adproducto = new ADProducto();
            id = adproducto.Modificar(producto);
            _mensaje = adproducto.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    }   
        public int Eliminar(Producto producto) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADProducto adproducto;
        try {
            adproducto = new ADProducto();
            id = adproducto.Eliminar(producto);
            _mensaje = adproducto.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
          public int VerificarIns(Producto producto) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADProducto adproducto;
        try {
            adproducto = new ADProducto();
            id = adproducto.Verificar_Antes_Insert(producto);
            _mensaje = adproducto.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    }
            public int VerificarMod(Producto producto) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADProducto adproducto;
        try {
            adproducto = new ADProducto();
            id = adproducto.Verificar_Antes_Mod(producto);
            _mensaje = adproducto.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    }
            public int VerificarProd(Producto producto) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADProducto adproducto;
        try {
            adproducto = new ADProducto();
            id = adproducto.Verificar_Antes_Eliminar(producto);
            _mensaje = adproducto.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    }
            public int VerificarExiste(Producto producto) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADProducto adproducto;
        try {
            adproducto = new ADProducto();
            id = adproducto.Verificar_Existe(producto);
            _mensaje = adproducto.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    }
}
