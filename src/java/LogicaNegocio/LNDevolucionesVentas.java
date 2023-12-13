/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaNegocio;

/**
 *
 * @author Telyman
 */

import AccesoDatos.ADDevolucionesVentas;
import Entidades.Cliente;
import Entidades.DetallesFactura;
import Entidades.DevolucionesVentas;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LNDevolucionesVentas {
    //Atributo
     private String _mensaje;
    
    public String getMensaje() {
        return _mensaje;
    }
   
    //Llamar a Acceso a Datos
    public int Insertar(DevolucionesVentas devoluciones) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADDevolucionesVentas addevoluciones;
        try {
            addevoluciones = new ADDevolucionesVentas();
            id = addevoluciones.Insertar(devoluciones);
            _mensaje = addevoluciones.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
    
    //Listar clientes con RESULT SET
    public ResultSet ListarRegistros(String condicion, String orden) throws Exception{
        ResultSet resultado;
        ADDevolucionesVentas addevoluciones;
        try {
            addevoluciones = new ADDevolucionesVentas();
            resultado= addevoluciones.ListarRegistros(condicion, orden);
        } catch (Exception e) {
            throw  e;
        }
        return resultado;
    }
    //FIN Listar clientes con RESULT SET
    
    //Listar clientes con la LISTA
    public List<DevolucionesVentas> ListarRegistros(String condicion) throws Exception{
        List<DevolucionesVentas> resultado = new ArrayList();
        ADDevolucionesVentas addevoluciones;
          try {
            addevoluciones = new ADDevolucionesVentas();
            resultado= addevoluciones.ListarRegistros(condicion);
        } catch (Exception e) {
            throw  e;
        }
        return resultado;
        
    }
    //FINListar clientes con la LISTA
    
    //Obtener un registro
    public DevolucionesVentas ObtenerRegistro(String condicion) throws Exception{
        DevolucionesVentas resultado;
        ADDevolucionesVentas addevoluciones;
        try{
            addevoluciones = new ADDevolucionesVentas();
            resultado = addevoluciones.ObtenerRegistro(condicion);
            if(resultado.isExiste()){
                _mensaje = "Devolución de venta recuperada exitosamente";
                
            }else{
                _mensaje = "La devolución no existe";
            }
        }catch(Exception e){
            throw e;
        }
        return resultado;
    }
    
    //Obtener registro FIN
       public int Modificar(DevolucionesVentas devoluciones) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADDevolucionesVentas addevoluciones;
        try {
            addevoluciones = new ADDevolucionesVentas();
            id = addevoluciones.Modificar(devoluciones);
            _mensaje = addevoluciones.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
       public int Eliminar(DevolucionesVentas devoluciones) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADDevolucionesVentas addevoluciones;
        try {
            addevoluciones = new ADDevolucionesVentas();
            id = addevoluciones.Eliminar(devoluciones);
            _mensaje = addevoluciones.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
        public int VerificarIns(DevolucionesVentas devoluciones) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADDevolucionesVentas addevoluciones;
        try {
            addevoluciones = new ADDevolucionesVentas();
            id = addevoluciones.Verificar_Antes_Insert(devoluciones);
            _mensaje = addevoluciones.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
        public int VerificarMod(DevolucionesVentas devoluciones) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADDevolucionesVentas addevoluciones;
        try {
            addevoluciones = new ADDevolucionesVentas();
            id = addevoluciones.Verificar_Antes_Mod(devoluciones);
            _mensaje = addevoluciones.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    }
           public int VerificarEliminar(DevolucionesVentas devoluciones) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADDevolucionesVentas addevoluciones;
        try {
            addevoluciones = new ADDevolucionesVentas();
            id = addevoluciones.Verificar_Antes_Eliminar(devoluciones);
            _mensaje = addevoluciones.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    }
           public int VerificarFecha(DevolucionesVentas devoluciones) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADDevolucionesVentas addevoluciones;
        try {
            addevoluciones = new ADDevolucionesVentas();
            id = addevoluciones.Verificar_Fecha(devoluciones);
            _mensaje = addevoluciones.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
           public int VerificarIng2(DevolucionesVentas devoluciones) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADDevolucionesVentas addevoluciones;
        try {
            addevoluciones = new ADDevolucionesVentas();
            id = addevoluciones.Verificar_Ingreso_2(devoluciones);
            _mensaje = addevoluciones.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
            public int VerificarExiste(DevolucionesVentas devoluciones) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADDevolucionesVentas addevoluciones;
        try {
            addevoluciones = new ADDevolucionesVentas();
            id = addevoluciones.VerificarExiste(devoluciones);
            _mensaje = addevoluciones.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
    
    
    
}
