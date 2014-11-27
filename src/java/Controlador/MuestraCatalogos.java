/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ConexionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jpachecov
 */
@WebServlet(name = "MuestraCatalogos", urlPatterns = {"/MuestraCatalogos"})
public class MuestraCatalogos extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            String catalogo = request.getParameter("catalogo");
            Formularios f = new Formularios();

            ConexionBD con = new ConexionBD();

            ArrayList<String[]> cats = con.regresaCatalogoConId(catalogo);
            out.print(generaTabla(cats));
        }
    }

    /**
     *
     * @param cat. Un arraylist con los elementos de un catalogo
     * @return tabla. La tabla en html.
     */
    public String generaTabla(ArrayList<String[]> cat) {
        String tabla = "";
        tabla += "<table style='width:100%'>";

        for (int i = 0; i < cat.size(); i++) {
            tabla += "<tr id='padre" + cat.get(i)[0] + "'>";
            tabla += "<td id='hijo" + cat.get(i)[0] + "' onclick='voyAeditar(" + cat.get(i)[0] + ")'>";
            tabla += cat.get(i)[1];
            tabla += "</td>";
            tabla += "</tr>";
        }
        tabla += "</table>";
        return tabla;
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
