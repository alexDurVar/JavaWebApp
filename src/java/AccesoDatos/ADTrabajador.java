/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

/**
 *
 * @author Telyman
 */


import Entidades.Cliente;
import Entidades.Producto;
import Entidades.Trabajador;
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


public class ADTrabajador {
       //Atributos
    private Connection _cnn;
    private String _mensaje;
    
    
    public String getMensaje() {
        return _mensaje;
    }
    
    public ADTrabajador() throws Exception {
        try {
             _cnn = ClaseConexion.getConnection();
            _mensaje = "";
        } catch (Exception e) {
            throw e;
        }
    }
    
    public int Insertar(Trabajador trabajador) throws Exception{
        int id_trabajador = 1;
        String sentencia = "insert into Trabajador(id_trabajador,nombre,apellidos,telefono,residencia) values(?,?,?,?,?)";
        
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia,Statement.RETURN_GENERATED_KEYS );
            ps.setInt(1, trabajador.getId_trabajador());
            ps.setString(2,trabajador.getNombre());
            ps.setString(3, trabajador.getApellidos());
            ps.setString(4,trabajador.getTelefono());
            ps.setString(5,trabajador.getResidencia());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys(); //El result set es como una tabla con un registro
            if(rs!=null && rs.next()){ //SI SE GENERÓ EL RESULTSET Y HAY OTRO REGISTRO
                id_trabajador=rs.getInt(1);
                _mensaje = "Trabajador ingresado con exito";
            }
            
        } catch (Exception e) {
            throw e;
        }
        finally{
            _cnn=null;
        }
        return id_trabajador;
    }//Fin Insertar
    
    public ResultSet ListarRegistros(String condicion , String orden) throws SQLException{
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT id_trabajador,nombre,apellidos,telefono,residencia from trabajador";
            if(!condicion.equals("")){
                sentencia=String.format("%s where %s" , sentencia,condicion);
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
    public List<Trabajador> ListarRegistros(String condicion) throws SQLException{
        ResultSet rs = null;
        List<Trabajador> lista = new ArrayList();
        try {
            Statement stm= _cnn.createStatement();
            String sentencia = "SELECT id_trabajador,nombre,apellidos,telefono,residencia from trabajador";
            if(!condicion.equals("")){
                sentencia=String.format("%s where %s" , sentencia,condicion);
            }
            rs= stm.executeQuery(sentencia);
            while(rs.next()){
                lista.add(new Trabajador(rs.getInt("id_trabajador"), rs.getString("nombre"),rs.getString("apellidos"), rs.getString("telefono"), rs.getString("residencia")));
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
    public Trabajador ObtenerRegistro(String condicion) throws SQLException{
        Trabajador trabajador = new Trabajador();
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT id_trabajador,nombre,apellidos,telefono,residencia from trabajador";
            
            if(!condicion.equals("")){
                sentencia = String.format("%s where %s" , sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            if(rs.next()){
                trabajador.setId_trabajador(rs.getInt(1));
                trabajador.setNombre(rs.getString(2));
                trabajador.setApellidos(rs.getString(3));
                trabajador.setTelefono(rs.getString(4));
                trabajador.setResidencia(rs.getString(5));
                trabajador.setExiste(true);
            }
        } catch (Exception e) {
            throw  e;
        } finally {
            _cnn = null;
        }
        return trabajador;
        
        
        
    }
    
    
        public int Modificar (Trabajador trabajador) throws Exception{
        int resultado = 0;
        String sentencia = "update trabajador SET nombre=?,apellidos=?,telefono=?,residencia=? where id_trabajador =?";
        try{
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
           
            ps.setString(1,trabajador.getNombre());
            ps.setString(2, trabajador.getApellidos());
            ps.setString(3,trabajador.getTelefono());
            ps.setString(4,trabajador.getResidencia());
             ps.setInt(5, trabajador.getId_trabajador());
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
    public int Eliminar(Trabajador trabajador) throws Exception{
        int resultado =0;
        String sentencia = "delete trabajador where id_trabajador=?";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setInt(1, trabajador.getId_trabajador());
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
    
     public int Verificar_Antes_Insert(Trabajador trabajador) throws Exception{
        int resultado = 0;
        //String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        
        try{
            CallableStatement CS;
            CS = _cnn.prepareCall("{?= call VERIFICAR_ANTES_DE_INSERTSAR_TRABAJADOR(?,?)}");
            CS.registerOutParameter(1, java.sql.Types.INTEGER);
            CS.setInt(2, trabajador.getId_trabajador());
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
     public int Verificar_Antes_Mod(Trabajador trabajador) throws Exception{
        int resultado = 0;
        //String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        
        try{
            CallableStatement CS;
            CS = _cnn.prepareCall("{?= call VERIFICAR_MODIFICAR_T(?,?,?,?,?,?)}");
            CS.registerOutParameter(1, java.sql.Types.INTEGER);
            CS.setInt(2, trabajador.getId_trabajador());
            CS.setString(3,trabajador.getNombre());
            CS.setString(4, trabajador.getApellidos());
            CS.setString(5,trabajador.getTelefono());
            CS.setString(6,trabajador.getResidencia());
            
            
            
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
        public int Verificar_Antes_Eliminar(Trabajador trabajador) throws Exception{
        int resultado = 0;
        //String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        
        try{
            CallableStatement CS;
            CS = _cnn.prepareCall("{?= call SP_ELIMINAR_TRABAJADOR(?,?)}");
            CS.registerOutParameter(1, java.sql.Types.INTEGER);
            CS.setInt(2, trabajador.getId_trabajador());
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
        
           public int Verificar_Existe(Trabajador trabajador) throws Exception{
        int resultado = 0;
        //String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        
        try{
            CallableStatement CS;
            CS = _cnn.prepareCall("{?= call VERIFICAR_Modificar_Antes_T(?,?)}");
            CS.registerOutParameter(1, java.sql.Types.INTEGER);
            CS.setInt(2, trabajador.getId_trabajador());
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
