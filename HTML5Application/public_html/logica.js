/* Variables */
var URLBase = "http://tagsi2013.jelastic.servint.net/HomeBanking-web/rest/";
var IdUsuario =  null;
var NomUsuario =  null;
var Password = null;
var IsAdmin = false;
var UsuarioLogueado = false; //vale false hasta que el usuario se loguea
var Cuentas = null; //Cuentas del usuario
var EventosIniciados = false;

/* ============================== Inicio de la aplicacion ============================== */
$(document).bind('pageinit', eventosInicio);

function eventosInicio(){
    if (!EventosIniciados){        
        //Eventos de usuario
    	$('#btn-login').bind('tap', btnLogin);
        $('#btn-transferencia').bind('tap', btnTransferencia);
        //Eventos al cargar la pagina de iniciobtnTransferencia
        $(document).on('pagebeforecreate', '#page-inicio', eventosPageInicio);
        //Eventos al cargar la pagina de inicio
        $(document).delegate('#page-sucursales', 'pageshow', eventosPageSucursales);
        //Eventos al cargar la pagina de historial
        $(document).delegate('#page-historial', 'pageshow', eventosPageHistorial);
        //Boton de logout
        $('.btnLogout').bind('tap', btnLogout);
        //Invocamos a la persistencia para cargar los datos iniciales de la aplicacion    
        cargarBDInicial();
        //En caso de que exista un usuario persistente, realizo login con ese usuario    
        if (!UsuarioLogueado){
            loginPersistente();
        }
        EventosIniciados = true;
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
    var CuentaDesdeId = $('#selectTransferenciaDesde').find(":selected").val();
    var CuentaHastaId = $('#selectTransferenciaHasta').find(":selected").val();
    var CuentaDesde = $('#selectTransferenciaDesde').find(":selected").text();
    var CuentaHasta = $('#selectTransferenciaHasta').find(":selected").text();
    var Monto = $('#txtTransferenciaMonto').val();
    if (Monto == ''){
        alert('Ingrese un monto válido para la transferencia');
    }else if (CuentaDesde == CuentaHasta){
        alert('Seleccione una cuenta destino distinta de la de origen');
    }else{
        //Realizar transferencia
        realizarTransferencia(CuentaDesdeId, CuentaDesde, CuentaHastaId, CuentaHasta, Monto);
    }
}

function realizarTransferencia(CuentaDesdeId, CuentaDesde, CuentaHastaId, CuentaHasta, Monto){
    if (IdUsuario != null){
        var url=URLBase + 'transferencias/jsonp/realizarTransferencia'+
            '/' + IdUsuario +
            '/' + CuentaDesdeId +
            '/' + IdUsuario +
            '/' + CuentaHastaId +
            '/' + Monto +
            '/' + 'Transferencia de ' + CuentaDesde + ' a ' + CuentaHasta;
 
        //Realizo el envio
        $.ajax({
            url: url,            
            dataType: 'jsonp',
            jsonpCallback: 'transferenciaSuccess',
            jsonp: 'callback',
        });
    }
}

function transferenciaSuccess(data){
    //Guardo la transferencia en el historial
    guardarTransferenciaHistorial(IdUsuario, data.cuentaOrigen.tipo, data.cuentaDestino.tipo, data.monto, new Date().toString("dd/MM/yyyy HH:mm:ss"));
    //Mostrar dialogo de confirmacion
    $("#lnkDialog").click();
}

function eventosPageHistorial(){
    //Invoca a la persistencia
    cargarHistorial();
}

function cargarEntradasHistorial(entradas){
    $('#body-historial').html('');
    for (var i=0; i<entradas.length; i++){
        $('#body-historial').append(
            '<tr>' +
                '<td>' + entradas.item(i).CuentaDesde + '</td>' +
                '<td>' + entradas.item(i).CuentaHasta + '</td>' +
                '<td>' + entradas.item(i).Monto + '</td>' +
                '<td>' + entradas.item(i).FechaHora + '</td>' +
            '</tr>'
        );
    }
}

function btnLogout(){
    IdUsuario =  null;
    NomUsuario =  null;
    Password = null;
    IsAdmin = false;
    UsuarioLogueado = false;
    Cuentas = null;
    EventosIniciados = false;
    eliminarUsuarioPersistente();
}

