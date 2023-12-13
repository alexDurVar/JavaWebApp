/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaNegocio;

/**
 *
 * @author Telyman
 */


import AccesoDatos.ADProveedor;
import Entidades.Proveedor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LNProovedor {
     //Atributo
     private String _mensaje;
    
    public String getMensaje() {
        return _mensaje;
    }
   
    //Llamar a Acceso a Datos
    public int Insertar(Proveedor proveedor) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADProveedor adproveedor;
        try {
            adproveedor = new ADProveedor();
            id = adproveedor.Insertar(proveedor);
            _mensaje = adproveedor.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
    
    //Listar proveedores con RESULT SET
    public ResultSet ListarRegistros(String condicion, String orden) throws Exception{
        ResultSet resultado;
        ADProveedor adproveedor;
        try {
            adproveedor= new ADProveedor();
            resultado= adproveedor.ListarRegistros(condicion, orden);
        } catch (Exception e) {
            throw  e;
        }
        return resultado;
    }
    //FIN Listar proveedor con RESULT SET
    
    //Listar clientes con la LISTA
    public List<Proveedor> ListarRegistros(String condicion) throws Exception{
        List<Proveedor> resultado = new ArrayList();
        ADProveedor adproveedor;
          try {
            adproveedor = new ADProveedor();
            resultado= adproveedor.ListarRegistros(condicion);
        } catch (Exception e) {
            throw  e;
        }
        return resultado;
        
    }
    //FINListar proveedor con la LISTA
    
    //Obtener un registro
    public Proveedor ObtenerRegistro(String condicion) throws Exception{
        Proveedor resultado;
        ADProveedor adproveedor;
        try{
            adproveedor = new ADProveedor();
            resultado = adproveedor.ObtenerRegistro(condicion);
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
    
        public int Modificar(Proveedor proveedor) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADProveedor adproveedor;
        try {
            adproveedor = new ADProveedor();
            id = adproveedor.Modificar(proveedor);
            _mensaje = adproveedor.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
        
        
       public int Eliminar(Proveedor proveedor) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADProveedor adproveedor;
        try {
            adproveedor = new ADProveedor();
            id = adproveedor.Eliminar(proveedor);
            _mensaje = adproveedor.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
       
        public int VeriricarInsercion(Proveedor proveedor) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADProveedor adproveedor;
        try {
            adproveedor = new ADProveedor();
            id = adproveedor.Verificar_Antes_Insert(proveedor);
            _mensaje = adproveedor.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
         public int VeriricarMod(Proveedor proveedor) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADProveedor adproveedor;
        try {
            adproveedor = new ADProveedor();
            id = adproveedor.Verificar_Antes_Mod(proveedor);
            _mensaje = adproveedor.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
            public int VeriricarEliminar(Proveedor proveedor) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADProveedor adproveedor;
        try {
            adproveedor = new ADProveedor();
            id = adproveedor.Verificar_Antes_Eliminar(proveedor);
            _mensaje = adproveedor.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
              public int VeriricarExiste(Proveedor proveedor) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADProveedor adproveedor;
        try {
            adproveedor = new ADProveedor();
            id = adproveedor.Verificar_Existe(proveedor);
            _mensaje = adproveedor.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
}
