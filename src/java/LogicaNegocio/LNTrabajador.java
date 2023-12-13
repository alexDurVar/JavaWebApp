/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaNegocio;

/**
 *
 * @author Telyman
 */


import AccesoDatos.ADTrabajador;
import Entidades.Cliente;
import Entidades.Trabajador;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LNTrabajador {
    //Atributo
     private String _mensaje;
    
    public String getMensaje() {
        return _mensaje;
    }
   
    //Llamar a Acceso a Datos
    public int Insertar(Trabajador trabajador) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADTrabajador adtrabajador;
        try {
            adtrabajador = new ADTrabajador();
            id = adtrabajador.Insertar(trabajador);
            _mensaje = adtrabajador.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
    
    //Listar clientes con RESULT SET
    public ResultSet ListarRegistros(String condicion, String orden) throws Exception{
        ResultSet resultado;
        ADTrabajador adtrabajador;
        try {
            adtrabajador = new ADTrabajador();
            resultado= adtrabajador.ListarRegistros(condicion, orden);
        } catch (Exception e) {
            throw  e;
        }
        return resultado;
    }
    //FIN Listar clientes con RESULT SET
    
    //Listar clientes con la LISTA
    public List<Trabajador> ListarRegistros(String condicion) throws Exception{
        List<Trabajador> resultado = new ArrayList();
        ADTrabajador adtrabajador;
          try {
            adtrabajador = new ADTrabajador();
            resultado= adtrabajador.ListarRegistros(condicion);
        } catch (Exception e) {
            throw  e;
        }
        return resultado;
        
    }
    //FINListar clientes con la LISTA
    
    //Obtener un registro
    public Trabajador ObtenerRegistro(String condicion) throws Exception{
        Trabajador resultado;
        ADTrabajador adtrabajador;
        try{
            adtrabajador = new ADTrabajador();
            resultado = adtrabajador.ObtenerRegistro(condicion);
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
    
    //Obtener registro FIN
        public int Modificar(Trabajador trabajador) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADTrabajador adtrabajador;
        try {
            adtrabajador = new ADTrabajador();
            id = adtrabajador.Modificar(trabajador);
            _mensaje = adtrabajador.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
    
            public int Eliminar(Trabajador trabajador) throws Exception{
        int id = -1; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADTrabajador adtrabajador;
        try {
            adtrabajador = new ADTrabajador();
            id = adtrabajador.Eliminar(trabajador);
            _mensaje = adtrabajador.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
        public int Verificar_Insert(Trabajador trabajador) throws Exception{
        int id = 0; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADTrabajador adtrabajador;
        try {
            adtrabajador = new ADTrabajador();
            id = adtrabajador.Verificar_Antes_Insert(trabajador);
            _mensaje = adtrabajador.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    }
         public int VerificarMod(Trabajador trabajador) throws Exception{
        int id = 0; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADTrabajador adtrabajador;
        try {
            adtrabajador = new ADTrabajador();
            id = adtrabajador.Verificar_Antes_Mod(trabajador);
            _mensaje = adtrabajador.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
       public int VerificarEliminar(Trabajador trabajador) throws Exception{
        int id = 0; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADTrabajador adtrabajador;
        try {
            adtrabajador = new ADTrabajador();
            id = adtrabajador.Verificar_Antes_Eliminar(trabajador);
            _mensaje = adtrabajador.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
    
       public int VerificarExiste(Trabajador trabajador) throws Exception{
        int id = 0; //-1 SI NO LOGRÓ NADA O SINO DEVUELVE EL ID DEL CLIENTE
        ADTrabajador adtrabajador;
        try {
            adtrabajador = new ADTrabajador();
            id = adtrabajador.Verificar_Existe(trabajador);
            _mensaje = adtrabajador.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
}
