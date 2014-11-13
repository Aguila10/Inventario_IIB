/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function validaCampos(hayform, form) {

    var reg = "^[0-9]+$";
    var claveAF = "";
    var numInvUNAM = "";
    var descripcion = "";
    var fecha = "";
    
    if(hayform != null){
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
            //alert("Debes meter alguno de los campos");
            escribeEn("errorActivoFijo","campo inválido");
            escribeEn("errorDescripcion","campo inválido");
            if(descripcion == ""){
                escribeEn("errorDescripcionExtendida","Debes llenar este campoo");
            } else {
                oculta("errorDescripcionExtendida");                
            }
            return false;
        }
        // claveAF = null y numInvUNAM != null
        var con = numInvUNAM.match(reg);
        if (con == null) {
            escribeEn("errorDescripcion","Este campo debe ser un numero");
            return false;
        }

        if (descripcion == null || descripcion == "") {
            escribeEn("errorDescripcionExtendida","Este campo no debe ser vacío");
            return false;

        }
        return true;
    }

    //claveAF != null

    //checar si claveAF es numero

    var con = claveAF.match(reg);
    if (con == null) {
        escribeEn("errorActivoFijo","Este campo debe ser un numero");
        return false;
    }
    if (numInvUNAM != "") {
        var r = numInvUNAM.match(reg)
        if (r == null) {
            escribeEn("errorDescripcion","Este campo debe ser un numero");
            return false;
        }
    }

    if (descripcion == null || descripcion == "") {
        escribeEn("errorDescripcionExtendida","Este campo no debe ser vacío");
        return false;
    }

    if(fecha == null || fecha == ""){
        escribeEn("errorFechaResguardo","Debes escribir una fecha");
        return false;
    }
    return true;

}

function buscaEquipo() {


    var x = document.getElementById("campoBusqueda").value;
    if (x === "" || x === null) {
           if(esVisible("errorBusqueda1")){
            escribeEn("errorBusqueda1","EL campo de búsqueda es vacío");
            } else {
                escribeEn("errorBusqueda1","EL campo de búsqueda es vacío");
                muestra("errorBusqueda1");
            }
            return;
    }
    var reg = "^[0-9]+$";
    var con = x.match(reg);
    if(con == null){
            if(esVisible("errorBusqueda1")){
            escribeEn("errorBusqueda1","La búsqueda no es un número");
        } else {
            escribeEn("errorBusqueda1","La búsqueda no es un número");
            muestra("errorBusqueda1");
        }
            return;
    }
    oculta("errorBusqueda1");
    
    $.post("BuscaEquipo", {
        campoBusqueda: x
    }, function (data) {
        if(data==="<label id='errorBusqueda' class='errorFormulario'>No se encontraron equipos</label>"){$("#errorBusqueda").html(data);}
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

function muestraFormulario(){
    
    var sel = document.getElementsByName("seleccion");
    var id_equipo;
    for(i = 0; i < sel.length; i++){
        if(sel[i].checked){
           id_equipo = sel[i].value;
        }
    }
    
    if(id_equipo == null){
        if(!esVisible("errorBusqueda1")){
            muestra("errorBusqueda1");       
        }
        escribeEn("errorBusqueda1","No has seleccionado nada");
        return;
    }
    oculta("errorBusqueda1");

    var act = 1;
    $.post("BuscaEquipo", {
        id: id_equipo,
        actualizar:act
    }, function (data) {
        $("#resultadoBusqueda").html(data);
    });
    
    return;
    
}

function actualizaEquipo() {
    if(!validaCampos("si","actualiza")){
        return false;
    }
    return true;
}
function validaMovimiento(){
    
   var movimiento = document.getElementById("movimiento").value;
   var fecha = document.getElementById("fecha").value;
   if(movimiento === ""){
       if(!esVisible("errorMovimiento")){
           escribeEn("errorMovimiento","Debes introducir un valor1");
           muestra("errorMovimiento");
       } else {
        escribeEn("errorMovimiento","Debes introducir un valor2");
        }   
    if(fecha === ""){
        if(!esVisible("errorFecha")){
           escribeEn("errorFecha","Debes introducir una fecha1");
           muestra("errorFecha");
       } else {
        escribeEn("errorFecha","debes introducir una fecha2");
        }

    } else {
    if(esVisible("errorFecha")){
        oculta("errorFecha");
    }    
    }
    return false
   }
   if(esVisible("errorMovimiento")){
       oculta("errorMovimiento");
   }
    if(fecha === ""){
        if(!esVisible("errorFecha")){
           escribeEn("errorFecha","Debes introducir una fecha3");
           muestra("errorFecha");
       } else {
        escribeEn("errorFecha","Debes introducir una fecha4");
        }
        return false;
    }
    if(esVisible("errorFecha")){
        oculta("errorFecha");
    }

    var sel = document.getElementsByName("seleccion");
    var id_equipo;

    for(i = 0; i < sel.length; i++){
        if(sel[i].checked){
           id_equipo = sel[i].value;
        }
    }
    if(id_equipo == null){
        escribeEn("errorBusqueda","No has seleccionado un equipo");
        return false;
    }
    
   
   return true;
}
function validaFecha(fecha){
    
   if(fecha === ""){
       return false;
   }
   return true;
}
function esVisible(id){
    return document.getElementById(id).style.visibility === 'visible';
}
function oculta(id){
    document.getElementById(id).style.visibility='hidden';
}
function muestra(id) {
    document.getElementById(id).style.visibility='visible';
}
function escribeEn(id,texto){
    document.getElementById(id).innerHTML=texto;
}
function imprimeTabla(){
    var busqueda = document.getElementById("campoBusqueda").value;
    var reg = "^[0-9]+$";
    if(busqueda === ""){
    escribeEn("errorBusqueda","El campo no puede ser nulo");
    return;
    }
    var con = busqueda.match(reg);
    if(con === null){
        escribeEn("errorBusqueda","Debe ser un número");
        return;
    }
    $.post("MovimientoEquipo", {
        equipo: busqueda
        
    }, function (data) {
        $("#resultadoBusqueda").html(data);
    });
}