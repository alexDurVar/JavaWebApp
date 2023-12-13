<%-- 
    Document   : FrmListarEncabezadoFactura
    Created on : 9 may. 2022, 18:25:37
    Author     : Telyman
--%>
<%@page import="Entidades.EncabezadoFactura"%>
<%@page import="LogicaNegocio.LNEncabezadoFactura"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado Facturas</title>
        <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <title>Listar Facturas</title>
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
                <h1>Listado de Encabzados de Facturas</h1>
            </div>
        <br/>
        <form action="FrmListarClientes.jsp" method="post">
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
                    <th>N° Factura</th>
                    <th>ID Trabajador</th>
                    <th>Fecha</th>
                    <th>Impuesto</th>
                    <th>Monto a Pagar</th>
                    <th>ID Cliente</th>
                    <th>Estado</th>
                    <th>Opciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    String nombre = "";
                    String condicion = "";
                    
                if(request.getParameter("txtnombre")!= null){
                    
                    nombre = request.getParameter("txtnombre");
                    condicion = "Numero_Factura like '%"+nombre+"%'";
                    }
                    
                    LNEncabezadoFactura logica = new LNEncabezadoFactura();
                    List<EncabezadoFactura> datos;
                    datos = logica.ListarRegistros(condicion);
                    
                    for(EncabezadoFactura registro : datos){
                        
                %>
                
                <tr>
                    <%int codigo = registro.getNumero_factura();%>
                    
                    <td><%= codigo%></td>
                    <td><%= registro.getId_trabajador()%></td>
                    <td><%= registro.getFecha()%></td>
                    <td><%= registro.getImpuesto()%></td>
                    <td><%= registro.getMonto_a_pagar()%></td>
                    <td><%= registro.getId_cliente()%></td>
                    <td><%= registro.getEstado()%></td>
                    <td>
                        <a href="Frm_facturar.jsp?txtnumFactura=<%=codigo%>"><i class="fas fa-user-edit"></i></a>
                        <!--<a href="EliminarEncabezado?txtnumFactura=<%=codigo%>"><i class="fas fa-trash-alt"></i></a>-->
                    </td>
                </tr>
                <%}%>
                
            </tbody>
        </table>
            <a href="Frm_facturar.jsp?txtnumFactura=-1">Agregar Nueva Factura</a> &nbsp; &nbsp;       
            <br><br><!-- comment -->
            <a href="index.html">Regresar al index</a>        
        </div>
    <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
    </body>
</html>
