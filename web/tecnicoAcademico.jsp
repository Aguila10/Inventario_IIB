<%-- 
    Document   : administrador
    Created on : Oct 15, 2014, 8:44:07 PM
    Author     : rae
--%>

<%
    /*Obtener la sesion ya iniciada*/
    HttpSession sesion = request.getSession(true);

    if (sesion.getAttribute("identidad") == null){
        response.sendRedirect("index.jsp");
    }else{
        if(!(sesion.getAttribute("identidad").equals("tecnicoAcademico"))){
         response.sendRedirect(sesion.getAttribute("identidad")+".jsp");   
        }
    }

%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Tecnico Académico - Inventario IIB</title>
        
        <!--Imagen pestaña-->
        <link rel="shortcut icon" type="image/x-icon" href="img/escudoUnamNegro.png">

        <!--Estilos plantilla-->
        <link rel="stylesheet" href="css/6cols.css">
        <link rel="stylesheet" href="css/5cols.css">
        <link rel="stylesheet" href="css/4cols.css">
        <link rel="stylesheet" href="css/col.css">
        <link rel="stylesheet" href="css/estilosPlantilla.css">
        <link rel="stylesheet" href="css/formulario.css">

        <!--Scripts página-->
        <script src="js/jquery-1.10.1.min.js"></script>
        <script src="js/ajaxFormularios.js"></script>
        <script src="js/altaEquipo.js"></script>
        <script src="js/altaUsuario.js"></script>
        <script src="js/bajaUsuario.js"></script>
        <script src="js/catalogo.js"></script>
        <script src="js/agregaCatalogo.js"></script>
        
    </head>
    <body>
        <!--Inicio encabezado-->
        <header>
            <div class="section group" id="encabezado">
                <div class="col span_1_of_6">
                    <img src="img/escudoUnam.png" height="40%" width="40%" alt="escudo unam" id="imagenUnam">
                </div>
                <div class="col span_4_of_6" id="nombreInstituto">
                    <span>Instituto de<br>Investigaciones<br>Bibliográficas</span>
                </div>
                <div class="col span_1_of_6">
                    <img src="img/logoBiblioteca.png" height="80%" width="80%" alt="escudo biblioteca" id="imagenBiblioteca">
                </div>
            </div>
            <div class="section group" id="barra">
                <div id="login">
                    <p>
                        Conectado como: 
                        <span id="loginUsuario"><%=sesion.getAttribute("login")%></span>
                        <img src="img/door_out.png" alt="salir">
                        <a href="CerrarSesion">Salir</a>
                    </p>
                </div>
            </div>
        </header>
        <!--Fin encabezado-->

        <div class="section group" id="main">
            <!--Inicio menú-->
            <div class="col span_1_of_4" id="menu">
                <ul>
                    <br><br>
                    <li>
                        <a id="equipo">Equipo</a>
                        <ul>
                            <li onclick="obtenerFormulario('equipoAlta', 'equipo')"><a>Dar alta</a></li>
                            <li onclick="obtenerFormulario('equipoActualizar', 'equipo')"><a>Actualizar</a></li>
                        </ul>
                    </li>
                    <li onclick="obtenerFormulario('movimientoEquipo', 'movimientoEquipo')">
                        <a id="movimientoEquipo">Movimiento de Equipo</a>
                    </li>
                    <li onclick="obtenerFormulario('consultaEquipo', 'consulta')">
                        <a id="consulta">Consultar Equipo</a>
                    </li>
                </ul>
                <br><br>
                <a href="http://www.iib.unam.mx/" target="_blank">
                    <img src="img/biblioteca.gif" alt=" biblioteca hemeroteca unam" id="enlaceBiblioteca">
                </a>
            </div>
            <!--Fin menú-->

            <!--Inicio formulario-->
            <div class="col span_3_of_4" id="formulario" >
                <!--Caratula con marca de agua-->

                <% 
            request.setCharacterEncoding("UTF-8");
                if(request.getParameter("mensaje") != null){
                    out.print("<div id='respuestaServidor'>");
                        if(request.getParameter("exito").equals("true")){
                            out.print("<img src=img/ok.jpg>");
                        } else {
                            out.print("<img src=img/error.png>");
                        }
                    out.print("<h3>"+request.getParameter("mensaje")+"</h3>");
                    out.print("</div>");
                } else{
                    out.print("<a href='https://www.facebook.com/darktech.enterprise' target='_blank'><img src='img/caratula.jpg' alt='caratula darktech' id='caratula'></a>");
                }
               
                %>     

            </div>
            <!--Fin formulario-->
        </div>

        <!--Inicio pie-->
        <footer>
            <div class="section group" id="pie">
                <p>Hecho en México, todos los derechos reservados 2014. Esta página puede ser reproducida con fines no lucrativos, siempre y cuando no se mutile, se cite la fuente completa y su dirección electrónica. De otra forma requiere permiso previo por escrito de la Institución.</p>
            </div>
        </footer>
        <!--Fin pie-->
    </body>
</html>
