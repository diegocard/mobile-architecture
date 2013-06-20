/* ========================== Manejo de tablas ========================== */
var BDCargada = false;
var VaciarBD = false;

//Ejecuta las transacciones pendientes y devuelve la base
function obtenerBD(){
	return window.openDatabase("Database", "1.0", "TAGSI", 200000);
}

// Invoca la creacion de las tablas de la base de datos en caso de que no existan
function cargarBDInicial() {
	if (!BDCargada){
		if (VaciarBD){
			obtenerBD().transaction(eliminarTablas, errorBD);
		}
		obtenerBD().transaction(iniciarTablaUsuario, errorBD);
		//guardarUsuarioPersistente('asd','das');
		BDCargada = true;
	}
}

// Elimina todas las tablas de la base
function eliminarTablas(tx){
	tx.executeSql('DROP TABLE IF EXISTS Cuenta');
	tx.executeSql('DROP TABLE IF EXISTS Transferencia');
	tx.executeSql('DROP TABLE IF EXISTS Usuario');
	tx.executeSql('DROP TABLE IF EXISTS Historial');
}

// Imprime errores de la base
function errorBD(err) {
	alert("Error " + err.code + " procesando SQL: " + err.message);
}

// Crea la tabla de cuentas en caso de que no exista 
function iniciarTablaCuenta(tx) {
	tx.executeSql('CREATE TABLE IF NOT EXISTS Cuenta (ID unique, Numero, Saldo, Tipo, Usuario_ID)');	
}

// Crea la tabla de transferencias en caso de que no exista 
function iniciarTablaTransferencia(tx) {
	tx.executeSql('CREATE TABLE IF NOT EXISTS Transferencia (ID unique, Descripcion, Monto, Cuenta_Destino_ID, Cuenta_Origen_ID)');	
}

// Crea la tabla que guarda los datos del usuario (unico)
function iniciarTablaUsuario(tx) {
	tx.executeSql('CREATE TABLE IF NOT EXISTS Usuario (ID unique, Nombre, Password, IsAdmin)');	
}

// Crea la tabla que guarda los datos del historial
function iniciarTablaUsuario(tx) {
	tx.executeSql('CREATE TABLE IF NOT EXISTS Historial (IDUsuario, Mensaje)');	
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
						IdUsuario = resultado.rows.item(0).ID;								
						NomUsuario = resultado.rows.item(0).Nombre;
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

function guardarUsuarioPersistente(ID, Name, Pass, IsAdm){
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
	var queryInsert = 'INSERT INTO Usuario (ID, Nombre, Password, IsAdmin) ' +
				'VALUES ("' + ID + '", "' + Name + '", "' + Pass + '", "' + IsAdm + '")';
	var db = obtenerBD();
	db.transaction(
		function(tx){
			tx.executeSql(queryInsert);
		},
		errorBD
	);
}

