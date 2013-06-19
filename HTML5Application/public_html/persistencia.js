/* ========================== Manejo de tablas ========================== */
var BDCargada = false;

//Ejecuta las transacciones pendientes y devuelve la base
function obtenerBD(){
	return window.openDatabase("Database", "1.0", "TAGSI", 200000);
}

// Invoca la creacion de las tablas de la base de datos en caso de que no existan
function cargarBDInicial() {
	if (!BDCargada){
		//obtenerBD().transaction(eliminarTablas, errorBD);	
		obtenerBD().transaction(iniciarTablaUsuario, errorBD);
		//guardarUsuarioPersistente('asd','das');
		BDCargada = true;
	}
}

// Elimina todas las tablas de la base
function eliminarTablas(tx){
	tx.executeSql('DROP TABLE IF EXISTS Cuenta');
	tx.executeSql('DROP TABLE IF EXISTS Transferencia');	
	tx.executeSql('DROP TABLE IF EXISTS Sucursal');
	tx.executeSql('DROP TABLE IF EXISTS Usuario');
}

// Imprime errores de la base
function errorBD(err) {
	alert("Error " + err.code + " procesando SQL: " + err.message);
}

// Crea la tabla de cuentas en caso de que no exista 
function iniciarTablaCuenta(tx) {
	tx.executeSql('CREATE TABLE IF NOT EXISTS Cuenta (ID unique, Numero, Saldo, Tipo, Usuario_ID)');	
}

// Crea la tabla de sucursales en caso de que no exista 
function iniciarTablaSucursal(tx) {
	tx.executeSql('CREATE TABLE IF NOT EXISTS Sucursal (ID unique, Direccion, Nombre, X, Y)');	
}

// Crea la tabla de transferencias en caso de que no exista 
function iniciarTablaTransferencia(tx) {
	tx.executeSql('CREATE TABLE IF NOT EXISTS Transferencia (ID unique, Descripcion, Monto, Cuenta_Destino_ID, Cuenta_Origen_ID)');	
}

// Crea la tabla que guarda los datos del usuario (unico)
function iniciarTablaUsuario(tx) {
	tx.executeSql('CREATE TABLE IF NOT EXISTS Usuario (Usuario, Password, IsAdmin)');	
}

/* ========================== Manejo de datos ========================== */

function cargarUsuarioPersistente(){
	var db = obtenerBD();
	var query = 'SELECT * FROM Usuario';
	db.transaction(
		function(tx){
			tx.executeSql(
				query, 
				[], 
				function (tx, resultado){
					var CantFilas = resultado.rows.length;					
					if (CantFilas > 0){								
						Usuario = resultado.rows.item(0).Usuario;
						Password = resultado.rows.item(0).Password;
						IsAdmin = resultado.rows.item(0).IsAdmin;
						login();
					}					
				}, 
				errorBD
			);
		},
		errorBD
	);
}

function guardarUsuarioPersistente(User, Pass, IsAdm){
	//Solo puede haber 1 usuario persistente
	//En caso de que haya alguno guardado, lo elimino
	var queryDelete = 'DELETE FROM USUARIO';
	var db = obtenerBD();
	db.transaction(
		function(tx){
			tx.executeSql(queryDelete);
		},
		errorBD
	);
	//Guardo el nuevo usuario persistente
	var queryInsert = 'INSERT INTO Usuario (Usuario, Password, IsAdmin) ' +
				'VALUES ("' + User + '", "' + Pass + '", "' + IsAdm + '")';
	var db = obtenerBD();
	db.transaction(
		function(tx){
			tx.executeSql(queryInsert);
		},
		errorBD
	);
}

function guardarFavoritoConChequeo(ID, Nombre, Direccion, Telefonos, Mail, Web){
	var db = obtenerBD();
	var query = 'SELECT * FROM Favoritos WHERE ID="' + ID + '"';
	db.transaction(
		function(tx){
			tx.executeSql(
				query, 
				[], 
				function (tx, resultado){
					var CantFilas = resultado.rows.length;					
					if (CantFilas == 0){						
						guardarFavorito(ID, Nombre, Direccion, Telefonos, Mail, Web);
					}					
				}, 
				errorBD
			);
		},
		errorBD
	);
}

