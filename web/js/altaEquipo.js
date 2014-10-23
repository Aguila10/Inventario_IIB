/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function validaCampos(){
    
    var reg = new RegExp("[0-9]+");
    var claveAF = document.forms["alta"]["activoFijo"].value;
    var numInvUNAM = document.forms["alta"]["descripcion"].value;
    var descripcion = document.forms["alta"]["descripcionExtendida"].value;
    
    if(claveAF == "" || claveAF == null){
        if(numInvUNAM == "" || numInvUNAM == null){
            //alert("Debes meter alguno de los campos");
            document.getElementById("errorActivoFijo").innerHTML="campo inválido";
            document.getElementById("errorDescripcion").innerHTML="campo inválido";
            document.getElementById("errorDescripcionExtendida").innerHTML="";
            return false;
        }
        // claveAF = null y numInvUNAM != null
        
        
        if (descripcion == null || descripcion == ""){
            document.getElementById("errorDescripcionExtendida").innerHTML="Este campo no debe ser vacío";
            return false;
        
        }
    return true;
    }
    //claveAF != null
    
    //checar si claveAF es numero
    
        if (descripcion == null || descripcion == ""){
            document.getElementById("errorDescripcionExtendida").innerHTML="Este campo no debe ser vacío";
            return false;
        }
    
    return true;
    
}