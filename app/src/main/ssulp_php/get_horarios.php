
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
if (isset($_POST["id_medico"]) && isset($_POST["fecha_agendada"])) {
    $id_medico = $_POST["id_medico"];
	$fecha_agendada = $_POST["fecha_agendada"];

    // get a product from products table
    $result = mysql_query("SELECT turno as turno FROM agenda WHERE id_medico = '$id_medico' and fecha_agendada = '$fecha_agendada'");
	

    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {
			$response["horarios"] = array();

			while ($row = mysql_fetch_array($result)) {
            $horario = array();
            $horario["turno"] = $row["turno"];
			
            // success
            $response["success"] = 1;

            array_push($response["horarios"], $horario);
			}
            // echoing JSON response
            echo json_encode($response);
        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "No product found";

            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "No product found";

        // echo no users JSON
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