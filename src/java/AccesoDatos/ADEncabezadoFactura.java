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


public class ADEncabezadoFactura {
     //Atributos
    private Connection _cnn;
    private String _mensaje;
    
    
    public String getMensaje() {
        return _mensaje;
    }
    
    public ADEncabezadoFactura() throws Exception {
        try {
            _cnn = ClaseConexion.getConnection();
            
            _mensaje = "";
        } catch (Exception e) {
            throw e;
        }
    }
    
//    public int Insertar(EncabezadoFactura encabezado) throws Exception{
//        int id_encabezado = 1;
//        String sentencia = "insert into ENCABEZADO_FACTURA(NUMERO_FACTURA,ID_TRABAJADOR,FECHA,IMPUESTO,MONTO_A_PAGAR,ID_CLIENTE) values(?,?,?,?,?,?)";
//        
//        try {
//            PreparedStatement ps = _cnn.prepareStatement(sentencia,Statement.RETURN_GENERATED_KEYS );
//            ps.setInt(1, encabezado.getNumero_factura());
//            ps.setInt(2,encabezado.getId_trabajador());
//            ps.setDate(3, encabezado.getFecha());
//            ps.setFloat(4,encabezado.getImpuesto());
//            ps.setFloat(5,encabezado.getMonto_a_pagar());
//            ps.setInt(6, encabezado.getId_cliente());
//            
//            ps.execute();
//            ResultSet rs = ps.getGeneratedKeys(); //El result set es como una tabla con un registro
//            if(rs!=null && rs.next()){ //SI SE GENERÓ EL RESULTSET Y HAY OTRO REGISTRO
//                id_encabezado=rs.getInt(1);
//                _mensaje = "Factura ingresada con exito";
//            }
//            
//        } catch (Exception e) {
//            throw e;
//        }
//        finally{
//            _cnn=null;
//        }
//        return id_encabezado;
//    }//Fin Insertar
    
