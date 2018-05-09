
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
    $result = mysql_query("SELECT * FROM beneficiarios WHERE id_beneficiario = $id_beneficiario");

    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {

            $result = mysql_fetch_array($result);
			
			$response["beneficiario"] = array();

            $beneficiario = array();
            $beneficiario["id_beneficiario"] = $result["ID_Beneficiario"];
			$beneficiario["fecha_afiliacion"] = $result["Fecha_Afiliacion"];
			$beneficiario["fecha_nacimiento"] = $result["Fecha_Nacimiento"];
			$beneficiario["primer_apellido"] = $result["Primer_Apellido"];
            $beneficiario["primer_nombre"] = $result["Primer_Nombre"];
			$beneficiario["segundo_apellido"] = $result["Segundo_Apellido"];
			$beneficiario["segundo_nombre"] = $result["Segundo_Nombre"];
			$beneficiario["tercer_nombre"] = $result["Tercer_Nombre"];
			$beneficiario["tipo_beneficiario"] = $result["Tipo_Benefiiciario"];
			$beneficiario["tipo_de_sangre"] = $result["Tipo_De_Sangre"];
			$beneficiario["ciudad"] = $result["Ciudad"];
			$beneficiario["direccion"] = $result["Direccion"];
			$beneficiario["genero"] = $result["Genero"];
            // success
            $response["success"] = 1;

            array_push($response["beneficiario"], $beneficiario);

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