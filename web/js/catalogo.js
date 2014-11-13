function voyAeditar(edita){
	//Cambiamos el hijo por un input text
	// primero borramos el hijo
	var hijo = document.getElementById("hijo"+edita);
	var contenidoHijo = hijo.innerHTML;
	var padre = document.getElementById("padre"+edita);
	padre.removeChild(hijo);

	var td = document.createElement("td");
	var input = document.createElement("input");
	input.type = "text";
	input.value = contenidoHijo;
	input.id = "contenido"+edita;
	td.appendChild(input);

	padre.appendChild(td);

	var td = document.createElement("td");
	var button = document.createElement("button");
	button.classList.add('botonGuardaCambios');
	button.appendChild(document.createTextNode("Guardar cambios"));

	// agregar función boton
	var id = edita; 
	var catalogo = document.getElementById("verCatalogo").value;
	button.setAttribute("onclick", "actualizaCatalogo('"+id+"','"+catalogo+"')");
		
	td.appendChild(button);

	padre.appendChild(td);
	
}

function actualizaCatalogo(id, catalogo){
	var cambio = document.getElementById("contenido"+id).value;
	
}