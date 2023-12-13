/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Entidades.Cliente;
import static AccesoDatos.ClaseConexion.getConnection;

import java.sql.*;
import java.util.*;

import Entidades.Cliente;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
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

/**
 *
 * @author Steven
 */
public class ADCliente {
    
    
    /*private String _mensaje;
    
    public String getMensaje(){
        return _mensaje;
    }
    
    public ADCliente(){
     _mensaje = "";
    }
     
     
     public List<Cliente> ListarRegistros(String condicion) throws SQLException, ClassNotFoundException {

        ResultSet rs = null;
        List<Cliente> lista = new ArrayList<Cliente>();
        Connection conexion =  null;
        try {
            Statement stm = ClaseConexion.getConnection().createStatement();
            String sentencia = "Select id_cliente,nombre,telefono,direccion from clientes";
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            while (rs.next()) {
                lista.add(new Cliente(rs.getInt("id_cliente"), rs.getString("nombre"),
                        rs.getString("direccion"), rs.getString("telefono")));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
             conexion = null;
        }
        return lista;
    }*/
     //Atributos
    private Connection _cnn;
    private String _mensaje;
    
    
    public String getMensaje() {
        return _mensaje;
    }
    
    public ADCliente() throws Exception {
        try {
             _cnn = ClaseConexion.getConnection();
            _mensaje = "";
        } catch (Exception e) {
            throw e;
        }
    }
    
    
    
    public int Insertar(Cliente cliente) throws Exception{
        int id_cliente = 1;
        String sentencia = "insert into Cliente(id_cliente,nombre,apellidos,telefono,residencia) values(?,?,?,?,?)";
        
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia,Statement.RETURN_GENERATED_KEYS );
            ps.setInt(1, cliente.getId_cliente());
            ps.setString(2,cliente.getNombre());
            ps.setString(3, cliente.getApellidos());
            ps.setString(4,cliente.getTelefono());
            ps.setString(5,cliente.getResidencia());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys(); //El result set es como una tabla con un registro
            if(rs!=null && rs.next()){ //SI SE GENERÓ EL RESULTSET Y HAY OTRO REGISTRO
                id_cliente=rs.getInt(1);
                _mensaje = "Cliente ingresado con exito";
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
            String sentencia = "SELECT id_cliente,nombre,apellidos,telefono,residencia from cliente";
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
    public List<Cliente> ListarRegistros(String condicion) throws SQLException{
        ResultSet rs = null;
        List<Cliente> lista = new ArrayList();
        Cliente cliente;
        try {
            Statement stm= _cnn.createStatement();
            String sentencia = "SELECT id_cliente,nombre,apellidos,telefono,residencia from cliente";
            if(!condicion.equals("")){
                sentencia=String.format("%s where %s" , sentencia,condicion);
            }
            rs= stm.executeQuery(sentencia);
            while(rs.next()){
                cliente =  new Cliente(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5));
                lista.add(cliente);
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
    public Cliente ObtenerRegistro(String condicion) throws SQLException{
        Cliente cliente = new Cliente();
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT id_cliente,nombre,apellidos,telefono,residencia from cliente";
            
            if(!condicion.equals("")){
                sentencia = String.format("%s where %s" , sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            if(rs.next()){
                cliente.setId_cliente(rs.getInt(1));
                cliente.setNombre(rs.getString(2));
                cliente.setApellidos(rs.getString(3));
                cliente.setTelefono(rs.getString(4));
                cliente.setResidencia(rs.getString(5));
                cliente.setExiste(true);
            }
        } catch (Exception e) {
            throw  e;
        } finally {
            _cnn = null;
        }
        return cliente;
    }
    
    //FIN OBTENER
    
     //MODIFICAR UN CLIENTE
    public int Modificar (Cliente cliente) throws Exception{
        int resultado = 0;
        String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        try{
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getResidencia());
            ps.setInt(5, cliente.getId_cliente());
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
            
    
     //ELIMINAR UN CLIENTE
    public int Eliminar(Cliente cliente) throws Exception{
        int resultado =0;
        String sentencia = "delete Cliente where id_cliente=?";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setInt(1, cliente.getId_cliente());
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
        public int Verificar_Antes_Insert(Cliente cliente) throws Exception{
        int resultado = 0;
        //String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        
        try{
            CallableStatement CS;
            CS = _cnn.prepareCall("{?= call VERIFICAR_ANTES_DE_INSERTSAR_C(?,?)}");
            CS.registerOutParameter(1, java.sql.Types.INTEGER);
            CS.setInt(2, cliente.getId_cliente());
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
       
          public int Verificar_Antes_Mod(Cliente cliente) throws Exception{
        int resultado = 0;
        //String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        
        try{
            CallableStatement CS;
            CS = _cnn.prepareCall("{?= call VERIFICAR_MODIFICACION_CLIENTE(?,?,?,?,?,?)}");
            CS.registerOutParameter(1, java.sql.Types.INTEGER);
            CS.setInt(2, cliente.getId_cliente());
            CS.setString(3, cliente.getNombre());
            CS.setString(4, cliente.getApellidos());
            CS.setString(5, cliente.getTelefono());
            CS.setString(6, cliente.getResidencia());
            
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
    public int Verificar_Antes_ELIMINAR(Cliente cliente) throws Exception{
        int resultado = 0;
        //String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        
        try{
            CallableStatement CS;
            CS = _cnn.prepareCall("{?= call SP_ELIMINAR_CLIENTE(?,?)}");
            CS.registerOutParameter(1, java.sql.Types.INTEGER);
            CS.setInt(2, cliente.getId_cliente());
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
    public int Verificar_Existe(Cliente cliente) throws Exception{
        int resultado = 0;
        //String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        
        try{
            CallableStatement CS;
            CS = _cnn.prepareCall("{?= call VERIFICAR_Modificar_Antes_C(?,?)}");
            CS.registerOutParameter(1, java.sql.Types.INTEGER);
            CS.setInt(2, cliente.getId_cliente());
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
