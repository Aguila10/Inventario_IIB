/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ConexionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "IniciarSesion", urlPatterns = {"/IniciarSesion"})
public class IniciarSesion extends HttpServlet {

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
        revisarSesion(request, response);
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

    private void revisarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ConexionBD bd = new ConexionBD();

        String usuario = request.getParameter("login");
        String contrasenia = request.getParameter("contrasenia");
        Boolean validacion = true;
        String tipo_usuario;
        HttpSession sesion;

        validacion = validacion && Validacion.valida_login(usuario);
        validacion = validacion && Validacion.valida_contrasenia(contrasenia, contrasenia);

        if (validacion) {
            tipo_usuario = bd.buscaLogin(usuario, contrasenia);
       
            if (!(tipo_usuario.equals("false"))) {

                sesion = request.getSession(true);

                switch (tipo_usuario) {
                    case "Administrador":
                        sesion.setAttribute("identidad", "administrador");
                        sesion.setAttribute("login", usuario);
                        break;
                    case "Secretaria":
                        sesion.setAttribute("identidad", "secretaria");
                        sesion.setAttribute("login", usuario);
                        break;

                    case "Tecnico Academico":
                        sesion.setAttribute("identidad", "tecnicoAcademico");
                        sesion.setAttribute("login", usuario);
                        break;

                    case "Jefe de inventario":
                        sesion.setAttribute("identidad", "jefeDeInventario");
                        sesion.setAttribute("login", usuario);
                        break;
                }
                response.sendRedirect(sesion.getAttribute("identidad")+".jsp");
            } else {
                response.sendRedirect("index.jsp");
            }
        } else {
            response.sendRedirect("index.jsp");
        }

    }

    /**
     *
     * @param mensaje
     * @param response
     * @throws IOException
     */
    public void mandaMensaje(String mensaje, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //response.sendRedirect("administrador.jsp?mensaje=" + mensaje);
            response.sendRedirect("index.jsp");
        }
    }

}
