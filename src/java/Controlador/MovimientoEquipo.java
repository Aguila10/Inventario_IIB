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
import java.util.ArrayList;
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
@WebServlet(name = "MovimientoEquipo", urlPatterns = {"/MovimientoEquipo"})
public class MovimientoEquipo extends HttpServlet {

    ConexionBD bd = new ConexionBD();

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
            String busqueda = request.getParameter("equipo");
            if (busqueda == null) {
                String mov = request.getParameter("movimiento");
                if (realizaMovimiento(request)) {

                    mandaMensaje("El movimiento originado por " + mov + " se realizó correctamente", "true", response);
                } else {
                    mandaMensaje("El movimiento originado por " + mov + " no se pudo realizar", "false", response);
                }
            } else {
                out.print(generaTabla(busqueda));
            }
        }
    }

    public boolean realizaMovimiento(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        String movimiento = request.getParameter("movimiento");
        System.out.println(movimiento);
        String fecha = request.getParameter("fecha");
        String login = (String) sesion.getAttribute("login");
        String equipo = request.getParameter("seleccion");
        return Validacion.valida_login(login) && bd.insertaMovimientos(Integer.parseInt(bd.regresaIDNombre(login)), Integer.parseInt(equipo), movimiento, fecha);
    }

    public String generaTabla(String busqueda) {
        ArrayList<String[]> equipos = bd.regresaMarcaSerieDeparta(Integer.parseInt(busqueda));
        String tablaIn = "<table style=\"width:100%\" id=\"tablaResultado\">";
        String tablaFin = "</table>";
        String tr1 = "<tr>";
        String tr2 = "</tr>";
        String td1 = "<td>";
        String td2 = "</td>";
        String tabla = "";
        tabla += tablaIn;
        tabla += "<tr> <th>Marca</th> <th>Serie</th> <th>Departemento</th> <th> Seleccion </th> </tr>";

        for (String[] e : equipos) {
            tabla += tr1;

            tabla += td1;
            tabla += e[0];
            tabla += td2;

            tabla += td1;
            tabla += e[1];
            tabla += td2;

            tabla += td1;
            tabla += e[2];
            tabla += td2;

            tabla += td1;
            tabla += "<input type=\"radio\" value=\"" + e[3] + "\" name=\"seleccion\" id=\"seleccion\"/>";
            tabla += td2;

            tabla += tr2;
        }
        tabla += tablaFin;
        return tabla;
    }

    public void mandaMensaje(String mensaje, String exito, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            response.sendRedirect("administrador.jsp?mensaje=" + URLEncoder.encode(mensaje, "UTF-8") + "&exito=" + exito);
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
