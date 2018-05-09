
<?php

/*
 * Following code will get single product details
 * A product is identified by product id (pid)
 */

// array for JSON response
$response = array();

// include db connect class
require_once '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// check for post data
if (isset($_POST["id_agenda"]) && isset($_POST["id_beneficiario"]) && isset($_POST["id_medico"]) && isset($_POST["fecha_agendada"]) && isset($_POST["turno"])) {
    $id_agenda = $_POST["id_agenda"];
    $id_beneficiario = $_POST["id_beneficiario"];
    $id_medico = $_POST["id_medico"];
    $fecha_agendada = $_POST["fecha_agendada"];
    $turno = $_POST["turno"];

    // get a product from products table
    $result = mysql_query("INSERT INTO agenda(id_agenda, id_beneficiario, fecha_agendada, numero_consulta, id_medico, turno) VALUES('$id_agenda', '$id_beneficiario', '$fecha_agendada', '0', '$id_medico', '$turno')");
    
	if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
		$response["message"] = "Se hizo la insercion con los datos $id_agenda $id_beneficiario $id_medico $fecha_agendada $turno";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";

        // echoing JSON response
        echo json_encode($response);
    }
    
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>