/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function exporta(){
     exportaConsulta();
     $("#resultadoConsulta").html("");
     $("#exportaConsulta").html("<a href='ExportaConsulta'>Descargar excel</a>");
}
function exportaConsulta() {

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

    $.post("ExportaConsulta", {
        marca: marc,
        numeroSerie: numS,
        familia: fam,
        ubicacion: ubic,
        responsable: res,
        tipoEquipo: tipoEqui,
        departamento: depa,
        fechaI: fecha1,
        fechaF: fecha2,
        estado: mov,
        genera: "yeah"
    });


}

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
        marca: marc,
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
        
    despliegaPestaña(data);
    });
}

function despliegaPestaña(data){
    
       var nuevaVentana = window.open("about","_blank");        
       nuevaVentana.document.write(data); 
}


function validaCampos(hayform, form) {

    var reg = "^[0-9]+$";
    var claveAF = "";
    var numInvUNAM = "";
    var descripcion = "";
    var fecha = "";

    escribeEn("errorAmbos", "");
    escribeEn("errorDescripcion","");
    escribeEn("errorActivoFijo","");
    escribeEn("errorFechaResguardo","");
    escribeEn("errorDescripcionExtendida","");

    if (hayform != null) {
        claveAF = document.forms[form]["activoFijo"].value;
        numInvUNAM = document.forms[form]["descripcion"].value;
        descripcion = document.forms[form]["descripcionExtendida"].value;
        fecha = document.forms[form]["fechaResguardo"].value;
    } else {
        claveAF = document.getElementById("activoFijo").value;
        numInvUNAM = document.getElementById("descripcion").value;
        descripcion = document.getElementById("descripcionExtendida").value;
        fecha = document.getElementById("fechaResguardo").value;
    }
    if (claveAF == "" || claveAF == null) {
        if (numInvUNAM == "" || numInvUNAM == null) {
            escribeEn("errorAmbos", "Al menos uno de los campos no debe quedar vacío");
            if (descripcion == "") {
                escribeEn("errorDescripcionExtendida", "Este campo no puede quedar vacío");
            } else {
                oculta("errorDescripcionExtendida");
            }
            return false;
        }
        // claveAF = null y numInvUNAM != null
        var con = numInvUNAM.match(reg);
        if (con == null) {
            escribeEn("errorDescripcion", "Este campo debe ser un número");
            return false;
        }
        if(numInvUNAM.length > 9){
            escribeEn("errorDescripcion","Este número no puede tener más de 9 digitos");
            return false;
        }
        if (descripcion == null || descripcion == "") {
            escribeEn("errorDescripcionExtendida", "Este campo no debe ser vacío");
            return false;

        }
        return true;
    }

    //claveAF != null

    //checar si claveAF es numero

    var con = claveAF.match(reg);
    if (con == null) {
        escribeEn("errorActivoFijo", "Este campo debe ser un número");
        return false;
    }
    if ( claveAF.length > 9){
        escribeEn("errorActivoFijo","Este número no puede tener más de 9 digitos");
        return false;
    }
    if (numInvUNAM != "") {
        var r = numInvUNAM.match(reg)
        if (r == null) {
            escribeEn("errorDescripcion", "Este campo debe ser un número");
            return false;
        }
        if(numInvUNAM.length > 9){
            escribeEn("errorDescripcion","Este número no puede tener más de 9 digitos");
        }
    }

    if (descripcion == null || descripcion == "") {
        escribeEn("errorDescripcionExtendida", "Este campo no debe ser vacío");
        return false;
    }

    if (fecha == null || fecha == "") {
        escribeEn("errorFechaResguardo", "Debes escribir una fecha");
        return false;
    }
    return true;

}

