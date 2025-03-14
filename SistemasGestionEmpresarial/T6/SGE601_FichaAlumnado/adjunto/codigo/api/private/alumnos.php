<?php

require_once('./apiClasses/alumno.php');

$api_utils = new ApiUtils();
$api_utils->setHeaders($api_utils::ALL_HEADERS);
$api_utils->displayErrors();

$request = json_decode(file_get_contents("php://input"), true);
$alumno = new Alumno();

$authorization = new Authorization();
$authorization->comprobarToken();

$id = isset($_GET["id"]) ? $_GET["id"] : null;
$id_unidad_centro = isset($_GET["id_unidad_centro"]) ? $_GET["id_unidad_centro"] : null;

$response = [
    "status" => false,
    "message" => "Acción no ejecutada",
    "data" => null
];

if ($authorization->token_valido) {
    switch ($_SERVER['REQUEST_METHOD']) {

        case "GET":
            if (!empty($id_unidad_centro)) {
                $response = (new Alumno())->getByUnidadCentro($id_unidad_centro);
            } else {
                $response["message"] = "Falta id_unidad_centro";
            }
            break;

        case "POST":
            $alumno = new Alumno();
            $response = $alumno->create($request);
            break;

        case "PUT":
            $alumno = new Alumno();
            $response = $alumno->update($request);
            break;

        case "DELETE":
            if (!empty($_GET["id"])) {
                $alumno = new Alumno();
                $response = $alumno->delete($_GET["id"]);
            } else {
                $response["message"] = "Falta id del alumno";
            }
            break;

        default:
            $response["message"] = "Método no permitido";
            break;
    }
} else {
    $response["message"] = "Token inválido o caducado";
}

echo json_encode($response, JSON_PRETTY_PRINT);
