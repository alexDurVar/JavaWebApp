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


public class ADProducto {
     //Atributos
    private Connection _cnn;
    private String _mensaje;
    
    
    public String getMensaje() {
        return _mensaje;
    }
    
    public ADProducto() throws Exception {
        try {
             _cnn = ClaseConexion.getConnection();
            _mensaje = "";
        } catch (Exception e) {
            throw e;
        }
    }
    
    public int Insertar(Producto producto) throws Exception{
        int id_producto = 1;
        String sentencia = "INSERT INTO PRODUCTO(NUMERO_PRODUCTO,NOMBRE_PRODUCTO,TIPO_PRODUCTO,PRECIO_VENTA,CANTIDAD,IMPUESTO,DESCUENTO,DESCRIPCION,GARANTIA,DISPONIBILIDAD,NUM_PROVEEDOR,PRECIO_COMPRA,PRECIO_TOTAL) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia,Statement.RETURN_GENERATED_KEYS );
            ps.setInt(1, producto.getNumero_producto());
            ps.setString(2,producto.getNombre_producto());
            ps.setString(3, producto.getTipo_producto());
            ps.setFloat(4,producto.getPrecio_venta());
            ps.setInt(5,producto.getCantidad());
            ps.setFloat(6,producto.getImpuesto());
            ps.setFloat(7,producto.getDescuento());
            ps.setString(8,producto.getDescripcion());
            ps.setString(9,producto.getGarantia());
            ps.setString(10,producto.getDisponibilidad());
            ps.setInt(11,producto.getNum_proveedor());
            ps.setFloat(12,producto.getPrecio_compra());
            ps.setFloat(13,producto.getPrecio_total());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys(); //El result set es como una tabla con un registro
            if(rs!=null && rs.next()){ //SI SE GENERÓ EL RESULTSET Y HAY OTRO REGISTRO
                id_producto=rs.getInt(1);
                _mensaje = "Producto ingresado con exito";
            }
            
        } catch (Exception e) {
            throw e;
        }
        finally{
            _cnn=null;
        }
        return id_producto;
    }//Fin Insertar
    
    public ResultSet ListarRegistros(String condicion , String orden) throws SQLException{
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT NUMERO_PRODUCTO,NOMBRE_PRODUCTO,TIPO_PRODUCTO,PRECIO_VENTA,CANTIDAD,IMPUESTO,DESCUENTO,DESCRIPCION,GARANTIA,DISPONIBILIDAD,NUM_PROVEEDOR,PRECIO_COMPRA,PRECIO_TOTAL from producto";
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
    public List<Producto> ListarRegistros(String condicion) throws SQLException{
        ResultSet rs = null;
        List<Producto> lista = new ArrayList();
        try {
            Statement stm= _cnn.createStatement();
            String sentencia = "SELECT NUMERO_PRODUCTO,NOMBRE_PRODUCTO,TIPO_PRODUCTO,PRECIO_VENTA,CANTIDAD,IMPUESTO,DESCUENTO,DESCRIPCION,GARANTIA,DISPONIBILIDAD,NUM_PROVEEDOR,PRECIO_COMPRA,PRECIO_TOTAL from producto";
            if(!condicion.equals("")){
                sentencia=String.format("%s where %s" , sentencia,condicion);
            }
            rs= stm.executeQuery(sentencia);
            while(rs.next()){
                lista.add(new Producto(rs.getInt("numero_producto"), rs.getString("nombre_producto"),rs.getString("tipo_producto"), rs.getFloat("precio_venta"), rs.getInt("cantidad"), rs.getFloat("impuesto"), rs.getFloat("descuento"), rs.getString("descripcion"), rs.getString("garantia"), rs.getString("disponibilidad"), rs.getInt("num_proveedor"), rs.getFloat("precio_compra"), rs.getFloat("precio_total")));
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
    public Producto ObtenerRegistro(String condicion) throws SQLException{
        Producto producto = new Producto();
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT NUMERO_PRODUCTO,NOMBRE_PRODUCTO,TIPO_PRODUCTO,PRECIO_VENTA,CANTIDAD,IMPUESTO,DESCUENTO,DESCRIPCION,GARANTIA,DISPONIBILIDAD,NUM_PROVEEDOR,PRECIO_COMPRA,PRECIO_TOTAL from producto";
            
            if(!condicion.equals("")){
                sentencia = String.format("%s where %s" , sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            if(rs.next()){
                producto.setNumero_producto(rs.getInt(1));
                producto.setNombre_producto(rs.getString(2));
                producto.setTipo_producto(rs.getString(3));
                producto.setPrecio_venta(rs.getFloat(4));
                producto.setCantidad(rs.getInt(5));
                producto.setImpuesto(rs.getFloat(6));
                producto.setDescuento(rs.getFloat(7));
                producto.setDescripcion(rs.getString(8));
                producto.setGarantia(rs.getString(9));
                producto.setDisponibilidad(rs.getString(10));
                producto.setNum_proveedor(rs.getInt(11));
                producto.setPrecio_compra(rs.getFloat(12));
                producto.setPrecio_total(rs.getFloat(13));
                producto.setExiste(true);
            }
        } catch (Exception e) {
            throw  e;
        } finally {
            _cnn = null;
        }
        return producto;
    }
    
    //FIN OBTENER
    
    
        //MODIFICAR UN CLIENTE
    public int Modificar (Producto producto) throws Exception{
        int resultado = 0;
        String sentencia = "update producto SET NOMBRE_PRODUCTO=?,TIPO_PRODUCTO=?,PRECIO_VENTA=?,CANTIDAD=?,IMPUESTO=?,DESCUENTO=?,DESCRIPCION=?,GARANTIA=?,DISPONIBILIDAD=?,NUM_PROVEEDOR=?,PRECIO_COMPRA=?,PRECIO_TOTAL=? where numero_producto =?";
        try{
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
           
            ps.setString(1,producto.getNombre_producto());
            ps.setString(2, producto.getTipo_producto());
            ps.setFloat(3,producto.getPrecio_venta());
            ps.setInt(4,producto.getCantidad());
            ps.setFloat(5,producto.getImpuesto());
            ps.setFloat(6,producto.getDescuento());
            ps.setString(7,producto.getDescripcion());
            ps.setString(8,producto.getGarantia());
            ps.setString(9,producto.getDisponibilidad());
            ps.setInt(10,producto.getNum_proveedor());
            ps.setFloat(11,producto.getPrecio_compra());
            ps.setFloat(12,producto.getPrecio_total());
            ps.setInt(13, producto.getNumero_producto());
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
    public int Eliminar(Producto producto) throws Exception{
        int resultado =0;
        String sentencia = "delete producto where numero_producto=?";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setInt(1,producto.getNumero_producto());
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
    public int Verificar_Antes_Insert(Producto producto) throws Exception{
        int resultado = 0;
        //String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        
        try{
            CallableStatement CS;
            CS = _cnn.prepareCall("{?= call VERIFICAR_ANTES_DE_INSERTSAR_PRODUCTO(?,?)}");
            CS.registerOutParameter(1, java.sql.Types.INTEGER);
            CS.setInt(2, producto.getNumero_producto());
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
     public int Verificar_Antes_Mod(Producto producto) throws Exception{
        int resultado = 0;
        //String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        
        try{
            CallableStatement CS;
            CS = _cnn.prepareCall("{?= call Verificar_MODIFICAR_PRODUCTO(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            CS.registerOutParameter(1, java.sql.Types.INTEGER);
            CS.setInt(2, producto.getNumero_producto());
            CS.setString(3,producto.getNombre_producto());
            CS.setString(4, producto.getTipo_producto());
            CS.setFloat(5,producto.getPrecio_venta());
            CS.setInt(6,producto.getCantidad());
            CS.setFloat(7,producto.getImpuesto());
            CS.setFloat(8,producto.getDescuento());
            CS.setString(9,producto.getDescripcion());
            CS.setString(10,producto.getGarantia());
            CS.setString(11,producto.getDisponibilidad());
            CS.setInt(12,producto.getNum_proveedor());
            CS.setFloat(13,producto.getPrecio_compra());
            CS.setFloat(14,producto.getPrecio_total());
            
            
            CS.setString(15, _mensaje);
            CS.registerOutParameter(15,Types.VARCHAR);
            CS.executeUpdate();
            resultado = CS.getInt(1);
            _mensaje = CS.getString(15);
            
            
//            if(resultado > 0){
//                _mensaje = "Registro modificado de manera exitosa";
//            }
        }catch (Exception e){
            throw e;
        }
        return resultado;
    }
     public int Verificar_Antes_Eliminar(Producto producto) throws Exception{
        int resultado = 0;
        //String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        
        try{
            CallableStatement CS;
            CS = _cnn.prepareCall("{?= call  VERIF_ELIMINAR_PRODUCTO(?,?)}");
            CS.registerOutParameter(1, java.sql.Types.INTEGER);
            CS.setInt(2, producto.getNumero_producto());
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
     public int Verificar_Existe(Producto producto) throws Exception{
        int resultado = 0;
        //String sentencia = "update cliente SET nombre=?,apellidos=?,telefono=?,residencia=? where id_cliente =?";
        
        try{
            CallableStatement CS;
            CS = _cnn.prepareCall("{?= call  VERIFICAR_Modificar_Antes_P(?,?)}");
            CS.registerOutParameter(1, java.sql.Types.INTEGER);
            CS.setInt(2, producto.getNumero_producto());
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
