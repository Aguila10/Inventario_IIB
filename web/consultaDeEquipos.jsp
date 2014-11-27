<%-- 
    Document   : consultaDeEquipos
    Created on : 24/11/2014, 07:36:16 PM
    Author     : mphb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta de equipos</title>

        <!--Estilos plantilla-->
        <link rel="stylesheet" href="css/6cols.css">
        <link rel="stylesheet" href="css/5cols.css">
        <link rel="stylesheet" href="css/4cols.css">
        <link rel="stylesheet" href="css/3cols.css">
        <link rel="stylesheet" href="css/col.css">
        <link rel="stylesheet" href="css/estilosPlantilla.css">

        <script>
            function consultaEquipos() {

                var marc = document.getElementById("marcaid").value;
                var numS = document.getElementById("numeroSerie").value;
                var fam = document.getElementById("familiaid").value;
                var ubic = document.getElementById("ubicacionid").value;
                var res = document.getElementById("responsableid").value;
                var tipoEqui = document.getElementById("tipoequipoid").value;
                var depa = document.getElementById("departam").value;
                var fecha1 = document.getElementById("fecha1").value;
                var fecha2 = document.getElementById("fecha2").value;
                var mov = document.getElementById("movimiento").value;

                $.post("ConsultaEquipo", {
                    marca:<%out.print(request.getParameter("marca"));%>,
                    numeroSerie: numS,
                    familia: fam,
                    ubicacion: ubic,
                    responsable: res,
                    tipoEquipo: tipoEqui,
                    departamento: depa,
                    fechaI: fecha1,
                    fechaF: fecha2,
                    estado: mov
                }, function (data) {
                    $("#resultadoConsulta").html(data);
                });
            }
        </script>  
    </head>
    <body>
        <!--Inicio encabezado-->
        <header>
            <div class="section group" id="encabezado">
                <div class="col span_1_of_6">
                    <img src="img/escudoUnam.png" height="40%" width="40%" alt="escudo unam" id="imagenUnam">
                </div>
                <div class="col span_4_of_6" id="nombreInstituto">
                    <span>Intituto de<br>Investigaciones<br>Bibliográficas</span>
                </div>
                <div class="col span_1_of_6">
                    <img src="img/logoBiblioteca.png" height="80%" width="80%" alt="escudo biblioteca" id="imagenBiblioteca">
                </div>
            </div>
            <div class="section group" id="barra"><br></div>
        </header>
        <!--Fin encabezado-->

        <!--Inicio contenido-->
        <!--Fin contenido-->

        <div id="resultadoConsulta">
            <div class="section group" id="main">
                <div id="resultadoConsulta"></div>
            </div>
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

