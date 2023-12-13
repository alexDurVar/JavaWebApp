<%-- 
    Document   : FrmListarDevoluciones
    Created on : 9 may. 2022, 19:08:55
    Author     : Telyman
--%>
<%@page import="Entidades.DevolucionesVentas"%>
<%@page import="LogicaNegocio.LNDevolucionesVentas"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <title>Listar Devoluciones</title>
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
                            <a class="nav-link text-dark" href="FrmListarProveedor.jsp">Proveedores</a>
                          </li><!-- comment -->
                            <li class="nav-item">
                            <a class="nav-link text-dark" href="FrmListarTrabajador.jsp">Trabajadores</a>
                        </li>
                          <li class="nav-item">
                            <a class="nav-link text-dark" href="FrmListarDetalles.jsp">Detalles</a>
                        </li> 
                          <li class="nav-item">
                            <a class="nav-link text-dark" href="FrmListarEncabezadoFactura.jsp">Encabezado</a>
                          </li><!-- comment -->
                            <li class="nav-item">
                            <a class="nav-link text-dark" href="FrmListarDevoluciones.jsp">Devoluciones</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-dark" href="FrmListarFacturas.jsp">Facturación</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
        <div class="container">
            <div class="card-header">
                <h1>Listado de Devoluciones</h1>
            </div>
        <br/>
        <form action="FrmListarDevoluciones.jsp" method="post">
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
                    <th>N° Devolucion</th>
                    <th>N° Factura</th>
                    <th>N° Producto</th>
                    <th>Fecha Realizacion</th>
                    <th>Fecha Limite</th>
                    <th>Motivo</th>
                    <th>Acetacion</th>
                    <th>ID Trabajador</th>
                    <th>Opciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    String nombre = "";
                    String condicion = "";
                    
                if(request.getParameter("txtnombre")!= null){
                    
                    nombre = request.getParameter("txtnombre");
                    condicion = " MOTVO_DEVOLUCION  like '%"+nombre+"%'";
                    }
                    
                    LNDevolucionesVentas logica = new LNDevolucionesVentas();
                    List<DevolucionesVentas> datos;
                    datos = logica.ListarRegistros(condicion);
                    
                    for(DevolucionesVentas registro : datos){
                        
                %>
                
                <tr>
                    <%int codigo = registro.getNumero_devolucion();%>
                    
                    <td><%= codigo%></td>
                    <td><%= registro.getNumero_factura()%></td>
                    <td><%= registro.getNumero_producto()%></td>
                    <td><%= registro.getFecha_realizacion()%></td>
                    <td><%= registro.getFecha_limite()%></td>
                    <td><%= registro.getMotivo_devolucion()%></td>
                    <td><%= registro.getAceptacion_devolucion()%></td>
                    <td><%= registro.getId_trabajador()%></td>
                    <td>
                        <a href="Frm_Devolucion.jsp?idCrearModificar=<%=codigo%>"><i class="fas fa-user-edit"></i></a>
                        <a href="EliminarDevolucion?idEliminar=<%=codigo%>"><i class="fas fa-trash-alt"></i></a>
                    </td>
                </tr>
                <%}%>
                
            </tbody>
        </table>
            <a href="Frm_Devolucion.jsp?idCrearModificar=-1">Agregar Nueva Devolucion</a>&nbsp; &nbsp;
            <a href="FrmListarDevoluciones.jsp">Actualizar</a>        
            <br><br><!-- comment -->
            <a href="index.html">Regresar al index</a>        
        </div>
    <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
    </body>
    </body>
</html>
