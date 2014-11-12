function muestra_usuarios(){
    
        $.post("BuscaEquipo", {
     }, function (data) {
        $("#resultadoBusqueda").html(data);    });
    
    return;
    
    
    
}