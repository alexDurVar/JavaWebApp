<%-- 
    Document   : FrmVentasMes
    Created on : 18 may. 2022, 00:10:23
    Author     : Telyman
--%>

<%@page import="Entidades.Cliente"%>
<%@page import="LogicaNegocio.LNCliente"%>
<%@page import="java.util.List"%>
<%@page import="Entidades.EncabezadoFactura"%>
<%@page import="LogicaNegocio.LNEncabezadoFactura"%>
<%@page import="java.text.DateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/DataTables/datatables.min.css" rel="stylesheet" type="text/css"/>
        <title>Ventas por mes</title>
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
                <h1>Listado de ventas por mes</h1>
            </div>
            <br/>
            <form action="FrmVentasMes.jsp" method="post">
               <div class="form-group">
                    <div class="input-group">
                        <input type="text" id="txtfecha" name="txtfecha" value=""
                               placeholder="Seleccione la fecha" class="datepicker"/>&nbsp; &nbsp;
                        <input type="submit" id="btnbuscar" name="btnbuscar" value="buscar"
                               class="btn btn-success"><br><br>
                    </div>
                </div>
                
              
                
               

                
            </form>
            <hr/>
            <table class="table">
                <thead>
                    <tr>
                        <th>Num. Factura</th>
                        <th>ID Cliente</th>
                        <!--<th>Cliente</th>-->
                        <th>Fecha</th>
                        <th>Impuesto</th>
                        <th>Estado</th>
                        <th>Subtotal</th>
                        <th>Monto Total</th>
                        <th>ID Trabajador</th>
                        <th>Opciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                       
                        
                        
                        String fecha = "";
                        String condicion = "";
                        if (request.getParameter("txtfecha") != null
                            && !request.getParameter("txtfecha").equals("")) {
                            fecha = request.getParameter("txtfecha");
                            condicion = condicion + "FECHA = '" + fecha + "'";
                        }
                        
                      
                        LNEncabezadoFactura logica = new LNEncabezadoFactura();
                       // Cliente logica2 = new Cliente();
                        List<EncabezadoFactura> datos = null;
                        datos = logica.ListarRegistros(condicion);
                        for (EncabezadoFactura registro : datos) {
                    %>
                    <tr>
                        <%int num = registro.getNumero_factura();%>
                        <td><%= num%></td>
                         <td><%= registro.getId_cliente()%></td>
                        <!--<td><//%= logica2.getNombre()%></td>-->
                        <td><%= registro.getFecha()%></td>
                        <td><%= registro.getImpuesto()%></td>
                        <td><%= registro.getEstado()%></td>
                        <td><%= registro.getSubtotal()%></td>
                        <td><%= registro.getMonto_a_pagar()%></td>
                        <td><%= registro.getId_trabajador()%></td>
                        <td>
                            <a href="Frm_facturar.jsp?txtnumFactura=<%=num%>&txtidCliente=<%=registro.getId_cliente()%> "><i class="fas fa-cart-plus"></i></a>
                            <!--response.sendRedirect("Frm_facturar.jsp?txtnumFactura="+-1+"&msgFac="+mensaje);-->
             
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
            <br/>
           
        </div>
        <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap-datepicker/locales/bootstrap-datepicker.es.min.js" type="text/javascript"></script>
        <script>
            $(".datepicker").datepicker({
                format: 'yyyy-mm-dd',//yyyy-mm-dd
                autoclose: true,
                language: 'es'
            });
        </script>
    </body>
</html>
