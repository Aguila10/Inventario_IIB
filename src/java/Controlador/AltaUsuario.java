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
 * @author rae
 */
@WebServlet(name = "AltaUsuario", urlPatterns = {"/AltaUsuario"})
public class AltaUsuario extends HttpServlet {

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

        String msj_exito = "Se registro el usuario exitosamente";
        String msj_error = "Error al registrar usuario";

        HttpSession sesion = request.getSession(true);
        String tipo_sesion = (String) sesion.getAttribute("identidad");

        if (registraUsuario(request, response)) {
            response.sendRedirect(tipo_sesion + ".jsp?mensaje=" + URLEncoder.encode(msj_exito, "UTF-8") + "&exito=true");
        } else {
            response.sendRedirect(tipo_sesion + ".jsp?mensaje=" + URLEncoder.encode(msj_error, "UTF-8") + "&exito=false");
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

    private boolean registraUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ConexionBD bd = new ConexionBD();
        String categoria = request.getParameter("categoria");
        String nombre = request.getParameter("nombre");
        String login = request.getParameter("login");
        String correo = request.getParameter("correo");
        String contraseniaUno = request.getParameter("contraseniaUno");
        String contraseniaDos = request.getParameter("contraseniaDos");

        Boolean validacion = true;

        validacion = validacion && Validacion.valida_nombre(nombre);
        validacion = validacion && Validacion.valida_login(login);
        validacion = validacion && Validacion.valida_mail(correo);
        validacion = validacion && Validacion.valida_contrasenia(contraseniaUno, contraseniaDos);

        if (validacion && bd.insertaUsuario(login, contraseniaUno, nombre, categoria)) {
            return true;
        } else {
            return false;
        }

    }

}
