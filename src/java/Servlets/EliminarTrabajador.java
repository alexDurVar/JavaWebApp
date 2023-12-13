/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Entidades.Trabajador;
import LogicaNegocio.LNTrabajador;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Telyman
 */
public class EliminarTrabajador extends HttpServlet {

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
         /*Este servlet no va a permitir eliminar a un trabajador si este se usa en otro formulario*/
        try  {
            /* TODO output your page here. You may use following sample code. */
           LNTrabajador logica = new LNTrabajador();
           int resultado=0; 
           
            String id = request.getParameter("idEliminar"); 
            // obtiene el parámetro del QUERY STRING 
            // SIEMPRE retorna un String
            
            int codigo = Integer.parseInt(id);
            Trabajador trabajador = new Trabajador();
            trabajador.setId_trabajador(codigo);
            
            if(logica.VerificarEliminar(trabajador)==1){
                 out.print("No se puede eliminar"); 
            }else{
                 resultado = logica.Eliminar(trabajador);
             out.print("Trabajador Eliminado"); 
            }
           
            /*  Si creáramos un método que elimine y sólo reciba un int, nos ahorraríamos
                crear la entidad. Pero en este ejemplo sólo tenemos un método que 
                eliminar que recibe una entidad.             
            */
            
            String mensaje = "Trabajador eliminado";
            // Obtenemos el mensaje que viene desde el SP
            
            mensaje = URLEncoder.encode(mensaje,"UTF-8");
            /*  Al mensaje le hacemos la codificación de caracteres porque 
                podría traer caracteres especiales que no se pueden escribir
                en el HTML
            */
            
            // Utilizamos el objeto intrínseco RESPONSE para responder:
            // Redireccionando a la página FrmListarClientes y 
            // enviando por parámetro el mensaje. 
            response.sendRedirect("FrmListarTrabajador.jsp?mensajeServletEliminarTrabajador=" + mensaje + "&resultado=" + resultado);
        }
        catch(Exception ex){
            out.print(ex.getMessage()); // imprime en el HTML
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
