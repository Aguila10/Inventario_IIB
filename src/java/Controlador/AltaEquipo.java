/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ConexionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jpachecov
 */
@WebServlet(name = "altaEquipo", urlPatterns = {"/altaEquipo"})
public class AltaEquipo extends HttpServlet {

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
 response.setCharacterEncoding("UTF-8");
 request.setCharacterEncoding("UTF-8");
        String msj_exito = "Se registró el equipo correctamente";
        String msj_error = "Error al registrar el equipo";

        HttpSession sesion = request.getSession(true);
        String tipo_sesion = (String) sesion.getAttribute("identidad");

        if (registraEquipo(request, response)) {
            response.sendRedirect(tipo_sesion + ".jsp?mensaje=" + URLEncoder.encode(msj_exito, "UTF-8") + "&exito=true");
        } else {
            response.sendRedirect(tipo_sesion + ".jsp?mensaje=" + URLEncoder.encode(msj_error, "UTF-8") + "&exito=false");
        }

    }

    /**
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public boolean registraEquipo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ConexionBD bd = new ConexionBD();
        request.setCharacterEncoding("UTF-8");
        String activoFijo = (String) request.getParameter("activoFijo");
        int actFijo = (activoFijo.equals("")) ? 0 : Integer.parseInt(activoFijo);

        String numInvUNAM = (String) request.getParameter("descripcion");
        int numInv = (numInvUNAM.equals("")) ? 0 : Integer.parseInt(numInvUNAM);

        int numInvInternoDepto = 0;
        
        String descripcion = request.getParameter("descripcionExtendida");
        String modelo = request.getParameter("modelo");
        String marca = request.getParameter("marca");
        String numSerie = request.getParameter("numeroSerie");
        String familia =  request.getParameter("familia");
        String tipoActivoFijo = request.getParameter("tipoActivoFijo");
        String proveedor =  request.getParameter("proveedor");
        String clase = request.getParameter("clase");
        String uso =  request.getParameter("uso");
        String nivelObsolencia = request.getParameter("nivelObsolencia");
        String estadoFisico =  request.getParameter("estadoFisico");
        String ubicacion = request.getParameter("ubicacion");
        String centroCosto =  request.getParameter("centroCosto");
        // idusuario NULL
        String fechaResguardo = request.getParameter("fechaResguardo");
        String responsable = request.getParameter("responsable");

        return bd.insertaEquipo(actFijo, numInv, descripcion, modelo, marca, numSerie, familia, tipoActivoFijo, proveedor, clase, uso, nivelObsolencia, estadoFisico, ubicacion, centroCosto, fechaResguardo, responsable);

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
