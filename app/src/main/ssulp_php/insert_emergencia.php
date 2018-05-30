
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
if (isset($_POST["id_beneficiario"]) && isset($_POST["localizacion"]) && isset($_POST["fecha_emergencia"])) {
    $id_beneficiario = $_POST["id_beneficiario"];
    $localizacion = $_POST["localizacion"];
    $fecha_emergencia = $_POST["fecha_emergencia"];

    // get a product from products table
    $result = mysql_query("INSERT INTO `emergencias`(`ID_Beneficiario`, `Localizacion`, `Fecha_Emergencia`) VALUES ('$id_beneficiario', '$localizacion', '$fecha_emergencia')");
    
	if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
		$response["message"] = "Se hizo la insercion con los datos $id_beneficiario $localizacion $fecha_emergencia";

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