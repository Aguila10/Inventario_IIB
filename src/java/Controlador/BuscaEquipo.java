/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Modelo.ConexionBD;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
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

    
    ConexionBD bd = new ConexionBD();
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
            out.print(obtenTabla(campoDeBusqueda));
        }
    }
    
    public String obtenTabla(String busqueda){
        
        ArrayList<Equipo> lista = bd.buscaEquipo(Integer.parseInt(busqueda));
        
        String tablaIn = "<table style=\"width:100%\">";
        String tablaFin = "</table>";
        String tr1 = "<tr>";
        String tr2 = "</tr>";
        String td1 = "<td>";
        String td2 = "</td>";
        String boton = "<button onclick=\"\" >Actualizar</button>";
        
        String tabla = "";
        tabla+=tablaIn;
        
        tabla+="<th> <td>Clave Activo Fijo</td> <td> Num. inventario </td> <td>Serie</td>"
                + "<td>Marca</td>  <td>Tipo Activo</td> <td>Selección</td> </th>";
        for(Equipo elem:lista){
            tabla+=tr1;
            
            tabla+=td1;
            tabla+=String.valueOf(elem.getClave_activo_fijo());
            tabla+=td2;
            
            tabla+=td1;
            tabla+=String.valueOf(elem.getNum_inv_unam());
            tabla+=td2;
            
            tabla+=td1;
            tabla+=elem.getSerie();
            tabla+=td2;
            
            tabla+=td1;            
            tabla+=elem.getClave_marcar();
            tabla+=td2;
            
            tabla+=td1;
            tabla+=elem.getClave_tipo();
            tabla+=td2;
            
            tabla+=td1;
            tabla+="<input type=\"radio\" value=\"inventario\"/>";
            tabla+=td2;
            
            tabla+=tr2;
        }
        tabla+=tablaFin;
        tabla+=boton;
        return tabla;
    }
    
    public String obtenFormulario(String campoDeBusqueda){
        ArrayList<Equipo> lista = bd.buscaEquipo(Integer.parseInt(campoDeBusqueda));
        
        String form = "";
        if(lista.size() != 0){
            Equipo equipo = lista.get(0);
           
            form = llenaFormularioAltaEquipo();
            
          
            form = form.replace("action=\"altaEquipo\"","action=\"\"");
            form = form.replace("method=\"post\" onsubmit=\"return validaCampos()\"","");
            form = form.replace("<h3>Equipo: Dar alta</h3>","");
            form = form.replace("<input type=\"submit\" value=\"Aceptar\" class=\"button\">","");
            
            form = form.replace("id=\"activoFijo\" name=\"activoFijo\"","id=\"activoFijo\" name=\"activoFijo\" value=\""+equipo.getClave_activo_fijo()+"\"");
            form = form.replace("id=\"descripcion\" name=\"descripcion\"","id=\"descripcion\" name=\"descripcion\" value=\"" +equipo.getNum_inv_unam()+"\"");
 
            form = form.replace("></textarea>",">"+equipo.getClave_descripcion()+"</textarea>");
            form = form.replace("list=\"marca\" name=\"marca\"","list=\"marca\" name=\"marca\" value=\""+equipo.getClave_marcar()+"\"");
            form = form.replace("id=\"numeroSerie\" name=\"numeroSerie\"","id=\"numeroSerie\" name=\"numeroSerie\" value=\""+equipo.getSerie()+"\"");
            form = form.replace("list=\"clase\" name=\"clase\"","list=\"clase\" name=\"clase\" value=\""+equipo.getClase()+"\"");
            form = form.replace("list=\"uso\" name=\"uso\"","list=\"uso\" name=\"uso\" value=\""+equipo.getUso()+"\"");
            form = form.replace("list=\"estadoFisico\" name=\"estadoFisico\"","list=\"estadoFisico\" name=\"estadoFisico\" value=\""+equipo.getEstado_físico()+"\"");
            form = form.replace("list=\"ubicacion\" name=\"ubicacion\"","list=\"ubicacion\" name=\"ubicacion\" value=\""+equipo.getClave_area()+"\"");
            form = form.replace("id=\"modelo\" name=\"modelo\"","id=\"modelo\" name=\"modelo\" value=\""+equipo.getClave_modelo()+"\"");
            form = form.replace("list=\"familia\" name=\"familia\"","list=\"familia\" name=\"familia\" value=\""+equipo.getClave_familia()+"\"");
            form = form.replace("list=\"tipoActivoFijo\" name=\"tipoActivoFijo\"","list=\"tipoActivoFijo\" name=\"tipoActivoFijo\" value=\""+equipo.getClave_tipo()+"\"");
            form = form.replace("list=\"nivelObsolencia\" name=\"nivelObsolencia\"","list=\"nivelObsolencia\" name=\"nivelObsolencia\" value=\""+equipo.getNivel_de_obsolescencia()+"\"");
            form = form.replace("list=\"centroCosto\" name=\"centroCosto\"","list=\"centroCosto\" name=\"centroCosto\" value=\""+equipo.getClave_institucion()+"\"");
            form = form.replace("list=\"proveedor\" name=\"proveedor\"","list=\"proveedor\" name=\"proveedor\" value=\""+equipo.getClave_proveedor()+"\"");
            form = form.replace("id=\"responsable\" name=\"responsable\"","id=\"responsable\" name=\"responsable\" value=\""+equipo.getResponsable()+"\"");
            form = form.replace("id=\"fechaResguardo\" name=\"fechaResguardo\"","id=\"fechaResguardo\" name=\"fechaResguardo\" value=\""+equipo.getFecha_de_resguardo()+"\"");
        System.out.print(form);
            /*    
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
            */
            
        }
        
        
        return form;
    }
    /**
     * Metodo que llena los catalogos del formulario equipoAlta antes de que se
     * le muestre al usuario.
     *
     * @return form El formulario con los catalogos llenos.
     */
    public String llenaFormularioAltaEquipo() {
        String form = "";
        ServletContext context = getServletContext();
        InputStream is = context.getResourceAsStream("/WEB-INF/formularios/equipoAlta.html");
        if (is != null) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            String text = "";
            try {
                while ((text = reader.readLine()) != null) {

                    if (text.contains("<datalist id=\"marca\" name=\"marca\">")) {
                        form += text;
                        text = reader.readLine(); // <option>----</option>
                        form += llenaCatalogo(getCatalogo("catalogo_marca"));
                        continue;
                    }
                    if (text.contains("<datalist id=\"familia\" name=\"familia\">")) {
                        form += text;
                        text = reader.readLine(); // <option>----</option>
                        form += llenaCatalogo(getCatalogo("catalogo_familia"));
                        continue;
                    }
                    if (text.contains("<datalist id=\"clase\" name=\"clase\">")) {
                        form += text;
                        text = reader.readLine(); // <option>----</option>
                        form += llenaCatalogo(getCatalogo("catalogo_clase"));
                        continue;
                    }
                    if (text.contains("<datalist id=\"tipoActivoFijo\" name=\"tipoActivoFijo\">")) {
                        form += text;
                        text = reader.readLine(); // <option>----</option>
                        form += llenaCatalogo(getCatalogo("catalogo_tipo_equipo"));
                        continue;
                    }
                    if (text.contains("<datalist id=\"uso\" name=\"uso\">")) {
                        form += text;
                        text = reader.readLine(); // <option>----</option>
                        form += llenaCatalogo(getCatalogo("catalogo_uso"));
                        continue;
                    }
                    if (text.contains("<datalist id=\"nivelObsolencia\" name=\"nivelObsolencia\">")) {
                        form += text;
                        text = reader.readLine(); // <option>----</option>
                        form += llenaCatalogo(getCatalogo("catalogo_nivel"));
                        continue;
                    }
                    if (text.contains("<datalist id=\"estadoFisico\" name=\"estadoFisico\">")) {
                        form += text;
                        text = reader.readLine(); // <option>----</option>
                        form += llenaCatalogo(getCatalogo("catalogo_estado_fisico"));
                        continue;
                    }
                    if (text.contains("<datalist id=\"centroCosto\" name=\"centroCosto\">")) {
                        form += text;
                        text = reader.readLine(); // <option>----</option>
                        form += llenaCatalogo(getCatalogo("catalogo_institucion"));
                        continue;
                    }
                    if (text.contains("<datalist id=\"ubicacion\" name=\"ubicacion\">")) {
                        form += text;
                        text = reader.readLine(); // <option>----</option>
                        form += llenaCatalogo(getCatalogo("catalogo_area"));
                        continue;
                    }
                    if (text.contains("<datalist id=\"proveedor\" name=\"proveedor\">")) {
                        form += text;
                        text = reader.readLine(); // <option>----</option>
                        form += llenaCatalogo(getCatalogo("catalogo_proveedor"));
                        continue;
                    }

                    form += text;

                }
            } catch (IOException ex) {
                Logger.getLogger(Formularios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return form;
    }

/**
     * Metodo que genera un catalogo en codigo html por ejemplo:
     * <option> elem1 </option>
     * <option> elem2 </option>
     *
     * @param elementos Un arraylist con elementos de un catalogo
     * @return res El catalogo en codigo html
     */
    public String llenaCatalogo(ArrayList<String> elementos) {
        String opcion = "<option label='";
        String opcion_ = "/>";
        String res = "";
        for (String elem : elementos) {

            res += opcion + elem + "' " + "value='" + elem + "' " + opcion_ + "\n";
        }
        return res;
    }

    /**
     * Metodo que obtiene un catalogo de la base de datos y regresa los
     * elementos de ese catalogo en un arraylist.
     *
     * @param catalogo El nombre del catalogo que se necesita
     * @return cat . El arraylist con los elementos del catalogo.
     */
    public ArrayList getCatalogo(String catalogo) {
        ArrayList<String> cat = bd.regresaCatalogo(catalogo);
        return cat;
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