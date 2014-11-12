/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jpachecov
 */
@WebServlet(name = "ActualizaEquipo", urlPatterns = {"/ActualizaEquipo"})
public class ActualizaEquipo extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            
            
            if(actualizaEquipo(request,response)){
                out.print("Actualización exitosa");
            } else {
                out.print("Actualización fallida");                
            }
                

        }
    }
        
    private boolean actualizaEquipo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        int activoFij = Integer.parseInt(request.getParameter("activoFij"));
        String descripcion = request.getParameter("descripcion");
        String descripcionExt = request.getParameter("descripcionExt");
        String numeroSer = request.getParameter("numeroSer");
        String clase = request.getParameter("clase");
        String uso = request.getParameter("uso");
        String estado = request.getParameter("estado");
        String ubicacion = request.getParameter("ubicacion");
        String fechaRes=request.getParameter("fechaRes");  
        String modelo=request.getParameter("modelo");
        String familia=request.getParameter("familia");
        String tipoActivo=request.getParameter("tipoAct");
        String nivelObs=request.getParameter("nivelObs");
        String centroCos=request.getParameter("centroCos");
        String proveedor=request.getParameter("proveedor");
        String responsable=request.getParameter("responsable");
        
        System.out.println(activoFij);
        System.out.println(descripcion);
        System.out.println(descripcionExt);
        System.out.println(numeroSer);
        System.out.println(clase);
        System.out.println(uso); 
        System.out.println(estado);
        System.out.println(ubicacion);
        System.out.println(fechaRes);
        System.out.println(modelo);
        System.out.println(familia);
        System.out.println(tipoActivo);
        System.out.println(nivelObs);
        System.out.println(centroCos);
        System.out.println(proveedor);
        System.out.println(responsable);
    return true;
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
