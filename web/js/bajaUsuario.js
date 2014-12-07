function revisa_usuarios() {

var usuarios = $("input:checkbox[name=usuarios]:checked").toArray(); 

if(usuarios.length === 0 ){  
    document.getElementById("errorUsuarioBaja").innerHTML = "Debes seleccionar al menos un usuario";
    return false;
}
    
return true;      
}
