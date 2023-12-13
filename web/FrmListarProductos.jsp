<%-- 
    Document   : FrmListarProductos
    Created on : 9 may. 2022, 17:31:00
    Author     : Telyman
--%>
<%@page import="Entidades.Producto"%>
<%@page import="LogicaNegocio.LNProducto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado Proveedores</title>
        <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <title>Listar Productos</title>
    </head>
    <body>
         <header>
        <nav class="navbar navbar-expand-sm navbar-toggleable-sm navbar-light bg-white border-bottom box-shadow mb-3">
            <div class="container">
                <a class="navbar-brand" href="index.html">Sistema Facturación <i class="fas fa-tasks"></i></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".navbar-collapse" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="navbar-collapse collapse d-sm-inline-flex flex-sm-row-reverse">
                      <ul class="navbar-nav flex-grow-1">
                        <li class="nav-item">
                            <a class="nav-link text-dark" href="index.html">Inicio</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-dark" href="FrmListarProductos.jsp">Productos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-dark" href="FrmListarClientes.jsp">Clientes</a>
                        </li>
                          
                            <li class="nav-item">
                            <a class="nav-link text-dark" href="FrmListarTrabajador.jsp">Trabajadores</a>
                       
                          <li class="nav-item">
                            <a class="nav-link text-dark" href="Frm_ListarFacturas.jsp">Encabezado</a>
                          
                        <li class="nav-item">
                            <a class="nav-link text-dark" href="Frm_facturar.jsp">Facturación</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-dark" href="FrmVentasMes.jsp">Ventas por mes</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
        <div class="container">
            <div class="card-header">
                <h1>Listado de Productos</h1>
            </div>
        <br/>
        <div class="container">
            
        </div>
        <form action="FrmListarProductos.jsp" method="post">
            <div class="form-group">
                <div class="input-group">
                    <input type="text" id="txtnombre" name="txtnombre" value="" placeholder="Buscar por nombre..." class="form-control"/>&nbsp; &nbsp;
                    <input type="submit" id="btnBuscar" name="btnBuscar" value="buscar" class="btn btn-primary"/><br><br>
                </div>
            </div>
        </form>
        <hr/>
        <table class="table">
           
                <thead>
                <tr id="titulos">
                    <th>N° Producto</th>
                    <th>Nombre Producto</th>
                    <th>Tipo Producto</th>
                    <th>Precio Venta</th>
                    <th>Cantidad</th>
                    <th>Impuesto</th>
                    <th>Descuento</th><!-- comment -->
                    <th>Descripción</th><!-- comment -->
                    <th>Garantía</th><!-- comment -->
                    <th>Disponibilidad</th>
                    <th>N° Proveedor</th>
                    <th>Precio Compra</th>
                    <th>Precio Total</th>
                    <th>Opciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    String nombre = "";
                    String condicion = "";
                    
                    if(request.getParameter("txtnombre")!= null){
                    
                    nombre = request.getParameter("txtnombre");
                    condicion = "Nombre_Producto like '%"+nombre+"%'";
                    }
                    
                    LNProducto logica = new LNProducto();
                    List<Producto> datos;
                    datos = logica.ListarRegistros(condicion);
                    
                    for(Producto registro : datos){ 
                        
                %>
                
                <tr>
                    <%int codigo = registro.getNumero_producto();%>
                    
                    <td><%= codigo%></td>
                    <td><%= registro.getNombre_producto()%></td>
                    <td><%= registro.getTipo_producto()%></td>
                    <td><%= registro.getPrecio_venta()%></td>
                    <td><%= registro.getCantidad()%></td>
                    <td><%= registro.getImpuesto()%></td>
                    <td><%= registro.getDescuento()%></td>
                    <td><%= registro.getDescripcion()%></td>
                    <td><%= registro.getGarantia()%></td>
                    <td><%= registro.getDisponibilidad()%></td>
                    <td><%= registro.getNum_proveedor()%></td>
                    <td><%= registro.getPrecio_compra()%></td>
                    <td><%= registro.getPrecio_total()%></td>
                    <td>
                   <a href="#"><i class="fas fa-user-edit"></i></a>
                    <a href="#"><i class="fas fa-trash-alt"></i></a>
                    </td>
                </tr>
                <%}%>
                
            </tbody>
            
            
        </table>
            
            <br><br><!-- comment -->
            <a href="index.html">Regresar al index</a>        
        </div>
          <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap-datepicker/locales/bootstrap-datepicker.es.min.js" type="text/javascript"></script>
    <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
    </body>
  
</html>
