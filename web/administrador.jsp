<%-- 
    Document   : administrador
    Created on : Oct 15, 2014, 8:44:07 PM
    Author     : rae
--%>
<%
    /*Obtener la sesion ya iniciada*/
    HttpSession sesion = request.getSession(true);
    
    if(sesion.getAttribute("identidad") == null){
       response.sendRedirect("index.jsp");
    }
   
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Administrador</title>

        <!--Estilos plantilla-->
        <link rel="stylesheet" href="css/6cols.css">
        <link rel="stylesheet" href="css/4cols.css">
        <link rel="stylesheet" href="css/col.css">
        <link rel="stylesheet" href="css/estilosPlantilla.css">
        <link rel="stylesheet" href="css/formulario.css">

        <!--Scripts página-->
        <script src="js/jquery-1.10.1.min.js"></script>
        <script src="js/ajaxFormularios.js"></script>
        <script src="js/altaEquipo.js"></script>
        <script src="js/iniciarSesion.js"></script> 
        <script src="js/altaUsuario.js"></script>
        <script src="js/bajaUsuario.js"></script>

    </head>
    <body>
        <!--Inicio encabezado-->
        <header>
            <div class="section group" id="encabezado">
                <div class="col span_1_of_6">
                    <img src="img/escudoUnam.png" height="40%" width="40%" alt="escudo unam" id="imagenUnam">
                </div>
                <div class="col span_4_of_6"></div>
                <div class="col span_1_of_6">
                    <!--logo biblioteca-->
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
                    <li>
                        <a id="usuario">Usuario</a>
                        <ul>
                            <li onclick="obtenerFormulario('usuarioAlta', 'usuario')"><a>Dar alta</a></li>
                            <li onclick="obtenerFormulario('usuarioBaja', 'usuario')"><a>Dar baja</a></li>
                        </ul>
                    </li>
                    <li>
                        <a id="equipo">Equipo</a>
                        <ul>
                            <li onclick="obtenerFormulario('equipoAlta', 'equipo')"><a>Dar alta</a></li>
                            <li onclick="obtenerFormulario('equipoActualizar', 'equipo')"><a>Actualizar</a></li>
                        </ul>
                    </li>
                    <li>
                        <a id="catalogo">Catálogo</a>
                        <ul>
                            <li onclick="obtenerFormulario('catalogoAltaRegistro', 'catalogo')"><a>Dar alta registro</a></li>
                            <li onclick="obtenerFormulario('catalogoVer', 'catalogo')"><a>Ver</a></li>
                        </ul>
                    </li>
                    <li onclick="obtenerFormulario('movimientoEquipo', 'movimientoEquipo')">
                        <a id="movimientoEquipo">Movimiento de Equipo</a>
                    </li>
                    <li>
                        <a id="consulta">Consulta</a>
                        <ul>
                            <li onclick="obtenerFormulario('consultaUsuario', 'consulta')"><a>Usuario</a></li>
                            <li onclick="obtenerFormulario('consultaEquipo', 'consulta')"><a>Equipo</a></li>
                        </ul>
                    </li>
                </ul>
                <a href="http://www.iib.unam.mx/" target="_blank">
                    <img src="img/biblioteca.gif" alt=" biblioteca hemeroteca unam" id="enlaceBiblioteca">
                </a>
            </div>
            <!--Fin menú-->

            <!--Inicio formulario-->
            <div class="col span_3_of_4" id="formulario" >
            <!--Caratula con marca de agua-->

                <% 
                if(request.getParameter("mensaje") != null){
                   
               out.print(request.getParameter("mensaje"));
                } else{
                    out.print("<img src='img/caratula.jpg' alt='caratula darktech' id='caratula'>");
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
