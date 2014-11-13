function muestra_usuarios() {

    window.alert("HOLA BANDA");

    $.post("BajaUsuario", {
    }, function (data) {
        $("#resultadoBusqueda").html(data);
    });

    return;



}