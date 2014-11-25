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
@WebServlet(name = "ConsultaEquipo", urlPatterns = {"/ConsultaEquipo"})
public class ConsultaEquipo extends HttpServlet {
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
        try (PrintWriter out = response.getWriter()) {
            out.print(hazConsulta(request));
        }
    }
    
    public String hazConsulta(HttpServletRequest request){
        String marca = request.getParameter("marca");
        String numero = request.getParameter("numeroSerie");
        String familia = request.getParameter("familia");
        String ubicacion = request.getParameter("ubicacion");
        String responsable = request.getParameter("responsable");
        String tipoEquipo = request.getParameter("tipoEquipo");
        String departamento = request.getParameter("departamento");
        String fechai = request.getParameter("fechaI");
        String fechaf = request.getParameter("fechaF");
        String estado = request.getParameter("estado");
        
        
        
        
       return generaTabla(bd.reportes(marca, numero, familia, tipoEquipo, fechai, fechaf, departamento, ubicacion, responsable, estado));//marca+" "+numero+" "+familia+" "+ubicacion+" "+responsable+" "+tipoEquipo+" "+departamento+
              // " "+fechai+" "+fechaf+" "+estado;                
    }

    public String generaTabla(ArrayList<Equipo> equipos){
        
        String tabla = "<table style='width:90%' id='tablaResultado'>\n";
        tabla+="<tr>\n";
        tabla+="<th>Num. Inv. interno</th> <th>Num. Inv. UNAM</th> <th>Marca</th> <th>Modelo</th>"
                + "<th>Serie</th> <th>Familia</th> <th>Tipo</th> <th>Fecha de registro</th>"
                + "<th>Departamento</th> <th>Ubicación</th> <th>Responsable</th>\n";
    
        if(equipos.size() == 0) {
            return "<label id=\"errorBusqueda\" class=\"errorFormulario\">No se encontraron equipos</label>";
        }
        for(Equipo e: equipos){
        tabla+="<tr>\n";
        
        tabla+="<td>"+e.getClave_activo_fijo()+"</td>";
        tabla+="<td>"+e.getNum_inv_unam()+"</td>";
        tabla+="<td>"+e.getClave_marcar()+"</td>";
        tabla+="<td>"+e.getClave_modelo()+"</td>";
        tabla+="<td>"+e.getSerie()+"</td>";
        tabla+="<td>"+e.getClave_familia()+"</td>";
        tabla+="<td>"+e.getClave_tipo()+"</td>";
        tabla+="<td>"+e.getFecha_de_resguardo()+"</td>";
        tabla+="<td>"+e.getClave_institucion()+"</td>";
        tabla+="<td>"+e.getClave_area()+"</td>";
        tabla+="<td>"+e.getResponsable()+"</td>";
        
        tabla+="</tr>\n";
        }
        
        tabla+="</tr>\n";
        tabla+="</table>\n";
        
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
