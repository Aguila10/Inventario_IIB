
function valida_inicio_sesion(){
    
    var usuario = document.getElementById("usuario").value;
    var contrasenia = document.getElementById("contrasenia").value;
    
    var usuario_pat = /^[A-Za-z0-9_ñ]+$/;
    var contrasenia_pat = /^[^';]+$/;
    
    if(usuario.match(usuario_pat) && contrasenia.match(contrasenia_pat)){    
        return true;
    }else{
        document.getElementById("error").style.visibility = 'visible';  
        return false;
    }
        
}

function limpia(){
    document.getElementById("error").style.visibility = 'hidden';
}