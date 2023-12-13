/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Entidades.*;

import LogicaNegocio.*;
import LogicaNegocio.LNEncabezadoFactura;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Steven
 */
public class Facturar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
        try{
            LNEncabezadoFactura LogicaFactura = new LNEncabezadoFactura();
            EncabezadoFactura EntidadFactura= new EncabezadoFactura();
            DetallesFactura EntidadDetalle = new DetallesFactura();
            int resultado;
            String mensaje= "";
            
            if(request.getParameter("txtNombreCliente")!= null && 
                    !request.getParameter("txtNombreCliente").equals("")){
                EntidadFactura.setId_cliente(Integer.parseInt(request.getParameter("txtIdCliente")));
                 EntidadFactura.setNombre(request.getParameter("txtNombreCliente")); 
                EntidadFactura.setNumero_factura(Integer.parseInt(request.getParameter("txtnumFactura")));
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                String fechaString  = request.getParameter("txtFechaFactura");
                Date fecha = formato.parse(fechaString);
                java.sql.Date fechasql = new java.sql.Date(fecha.getTime());
                EntidadFactura.setImpuesto(Float.parseFloat(request.getParameter("txtIm")));
                EntidadFactura.setMonto_a_pagar(Float.parseFloat(request.getParameter("txtMontot")));
                EntidadFactura.setId_trabajador(Integer.parseInt(request.getParameter("txtIdTrabajador"))); 
                EntidadFactura.setFecha(fechasql);
                EntidadFactura.setId_cliente(Integer.parseInt(request.getParameter("txtIdCliente")));
                EntidadFactura.setEstado("Pendiente");
                if(!(request.getParameter("txtdescripcion").equals("")) &&
                        !(request.getParameter("txtcantidad").equals("")) &&
                        !(request.getParameter("txtprecio").equals(""))){
                    EntidadDetalle.setNumero_factura(-1);
                    EntidadDetalle.setNumero_producto(Integer.parseInt(request.getParameter("txtIdProducto")));
                    EntidadDetalle.setNombre_producto(request.getParameter("txtdescripcion")); 
                    EntidadDetalle.setCantidad(Integer.parseInt(request.getParameter("txtcantidad")));
                    EntidadDetalle.setPrecio(Float.parseFloat(request.getParameter("txtprecio")));
                    resultado = LogicaFactura.Insertar(EntidadFactura, EntidadDetalle);
                    mensaje = LogicaFactura.getMensaje();
                }else{
                    resultado = LogicaFactura.ModificarFactura(EntidadFactura);
                }
                response.sendRedirect("Frm_facturar.jsp?msgFac="+mensaje+"&txtnumFactura="+resultado);
            }else{
                response.sendRedirect("Frm_facturar.jsp?txtnumFactura="+
                        request.getParameter("txtnumFactura"));
            }
        }catch(Exception ex){
            out.print(ex.getMessage());
        }
    }

    //// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
