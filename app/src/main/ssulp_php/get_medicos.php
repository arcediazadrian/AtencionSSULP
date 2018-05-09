
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
// get a product from products table
$result = mysql_query("SELECT C.id_oficina as consultorio_id, C.nombre as consultorio_nombre, C.codigo as consultorio_codigo, C.piso as consultorio_piso,
					   M.id_medico as id_medico, M.primer_apellido as medico_primer_apellido, M.primer_nombre as medico_primer_nombre, M.segundo_apellido as medico_segundo_apellido, M.segundo_nombre as medico_segundo_nombre, M.correo_institucional as medico_correo_institucional, M.correo_diario as medico_correo_diario, 
					   M.telefono_movil as medico_telefono_movil, M.telefono_oficina as medico_telefono_oficina, M.especialidad as medico_especialidad, M.horario_inicio as medico_horario_inicio, M.horario_salida as medico_horario_salida 
					   FROM consultorios C, medicos M where C.id_oficina = M.id_oficina");

if (!empty($result)) {
    // check for empty result
    if (mysql_num_rows($result) > 0){
		$response["medicos"] = array();
        
		while ($row = mysql_fetch_array($result)) {
		$medico = array();
        $medico["id_medico"] = $row["id_medico"];
		$medico["medico_primer_apellido"] = $row["medico_primer_apellido"];
		$medico["medico_primer_nombre"] = $row["medico_primer_nombre"];
		$medico["medico_segundo_apellido"] = $row["medico_segundo_apellido"];
		$medico["medico_segundo_nombre"] = $row["medico_segundo_nombre"];
		$medico["medico_correo_institucional"] = $row["medico_correo_institucional"];
		$medico["medico_correo_diario"] = $row["medico_correo_diario"];
		$medico["medico_telefono_movil"] = $row["medico_telefono_movil"];
		$medico["medico_telefono_oficina"] = $row["medico_telefono_oficina"];
		$medico["medico_especialidad"] = $row["medico_especialidad"];
		$medico["medico_horario_inicio"] = $row["medico_horario_inicio"];
		$medico["medico_horario_salida"] = $row["medico_horario_salida"];
		$medico["consultorio_id"] = $row["consultorio_id"];
		$medico["consultorio_nombre"] = $row["consultorio_nombre"];
		$medico["consultorio_codigo"] = $row["consultorio_codigo"];
		$medico["consultorio_piso"] = $row["consultorio_piso"];

		// success
        $response["success"] = 1;
        array_push($response["medicos"], $medico);
		
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

?>