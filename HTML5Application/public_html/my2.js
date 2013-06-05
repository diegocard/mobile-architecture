/* Variables */
var URLBase = "http://localhost:8080/HomeBanking-web/rest/";
$(document).bind('pageinit', iniEventos);

function iniEventos(){
	$('#btn-login').bind('tap', login);
}

/* ============================== Funciones de consulta ============================== */
function login(){
    
    var Usuario = $("#input-user").val();
    var Pass = $("#input-password").val();
    //var url=URLBase + 'usuarios/validar/jsonp/' + Usuario + '/' + Pass + '/false';
    var url=URLBase + 'usuarios/jsonp';
    
    $.ajax({
        url: url,
        data: "{}",
        type: "GET",
        contentType: "application/javascript",
        dataType: "jsonp",
        error: function() {
            alert('error');
        },
        failure: function() {
            alert('failure');
        },
        success: function(data) {
            $.each(data, function(k, v){
                alert(v.nombre);
            })
        },
    });          
    
}
