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
@WebServlet(name = "ExportaConsulta", urlPatterns = {"/ExportaConsulta"})
public class ExportaConsulta extends HttpServlet {

    private String marca;
    private String numero;
    private String familia;
    private String ubicacion;
    private String responsable;
    private String tipoEquipo;
    private String departamento;
    private String fechai;
    private String fechaf;
    private String estado;

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
 response.setCharacterEncoding("UTF-8");
 request.setCharacterEncoding("UTF-8");
        String msj_error = "Error al exportar consulta";
        
        HttpSession sesion = request.getSession(true);
        String tipo_sesion = (String) sesion.getAttribute("identidad");
        
        String genera = request.getParameter("genera");
        if (genera == null) { // si apretado el link de descarga  

            try {
                
                creaXLS(response);

            } catch (ServletException | IOException e) {
                
                response.sendRedirect(tipo_sesion + ".jsp?mensaje=" + URLEncoder.encode(msj_error, "UTF-8") + "&exito=false");
            }

        } else {
            marca = request.getParameter("marca");
            numero = request.getParameter("numeroSerie");
            familia = request.getParameter("familia");
            ubicacion = request.getParameter("ubicacion");
            responsable = request.getParameter("responsable");
            tipoEquipo = request.getParameter("tipoEquipo");
            departamento = request.getParameter("departamento");
            fechai = request.getParameter("fechaI");
            fechaf = request.getParameter("fechaF");
            estado = request.getParameter("estado");

        }

    }

    public void creaXLS(HttpServletResponse response) throws ServletException, IOException {
        ConexionBD bd = new ConexionBD();
        ArrayList<Equipo> equipos = bd.reportes(marca, numero, familia, tipoEquipo, fechai, fechaf, departamento, ubicacion, responsable, estado);
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=Reporte.xls");

        response.getWriter().write("Num. Inv. Interno");
        response.getWriter().write(",");
        response.getWriter().write("Num. Inv. UNAM");
        response.getWriter().write(",");
        response.getWriter().write("Marca");
        response.getWriter().write(",");
        response.getWriter().write("Modelo");
        response.getWriter().write(",");
        response.getWriter().write("Serie");
        response.getWriter().write(",");
        response.getWriter().write("Familia");
        response.getWriter().write(",");
        response.getWriter().write("Tipo");
        response.getWriter().write(",");
        response.getWriter().write("Fecha de registro");
        response.getWriter().write(",");
        response.getWriter().write("Departamento");
        response.getWriter().write(",");
        response.getWriter().write("Ubicación");
        response.getWriter().write(",");
        response.getWriter().write("Responsable");
        response.getWriter().write("\n");
        for (Equipo e : equipos) {
            response.getWriter().write(new Integer(e.getClave_activo_fijo()).toString());
            response.getWriter().write(",");
            response.getWriter().write(new Integer(e.getNum_inv_unam()).toString());
            response.getWriter().write(",");
            response.getWriter().write(e.getClave_marcar());
            response.getWriter().write(",");
            response.getWriter().write(e.getClave_modelo());
            response.getWriter().write(",");
            response.getWriter().write(e.getSerie());
            response.getWriter().write(",");
            response.getWriter().write(e.getClave_familia());
            response.getWriter().write(",");
            response.getWriter().write(e.getClave_tipo());
            response.getWriter().write(",");
            response.getWriter().write(e.getFecha_de_resguardo());
            response.getWriter().write(",");
            response.getWriter().write(e.getClave_institucion());
            response.getWriter().write(",");
            response.getWriter().write(e.getClave_area());
            response.getWriter().write(",");
            response.getWriter().write(e.getResponsable());
            response.getWriter().write("\n");

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
