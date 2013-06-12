/* Variables */
var URLBase = "http://tagsi.jelastic.servint.net:8080/HomeBanking-web/rest/";

/* ============================== Inicio de la aplicacion ============================== */

$(document).bind('pageinit', eventosInicio);

function eventosInicio(){
    //Eventos de usuario
	$('#btn-login').bind('tap', login);
    //Invocamos a la persistencia para cargar los datos iniciales de la aplicacion
    cargarBDInicial();
}

/* ============================== Logica de la aplicacion ============================== */

function loginSuccess(data){
    alert(data);
}

function login(){
    
    var Usuario = $("#input-user").val();
    var Pass = $("#input-password").val();
    var url=URLBase + 'usuarios/validar/jsonp/' + Usuario + '/' + Pass + '/false';

    $.ajax({
        url: url,
        dataType: 'jsonp',
        jsonpCallback: 'loginSuccess',
        jsonp: 'callback',
    });
}
