/*Variable para guardar el menu que se encuentra seleccionado*/
var seleccion;

/*
	Función mediante la cual es solicitado el 
	formulario al servidor (AJAX)
*/
function obtenerFormulario(f, m){
    mantenSeleccionado(m);
    $("#formulario").html("");
    //$('#formulario').hide(1000);
	$.post("Formularios", {
		formulario : f
	},function(data){
		$("#formulario").html(data);
        //$('#formulario').show(500);
	});
}

/*
	Función para mantener seleccionado el 
	menú que solicito el usuario
*/
function mantenSeleccionado(formulario){
	if(document.getElementById(seleccion)!= null){
	/*Se quita el que estaba seleccionado ateriormente*/
	seleccionado = document.getElementById(seleccion);
	seleccionado.classList.remove('seleccionado');
}
	/*Se actualiza al nuevo seleccionado*/
	seleccionado = document.getElementById(formulario);
	seleccionado.classList.add('seleccionado');
	seleccion = formulario;
}

function registraEquipo(){

   $('#formulario').hide(1000);
   var activoFijo = document.getElementById(activoFijo).value;
   var descripcion = document.getElementById().value;
   
   
   
}