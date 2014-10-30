/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function validaCampos() {

    var reg = "[0-9]+$";

    var claveAF = document.forms["alta"]["activoFijo"].value;
    var numInvUNAM = document.forms["alta"]["descripcion"].value;
    var descripcion = document.forms["alta"]["descripcionExtendida"].value;

    if (claveAF == "" || claveAF == null) {
        if (numInvUNAM == "" || numInvUNAM == null) {
            //alert("Debes meter alguno de los campos");
            document.getElementById("errorActivoFijo").innerHTML = "campo inválido";
            document.getElementById("errorDescripcion").innerHTML = "campo inválido";
            document.getElementById("errorDescripcionExtendida").innerHTML = "";
            return false;
        }
        // claveAF = null y numInvUNAM != null
        var con = numInvUNAM.match(reg);
        if (con == null) {
            document.getElementById("errorDescripcion").innerHTML = "Este campo debe ser un numero";
            return false;
        }


        if (descripcion == null || descripcion == "") {
            document.getElementById("errorDescripcionExtendida").innerHTML = "Este campo no debe ser vacío";
            return false;

        }
        return true;
    }

    //claveAF != null

    //checar si claveAF es numero

    var con = claveAF.match(reg);
    if (con == null) {
        document.getElementById("errorActivoFijo").innerHTML = "Este campo debe ser un numero";
        return false;
    }
    if (numInvUNAM != "") {
        var r = numInvUNAM.match(reg)
        if (r == null) {
            document.getElementById("errorDescripcion").innerHTML = "Este campo debe ser un numero";
            return false;
        }
    }

    if (descripcion == null || descripcion == "") {
        document.getElementById("errorDescripcionExtendida").innerHTML = "Este campo no debe ser vacío";
        return false;
    }

    return true;

}

function buscaEquipo() {


    var x = document.getElementById("campoBusqueda").value;
    if (x == "" || x == null) {
        document.getElementById("errorBusqueda").innerHMTL = "EL campo de busqueda es vacio";
        return;
    }
    var reg = "[0-9]+$";
    var con = x.match(reg);
    if(con == null){
            document.getElementById("errorBusqueda").innerHTML="Debe ser un numero";
            return;
    }
    $.post("BuscaEquipo", {
        campoBusqueda: x
    }, function (data) {
        $("#resultadoBusqueda").html(data);
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
