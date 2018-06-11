
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
if (isset($_POST["id_beneficiario"]) && isset($_POST["fecha_agendada"])) {
    $id_beneficiario = $_POST["id_beneficiario"];
	$fecha_filtro = $_POST["fecha_agendada"];

    // get a product from products table
    $result = mysql_query("SELECT A.id_agenda as id_agenda, A.id_beneficiario as id_beneficiario, A.fecha_agendada as fecha_agendada, A.numero_consulta as numero_consulta, A.id_medico as id_medico, A.turno as turno,
						   B.primer_apellido as beneficiario_primer_apellido, B.primer_nombre as beneficiario_primer_nombre, B.segundo_apellido as beneficiario_segundo_apellido, B.segundo_nombre as beneficiario_segundo_nombre,
						   C.id_oficina as consultorio_id, C.nombre as consultorio_nombre, C.codigo as consultorio_codigo, C.piso as consultorio_piso,
						   M.primer_apellido as medico_primer_apellido, M.primer_nombre as medico_primer_nombre, M.segundo_apellido as medico_segundo_apellido, M.segundo_nombre as medico_segundo_nombre, M.correo_institucional as medico_correo_institucional, M.correo_diario as medico_correo_diario, M.telefono_movil as medico_telefono_movil, M.telefono_oficina as medico_telefono_oficina, M.especialidad as medico_especialidad
						   FROM agenda A, beneficiarios B, consultorios C, medicos M WHERE A.id_beneficiario = $id_beneficiario and B.id_beneficiario = $id_beneficiario and A.id_medico = M.id_medico and M.id_oficina = C.id_oficina and A.fecha_agendada = '$fecha_filtro'");

    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {
			
			$response["agenda"] = array();
			
			while ($row = mysql_fetch_array($result)) {

            $agenda = array();
			$agenda["id_agenda"] = $row["id_agenda"];
            $agenda["id_beneficiario"] = $row["id_beneficiario"];
            $agenda["fecha_agendada"] = $row["fecha_agendada"];
			$agenda["numero_consulta"] = $row["numero_consulta"];
			$agenda["id_medico"] = $row["id_medico"];
			$agenda["turno"] = $row["turno"];
			$agenda["beneficiario_primer_apellido"] = $row["beneficiario_primer_apellido"];
			$agenda["beneficiario_primer_nombre"] = $row["beneficiario_primer_nombre"];
			$agenda["beneficiario_segundo_apellido"] = $row["beneficiario_segundo_apellido"];
			$agenda["beneficiario_segundo_nombre"] = $row["beneficiario_segundo_nombre"];
			$agenda["consultorio_id"] = $row["consultorio_id"];
			$agenda["consultorio_nombre"] = $row["consultorio_nombre"];
			$agenda["consultorio_codigo"] = $row["consultorio_codigo"];
			$agenda["consultorio_piso"] = $row["consultorio_piso"];
			$agenda["medico_primer_apellido"] = $row["medico_primer_apellido"];
			$agenda["medico_primer_nombre"] = $row["medico_primer_nombre"];
			$agenda["medico_segundo_apellido"] = $row["medico_segundo_apellido"];
			$agenda["medico_segundo_nombre"] = $row["medico_segundo_nombre"];
			$agenda["medico_telefono_oficina"] = $row["medico_telefono_oficina"];
			$agenda["medico_telefono_movil"] = $row["medico_telefono_movil"];
			$agenda["medico_correo_institucional"] = $row["medico_correo_institucional"];
			$agenda["medico_correo_diario"] = $row["medico_correo_diario"];
			$agenda["medico_especialidad"] = $row["medico_especialidad"];
			
			// success
            $response["success"] = 1;

            array_push($response["agenda"], $agenda);

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