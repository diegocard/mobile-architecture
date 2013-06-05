/* Variables */
var URLBase = "localhost:8080/HomeBanking-web/rest/";
$(document).bind('pageinit', iniEventos);

function iniEventos(){
	$('#btn-login').bind('tap', login);
}

/* ============================== Funciones de consulta ============================== */
function login(){
	alert('entra');
	var Usuario = $("#input-user").val();
	var Pass = $("#input-password").val();
    var url=URLBase + 'usuarios/validar/' + Usuario + '/' + Pass + '/false';
    prompt(url);
	$.getJSON(url, function(data){ 
		//Invoco al servicio de login
		//dataUltimaBusqueda = data;
		$.each(data,function(algo){	
			alert(algo);
		});
    });
    alert('sale');
}
