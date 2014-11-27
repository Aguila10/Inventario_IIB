function revisa_usuarios() {

if(usuarios.length === 0 ){  
    document.getElementById("errorUsuarioBaja").innerHTML = "Debes seleccionar al menos un usuario";
    return false;
}
    
return true;      
}
