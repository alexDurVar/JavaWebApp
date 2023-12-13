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
import Entidades.Proveedor;
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

public class ADProveedor {
      //Atributos
    private Connection _cnn;
    private String _mensaje;
    
    
    public String getMensaje() {
        return _mensaje;
    }
    
    public ADProveedor() throws Exception {
        try {
             _cnn = ClaseConexion.getConnection();
            _mensaje = "";
        } catch (Exception e) {
            throw e;
        }
    }
    
    public int Insertar(Proveedor proveedor) throws Exception{
        int id_proveedor = 1;
        String sentencia = "insert into Proveedor(num_proveedor,nombre_org,correo,direccion,telefono) values(?,?,?,?,?)";
        
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia,Statement.RETURN_GENERATED_KEYS );
            ps.setInt(1, proveedor.getNum_proveedor());
            ps.setString(2,proveedor.getNombre_org());
            ps.setString(3, proveedor.getCorreo());
            ps.setString(4,proveedor.getDireccion());
            ps.setString(5,proveedor.getTelefono());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys(); //El result set es como una tabla con un registro
            if(rs!=null && rs.next()){ //SI SE GENERÓ EL RESULTSET Y HAY OTRO REGISTRO
                id_proveedor=rs.getInt(1);
                _mensaje = "Proveedor ingresado con exito";
            }
            
        } catch (Exception e) {
            throw e;
        }
        finally{
            _cnn=null;
        }
        return id_proveedor;
    }//Fin Insertar
    
    public ResultSet ListarRegistros(String condicion , String orden) throws SQLException{
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT num_proveedor,nombre_org,correo,direccion,telefono from proveedor";
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
    public List<Proveedor> ListarRegistros(String condicion) throws SQLException{
        ResultSet rs = null;
        List<Proveedor> lista = new ArrayList();
        try {
            Statement stm= _cnn.createStatement();
            String sentencia = "SELECT num_proveedor,nombre_org,correo,direccion,telefono from proveedor";
            if(!condicion.equals("")){
                sentencia=String.format("%s where %s" , sentencia,condicion);
            }
            rs= stm.executeQuery(sentencia);
            while(rs.next()){
                lista.add(new Proveedor(rs.getInt("num_proveedor"), rs.getString("nombre_org"),rs.getString("correo"), rs.getString("direccion"), rs.getString("telefono")));
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
    public Proveedor ObtenerRegistro(String condicion) throws SQLException{
        Proveedor proveedor = new Proveedor();
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT num_proveedor,nombre_org,correo,direccion,telefono from proveedor";
            
            if(!condicion.equals("")){
                sentencia = String.format("%s where %s" , sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            if(rs.next()){
                proveedor.setNum_proveedor(rs.getInt(1));
                proveedor.setNombre_org(rs.getString(2));
                proveedor.setCorreo(rs.getString(3));
                proveedor.setDireccion(rs.getString(4));
                proveedor.setTelefono(rs.getString(5));
                proveedor.setExiste(true);
            }
        } catch (Exception e) {
            throw  e;
        } finally {
            _cnn = null;
        }
        return proveedor;
    }
    
    //FIN OBTENER
     //MODIFICAR UN CLIENTE
    public int Modificar (Proveedor proveedor) throws Exception{
        int resultado = 0;
        String sentencia = "update proveedor SET nombre_org=?,correo=?,direccion=?,telefono=? where num_proveedor =?";
        try{
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setString(1,proveedor.getNombre_org());
            ps.setString(2, proveedor.getCorreo());
            ps.setString(3,proveedor.getDireccion());
            ps.setString(4,proveedor.getTelefono());
            ps.setInt(5, proveedor.getNum_proveedor());
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
    public int Eliminar(Proveedor proveedor) throws Exception{
        int resultado =0;
        String sentencia = "delete proveedor where num_proveedor=?";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setInt(1, proveedor.getNum_proveedor());
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
     public int Verificar_Antes_Insert(Proveedor proveedor) throws Exception{
        int resultado = 0;
        //String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        
        try{
            CallableStatement CS;
            CS = _cnn.prepareCall("{?= call VERIFICAR_ANTES_DE_INSERTSAR_PRO(?,?)}");
            CS.registerOutParameter(1, java.sql.Types.INTEGER);
            CS.setInt(2, proveedor.getNum_proveedor());
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
       public int Verificar_Antes_Mod(Proveedor proveedor) throws Exception{
        int resultado = 0;
        //String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        
        try{
            CallableStatement CS;
            CS = _cnn.prepareCall("{?= call VERIFICAR_MODIFICAR_PROV(?,?,?,?,?,?)}");
            CS.registerOutParameter(1, java.sql.Types.INTEGER);
            CS.setInt(2, proveedor.getNum_proveedor());
            CS.setString(3,proveedor.getNombre_org());
            CS.setString(4, proveedor.getCorreo());
            CS.setString(5,proveedor.getDireccion());
            CS.setString(6,proveedor.getTelefono());
           
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
         public int Verificar_Antes_Eliminar(Proveedor proveedor) throws Exception{
        int resultado = 0;
        //String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        
        try{
            CallableStatement CS;
            CS = _cnn.prepareCall("{?= call VERIF_ELIMINAR_PROVEEDOR(?,?)}");
            CS.registerOutParameter(1, java.sql.Types.INTEGER);
            CS.setInt(2, proveedor.getNum_proveedor());
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
             public int Verificar_Existe(Proveedor proveedor) throws Exception{
        int resultado = 0;
        //String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        
        try{
            CallableStatement CS;
            CS = _cnn.prepareCall("{?= call VERIFICAR_Modificar_Antes(?,?)}");
            CS.registerOutParameter(1, java.sql.Types.INTEGER);
            CS.setInt(2, proveedor.getNum_proveedor());
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
