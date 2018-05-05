
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
if (isset($_POST["id_beneficiario"])) {
    $id_beneficiario = $_POST["id_beneficiario"];

    // get a product from products table
    $result = mysql_query("SELECT id_beneficiario, primer_nombre FROM beneficiarios WHERE id_beneficiario = $id_beneficiario");

    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {

            $result = mysql_fetch_array($result);
			
			$response["beneficiarios"] = array();

            $beneficiario = array();
            $beneficiario["id_beneficiario"] = $result["id_beneficiario"];
            $beneficiario["primer_nombre"] = $result["primer_nombre"];
            // success
            $response["success"] = 1;

            // user node
            $response["beneficiario"] = array();

            array_push($response["beneficiarios"], $beneficiario);

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