function guardarFavorito(ID, Nombre, Direccion, Telefonos, Mail, Web){	
	var db = obtenerBD();
	var query = 'INSERT INTO Favoritos (ID, Nombre, Direccion, Telefonos, Mail, Web) ' +
			'VALUES ("' + ID + '", "' + Nombre + '", "' + Direccion + '", "' + Telefonos + '", "' + Mail + '", "' + Web + '")';
	db.transaction(
		function(tx){tx.executeSql(query);},
		errorBD
	);
}

function eliminarFavorito(ID){
	var db = obtenerBD();
	var query = 'DELETE FROM Favoritos WHERE ID="' + ID + '"';
	db.transaction(
		function(tx){tx.executeSql(query);}, 
		errorBD
	);	
}

function mostrarBotonEliminarSiExiste(id){
	var db = obtenerBD();
	var query = 'SELECT * FROM Favoritos WHERE ID="' + id + '"';
	db.transaction(
		function(tx){
			tx.executeSql(
				query, 
				[], 
				function (tx, resultado){
					var CantFilas = resultado.rows.length;
					if (CantFilas > 0){
						mostrarBotonEliminarFavoritos();
					}else{
						mostrarBotonAgregarFavoritos();
					}					
				}, 
				errorBD
			);
		},
		errorBD
	);
}

function mostrarBD(){
	var db = obtenerBD();
	db.transaction(
		function(tx){
			tx.executeSql(
				'SELECT * FROM Favoritos;', 
				[], 
				function(tx, resultado){ 
					var mensaje = '';
					var CantFilas = resultado.rows.length;
					mensaje += "Tabla Favoritos: " + CantFilas + " filas encontradas. \n";
					for (var i=0; i<CantFilas; i++){
						mensaje += "Fila = " + i + " ID = " + resultado.rows.item(i).ID + " Nombre =  " + resultado.rows.item(i).Nombre + "\n";
					}
					alert(mensaje);	
				}, 
				errorBD
			);
		}, 
		errorBD
	);
}

function accionCargarFavoritos(){
	var db = obtenerBD();
	db.transaction(
		function(tx){
			$("#lstFavoritos").html("");
			tx.executeSql(
				'SELECT * FROM Favoritos;', 
				[], 
				function(tx, resultado){ 
					var CantFilas = resultado.rows.length;
					for (var i=0; i<CantFilas; i++){
						var insert=
							"<li data-theme='b'>" +
								"<a href='#InfoOperador' data-transition='slide' id=\"" + resultado.rows.item(i).ID + "\">" +
									resultado.rows.item(i).Nombre +
								"</a>" +
							"</li>";
						$("#lstFavoritos").append(insert);
					}
					$("#lstFavoritos").listview('refresh');
				}, 
				errorBD
			);
		}, 
		errorBD
	);
}

function tapFavorito(){
	var index = $(this).index();
	indiceFavoritoSeleccionado = index;	
	var db = obtenerBD();
	//Busco el favorito seleccionado en la BD
	db.transaction(
		function(tx){
			$("#lstFavoritos").html("");
			tx.executeSql(
				'SELECT * FROM Favoritos;', 
				[], 
				function(tx, resultado){
					var ID = resultado.rows.item(index).ID;
					var Nombre = resultado.rows.item(index).Nombre;
					var Direccion = resultado.rows.item(index).Direccion;
					var Telefonos = resultado.rows.item(index).Telefono;
					var Mail = resultado.rows.item(index).Mail;
					var Web = resultado.rows.item(index).Web;
					cargarPaginaOperador(ID, Nombre, Direccion, Telefonos, Mail, Web);					
				}, 
				errorBD
			);
		}, 
		errorBD
	);
}

