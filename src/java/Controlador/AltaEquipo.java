/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ConexionBD;
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

        boolean edo = registraEquipo(request, response);

        if (edo) {
            mandaMensaje("Registro Exitoso ", response);
        } else {
            mandaMensaje("No se pudo guardar ", response);
        }
    }

    public boolean registraEquipo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ConexionBD bd = new ConexionBD();
        String activoFijo = (String) request.getParameter("activoFijo");
        int actFijo = (activoFijo.equals("")) ? 0 : Integer.parseInt(activoFijo);

        String numInvUNAM = (String) request.getParameter("descripcion");
        int numInv = (numInvUNAM.equals("")) ? 0 : Integer.parseInt(numInvUNAM);

        int numInvInternoDepto = 0;

        String descripcion = (String) request.getParameter("descripcionExtendida");
        System.out.println("descripcion " + descripcion);
        String modelo = (String) request.getParameter("modelo");
        System.out.println("modelo " + modelo);
        String marca = (String) request.getParameter("marca");
        System.out.println("marca " + marca);
        String numSerie = (String) request.getParameter("numeroSerie");
        System.out.println("numSerie " + numSerie);
        String familia = (String) request.getParameter("familia");
        System.out.println("familia " + familia);
        String tipoActivoFijo = (String) request.getParameter("tipoActivoFijo");
        System.out.println("tipoActivoFijo " + tipoActivoFijo);
        String proveedor = (String) request.getParameter("proveedor");
        System.out.println("proveedor " + proveedor);
        String clase = (String) request.getParameter("clase");
        System.out.println("clase " + clase);
        String uso = (String) request.getParameter("uso");
        System.out.println("uso " + uso);
        String nivelObsolencia = (String) request.getParameter("nivelObsolencia");
        System.out.println("nivelObsolencia " + nivelObsolencia);
        String estadoFisico = (String) request.getParameter("estadoFisico");
        System.out.println("estadoFisico " + estadoFisico);
        String ubicacion = (String) request.getParameter("ubicacion");
        System.out.println("ubicacion " + ubicacion);
        String centroCosto = (String) request.getParameter("centroCosto");
        System.out.println("centroCosto " + centroCosto);

        // idusuario NULL
        String fechaResguardo = (String) request.getParameter("fechaResguardo");
        // Responsable NULL

        return bd.insertaEquipo(actFijo, numInv, descripcion, modelo, marca, numSerie, familia, tipoActivoFijo, proveedor, clase, uso, nivelObsolencia, estadoFisico, ubicacion, centroCosto, fechaResguardo, "responsable");

    }

    public void mandaMensaje(String mensaje, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            response.sendRedirect("administrador.jsp?mensaje=" + mensaje);
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
