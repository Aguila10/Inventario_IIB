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
import javax.servlet.http.HttpSession;

/**
 *
 * @author mphb
 */
@WebServlet(name = "Formularios", urlPatterns = {"/Formularios"})
public class Formularios extends HttpServlet {

    /*Objeto para acceso a la base de datos*/
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

        String form = "";
        HttpSession sesion = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            /*Formulario solicitado*/
            String formulario = request.getParameter("formulario");
            if (formulario.equals("movimientoEquipo")) {
                form = obtenFormulario(formulario);
                form = form.replace("<input type=\"text\" id=\"nombre\" name=\"nombre\">", "<input type=\"text\" id=\"nombre\" "
                        + "name=\"nombre\" value=\""
                        + bd.regresaNombre((String) sesion.getAttribute("login")) + "\" disabled>");
                out.print(form);
            } else {
                if (formulario.equals("usuarioBaja")) {
                    form = obtenFormulario(formulario);
                    form = form.replace("<aqui va la tabla>", obten_tabla_usuarios((String) sesion.getAttribute("login")));
                    out.print(form);
                } else {
                    if (formulario.equals("catalogoAltaRegistro")) {
                        form = obtenFormulario(formulario);
                        form = form.replace("<aqui va el catalogo>",obten_select_catalogo("catalogo_tipo_equipo"));
                        out.print(form);
                    } else {
                        out.print(obtenFormulario(formulario));
                    }
                }

            }
        }
    }

    
    private String obten_select_catalogo(String nombre_catalogo){
    
    ConexionBD con = new ConexionBD();
    ArrayList<String[]> elementos = con.regresaCatalogoConId(nombre_catalogo);
    String select = "";

    //datalist = "<input type=\"text\" list=\"list_"+nombre_catalogo+"\" id=\"input_"+ nombre_catalogo+"\" name=\"input_"+ nombre_catalogo+"\" style=\"display:none\"/>\n" +
     select =  "<select id=\"select_"+ nombre_catalogo  +"\" name=\"select_"+ nombre_catalogo +"\" style=\"display:none\">\n";
    
    for(String[] elemento : elementos){
        select += "<option value=\""+ elemento[0] +"\">" + elemento[1]  + "</option>\n";
    }
    select += "</select>\n";
    
    return select;
    }
    
    private String obten_tabla_usuarios(String login) {

        ConexionBD con = new ConexionBD();
        ArrayList<String[]> usuarios = con.buscaNombreLogin(login);
        String tabla = "";

        tabla += "<center><table style=\"width:100%\" id=\"tablaResultado\">\n"
                + "    		<tr>\n"
                + "    			<th>Login</th>\n"
                + "    			<th class=\"campoNombre\">Nombre</th>\n"
                + "    			<th>Seleccionar</th>\n"
                + "    		</tr>\n";

        for (String[] usuario : usuarios) {

            tabla += "<tr>\n"
                    + "    			<td>" + usuario[0] + "</td>\n"
                    + "    			<td class=\"campoNombre\">" + usuario[1] + "</td>\n"
                    + "    			<td><input type=\"checkbox\" value=\"" + usuario[0] + "\" name=\"usuarios\"></td>\n"
                    + "    		</tr>\n";

        }

        tabla += "</table></center>";

        return tabla;
    }

    /**
     * Metodo que regresa un formulario en forma de cadena
     *
     * @param formulario El formulario que queremos obtener.
     * @return form. El formulario en forma de cadena.
     */
    public String obtenFormulario(String formulario) {
        String form = "";
        switch (formulario) {
            case "equipoAlta":
                form = llenaFormularioAltaEquipo();
                break;
            case "equipoActualizar": // Tiene catalogos
                form = obtenFormularioSinCatalogos("equipoActualizar");
                break;
            case "catalogoAltaRegistro": // Tiene catalogos
                form = obtenFormularioSinCatalogos("catalogoAltaRegistro");
                break;
            case "catalogoVer": // Tiene catalogos
                form = obtenFormularioSinCatalogos("catalogoVer");
                break;
            case "movimientoEquipo": //
                form = obtenFormularioSinCatalogos("movimientoEquipo");
                break;
            case "usuarioAlta": // No tiene catalogos
                form = obtenFormularioSinCatalogos("usuarioAlta");
                break;
            case "usuarioBaja": // No tiene catalogos
                form = obtenFormularioSinCatalogos("usuarioBaja");
                break;
            case "consultaEquipo":
                form = obtenFormularioConCatalogos("consultaEquipo");
                break;
            case "consultaEquipo":
                form = obtenFormularioConCatalogos("consultaEquipo");
                break;
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
                    if (text.contains("<datalist id=\"responsable\" name=\"responsable\">")) {
                        form += text;
                        text = reader.readLine(); // <option>----</option>
                        form += llenaCatalogo(getCatalogo("catalogo_responsable"));
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

    public String obtenFormularioConCatalogos(String formulario){
        String form = "";
        ServletContext context = getServletContext();
        InputStream is = context.getResourceAsStream("/WEB-INF/formularios/"+formulario+".html");
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
                    if (text.contains("<datalist id=\"responsable\" name=\"responsable\">")) {
                        form += text;
                        text = reader.readLine(); // <option>----</option>
                        form += llenaCatalogo(getCatalogo("catalogo_responsable"));
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
     *
     * @param formulario
     * @return
     */
    public String obtenFormularioSinCatalogos(String formulario) {
        String form = "";
        ServletContext context = getServletContext();
        InputStream is = context.getResourceAsStream("/WEB-INF/formularios/" + formulario + ".html");
        if (is != null) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            String text = "";
            try {
                while ((text = reader.readLine()) != null) {

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

    /**
     *
     * @param ruta
     * @param out
     */
    protected void getFormulario(String ruta, PrintWriter out) {
        //    ConexionBD bd = new ConexionBD();

        ServletContext context = getServletContext();
        InputStream is = context.getResourceAsStream(ruta);
        if (is != null) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            String text = "";
            try {
                while ((text = reader.readLine()) != null) {
                    out.println(text);
                    System.out.println(text);
                }
            } catch (IOException ex) {
                Logger.getLogger(Formularios.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        //processRequest(request, response);
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
    }// </editor-fold
}
