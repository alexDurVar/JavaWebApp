/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaNegocio;

/**
 *
 * @author Telyman
 */


import AccesoDatos.ADDetalleFactura;
import Entidades.Cliente;
import Entidades.DetallesFactura;
import Entidades.EncabezadoFactura;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LNDetalleFactura {
    //Atributo
     private String _mensaje;
    
    public String getMensaje() {
        return _mensaje;
    }
   
    //Llamar a Acceso a Datos
    public int Insertar(DetallesFactura detalle) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIEN
        ADDetalleFactura addetalle;
        try {
            addetalle= new ADDetalleFactura();
            id = addetalle.Insertar(detalle);
            _mensaje = addetalle.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
    
    //Listar clientes con RESULT SET
    public ResultSet ListarRegistros(String condicion, String orden) throws Exception{
       ResultSet resultado;
        ADDetalleFactura addetalle;
        try {
            addetalle = new ADDetalleFactura();
            resultado= addetalle.ListarRegistros(condicion, orden);
        } catch (Exception e) {
            throw  e;
        }
        return resultado;
    }
    //FIN Listar clientes con RESULT SET
    
    //Listar clientes con la LISTA
    public List<DetallesFactura> ListarRegistros(String condicion) throws Exception{
        List<DetallesFactura> resultado = new ArrayList();
        ADDetalleFactura addetalle;
          try {
            addetalle = new ADDetalleFactura();
            resultado= addetalle.ListarRegistros(condicion);
        } catch (Exception e) {
            throw  e;
        }
        return resultado;
        
    }
    //FINListar clientes con la LISTA
    
    //Obtener un registro
    public DetallesFactura ObtenerRegistro(String condicion) throws Exception{
        DetallesFactura resultado;
        ADDetalleFactura addetalle;
        try{
            addetalle= new ADDetalleFactura();
            resultado = addetalle.ObtenerRegistro(condicion);
            if(resultado.isExiste()){
                _mensaje = "Detalle de factura recuperado exitosamente";
                
            }else{
                _mensaje = "El Detalle de factura no existe";
            }
        }catch(Exception e){
            throw e;
        }
        return resultado;
    }
    
    //Obtener registro FIN
       public int Modificar(DetallesFactura detalles) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADDetalleFactura addetalle;
        try {
            addetalle = new ADDetalleFactura();
            id = addetalle.Modificar(detalles);
            _mensaje = addetalle.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
       public int Eliminar(DetallesFactura detalles) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADDetalleFactura addetalle;
        try {
            addetalle = new ADDetalleFactura();
            id = addetalle.EliminarDetalle(detalles);
            _mensaje = addetalle.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
         public int VerificarIns(DetallesFactura detalles) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADDetalleFactura addetalle;
        try {
            addetalle = new ADDetalleFactura();
            id = addetalle.Verificar_Antes_Insert(detalles);
            _mensaje = addetalle.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
             public int VerificarMod(DetallesFactura detalles) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADDetalleFactura addetalle;
        try {
            addetalle = new ADDetalleFactura();
            id = addetalle.Verificar_Antes_Mod(detalles);
            _mensaje = addetalle.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
      
}
