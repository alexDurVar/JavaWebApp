/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

/**
 *
 * @author Telyman
 */

import AccesoDatos.ClaseConexion;
import Entidades.Cliente;
import Entidades.DetallesFactura;
import Entidades.EncabezadoFactura;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class ADDetalleFactura {
     //Atributos
    private Connection _cnn;
    private String _mensaje;
    
    
    public String getMensaje() {
        return _mensaje;
    }
    
    public ADDetalleFactura() throws Exception {
        try {
             _cnn = ClaseConexion.getConnection();
            
            _mensaje = "";
        } catch (Exception e) {
            throw e;
        }
    }
    
    public int Insertar(DetallesFactura detalle) throws Exception{
        int id_detalle = 1;
        String sentencia = "insert into DETALLES_FACTURA(NUMERO_FACTURA,NUMERO_PRODUCTO,NOMBRE_PRODUCTO,CANTIDAD_COMPRADA,PRECIO) values(?,?,?,?,?,?)";
        
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia,Statement.RETURN_GENERATED_KEYS );
            ps.setInt(1, detalle.getNumero_factura());
            ps.setInt(2, detalle.getNumero_producto());
            ps.setString(3, detalle.getNombre_producto());
            ps.setInt(4, detalle.getCantidad());
            ps.setFloat(5, detalle.getPrecio());
            ps.execute();
            //ResultSet rs = ps.getGeneratedKeys(); //El result set es como una tabla con un registro
//            if(rs!=null && rs.next()){ //SI SE GENERÓ EL RESULTSET Y HAY OTRO REGISTRO
//                id_detalle=rs.getInt(1);
//                _mensaje = "Detalle de factura ingresada con exito";
//            }
            
        } catch (Exception e) {
            throw e;
        }
        finally{
            _cnn=null;
        }
        return id_detalle;
    }//Fin Insertar
    
    public ResultSet ListarRegistros(String condicion , String orden) throws SQLException{
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT NUMERO_FACTURA,NUMERO_PRODUCTO,NOMBRE_PRODUCTO,CANTIDAD_COMPRADA,PRECIO from DETALLES_FACTURA";
            if(!condicion.equals("")){
                sentencia=String.format("%s where %s", sentencia,condicion);
            }
            if(!orden.equals("")){
                sentencia = String.format("%s order by %s" , sentencia, orden);
            }
            rs = stm.executeQuery(sentencia);
        } catch (Exception e) {
            throw  e;
        } finally {
            _cnn=null;
        }
        return rs; //devuelve el result set
    }
    
    //OTRA MANERA DE LISTAR
    public List<DetallesFactura> ListarRegistros(String condicion) throws SQLException{
        ResultSet rs = null;
        List<DetallesFactura> lista = new ArrayList();
        try {
            Statement stm= _cnn.createStatement();
            String sentencia = "SELECT NUMERO_FACTURA,NUMERO_PRODUCTO,NOMBRE_PRODUCTO,CANTIDAD_COMPRADA,PRECIO,DESCUENTO from DETALLES_FACTURA";
            if(!condicion.equals("")){
                sentencia=String.format("%s where %s" , sentencia,condicion);
            }
            rs= stm.executeQuery(sentencia);
            while(rs.next()){
                lista.add(new DetallesFactura(rs.getInt("numero_factura"), rs.getInt("numero_producto"),rs.getString("nombre_producto"), rs.getInt("cantidad_comprada"),rs.getFloat("precio"),rs.getFloat("descuento")));
            }
            
            
        } catch (Exception e) {
            throw  e;
        } finally {
            _cnn = null;
        }
        return lista;
    }

    //FIN OTRA MANERA DE LISTAR
    
    //OBTENER UN CLIENTE
    public DetallesFactura ObtenerRegistro(String condicion) throws SQLException{
        DetallesFactura detalles = new DetallesFactura();
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT NUMERO_FACTURA,NUMERO_PRODUCTO,NOMBRE_PRODUCTO,CANTIDAD_COMPRADA,PRECIO, DESCUENTO from DETALLES_FACTURA";
            
            if(!condicion.equals("")){
                sentencia = String.format("%s where %s" , sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            if(rs.next()){
                detalles.setNumero_factura(rs.getInt(1));
                detalles.setNumero_producto(rs.getInt(2));
                detalles.setNombre_producto(rs.getString(3));
                detalles.setCantidad(rs.getInt(4));
                detalles.setPrecio(rs.getFloat(5));
                 detalles.setDescuento(rs.getFloat(6));
                detalles.setExiste(true);
            }
        } catch (Exception e) {
            throw  e;
        } finally {
            _cnn = null;
        }
        return detalles;
    }
    
    //FIN OBTENER
    
        public int Modificar (DetallesFactura detalle) throws Exception{
        int resultado = 0;
        String sentencia = "update DETALLES_FACTURA SET NOMBRE_PRODUCTO=? ,CANTIDAD_COMPRADA=?, PRECIO=? where NUMERO_FACTURA =? and NUMERO_PRODUCTO=? ";
        try{
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            
           
            ps.setString(1, detalle.getNombre_producto());
            ps.setInt(2, detalle.getCantidad());
            ps.setFloat(3, detalle.getPrecio());
             ps.setInt(4, detalle.getNumero_factura());
            
            ps.setInt(5, detalle.getNumero_producto());
            resultado = ps.executeUpdate();
            
            if(resultado > 0){
                _mensaje = "Registro modificado de manera exitosa";
            }
        }catch (Exception e){
            throw e;
        }finally{
            _cnn = null;
        }
        return resultado;
    }
            
    
     //ELIMINAR UNA DEVOLUCION
    public int Eliminar(DetallesFactura detalle) throws Exception{
        int resultado =0;
        String sentencia = "delete DETALLES_FACTURA  where numero_FACTURA=? and numero_producto = ?";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setInt(1, detalle.getNumero_factura());
            ps.setInt(2, detalle.getNumero_producto());
            resultado = ps.executeUpdate();
             if(resultado > 0){
                _mensaje = "Registro borrado de manera exitosa";
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return resultado;
    }
       public int Verificar_Antes_Insert(DetallesFactura detalles) throws Exception{
        int resultado = 0;
        //String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        
        try{
            CallableStatement CS;
            CS = _cnn.prepareCall("{?= call VERIFICAR_ANTES_DE_INSERTSAR__DETALLES_F(?,?,?)}");
            CS.registerOutParameter(1, java.sql.Types.INTEGER);
            CS.setInt(2, detalles.getNumero_factura());
            CS.setInt(3, detalles.getNumero_producto());
            CS.setString(4, _mensaje);
            CS.registerOutParameter(4,Types.VARCHAR);
            CS.executeUpdate();
            resultado = CS.getInt(1);
            _mensaje = CS.getString(4);
            
            
//            if(resultado > 0){
//                _mensaje = "Registro modificado de manera exitosa";
//            }
        }catch (Exception e){
            throw e;
        }
        return resultado;
    }
       
     public int Verificar_Antes_Mod(DetallesFactura detalle) throws Exception{
        int resultado = 0;
        //String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        
        try{
            CallableStatement CS;
            CS = _cnn.prepareCall("{?= call VERIFICAR_MODIFICAR_DETALLES_FACTURA(?,?,?,?,?,?)}");
            CS.registerOutParameter(1, java.sql.Types.INTEGER);
            CS.setInt(2, detalle.getNumero_factura());
            CS.setInt(3, detalle.getNumero_producto());
            CS.setString(4, detalle.getNombre_producto());
            CS.setInt(5, detalle.getCantidad());
            CS.setFloat(6, detalle.getPrecio());
            
            
            CS.setString(7, _mensaje);
            CS.registerOutParameter(7,Types.VARCHAR);
            CS.executeUpdate();
            resultado = CS.getInt(1);
            _mensaje = CS.getString(7);
            
            
//            if(resultado > 0){
//                _mensaje = "Registro modificado de manera exitosa";
//            }
        }catch (Exception e){
            throw e;
        }
        return resultado;
    }
     
     /*Llama a Eliminar Detalle, si se hacen los requisitos, se elimina los detalles y hastala factura y si no, no se eliminara nada*/
     public int EliminarDetalle(DetallesFactura Entidad) throws Exception{
        CallableStatement CS = null;
        int resultado = 0;
        Connection _Conexion = null;
        try{
            _Conexion = ClaseConexion.getConnection();
            CS = _Conexion.prepareCall("{call eliminar_Detalle(?,?,?)}");
            
            CS.setInt(1, Entidad.getNumero_factura());
            CS.setInt(2, Entidad.getNumero_producto());
            CS.setString(3, _mensaje);  
            resultado = CS.executeUpdate();
        }catch(Exception ex){
            resultado = -1;
            throw ex;
        }finally{
            if(_Conexion != null){
                ClaseConexion.close(_Conexion);
            }
        }
        return resultado;
    }
    
}
