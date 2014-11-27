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
        registraUsuario(request, response);
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

    private void registraUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ConexionBD bd = new ConexionBD();
        String categoria = request.getParameter("categoria");
        String nombre = request.getParameter("nombre");
        String login = request.getParameter("login");
        String correo = request.getParameter("correo");
        String contraseniaUno = request.getParameter("contraseniaUno");
        String contraseniaDos = request.getParameter("contraseniaDos");
        HttpSession sesion;

        Boolean validacion = true;

        validacion = validacion && Validacion.valida_nombre(nombre);
        validacion = validacion && Validacion.valida_login(login);
        validacion = validacion && Validacion.valida_mail(correo);
        validacion = validacion && Validacion.valida_contrasenia(contraseniaUno, contraseniaDos);

        if (validacion) {
            bd.insertaUsuario(login, contraseniaUno, nombre, categoria);
            sesion = request.getSession(true);

            switch (categoria) {
                case "Administrador":
                    sesion.setAttribute("identidad", "administrador");
                    sesion.setAttribute("login", login);
                    //  response.sendRedirect("administrador.jsp");
                    break;
                case "Secretaria":
                    sesion.setAttribute("identidad", "secretaria");
                    sesion.setAttribute("login", login);
                    // response.sendRedirect("administrador.jsp");
                    break;

                case "Tecnico Academico":
                    sesion.setAttribute("identidad", "tecnico academico");
                    sesion.setAttribute("login", login);
                    // response.sendRedirect("administrador.jsp");
                    break;

                case "Jefe de inventario":
                    sesion.setAttribute("identidad", "jefe de inventario");
                    sesion.setAttribute("login", login);
                    break;
            }

            response.sendRedirect("administrador.jsp?mensaje=Se registro usuario exitosamente&exito=true");

        } else {

            response.sendRedirect("administrador.jsp?mensaje=Error al registrar usuario&exito=false");

        }

    }

    public void mandaMensaje(String mensaje, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            response.sendRedirect("administrador.jsp?mensaje=" + mensaje);
        }
    }

}
