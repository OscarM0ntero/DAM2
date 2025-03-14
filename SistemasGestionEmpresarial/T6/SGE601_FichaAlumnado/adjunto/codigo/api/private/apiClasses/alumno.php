<?php
require_once('interfaces/crud.php');
require_once('../conn.php');

class Alumno extends Conexion {

    function __construct() {
        parent::__construct();
    }

    // Obtener alumnos por unidad centro
    public function getByUnidadCentro($id_unidad_centro) {
        try {
            $sql = $this->conexion->prepare("SELECT id, nombre, apellidos, fecha_nacimiento, linkedin, nivel_ingles,
                                                   minusvalia, otra_formacion, id_unidad_centro
                                              FROM sgi_alumnos
                                              WHERE id_unidad_centro = :id_unidad_centro
                                              ORDER BY apellidos, nombre");
    
            $sql->bindParam(":id_unidad_centro", $id_unidad_centro, PDO::PARAM_INT);
            $sql->execute();
            $data = $sql->fetchAll(PDO::FETCH_ASSOC);
    
            return [
                "status" => true,
                "data" => $data,
                "message" => empty($data) ? "No hay alumnos registrados" : "Alumnos obtenidos correctamente"
            ];
        } catch (PDOException $e) {
            return ["status" => false, "message" => "Error: " . $e->getMessage()];
        }
    }
    

    public function create($data) {
        try {
            $sql = $this->conexion->prepare("INSERT INTO sgi_alumnos
                (id, nombre, apellidos, fecha_nacimiento, linkedin, nivel_ingles, minusvalia, otra_formacion, id_unidad_centro)
                VALUES (:id, :nombre, :apellidos, :fecha_nacimiento, :linkedin, :nivel_ingles, :minusvalia, :otra_formacion, :id_unidad_centro)");

            $sql->bindParam(":id", $data['id'], PDO::PARAM_STR);
            $sql->bindParam(":nombre", $data['nombre'], PDO::PARAM_STR);
            $sql->bindParam(":apellidos", $data['apellidos'], PDO::PARAM_STR);
            $sql->bindParam(":fecha_nacimiento", $data['fecha_nacimiento'], PDO::PARAM_STR);
            $sql->bindParam(":linkedin", $data['linkedin'], PDO::PARAM_STR);
            $sql->bindParam(":nivel_ingles", $data['nivel_ingles'], PDO::PARAM_STR);
            $sql->bindParam(":minusvalia", $data['minusvalia'], PDO::PARAM_INT);
            $sql->bindParam(":otra_formacion", $data['otra_formacion'], PDO::PARAM_STR);
            $sql->bindParam(":id_unidad_centro", $data['id_unidad_centro'], PDO::PARAM_INT);

            $resultado = $sql->execute();

            return [
                "status" => $resultado,
                "message" => $resultado ? "Alumno creado correctamente" : "Error al crear alumno"
            ];
        } catch (PDOException $e) {
            return ["status" => false, "message" => "Error: " . $e->getMessage()];
        }
    }

    public function update($data) {
        // SOLO para depuración — asegúrate de que se muestre bien en logs
        if ($_SERVER['REQUEST_METHOD'] === 'PUT') {
            error_log(print_r($data, true)); // Esto enviará el resultado al log en lugar de romper la respuesta JSON
        }
    
        try {
            $sql = $this->conexion->prepare("
                UPDATE sgi_alumnos SET
                    nombre = :nombre,
                    apellidos = :apellidos,
                    fecha_nacimiento = :fecha_nacimiento,
                    linkedin = :linkedin,
                    nivel_ingles = :nivel_ingles,
                    minusvalia = :minusvalia,
                    otra_formacion = :otra_formacion,
                    id_unidad_centro = :id_unidad_centro
                WHERE id = :id");
    
            $sql->bindParam(":id", $data['id'], PDO::PARAM_STR);
            $sql->bindParam(":nombre", $data['nombre'], PDO::PARAM_STR);
            $sql->bindParam(":apellidos", $data['apellidos'], PDO::PARAM_STR);
            $sql->bindParam(":fecha_nacimiento", $data['fecha_nacimiento'], PDO::PARAM_STR);
            $sql->bindParam(":linkedin", $data['linkedin'], PDO::PARAM_STR);
            $sql->bindParam(":nivel_ingles", $data['nivel_ingles'], PDO::PARAM_STR);
            $sql->bindParam(":minusvalia", $data['minusvalia'], PDO::PARAM_INT);
            $sql->bindParam(":otra_formacion", $data['otra_formacion'], PDO::PARAM_STR);
            $sql->bindParam(":id_unidad_centro", $data['id_unidad_centro'], PDO::PARAM_INT);
    
            $resultado = $sql->execute();
    
            return [
                "status" => $resultado,
                "message" => $resultado ? "Alumno actualizado correctamente" : "Error al actualizar alumno"
            ];
        } catch (PDOException $e) {
            return ["status" => false, "message" => "Error: " . $e->getMessage()];
        }
    }
    

    public function delete($id) {
        try {
            $sql = $this->conexion->prepare("DELETE FROM sgi_alumnos WHERE id = :id");
            $sql->bindParam(":id", $id, PDO::PARAM_STR);
            $resultado = $sql->execute();

            return [
                "status" => $resultado,
                "message" => $resultado ? "Alumno eliminado correctamente" : "Error al eliminar alumno"
            ];
        } catch (PDOException $e) {
            return ["status" => false, "message" => "Error: " . $e->getMessage()];
        }
    }
}
?>