function buscaEquipo() {


    var x = document.getElementById("campoBusqueda").value;
    if (x === "" || x === null) {
        if (esVisible("errorBusqueda1")) {
            escribeEn("errorBusqueda1", "Este campo no debe ser vacío");
        } else {
            escribeEn("errorBusqueda1", "Este campo no debe ser vacío");
            muestra("errorBusqueda1");
        }
        return;
    }
    var reg = "^[0-9]+$";
    var con = x.match(reg);
    if (con == null) {
        if (esVisible("errorBusqueda1")) {
            escribeEn("errorBusqueda1", "Este campo debe ser un número");
        } else {
            escribeEn("errorBusqueda1", "Este campo debe ser un número");
            muestra("errorBusqueda1");
        }
        return;
    }
    oculta("errorBusqueda1");

    $.post("BuscaEquipo", {
        campoBusqueda: x
    }, function (data) {
        if (data === "<label id='errorBusqueda' class='errorFormulario'>No se encontraron equipos</label>") {
            $("#errorBusqueda").html(data);
        }
        else {
            $("#resultadoBusqueda").html(data);
        }

    });

}

function obtenCatalogo() {
    var cat = document.getElementById("verCatalogo").value;
    $.post("MuestraCatalogos", {
        catalogo: cat
    }, function (data) {
        $("#resultadoBusqueda").html(data);
    });

}

function muestraFormulario() {

    var sel = document.getElementsByName("seleccion");
    var id_equipo;
    for (i = 0; i < sel.length; i++) {
        if (sel[i].checked) {
            id_equipo = sel[i].value;
        }
    }

    if (id_equipo == null) {
        if (!esVisible("errorBusqueda1")) {
            muestra("errorBusqueda1");
        }
        escribeEn("errorBusqueda1", "No has seleccionado nada");
        return;
    }
    oculta("errorBusqueda1");

    var act = 1;
    $.post("BuscaEquipo", {
        id: id_equipo,
        actualizar: act
    }, function (data) {
        $("#resultadoBusqueda").html(data);
    });

    return;

}

function actualizaEquipo() {
    if (!validaCampos("si", "actualiza")) {
        return false;
    }
    return true;
}
function validaMovimiento() {

    var movimiento = document.getElementById("movimiento").value;
    var fecha = document.getElementById("fecha").value;
    if (movimiento === "") {
        if (!esVisible("errorMovimiento")) {
            escribeEn("errorMovimiento", "Debes introducir un valor");
            muestra("errorMovimiento");
        } else {
            escribeEn("errorMovimiento", "Debes introducir un valor");
        }
        if (fecha === "") {
            if (!esVisible("errorFecha")) {
                escribeEn("errorFecha", "Debes introducir una fecha");
                muestra("errorFecha");
            } else {
                escribeEn("errorFecha", "Debes introducir una fecha");
            }

        } else {
            if (esVisible("errorFecha")) {
                oculta("errorFecha");
            }
        }
        return false
    }
    if (esVisible("errorMovimiento")) {
        oculta("errorMovimiento");
    }
    if (fecha === "") {
        if (!esVisible("errorFecha")) {
            escribeEn("errorFecha", "Debes introducir una fecha");
            muestra("errorFecha");
        } else {
            escribeEn("errorFecha", "Debes introducir una fecha");
        }
        return false;
    }
    if (esVisible("errorFecha")) {
        oculta("errorFecha");
    }

    var sel = document.getElementsByName("seleccion");
    var id_equipo;

    for (i = 0; i < sel.length; i++) {
        if (sel[i].checked) {
            id_equipo = sel[i].value;
        }
    }
    if (id_equipo == null) {
        escribeEn("errorBusqueda", "Debes seleccionar un equipo");
        return false;
    }


    return true;
}
function validaFecha(fecha) {

    if (fecha === "") {
        return false;
    }
    return true;
}
function esVisible(id) {
    return document.getElementById(id).style.visibility === 'visible';
}
function oculta(id) {
    document.getElementById(id).style.visibility = 'hidden';
}
function muestra(id) {
    document.getElementById(id).style.visibility = 'visible';
}
function escribeEn(id, texto) {
    document.getElementById(id).innerHTML = texto;
}
function imprimeTabla() {
    var busqueda = document.getElementById("campoBusqueda").value;
    var reg = "^[0-9]+$";
    if (busqueda === "") {
        escribeEn("errorBusqueda", "Este campo no debe ser vacío");
        return;
    }
    var con = busqueda.match(reg);
    if (con === null) {
        escribeEn("errorBusqueda", "Este campo debe ser un número");
        return;
    }
    $.post("MovimientoEquipo", {
        equipo: busqueda

    }, function (data) {
        $("#resultadoBusqueda").html(data);
    });
}
