/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Entidades.DetallesFactura;
import LogicaNegocio.LNDetalleFactura;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Telyman
 */
public class EliminarDetalle extends HttpServlet {

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
        /*Este servlet es ara eliminar los detalles de factura de las facturas pendientes, no se podran eliminar los detalles de las facturas canceladas, solo se eliminar los detlles de las facturas pendientes, y la factura tambien se va a eliminar */
        try  {
            /* TODO output your page here. You may use following sample code. */
             LNDetalleFactura LogicaDetalle = new  LNDetalleFactura();
             int codigo =0;
              int factura=0;
             codigo = Integer.parseInt(request.getParameter("idproducto"));
             factura = Integer.parseInt(request.getParameter("idfactura"));
            DetallesFactura Entidad = new DetallesFactura();
            Entidad.setNumero_producto(codigo);
            Entidad.setNumero_factura(factura);
           LogicaDetalle.Eliminar(Entidad);
            response.sendRedirect("Frm_ListarFacturas.jsp");
            
        }catch(Exception e){
            out.print(e.getMessage());
        }
           
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
