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
import Entidades.DevolucionesVentas;
import Entidades.Producto;
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

public class ADDevolucionesVentas {
     //Atributos
    private Connection _cnn;
    private String _mensaje;
    
    
    public String getMensaje() {
        return _mensaje;
    }
    
    public ADDevolucionesVentas() throws Exception {
        try {
            _cnn = ClaseConexion.getConnection();
          
            _mensaje = "";
        } catch (Exception e) {
            throw e;
        }
    }
    
    public int Insertar(DevolucionesVentas devoluciones) throws Exception{
        int id_cliente = 1;
        String sentencia = "insert into DEVOLUCIONES_VENTAS(NUMERO_DEVOLUCION,NUMERO_FACTURA,NUMERO_PRODUCTO,FECHA_REALIZACION,FECHA_LIMITE_GARANTIA,MOTVO_DEVOLUCION,ACEPTACION_DEVOLUCION,ID_TRABAJADOR) values(?,?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia,Statement.RETURN_GENERATED_KEYS );
            ps.setInt(1, devoluciones.getNumero_devolucion());
            ps.setInt(2,devoluciones.getNumero_factura());
            ps.setInt(3,devoluciones.getNumero_producto());
            ps.setDate(4, devoluciones.getFecha_realizacion());
            ps.setDate(5, devoluciones.getFecha_limite());
            ps.setString(6,devoluciones.getMotivo_devolucion());
            ps.setString(7,devoluciones.getAceptacion_devolucion());
            ps.setInt(8,devoluciones.getId_trabajador());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys(); //El result set es como una tabla con un registro
            if(rs!=null && rs.next()){ //SI SE GENERÓ EL RESULTSET Y HAY OTRO REGISTRO
                id_cliente=rs.getInt(1);
                _mensaje = "Devolución ingresado con exito";
            }
            
        } catch (Exception e) {
            throw e;
        }
        finally{
            _cnn=null;
        }
        return id_cliente;
    }//Fin Insertar
    
    public ResultSet ListarRegistros(String condicion , String orden) throws SQLException{
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT NUMERO_DEVOLUCION,NUMERO_FACTURA,NUMERO_PRODUCTO,FECHA_REALIZACION,FECHA_LIMITE_GARANTIA,MOTVO_DEVOLUCION,ACEPTACION_DEVOLUCION,ID_TRABAJADOR from devoluciones_ventas";
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
    public List<DevolucionesVentas> ListarRegistros(String condicion) throws SQLException{
        ResultSet rs = null;
        List<DevolucionesVentas> lista = new ArrayList();
        try {
            Statement stm= _cnn.createStatement();
            String sentencia = "SELECT NUMERO_DEVOLUCION,NUMERO_FACTURA,NUMERO_PRODUCTO,FECHA_REALIZACION,FECHA_LIMITE_GARANTIA,MOTVO_DEVOLUCION,ACEPTACION_DEVOLUCION,ID_TRABAJADOR from devoluciones_ventas";
            if(!condicion.equals("")){
                sentencia=String.format("%s where %s" , sentencia,condicion);
            }
            rs= stm.executeQuery(sentencia);
            while(rs.next()){
                lista.add(new DevolucionesVentas(rs.getInt("numero_devolucion"), rs.getInt("numero_factura"), rs.getInt("numero_producto"),rs.getDate("fecha_realizacion"),rs.getDate("fecha_limite_garantia"), rs.getString("motvo_devolucion"), rs.getString("aceptacion_devolucion"), rs.getInt("id_trabajador")));
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
    public DevolucionesVentas ObtenerRegistro(String condicion) throws SQLException{
        DevolucionesVentas devoluciones = new DevolucionesVentas();
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT NUMERO_DEVOLUCION,NUMERO_FACTURA,NUMERO_PRODUCTO,FECHA_REALIZACION,FECHA_LIMITE_GARANTIA,MOTVO_DEVOLUCION,ACEPTACION_DEVOLUCION,ID_TRABAJADOR from devoluciones_ventas";
            
            if(!condicion.equals("")){
                sentencia = String.format("%s where %s" , sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            if(rs.next()){
                devoluciones.setNumero_devolucion(rs.getInt(1));
                devoluciones.setNumero_factura(rs.getInt(2));
                devoluciones.setNumero_producto(rs.getInt(3));
                devoluciones.setFecha_realizacion(rs.getDate(4));
                devoluciones.setFecha_limite(rs.getDate(5));
                devoluciones.setMotivo_devolucion(rs.getString(6));
                devoluciones.setAceptacion_devolucion(rs.getString(7));
                devoluciones.setId_trabajador(rs.getInt(8));
                devoluciones.setExiste(true);
            }
        } catch (Exception e) {
            throw  e;
        } finally {
            _cnn = null;
        }
        return devoluciones;
    }
      public int Modificar (DevolucionesVentas  devoluciones) throws Exception{
        int resultado = 0;
        String sentencia = "update DEVOLUCIONES_VENTAS SET NUMERO_FACTURA=?,NUMERO_PRODUCTO =?,FECHA_REALIZACION=?,FECHA_LIMITE_GARANTIA=?,MOTVO_DEVOLUCION=?,ACEPTACION_DEVOLUCION=?,ID_TRABAJADOR=? where NUMERO_DEVOLUCION =?";
        try{
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            
           
             
            ps.setInt(1,devoluciones.getNumero_factura());
            ps.setInt(2,devoluciones.getNumero_producto());
            ps.setDate(3, devoluciones.getFecha_realizacion());
            ps.setDate(4, devoluciones.getFecha_limite());
            ps.setString(5,devoluciones.getMotivo_devolucion());
            ps.setString(6,devoluciones.getAceptacion_devolucion());
            ps.setInt(7,devoluciones.getId_trabajador());
            ps.setInt(8, devoluciones.getNumero_devolucion());
            
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
    public int Eliminar(DevolucionesVentas  devoluciones) throws Exception{
        int resultado =0;
        String sentencia = "delete DEVOLUCIONES_VENTAS where numero_devolucion=?";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setInt(1, devoluciones.getNumero_devolucion());
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
    
    //FIN OBTENER
    
       public int Verificar_Antes_Insert(DevolucionesVentas devoluciones) throws Exception{
        int resultado = 0;
        //String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        
        try{
            CallableStatement CS;
            CS = _cnn.prepareCall("{?= call VERIFICAR_ANTES_DE_INSERTSAR__DEVOLUCIONES_VENTAS(?,?)}");
            CS.registerOutParameter(1, java.sql.Types.INTEGER);
            CS.setInt(2, devoluciones.getNumero_devolucion());
            CS.setString(3, _mensaje);
            CS.registerOutParameter(3,Types.VARCHAR);
            CS.executeUpdate();
            resultado = CS.getInt(1);
            _mensaje = CS.getString(3);
            
            
//            if(resultado > 0){
//                _mensaje = "Registro modificado de manera exitosa";
//            }
        }catch (Exception e){
            throw e;
        }
        return resultado;
    }
          
     public int Verificar_Antes_Mod(DevolucionesVentas devoluciones) throws Exception{
        int resultado = 0;
        //String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        
        try{
            CallableStatement CS;
            CS = _cnn.prepareCall("{?= call VERIFICAR_MODIFICAR_DEVOLUCION_VENTA(?,?,?,?,?,?,?,?,?)}");
            CS.registerOutParameter(1, java.sql.Types.INTEGER);
            CS.setInt(2, devoluciones.getNumero_devolucion());
            CS.setInt(3,devoluciones.getNumero_factura());
            CS.setInt(4, devoluciones.getNumero_producto());
            CS.setDate(5, devoluciones.getFecha_realizacion());
            CS.setDate(6, devoluciones.getFecha_limite());
            CS.setString(7,devoluciones.getMotivo_devolucion());
            CS.setString(8,devoluciones.getAceptacion_devolucion());
            CS.setInt(9,devoluciones.getId_trabajador());
            
            
            
            CS.setString(10, _mensaje);
            CS.registerOutParameter(10,Types.VARCHAR);
            CS.executeUpdate();
            resultado = CS.getInt(1);
            _mensaje = CS.getString(10);
            
            
//            if(resultado > 0){
//                _mensaje = "Registro modificado de manera exitosa";
//            }
        }catch (Exception e){
            throw e;
        }
        return resultado;
    }
        public int Verificar_Antes_Eliminar(DevolucionesVentas devoluciones) throws Exception{
        int resultado = 0;
        //String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        
        try{
            CallableStatement CS;
            CS = _cnn.prepareCall("{?= call V_ELIMINAR_DEVOLUCION_VENTA(?,?,?)}");
            CS.registerOutParameter(1, java.sql.Types.INTEGER);
            CS.setInt(2, devoluciones.getNumero_devolucion());
            CS.setString(3, devoluciones.getAceptacion_devolucion());
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
        
       public int Verificar_Fecha(DevolucionesVentas devoluciones) throws Exception{
        int resultado = 0;
        //String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        
        try{
            CallableStatement CS;
            CS = _cnn.prepareCall("{?= call VERIFICAR_FECHA_GARANTIA2(?,?,?)}");
            CS.registerOutParameter(1, java.sql.Types.INTEGER);
           
            CS.setDate(2, devoluciones.getFecha_realizacion());
            CS.setDate(3, devoluciones.getFecha_limite());
           
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
        public int Verificar_Ingreso_2(DevolucionesVentas devoluciones) throws Exception{
        int resultado = 0;
        //String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        
        try{
            CallableStatement CS;
            CS = _cnn.prepareCall("{?= call VERIFICAR_Ingreso2(?,?)}");
            CS.registerOutParameter(1, java.sql.Types.INTEGER);
           
            
            CS.setInt(2, devoluciones.getNumero_producto());
           
            CS.setString(3, _mensaje);
            CS.registerOutParameter(3,Types.VARCHAR);
            CS.executeUpdate();
            resultado = CS.getInt(1);
            _mensaje = CS.getString(3);
            
            
//            if(resultado > 0){
//                _mensaje = "Registro modificado de manera exitosa";
//            }
        }catch (Exception e){
            throw e;
        }
        return resultado;
    }  
     
          public int VerificarExiste(DevolucionesVentas devoluciones) throws Exception{
        int resultado = 0;
        //String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        
        try{
            CallableStatement CS;
            CS = _cnn.prepareCall("{?= call VERIFICAR_Modificar_Antes_DV(?,?)}");
            CS.registerOutParameter(1, java.sql.Types.INTEGER);
            CS.setInt(2, devoluciones.getNumero_devolucion());
            
            CS.setString(3, _mensaje);
            CS.registerOutParameter(3,Types.VARCHAR);
            CS.executeUpdate();
            resultado = CS.getInt(1);
            _mensaje = CS.getString(3);
            
            
//            if(resultado > 0){
//                _mensaje = "Registro modificado de manera exitosa";
//            }
        }catch (Exception e){
            throw e;
        }
        return resultado;
    }
}
