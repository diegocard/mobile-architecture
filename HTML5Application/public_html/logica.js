/* Variables */
var URLBase = "http://tagsi2013.jelastic.servint.net/HomeBanking-web/rest/";
var IdUsuario =  null;
var NomUsuario =  null;
var Password = null;
var IsAdmin = false;
var UsuarioLogueado = false; //vale false hasta que el usuario se loguea
var Cuentas = null; //Cuentas del usuario

/* ============================== Inicio de la aplicacion ============================== */

$(document).bind('pageinit', eventosInicio);

function eventosInicio(){
    //Eventos de usuario
	$('#btn-login').bind('tap', btnLogin);
    $('#btn-transferencia').bind('tap', btnTransferencia);
    //Eventos al cargar la pagina de iniciobtnTransferencia
    $(document).on('pagebeforecreate', '#page-inicio', eventosPageInicio);
    //Eventos al cargar la pagina de inicio
    $(document).on('pagebeforecreate', '#page-sucursales', eventosPageSucursales);
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

function btnLogin(){
    NomUsuario = $("#input-user").val();
    Password = $("#input-password").val();
    IsAdmin = $("#lbl-is-admin").hasClass('ui-checkbox-on');
    login();    
}

function login(){
    //Realiza login con los datos ya cargados en las variables Usuario, Password e IsAdmin
    var url=URLBase + 'usuarios/validar/jsonp/' + NomUsuario + '/' + Password + '/' + IsAdmin;
    $.ajax({
        url: url,
        dataType: 'jsonp',
        jsonpCallback: 'loginSuccess',
        jsonp: 'callback',
    });
}

function loginSuccess(ID){
    if (ID != -1){
        UsuarioLogueado = true;
        IdUsuario = ID;
        guardarUsuarioPersistente(IdUsuario, NomUsuario, Password, IsAdmin);
        redirectToPage('#page-inicio');
    }else{
        alert('Username or password are incorrect');
    }
}

function eventosPageInicio(){
    //Carga las cuentas del usuario
    if (IdUsuario != null){
        var url=URLBase + 'cuentas/jsonp/usuario/' + IdUsuario;
        $.ajax({
            url: url,
            dataType: 'jsonp',
            jsonpCallback: 'cargarCuentasSuccess',
            jsonp: 'callback',
        });
    }    
}

function cargarCuentasSuccess(data){
    //Si tengo nuevos datos de cuenta, los guardo
    if (data != null){
        Cuentas = data;       
    }
    //Cargo la cuenta en el listado de inicio y de transferencias
    $.each(Cuentas, function(index, cuenta){
        cargarCuentaListadoInicio(cuenta);
        cargarCuentaListadosTransferencia(cuenta);
    });
    $('#dividerCuentas').listview();
    $('#dividerCuentas .btnDetaleCuenta').bind('tap', cargarDetalleCuenta);

}

function cargarCuentaListadoInicio(cuenta){
    $('#dividerCuentas').append(
        '<li data-theme="c">' +
            '<a id="' + cuenta.id + '" href="#page-detalle-cuenta" data-transition="slide" class="btnDetaleCuenta">' + 
                cuenta.tipo + ' ' + cuenta.numero +
            '</a>' +
        '</li>'
    );
}

function cargarCuentaListadosTransferencia(cuenta){
    $('#selectTransferenciaDesde').append(
        '<option value="' + cuenta.id + '">' +
            cuenta.tipo + ' ' + cuenta.numero +
        '</option>'
    );

    $('#selectTransferenciaHasta').append(
        '<option value="' + cuenta.id + '">' +
            cuenta.tipo + ' ' + cuenta.numero +
        '</option>'
    );
}


function eventosPageSucursales(){
    //Carga las cuentas del usuario
    if (IdUsuario != null){
        var url=URLBase + 'sucursales';
        $.ajax({
            url: url,
            dataType: 'jsonp',
            jsonpCallback: 'cargarSucursalesSuccess',
            jsonp: 'callback',
        });
    }    
}

function cargarDetalleCuenta(){
    var IDCuentaSeleccionada = $(this).attr('id');
    if (Cuentas != null){
        $.each(Cuentas, function(index, cuenta){
            if (cuenta.id == IDCuentaSeleccionada){
                $('#txtCuentaNumero').html(cuenta.numero);
                $('#txtCuentaTipo').html(cuenta.tipo);
                $('#txtCuentaMonto').html(cuenta.saldo);
            }            
        });
    }
}

function cargarSucursalesSuccess(data){
    if (data != null){
        $.each(data, function(index, entrada){
            alert(index + ' ' + data);
        });
    }
}

function btnTransferencia(){
    var CuentaDesde = $('#selectTransferenciaDesde').find(":selected").text();
    alert(CuentaDesde);
    redirectToPage('#page-inicio');
}

