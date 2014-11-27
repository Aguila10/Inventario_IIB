
function valida_usuario_alta() {

    document.getElementById("errorNombre").innerHTML = "";
    document.getElementById("errorLogin").innerHTML = "";
    document.getElementById("errorCorreo").innerHTML = "";
    document.getElementById("errorPassword").innerHTML = "";

    var validacion_nombre = revisaNombre();
    var validacion_login = revisaLogin();
    var validacion_mail = revisaMail();
    var validacion_contrasenia = revisaContrasenia();

    return validacion_nombre && validacion_login && validacion_mail && validacion_contrasenia;

}

function revisaNombre() {

    var nombre = document.getElementsByName("nombre")[0].value;
    var nombre_pat = /^([A-Za-zñáéíóú])+([\s]{1}[A-Za-zñáéíóú]+)?([\s]{1}[A-Za-zñáéíóú]+)?$/;

    if (nombre.match(nombre_pat)) {
        if (nombre.length >= 2 && nombre.length <= 70) { //Tamaño [2,70]
            return true;
        } else {
            document.getElementById("errorNombre").innerHTML = "El nombre debe tener un mínimo de 2 caracteres y un máximo de 70";
            return false;
        }
    } else {
        if (nombre === "") {
            document.getElementById("errorNombre").innerHTML = "Este campo no pude quedar vacio";
        } else {
            document.getElementById("errorNombre").innerHTML = "El nombre solo puede contener letras y debe tener la estructura: [Nombre] [ApellidoP (opcional)] [ApellidoM (opcional)].";
        }
        return false;
    }

}

function revisaLogin() {

    var login = document.getElementsByName("login")[0].value;
    var login_pat = /^[A-Za-z0-9_ñ]+$/;

    if (login.match(login_pat)) {
        if (login.length >= 4 && login.length <= 15) { //  tamaño [4,15]
            return true;
        } else {
            document.getElementById("errorLogin").innerHTML = "El login debe tener un mínimo de 4 caracteres y un máximo de 15";
            return false;
        }
    } else {
        if (login === "") {
            document.getElementById("errorLogin").innerHTML = "El campo no puede quedar vacio";
        } else {
            document.getElementById("errorLogin").innerHTML = "El login solo puede contener numeros,letras y guiones bajos";
        }
        return false;
    }
}

function revisaMail() {

    var mail = document.getElementsByName("correo")[0].value;
    var mail_pat = /^[A-Za-z0-9_](\.?[\w-]+)*@[a-zA-Z]+(\.[a-zA-z]+){1,2}$/;

    if (mail.match(mail_pat)) {
        if (mail.length <= 70) {  // Si cumple la estructura y su tamaño es menor a 70 
            return true;
        } else {
            document.getElementById("errorCorreo").innerHTML = "El mail debe tener un tamaño máximo de 70 caracteres";
            return false;
        }
    } else {
        if (mail === "") {
            document.getElementById("errorCorreo").innerHTML = "Este campo no puede quedar vacio";
        } else {
            document.getElementById("errorCorreo").innerHTML = "El mail debe tener la siguiente estructura:  \n\
                                                                  [nombre]@[dominio].[subdominio (opcional)].com";
        }
        return false;
    }

}

function revisaContrasenia() {
    var contraseniaUno = document.getElementsByName("contraseniaUno")[0].value;
    var contraseniaDos = document.getElementsByName("contraseniaDos")[0].value;
    var contrasenia_pat = /^[^';]+$/;

    if (contraseniaUno.match(contrasenia_pat)) {
        if (contraseniaUno.length < 5 || contraseniaUno.length > 15) {
            document.getElementById("errorPassword").innerHTML = "La contraseña debe tener un mínimo de 5 caracteres y un máximo de 15";
            return false;
        }
    } else {
        if (contraseniaUno === "") {
            document.getElementById("errorPassword").innerHTML = "El campo no puede quedar vacio";
        } else {
            document.getElementById("errorPassword").innerHTML = "La contraseña no puede contener comillas simples (') ni comas (;)";
        }
        return false;
    }

    if (contraseniaUno !== contraseniaDos) {
        document.getElementById("errorPassword").innerHTML = "Las contraseñas no coinciden";
        return false;
    }
    return true;
}



