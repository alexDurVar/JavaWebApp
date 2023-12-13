/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaNegocio;

import AccesoDatos.ADCliente;
import Entidades.Cliente;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Steven
 */
public class LNCliente {
    
   //Atributo
     private String _mensaje;
    
    public String getMensaje() {
        return _mensaje;
    }
   
    //Llamar a Acceso a Datos
    public int Insertar(Cliente cliente) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADCliente adcliente;
        try {
            adcliente = new ADCliente();
            id = adcliente.Insertar(cliente);
            _mensaje = adcliente.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
    
    //Listar clientes con RESULT SET
    public ResultSet ListarRegistros(String condicion, String orden) throws Exception{
        ResultSet resultado;
        ADCliente adcliente;
        try {
            adcliente = new ADCliente();
            resultado= adcliente.ListarRegistros(condicion, orden);
        } catch (Exception e) {
            throw  e;
        }
        return resultado;
    }
    //FIN Listar clientes con RESULT SET
    
    //Listar clientes con la LISTA
    public List<Cliente> ListarRegistros(String condicion) throws Exception{
        List<Cliente> resultado = new ArrayList();
        ADCliente adcliente = new ADCliente();
          try {
            
            resultado= adcliente.ListarRegistros(condicion);
        } catch (Exception e) {
            throw  e;
        }
        return resultado;
        
    }
    //FINListar clientes con la LISTA
    /*
       
    public List<Cliente> ListarRegistros(String condicion) throws Exception{
        List<Cliente> resultado = new ArrayList();
        DA_Cliente accesoDatos  = new DA_Cliente();
        
        try {
            resultado = accesoDatos.ListarRegistros(condicion);
        } 
        catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    */
    
    
    
    //Obtener un registro
    public Cliente ObtenerRegistro(String condicion) throws Exception{
        Cliente resultado;
        ADCliente adcliente;
        try{
            adcliente = new ADCliente();
            resultado = adcliente.ObtenerRegistro(condicion);
            if(resultado.isExiste()){
                _mensaje = "Client recuperado exitosamente";
                
            }else{
                _mensaje = "El cliente no existe";
            }
        }catch(Exception e){
            throw e;
        }
        return resultado;
    }
    
    
    //MODIFICAR UN CLIENTE
    
    public int Modificar(Cliente cliente) throws Exception{
        int resultado = -1;
        ADCliente adcliente;
        try {
            adcliente = new ADCliente();
            resultado = adcliente.Modificar(cliente);
            _mensaje = adcliente.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
      //ELIMINAR UN CLIENTE
    
    public int Eliminar(Cliente cliente) throws Exception{
        int resultado = -1;
        ADCliente adcliente;
        try {
            adcliente = new ADCliente();
            resultado = adcliente.Eliminar(cliente);
            _mensaje = adcliente.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
   
    
        public int VerificarInsert(Cliente cliente) throws Exception{
        int resultado = 0;
        ADCliente adcliente;
        try {
            adcliente = new ADCliente();
            resultado = adcliente.Verificar_Antes_Insert(cliente);
            _mensaje = adcliente.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
       public int VerificarMod(Cliente cliente) throws Exception{
        int resultado = 0;
        ADCliente adcliente;
        try {
            adcliente = new ADCliente();
            resultado = adcliente.Verificar_Antes_Mod(cliente);
            _mensaje = adcliente.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
        public int VerificarEliminar(Cliente cliente) throws Exception{
        int resultado = 0;
        ADCliente adcliente;
        try {
            adcliente = new ADCliente();
            resultado = adcliente.Verificar_Antes_ELIMINAR(cliente);
            _mensaje = adcliente.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
         public int VerificarExiste(Cliente cliente) throws Exception{
        int resultado = 0;
        ADCliente adcliente;
        try {
            adcliente = new ADCliente();
            resultado = adcliente.Verificar_Existe(cliente);
            _mensaje = adcliente.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
}
