<?php


require_once('./apiClasses/unidad_centro.php');

$api_utils = new ApiUtils();
$api_utils->setHeaders($api_utils::ALL_HEADERS);
$api_utils->displayErrors();

$request = json_decode(file_get_contents("php://input"), true);
$unidadCentro = new UnidadCentro();

$id = isset($_GET["id"]) ? $_GET["id"] : null;
$route = isset($_GET["route"]) ? $_GET["route"] : "";

$response = [
    "status" => false,
    "message" => "Acción no ejecutada",
    "data" => null
];

switch ($_SERVER['REQUEST_METHOD']) {
    case "GET":
        if (!empty($id)) {
            $data = $unidadCentro->getById($id);
            $response["status"] = true;
            $response["message"] = "Unidad centro obtenida";
            $response["data"] = $data;
        } else {
            $data = $unidadCentro->getAll();
            $response["status"] = true;
            $response["message"] = "Unidades centro obtenidas";
            $response["data"] = $data;
        }
        break;

    case "POST":
        $data = $unidadCentro->create($request);
        $response["status"] = true;
        $response["message"] = "Unidad centro creada";
        $response["data"] = $data;
        break;

    case "PUT":
        $data = $unidadCentro->update($request);
        $response["status"] = true;
        $response["message"] = "Unidad centro actualizada";
        $response["data"] = $data;
        break;

    case "DELETE":
        if (!empty($id)) {
            $data = $unidadCentro->delete($id);
            $response["status"] = true;
            $response["message"] = "Unidad centro eliminada";
            $response["data"] = $data;
        }
        break;

    default:
        $response["message"] = "Método no permitido";
        break;
}

echo json_encode($response, JSON_PRETTY_PRINT);
?>