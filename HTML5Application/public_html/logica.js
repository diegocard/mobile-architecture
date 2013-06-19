/* Variables */
var URLBase = "http://tagsi.jelastic.servint.net:8080/HomeBanking-web/rest/";
var Usuario =  null;
var Password = null;
var IsAdmin = false;
var UsuarioLogueado = false; //vale false hasta que el usuario se loguea

/* ============================== Inicio de la aplicacion ============================== */

$(document).bind('pageinit', eventosInicio);

function eventosInicio(){
    //Eventos de usuario
	$('#btn-login').bind('tap', btnLogin);
    //Invocamos a la persistencia para cargar los datos iniciales de la aplicacion    
    cargarBDInicial();
    //En caso de que exista un usuario persistente, realizo login con ese usuario    
    if (!UsuarioLogueado){
        loginPersistente();
    }
    
}

/* ============================== Logica de la aplicacion ============================== */

function redirectToPage(page){
    location.href=page;
}

function loginPersistente(){
    cargarUsuarioPersistente();
}

function loginSuccess(data){
    if (data == true){
        UsuarioLogueado = true;
        guardarUsuarioPersistente(Usuario, Password, IsAdmin);
        redirectToPage('#page-inicio');
    }else{
        alert('Username or password are incorrect');
    }
}

function btnLogin(){
    Usuario = $("#input-user").val();
    Password = $("#input-password").val();
    IsAdmin = $("#lbl-is-admin").hasClass('ui-checkbox-on');
    login();    
}

function login(){
    //Reliza login con los datos ya cargados en las variables Usuario, Password e IsAdmin
    var url=URLBase + 'usuarios/validar/jsonp/' + Usuario + '/' + Password + '/' + IsAdmin;
    $.ajax({
        url: url,
        dataType: 'jsonp',
        jsonpCallback: 'loginSuccess',
        jsonp: 'callback',
    });
}
