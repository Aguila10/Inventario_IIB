function agregaCatalogos(){ 
    
    var catalogo = document.getElementById("elegirCatalogo").value;
    var descripcion = document.getElementById("descripcion").value;
    
    var b_catalogo = true;
    var b_descripcion = true;
    
     if(catalogo === ""){
        document.getElementById("errorElegirCatalogo").innerHTML = "Debes seleccionar un catalogo";
        b_catalogo = false;
    }  
    
      if(descripcion === ""){
        document.getElementById("errorDescripcion").innerHTML = "No pudes dejar la descripcion vacia";
        b_descripcion = false;
    }
    
    return b_catalogo && b_descripcion;
     
}


function agregaFamiliaCatalogo(){
    
    var catalogo = document.getElementById("elegirCatalogo").value;
    
    if(catalogo === "catalogo_familia"){
        document.getElementById("msj_catalogo_tipo_equipo").style.display = "block";
        document.getElementById("select_catalogo_tipo_equipo").style.display = "block";
    }else{
        document.getElementById("msj_catalogo_tipo_equipo").style.display = "none";
        document.getElementById("select_catalogo_tipo_equipo").style.display = "none";
    }
   
}