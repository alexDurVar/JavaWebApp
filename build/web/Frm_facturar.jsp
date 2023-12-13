<%-- 
    Document   : Frm_facturar
    Created on : 9/05/2022, 11:34:14 AM
    Author     : Steven
--%>

<%@page import="Servlets.Facturar"%>
<%@page import="Servlets.Facturar2"%>
<%@page import="Servlets.EliminarDetalle"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="Entidades.*"%>
<%@page import="java.util.*"%>
<%@page import="LogicaNegocio.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/DataTables/DataTables-1.10.21/css/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/DataTables/datatables.min.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Facturar</title>
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
            <div class="row">
                <div class="col-10">
                    <h1>Facturación</h1>
                </div>
            </div>
            <%
                int numFactura = -1;
                //Estas son las variables que se van a usar para sacar el total de la factura
                float total = 0;
                float desc1= 0;
                float subtotal = 0;
                float totaldesc = 0;
                float IVA = 0;
                 float IVA2 = 0;
                EncabezadoFactura EntidadFactura;
                LNEncabezadoFactura logicaFactura = new LNEncabezadoFactura();
                LNDetalleFactura logicaDetalle = new LNDetalleFactura();
                List<DetallesFactura> DatosDetalles = null;
                
             
                
                if (request.getParameter("txtnumFactura") != null && Integer.parseInt(request.getParameter("txtnumFactura")) != -1) {
                    numFactura = Integer.parseInt(request.getParameter("txtnumFactura"));
                    EntidadFactura = logicaFactura.ObtenerRegistro("Numero_Factura= " + numFactura);
                    DatosDetalles = logicaDetalle.ListarRegistros("Numero_Factura= " + numFactura);
                } else {
                    EntidadFactura = new EncabezadoFactura();
                    EntidadFactura.setNumero_factura(-1);
                    Date fecha = new Date();
                    java.sql.Date fechasql = new java.sql.Date(fecha.getTime());
                    EntidadFactura.setFecha(fechasql);
                }
            %>
            <br/>
            <form action="Facturar2" method="post" id="Frm_facturar">
                <div class="form-group float-right">

                    <div class="input-group">
                        <label for="txtnumFactura" class="form-control">Num. Factura</label>
                        <input type="text" id="txtnumFactura" name="txtnumFactura" value="<%=EntidadFactura.getNumero_factura()%>"
                               readonly class="form-control"/>
                    </div>


                    <div class="input-group">
                        <label for="txtFechaFactura" class="form-control">Fecha</label>
                        <input type="text" id="txtFechaFactura" name="txtFechaFactura" readonly value="<%=EntidadFactura.getFecha()%>"
                               required class="datepicker form-control"/>
                    </div>
                     <div class="input-group">
                        <label for="txtIm" class="form-control">Impuesto</label>
                        <input type="text" id="txtIm" name="txtIm" readonly value="<%=13.0%>"
                               required class="datepicker form-control"/>
                    </div>
                </div>
                <br/>
                <div class="form-group">
                    <div class="input-group">
                   

                        <div class="container">
                            <p>Seleccione un Cliente</p>
                        </div>
                          <input type="text" id="txtIdCliente" name="txtIdCliente" value="<%=EntidadFactura.getId_cliente()%>"                              readonly="" placeholder="Seleccione un cliente" class="form-control"/>
                        <input type="hidden" id="txtNombreCliente" name="txtNombreCliente" value="<%=EntidadFactura.getNombre()%>"
                               readonly="" class="form-control" placeholder="Seleccione un cliente"/> &nbsp; &nbsp;
                        <a id="btnbuscar" class="btn btn-success" data-toggle="modal" data-target="#buscarCliente"><i class="fas fa-search"></i></a>
                       
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <div class="input-group">
                        <input type="text" id="txtIdProducto" placeholder="Seleccione un producto" name="txtIdProducto" value="" readonly class="form-control"/>
                        <input type="text" id="txtdescripcion" name="txtdescripcion" value="" class="form-control" readonly="true"
                               placeholder="Seleccione un producto"/> &nbsp; &nbsp;
                        <a id="btnBuscarP" class="btn btn-success" data-toggle="modal" data-target="#buscarProducto">
                            <i class="fas fa-search"></i></a>&nbsp; &nbsp;
                        <input type="number" id="txtcantidad" name="txtcantidad" value="" class="form-control"
                               placeholder="Cantidad"/> &nbsp; &nbsp;
                         
                        <input type="number" id="txtprecio" name="txtprecio" value="" class="form-control"
                               placeholder="Precio" readonly="true"/> &nbsp; &nbsp;
                         <input type="number" id="txtdesc" name="txtdesc" value="" class="form-control"
                               readonly placeholder="Descuento"/> &nbsp; &nbsp;
                        <input type="number" id="txtexistencia" name="txtexistencia" value="" class="form-control"
                               placeholder="Existencia" readonly="true"/> &nbsp; &nbsp;
                    </div>
                </div>
                <br/>
                <div class="form-group">
                    <input type="submit" name="Guardar" id="btnGuardar" value="Agregar y Guardar" class="btn btn-primary"/>
                </div>
                
                
                
    <!-- AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII -->    
                
          <h5>Detalle Factura</h5>
            <table id="DetalleFactura" class="table">
                <thead>
                    <tr>
                        <!--<th>N° Factura</th>-->
                        <th>N° Producto</th>
                        <th>Nombre Producto</th>
                        <th>Cantidad</th>
                        <th>Precio</th>
                        <th>Descuento</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (DatosDetalles != null) {
                            for (DetallesFactura registroDetalle : DatosDetalles) {

                    %>
                    <tr>
                        <%  int numfactura = registroDetalle.getNumero_factura();
                            int codigop = registroDetalle.getNumero_producto();
                            String descripcion = new String(registroDetalle.getNombre_producto().getBytes("ISO-8859-1"), "UTF-8");                   float descuento = registroDetalle.getDescuento();
                            int cantidad = registroDetalle.getCantidad();
                            float precioV = registroDetalle.getPrecio();
                            
                           
                            subtotal += (cantidad*precioV); //Este va a ser el resultado de la cantidad que el cliente esta comprando por el precio de el producto
                            EncabezadoFactura factura = new EncabezadoFactura();
                            factura.setNumero_factura(registroDetalle.getNumero_factura()); //Se busca el id de la factura y se le asigna a una variable para su posterior uso
                            IVA = (float)(subtotal*0.13); //este va a ser el monto del impuesto se hace una conversion a float
                            total= (subtotal+IVA); //este es el total de la factura
                            LNEncabezadoFactura logica = new LNEncabezadoFactura();
                            logica.TotalFactura(factura, subtotal,total); //Esto va a asignar a los camos SUBTOTAL Y MONTO_A_PAGAR de la base de datos, sus respectivos valores, es decir, los que estan en los inputs en ese momento
                            EntidadFactura.setMonto_a_pagar(total);
                        %>
                        <td><%=codigop%></td>
                        <td><%=descripcion%></td>
                        <td><%=cantidad%></td>
                        <td><%=precioV%></td>
                        <td><%=descuento%></td>
                        
                        <td>
                            <a href="EliminarDetalle?idproducto=<%=codigop%>&idfactura=<%=numfactura%>">
                            
                                <i class="fas fa-trash-alt"></i>
                            </a>
                        </td>
                    </tr>
                    <%} 
                       }%>
                </tbody>
            </table>
             <div class="float-left">
                
            </div>
            <div class="float-right">
                <div class="form-group">
                    <div class="input-group">
                    <label for="txtSubt" class="form-control">SubTotal</label>
                    <input type="number" id="txtSubt" name="txtSubt" value="<%= subtotal%>">
                     
                    <label for="txtMontot" class="form-control">Total</label>
                    <input type="number" id="txtMontot" name="txtMontot" value="<%= total%>">
                   
                 
                  
               
                    </div>
                </div>
               <!-- <br>                
                <p class="txt-danger h5">Total = <//%= total%></p> -->
            </div>
                <hr>
            
               <div class="form-group">
                 
                    <div class="input-group"> 
                           <div class="container">
                        <hr>
                    </div>
                  
                   
                        <div class="container">
                            <p>Seleccione un Trabajador</p>
                        </div>
                        <input type="text" id="txtIdTrabajador" name="txtIdTrabajador" placeholder="Seleccione un trabajador" value="<%=EntidadFactura.getId_trabajador()%>" readonly=""  class="form-control"/>

                        <a id="btnbuscar" class="btn btn-success" data-toggle="modal" data-target="#buscarTrabajador"><i class="fas fa-search"></i></a>
                    </div>
                </div>
            
                   
                
                
                
                
                
                 <br><br>
                     
            <%
                if (request.getParameter("msgFac") != null) {
                    out.print("<p class='text-danger'>" + new String(request.getParameter("msgFac").getBytes("ISO-8859-1"), "UTF-8") + "</p>");
                }
            %>
        <div>
            <input type="button" id="BtnCancelar" value="Realizar Facturación"
                   onclick="location.href = 'CancelarFactura2?txtnumFactura='+<%=EntidadFactura.getNumero_factura()%>"
                   class="btn btn-success"/>&nbsp;&nbsp;
            <a href="Frm_ListarFacturas.jsp" class="btn btn-secondary">Regresar</a>
        </div>
                  
            <br><br>     
                
                
            </form>
           
             <!--Este es un modal, es decir, una ventana para poder acceder a la informacion en este caso de los clientes  -->       
                      <!-- Modal de Clientes -->
        <div class="modal" id="buscarCliente" tabindex="1" role="dialog" aria-labelledby="tituloVentana">
             <div class="modal-dialog" role="document">
                  <div class="modal-content">
                       <div class="modal-header">
                            <h5 id="tituloVentaja">Buscar Cliente</h5>
                            <button class="close" data-dismiss="modal" aria-label="Cerrar" aria-hidden="true" onclick="Limpiar()">
                                 <span aria-hidden="true">&times;</span>
                            </button>
                       </div>
                       <div class="modal-body">
                            <table id="tablaClientes">
                                 <thead>
                                      <tr>
                                           <th>Código</th>
                                           <th>Nombre</th>
                                           <th>Seleccionar</th>
                                      </tr>
                                 </thead>
                                 <tbody>
                                      <%
                                           LNCliente logicaCliente = new LNCliente();
                                           List<Cliente> datosClientes;
                                           datosClientes = logicaCliente.ListarRegistros("");
                                           for (Cliente registroC : datosClientes)  {
                                      %>
                                      <tr>
                                           <% int codigoCliente = registroC.getId_cliente();
                                                  String nombreCliente = registroC.getNombre();%>
                                           <td><%= codigoCliente%></td>
                                           <td><%= nombreCliente%></td>
                                           <td>
                                                <a href="#" data-dismiss="modal"
                                                   onclick="SeleccionarCliente('<%=codigoCliente%>', '<%= nombreCliente%>');">Seleccionar</a>
                                           </td>
                                      </tr>
                                      <%}%>
                                 </tbody>
                            </table>
                       </div> <!-- Modal Body-->
                       <div class="modal-footer">
                            <button class="btn btn-warning" type="button" data-dismiss="modal" onclick="Limpiar()">
                                 Cancelar
                            </button>
                       </div>
                  </div> <!-- Modal Content-->
             </div> <!-- Modal Dialog-->
        </div> 
        <!-- fin modal Clientes -->
        
         <!--Este es un modal, es decir, una ventana para poder acceder a la informacion en este caso de los productos -->
        <div class="modal" id="buscarProducto" tabindex="1" role="dialog" aria-labelledby="tituloVentana">
            <div class="modal-dialog modal-lg" role="document"> <!--"modal-lg" Es para que el ancho se ajuste al contenido -->
                <div class="modal-content"> <!-- "modal-lg" Hace que el ancho y largo del modal se ajueste -->
                    <div class="modal-header">
                        <h5 id="tituloVentana">Buscar Producto</h5>
                        <button class="close" data-dismiss="modal" aria-label="Cerrar" aria-hidden='true'
                                onclick="LimpiarProducto()">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <table id="tablaProductos">
                            <thead>
                                <tr>
                                    <td>Código</td>
                                    <td>Descripción</td>
                                     <td>Descuento</td>
                                   <td>Precio</td>
                                    <td>Seleccionar</td>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    LNProducto logicaProducto = new LNProducto();
                                    List<Producto> datosProductos;
                                    datosProductos = logicaProducto.ListarRegistros("");
                                    for (Producto registroP : datosProductos) {
                                %>
                                <tr>
                                    <% int codigoProducto = registroP.getNumero_producto();
                                        double precio = registroP.getPrecio_venta();
                                        float desc = registroP.getDescuento();
                                        double existencia = registroP.getCantidad();
                                        
                                        String descripcionProducto = registroP.getNombre_producto();%>
                                    <td><%=codigoProducto%></td>
                                    <td><%=descripcionProducto%></td>
                                    <td><%=desc%></td>
                                    <td><%=precio%></td>
                                    <td>
                                        <a href="#" data-dismiss="modal"
                                           onclick="SeleccionarProducto('<%=codigoProducto%>', '<%=descripcionProducto%>', '<%=precio%>','<%=desc%>','<%=existencia%>');">Seleccionar</a>
                                    </td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div>

                    <div class="modal-footer">
                        <button class="btn btn-warning" type="button" data-dismiss="modal" onclick="LimpiarProducto()">
                            Cancelar
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <!-- fin modal Productos -->
        
        
        
        
        
        
              <!-- Buscar Trabajador -->
          <!--Este es un modal, es decir, una ventana para poder acceder a la informacion en este caso de los trabajadores -->
        <div class="modal" id="buscarTrabajador" tabindex="1" role="dialog" aria-labelledby="tituloVentana">
            <div class="modal-dialog modal-lg" role="document"> <!--"modal-lg" Es para que el ancho se ajuste al contenido -->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 id="tituloVentana">Buscar Trabajador</h5>
                        <button class="close" data-dismiss="modal" aria-label="Cerrar" aria-hidden='true'
                                onclick="LimpiarProducto()">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <table id="tablaTrabajadores">
                            <thead>
                                <tr>
                                    <td>ID Trabajador</td>
                                    <td>Nombre</td>
                                    <td>Seleccionar</td>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    LNTrabajador logicaTrabajador = new LNTrabajador();
                                    List<Trabajador> datosTrabajador;
                                    datosTrabajador = logicaTrabajador.ListarRegistros("");
                                    for (Trabajador registroT : datosTrabajador) {
                                %>
                                <tr>
                                    <% int codigoT = registroT.getId_trabajador();
                                        String nombre = registroT.getNombre();%>
                                    <td><%=codigoT%></td>
                                    <td><%=nombre%></td>
                                    
                                    <td>
                                        <a href="#" data-dismiss="modal"
                                           onclick="SeleccionarTrabajador('<%=codigoT%>');">Seleccionar</a>
                                    </td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div>

                    <div class="modal-footer">
                        <button class="btn btn-warning" type="button" data-dismiss="modal" onclick="LimpiarProducto()">
                            Cancelar
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <!-- fin modal Productos -->
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        

        <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap-datepicker/locales/bootstrap-datepicker.es.min.js" type="text/javascript"></script>
        <script src="lib/DataTables/datatables.min.js" type="text/javascript"></script>
        <script src="lib/DataTables/DataTables-1.10.21/js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
        <script>
                            $(document).ready(function () {

                                $('.datepicker').datepicker({

                                    format: 'yyyy-mm-dd',
                                    autoclose: true,
                                    language: 'es'
                                });

                                /*$("#tablaClientes").DataTable({
                                    "lenghtMenu": [[5, 15, 15, -1], [5, 10, 15, "All"]],//"lengthMenu": [[5, 15, 15, -1], [5, 10, 15, "All"]],
                                    "language": {
                                        "info": "Página_PAGE_de_PAGES",
                                        "infoEmpty": "No existen Registros disponibles",
                                        "zeroRecords": "No se encuentran Registros",
                                        "search": "Buscar",
                                        "infoFiltered": "",
                                        "lengthMenu": "Mostrar_MENU_Registros",
                                        "paginate": {

                                            "first": "Primero",
                                            "last": "Último",
                                            "next": "Siguiente",
                                            "previous": "Anterior"
                                        }
                                    }
                                });*/
        $('#tablaClientes').dataTable({
                        "lenghtMenu": [[5, 15, 15, -1], [5, 10, 15, "All"]],
                        "language": {
                            "info": "Pagina _PAGE_ de _PAGES_",
                            "infoEmpty": "No existen Registros disponibles",
                            "zeroRecords": "No se encuentran registros",
                            "search": "Buscar",
                            "infoFiltered": "",
                            "lenghtMenu": "Mostrar _MENU_ Registro",
                            "paginate": {
                                "first": "Primero",
                                "last": "Ultimo",
                                "next": "Siguiente",
                                "previous": "Anterior"
                            }
                        }
                    });
                                $('#tablaProductos').DataTable({
                                    "lengthMenu": [[30, 50, 50, 20], [30, 40, 50, "All"]],
                                    "language": {
                                        "info": "Página_PAGE_de_PAGES",
                                        "infoEmpty": "No existen Registros disponibles",
                                        "zeroRecords": "No se encuentran Registros",
                                        "search": "Buscar",
                                        "infoFiltered": "",
                                        "lengthMenu": "Mostrar_MENU_Registros",
                                        "paginate": {

                                            "first": "Primero",
                                            "last": "Último",
                                            "next": "Siguiente",
                                            "previous": "Anterior"
                                        }
                                    }
                                });
                                 $('#tablaTrabajadores').DataTable({
                                    "lengthMenu": [[30, 50, 50, 20], [30, 40, 50, "All"]],
                                    "language": {
                                        "info": "Página_PAGE_de_PAGES",
                                        "infoEmpty": "No existen Registros disponibles",
                                        "zeroRecords": "No se encuentran Registros",
                                        "search": "Buscar",
                                        "infoFiltered": "",
                                        "lengthMenu": "Mostrar_MENU_Registros",
                                        "paginate": {

                                            "first": "Primero",
                                            "last": "Último",
                                            "next": "Siguiente",
                                            "previous": "Anterior"
                                        }
                                    }
                                });
                            
                            });
                           
                             function SeleccionarCliente(idCliente, nombreCliente){
                  $("#txtIdCliente").val(idCliente);
                  $("#txtNombreCliente").val(nombreCliente);
             }
           
           /*  function Limpiar(){
                  $("#txtIdCliente").val("");
                  $("#txtNombreCliente").val("");
             }*/
                           
                           
                           
                           
                            /*function SeleccionarCliente(idCliente, nombreCliente) {

                                $("#txtIdCliente").val(idCliente);
                                $("#txtNombreCliente").val(nombreCliente);
                            }*/
                            
                               function SeleccionarTrabajador(idTrabajador) {

                                $("#txtIdTrabajador").val(idTrabajador);
                            }

                            
                            function SeleccionarProducto(idProducto, Descripcion, Precio,Desc ,Existencia) {
                                $("#txtIdProducto").val(idProducto);
                                $("#txtdescripcion").val(Descripcion);
                                $("#txtprecio").val(Precio);
                                 $("#txtdesc").val(Desc);
                                $("#txtexistencia").val(Existencia);
                               
                                $("#txtcantidad").focus();
                            }

                            /*function Limpiar() {
                                $("#txtIdCliente").val("");
                                $("#txtNombreCliente").val("");
                            }*/

                            function LimpiarProducto() {
                                $("#txtIdProducto").val("");
                                $("#txtdescripcion").val("");
                                $("#txtprecio").val("");
                                $("#txtexistencia").val("");
                            }
        </script>
    </body>
</html>