    public ResultSet ListarRegistros(String condicion , String orden) throws SQLException{
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT NUMERO_FACTURA,ID_TRABAJADOR,FECHA,IMPUESTO,MONTO_A_PAGAR,ID_CLIENTE from ENCABEZADO_FACTURA ";
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
    public List<EncabezadoFactura> ListarRegistros(String condicion) throws SQLException{
        ResultSet rs = null;
        List<EncabezadoFactura> lista = new ArrayList();
        try {
            Statement stm= _cnn.createStatement();
            String sentencia = "SELECT NUMERO_FACTURA,ID_TRABAJADOR,FECHA,IMPUESTO,MONTO_A_PAGAR,ID_CLIENTE,ESTADO,SUBTOTAL from ENCABEZADO_FACTURA ";
            if(!condicion.equals("")){
                sentencia=String.format("%s where %s" , sentencia,condicion);
            }
            rs= stm.executeQuery(sentencia);
            while(rs.next()){
                lista.add(new EncabezadoFactura(rs.getInt("numero_factura"), rs.getInt("id_trabajador"),rs.getDate("fecha"), rs.getFloat("impuesto"), rs.getFloat("monto_a_pagar"),rs.getInt("id_cliente"), rs.getString("estado"), rs.getFloat("subtotal")));
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
    public EncabezadoFactura ObtenerRegistro(String condicion) throws SQLException{
        EncabezadoFactura encabezado = new EncabezadoFactura();
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT NUMERO_FACTURA,ID_TRABAJADOR,FECHA,IMPUESTO,MONTO_A_PAGAR,ID_CLIENTE,SUBTOTAL from ENCABEZADO_FACTURA";
            
            if(!condicion.equals("")){
                sentencia = String.format("%s where %s" , sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            if(rs.next()){
                encabezado.setNumero_factura(rs.getInt(1));
                encabezado.setId_trabajador(rs.getInt(2));
                encabezado.setFecha(rs.getDate(3));
                encabezado.setImpuesto(rs.getFloat(4));
                encabezado.setMonto_a_pagar(rs.getFloat(5));
                encabezado.setId_cliente(rs.getInt(6));
                encabezado.setSubtotal(rs.getInt(7));
                encabezado.setExiste(true);
            }
        } catch (Exception e) {
            throw  e;
        } finally {
            _cnn = null;
        }
        return encabezado;
    }
    
    //FIN OBTENER
    
        public int Modificar (EncabezadoFactura encabezado) throws Exception{
        int resultado = 0;
        String sentencia = "update ENCABEZADO_FACTURA SET ID_TRABAJADOR=?,IMPUESTO=?,MONTO_A_PAGAR=?,ID_CLIENTE=?, SUBTOTAL=? where NUMERO_FACTURA =?";
        try{
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            
           
         
            ps.setInt(1,encabezado.getId_trabajador());
            ps.setFloat(2,encabezado.getImpuesto());
            ps.setFloat(3,encabezado.getMonto_a_pagar());
            ps.setInt(4,encabezado.getId_cliente());
            ps.setInt(5, encabezado.getNumero_factura());
            ps.setFloat(6, encabezado.getSubtotal());
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
    public int Eliminar(EncabezadoFactura encabezado) throws Exception{
        int resultado =0;
        String sentencia = "delete ENCABEZADO_FACTURA where NUMERO_FACTURA=?";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setInt(1, encabezado.getNumero_factura());
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
    
     public int Verificar_Antes_Insert(EncabezadoFactura encabezado) throws Exception{
        int resultado = 0;
        //String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        
        try{
            CallableStatement CS;
            CS = _cnn.prepareCall("{?= call VERIFICAR_ANTES_DE_INSERTSAR__ENC_F(?,?)}");
            CS.registerOutParameter(1, java.sql.Types.INTEGER);
            CS.setInt(2, encabezado.getNumero_factura());
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
              public int Verificar_Antes_Mod(EncabezadoFactura encabezado) throws Exception{
        int resultado = 0;
        //String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        
        try{
            CallableStatement CS;
            CS = _cnn.prepareCall("{?= call VERIFICAR_MODIFICACION_ENCAB(?,?,?,?,?,?)}");
            CS.registerOutParameter(1, java.sql.Types.INTEGER);
            CS.setInt(2, encabezado.getNumero_factura());
            CS.setInt(3,encabezado.getId_trabajador());
            CS.setFloat(4,encabezado.getImpuesto());
            CS.setFloat(5,encabezado.getMonto_a_pagar());
            CS.setInt(6,encabezado.getId_cliente());
            
            
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
       public int Verificar_Antes_Eliminar(EncabezadoFactura encabezado) throws Exception{
        int resultado = 0;
        //String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        
        try{
            CallableStatement CS;
            CS = _cnn.prepareCall("{?= call SP_ELIMINAR_ENCABEZADO_FACTURA(?,?)}");
            CS.registerOutParameter(1, java.sql.Types.INTEGER);
            CS.setInt(2, encabezado.getNumero_factura());
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
       
   
     
      public int Insertar(EncabezadoFactura EntidadFactura, DetallesFactura EntidadDetalle) throws Exception {
        CallableStatement CS;
        int resultado;
        int idFactura;
        Connection _Conexion = null;
        /*Primero va a llamar al SP de de guardar la factira, mandandole todos los arameros necesarios y desues hace lo mismo con los detalles de la factura, si los requisitos se cumplen, los inserta, actualiza o elimina */
        try {
            _Conexion = ClaseConexion.getConnection();

            _Conexion.setAutoCommit(false);

            CS = _Conexion.prepareCall("{call Guardar_Factura(?,?,?,?,?,?,?,?,?)}");
            CS.setInt(1, EntidadFactura.getNumero_factura());
            CS.setInt(2, EntidadFactura.getId_cliente());
            CS.setDate(3, EntidadFactura.getFecha());
            CS.setFloat(4, EntidadFactura.getImpuesto());
            CS.setFloat(5, EntidadFactura.getMonto_a_pagar());
            CS.setString(6, EntidadFactura.getEstado());
             CS.setInt(7, EntidadFactura.getId_trabajador());
             CS.setFloat(8, EntidadFactura.getSubtotal());
            CS.setString(9, _mensaje);
            CS.registerOutParameter(1, Types.INTEGER);

            resultado = CS.executeUpdate();
            idFactura = CS.getInt(1);

            CS = _Conexion.prepareCall("{call Guardar_Detalle(?,?,?,?,?,?,?)}");
            CS.setInt(1, idFactura);
            CS.setInt(2, EntidadDetalle.getNumero_producto());
            CS.setString(3, EntidadDetalle.getNombre_producto());
            CS.setInt(4, EntidadDetalle.getCantidad());
            CS.setFloat(5,  EntidadDetalle.getPrecio());
            CS.setFloat(6,  EntidadDetalle.getDescuento());
            CS.setString(7, _mensaje); 

            CS.registerOutParameter(7, Types.VARCHAR);
            resultado = CS.executeUpdate();

            _mensaje = CS.getString(7);
            _Conexion.commit();
        } catch (ClassNotFoundException | SQLException ex) {
            _Conexion.rollback();
            throw ex;
        } finally {
            if (_Conexion != null) {
                ClaseConexion.close(_Conexion);
            }
        }
        return idFactura;
    }
     
       public int ModificarFactura(EncabezadoFactura EntidadFactura) throws Exception {
        int idfactura = 0;
        Connection _Conexion = null;
        try {
            _Conexion = ClaseConexion.getConnection();
            PreparedStatement PS = _Conexion.prepareStatement("update Factura set ID_CLIENTE = ? where NUMERO_FACTURA = ?");

            PS.setInt(1, EntidadFactura.getId_cliente());
            PS.setInt(2, EntidadFactura.getNumero_factura());

            PS.executeUpdate();
            idfactura = EntidadFactura.getNumero_factura();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (_Conexion != null) {
                ClaseConexion.close(_Conexion);
            }
        }
        return idfactura;
    }
     
       
       
   

    public int ModificarEstado(EncabezadoFactura EntidadFactura) throws Exception {
        int resultado = 0;
        Connection _Conexion = null;
        //Esto cambia el estado de la factura
        try {
            _Conexion = ClaseConexion.getConnection();
            PreparedStatement PS = _Conexion.prepareStatement("Update Encabezado_Factura set Estado = ? where NUMERO_FACTURA = ?");

            PS.setString(1, EntidadFactura.getEstado());
            PS.setInt(2, EntidadFactura.getNumero_factura());

            resultado = PS.executeUpdate();
        } catch (Exception ex) {
            resultado = -1;
            throw ex;
        } finally {
            if (_Conexion != null) {
                ClaseConexion.close(_Conexion);
            }
        }
        if(resultado > 0){
            _mensaje = "Factura Cancelada";
        }else{
            _mensaje = "No se ha podido Cancelar la factura";
        }
        return resultado;
    }
  public int MontoTotal(EncabezadoFactura EntidadFactura,float subtotal ,float total) throws Exception {
        int resultado = 0;
        Connection _Conexion = null;
        /*Esto va a actualizar lo que tenga subtotal y monto a pagar, esto se hace cada vez que hay una actualizacion, eliminacion o insercion*/
        try {
            _Conexion = ClaseConexion.getConnection();
            PreparedStatement PS = _Conexion.prepareStatement("Update Encabezado_Factura set SUBTOTAL=?, MONTO_A_PAGAR = ? where NUMERO_FACTURA = ?");

            PS.setFloat(1, subtotal);
             PS.setFloat(2, total);
            PS.setInt(3, EntidadFactura.getNumero_factura());

            resultado = PS.executeUpdate();
        } catch (Exception ex) {
            resultado = -1;
            throw ex;
        } finally {
            if (_Conexion != null) {
                ClaseConexion.close(_Conexion);
            }
        }
       
        return resultado;
    }
  
   
    
}




