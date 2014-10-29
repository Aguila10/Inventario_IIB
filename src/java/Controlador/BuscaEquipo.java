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
@WebServlet(name = "BuscaEquipo", urlPatterns = {"/BuscaEquipo"})
public class BuscaEquipo extends HttpServlet {

    
    
    public static final String PRIMERA_LINEA = "<form name=\"actualizacion\" action=\"\" class=\"smart-blue\" method=\"post\" onsubmit=\"\">";
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
            String campoDeBusqueda = request.getParameter("campoBusqueda");
            out.print(obtenFormulario(campoDeBusqueda));
        }
    }
    
    public String obtenFormulario(String campoDeBusqueda){
        String form = "";
        String cad = "cadena";
        form += PRIMERA_LINEA;
        form += "<div class=\"dosColumnas\">\n" +
"				<label for=\"activoFijo\">Clave activo fijo:</label>\n" +
"				<input type=\"text\" id=\"activoFijo\" name=\"activoFijo\" value=\""+cad+"\">\n" +
"                                <label id=\"errorActivoFijo\" class=\"errorFormulario\"></label>\n" +
"			</div>\n" +
"			<div class=\"dosColumnas derecha\">\n" +
"				<label for=\"descripcion\">Descripción: <i>(Número de inventario UNAM)</i></label>\n" +
"				<input type=\"text\" id=\"descripcion\" name=\"descripcion\" value=\""+cad+"\">\n" +
"				<label for=\"descripcion\" id=\"errorDescripcion\" class=\"errorFormulario\"></label>\n" +
"			</div>\n" +
"			<div style=\"margin-top: 12%;\">\n" +
"				<label for=\"descripcionExtendida\">Descricpión extendida: <i>(Se hace la indicación de que incluye teclado y mouse u otros aditamentos, agregar aquí números de inventario de los aditamentos, en caso de tenerlo)</i></label>\n" +
"				<textarea maxlength = \"500\" style=\"height:30%;\" id=\"descripcionExtendida\" name=\"descripcionExtendida\" cols=\"30\" rows=\"10\"></textarea>\n" +
"				<label id=\"errorDescripcionExtendida\" class=\"errorFormulario\"></label>\n" +
"			</div>\n" +
"\n" +
"\n" +
"			<div class=\"dosColumnas\">\n" +
"				<label for=\"marca\">Marca:</label>\n" +
"				<!--Cátalogo BD-->\n" +
"                                <input type=\"text\" list=\"marca\"  value=\"" +"MARCA"+ "\"/>\n" +
"				<datalist id=\"marca\" name=\"marca\" >\n" +
"                                                            \n" +
"				</datalist>\n" +
"				<!--<label id=\"errorMarca\" class=\"errorFormulario\">Esto es un error</label>-->\n" +
"				<label for=\"numeroSerie\">Número de serie:</label>\n" +
"				<input type=\"text\" id=\"numeroSerie\" name=\"numeroSerie\">\n" +
"				<!--<label id=\"errorNumeroSerie\" class=\"errorFormulario\">Esto es un error</label>-->\n" +
"				<label for=\"clase\">Clase:</label>\n" +
"				<!--Cátalogo BD-->\n" +
"                                <input type=\"text\" list=\"clase\" />\n" +
"					<datalist id=\"clase\" name=\"clase\">\n" +
"						\n" +
"					</datalist>\n" +
"				<!--<label id=\"errorClase\" class=\"errorFormulario\">Esto es un error</label>-->\n" +
"				<label for=\"uso\">Uso:</label>\n" +
"				<!--Cátalogo BD-->\n" +
"                                <input type=\"text\" list=\"uso\" />\n" +
"				<datalist id=\"uso\" name=\"uso\">\n" +
"					\n" +
"				</datalist>\n" +
"				<!--<label id=\"errorUso\" class=\"errorFormulario\">Esto es un error</label>-->\n" +
"				<label for=\"estadoFisico\">Estado físico:</label>\n" +
"				<!--Cátalogo BD-->\n" +
"                                <input type=\"text\" list=\"estadoFisico\" />\n" +
"				<datalist id=\"estadoFisico\" name=\"estadoFisico\">\n" +
"					\n" +
"				</datalist>\n" +
"				<!--<label id=\"errorEstadoFisico\" class=\"errorFormulario\">Esto es un error</label>-->\n" +
"				<label for=\"ubicacion\">Ubicación:</label>\n" +
"				<!--Cátalogo BD-->\n" +
"                                <input type=\"text\" list=\"ubicacion\" />\n" +
"				<datalist id=\"ubicacion\" name=\"ubicacion\">\n" +
"					\n" +
"				</datalist>\n" +
"				<!--<label id=\"errorUbicacion\" class=\"errorFormulario\">Esto es un error</label>-->\n" +
"			</div>\n" +
"\n" +
"\n" +
"			<div class=\"dosColumnas derecha\">\n" +
"				<label for=\"modelo\">Modelo:</label>\n" +
"				<input type=\"text\" id=\"modelo\" name=\"modelo\">\n" +
"				<!--<label id=\"errorModelo\" class=\"errorFormulario\">Esto es un error</label>-->\n" +
"				<label for=\"familia\">Familia:</label>\n" +
"				<!--Cátalogo BD-->\n" +
"                                <input type=\"text\" list=\"familia\" />\n" +
"				<datalist id=\"familia\" name=\"familia\">\n" +
"					\n" +
"				</datalist>\n" +
"				<!--<label id=\"errorFamilia\" class=\"errorFormulario\">Esto es un error</label>-->\n" +
"				<label for=\"tipoActivoFijo\">Tipo activo fijo:</label>\n" +
"				<!--Cátalogo BD-->\n" +
"                                <input type=\"text\" list=\"tipoActivoFijo\" />\n" +
"				<datalist id=\"tipoActivoFijo\" name=\"tipoActivoFijo\">\n" +
"					\n" +
"				</datalist>\n" +
"				<!--<label id=\"errorActivoFijo\" class=\"errorFormulario\">Esto es un error</label>-->\n" +
"				<label for=\"nivelObsolencia\">Nivel de obsolencia:</label>\n" +
"				<!--Cátalogo BD-->\n" +
"                                <input type=\"text\" list=\"nivelObsolencia\" />\n" +
"				<datalist id=\"nivelObsolencia\" name=\"nivelObsolencia\">\n" +
"					\n" +
"				</datalist>\n" +
"				<!--<label id=\"errorNivelObsolencia\" class=\"errorFormulario\">Esto es un error</label>-->\n" +
"				<label for=\"centroCosto\">Centro de costo:</label>\n" +
"				<!--Cátalogo BD-->\n" +
"                                <input type=\"text\" list=\"centroCosto\" />\n" +
"				<datalist id=\"centroCosto\" name=\"centroCosto\">\n" +
"					\n" +
"				</datalist>\n" +
"				<!--<label id=\"errorCentroCosto\" class=\"errorFormulario\">Esto es un error</label>-->\n" +
"				<label for=\"proveedor\">Proveedor:</label>\n" +
"				<!--Cátalogo BD-->\n" +
"                                <input type=\"text\" list=\"proveedor\" />\n" +
"				<datalist id=\"proveedor\" name=\"proveedor\">\n" +
"					\n" +
"				</datalist>\n" +
"				<!--<label id=\"errorProveedor\" class=\"errorFormulario\">Esto es un error</label>-->\n" +
"			</div>\n" +
"			<label for=\"responsable\">Responsable: <i>(Usuario)</i></label>\n" +
"			<input type=\"text\">\n" +
"			<label for=\"fechaResguardo\">Fecha de resguardo: <i>(Fecha en la que se hizo la verificación)</i></label>\n" +
"			<input type=\"date\" id=\"fechaResguardo\" name=\"fechaResguardo\" requiered>\n" +
"			<!--<label id=\"errorFechaResguardo\" class=\"errorFormulario\">Esto es un error</label>-->\n" +
"\n" +
"			<input type=\"submit\" value=\"Aceptar\" class=\"button\">\n" +
"		</form>";
        
        return form;
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
