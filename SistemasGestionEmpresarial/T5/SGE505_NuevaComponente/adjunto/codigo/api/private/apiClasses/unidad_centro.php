<?php
require_once('interfaces/crud.php');
require_once('../conn.php');

class UnidadCentro extends Conexion {
    public $status = false;
    public $message = NULL;
    public $data = NULL;

    function __construct() {
        parent::__construct();
    }

    public function getAll() {
        try {
            $sql = $this->conexion->prepare("SELECT U.*, C.ciclo AS fk_ciclo
                FROM sgi_unidades_centro U
                LEFT JOIN sgi_ciclos C ON U.id_ciclo = C.id_ciclo
                ORDER BY U.unidad_centro, C.ciclo, U.observaciones");

            $sql->execute();
            $data = $sql->fetchAll(PDO::FETCH_ASSOC);

            return [
                "status" => true,
                "data" => $data,
                "message" => empty($data) ? "No hay unidades de centro registradas" : "Unidades centro obtenidas correctamente"
            ];
        } catch (PDOException $e) {
            return ["error" => "Error en la consulta: " . $e->getMessage()];
        }
    }

    public function getById($id_unidad_centro) {
        try {
            $sql = $this->conexion->prepare("SELECT U.*, C.ciclo AS fk_ciclo
                FROM sgi_unidades_centro U
                LEFT JOIN sgi_ciclos C ON U.id_ciclo = C.id_ciclo
                WHERE U.id_unidad_centro = :id_unidad_centro");

            $sql->bindParam(":id_unidad_centro", $id_unidad_centro, PDO::PARAM_INT);
            $sql->execute();
            $data = $sql->fetch(PDO::FETCH_ASSOC);

            return [
                "status" => !empty($data),
                "data" => $data,
                "message" => !empty($data) ? "Unidad centro obtenida correctamente" : "No se encontró la unidad centro"
            ];
        } catch (PDOException $e) {
            return ["error" => "Error en la consulta: " . $e->getMessage()];
        }
    }

    public function create($data) {
        try {
            $sql = $this->conexion->prepare("INSERT INTO sgi_unidades_centro (unidad_centro, id_ciclo, observaciones)
                VALUES (:unidad_centro, :id_ciclo, :observaciones)");

            $sql->bindParam(":unidad_centro", $data['unidad_centro'], PDO::PARAM_STR);
            $sql->bindParam(":id_ciclo", $data['id_ciclo'], PDO::PARAM_INT);
            $sql->bindParam(":observaciones", $data['observaciones'], PDO::PARAM_STR);
			
            $resultado = $sql->execute();

            return [
                "status" => $resultado,
                "message" => $resultado ? "Unidad centro creada correctamente" : "Error al crear unidad centro"
            ];
        } catch (PDOException $e) {
            return ["error" => "Error en la inserción: " . $e->getMessage()];
        }
    }

    public function update($data) {
        try {
            $sql = $this->conexion->prepare("UPDATE sgi_unidades_centro SET
                    unidad_centro = :unidad_centro,
                    id_ciclo = :id_ciclo,
                    observaciones = :observaciones
                    WHERE id_unidad_centro = :id_unidad_centro");

            $sql->bindParam(":unidad_centro", $data['unidad_centro'], PDO::PARAM_STR);
            $sql->bindParam(":id_ciclo", $data['id_ciclo'], PDO::PARAM_INT);
            $sql->bindParam(":observaciones", $data['observaciones'], PDO::PARAM_STR);
            $sql->bindParam(":id_unidad_centro", $data['id_unidad_centro'], PDO::PARAM_INT);

            $resultado = $sql->execute();

            return [
                "status" => $resultado,
                "message" => $resultado ? "Unidad centro actualizada correctamente" : "Error al actualizar unidad centro"
            ];
        } catch (PDOException $e) {
            return ["error" => "Error en la actualización: " . $e->getMessage()];
        }
    }

    public function delete($id_unidad_centro) {
        try {
            $sql = $this->conexion->prepare("DELETE FROM sgi_unidades_centro WHERE id_unidad_centro = :id_unidad_centro");
            $sql->bindParam(":id_unidad_centro", $id_unidad_centro, PDO::PARAM_INT);
            $resultado = $sql->execute();

            return [
                "status" => $resultado,
                "message" => $resultado ? "Unidad centro eliminada correctamente" : "Error al eliminar unidad centro"
            ];
        } catch (PDOException $e) {
            return ["error" => "Error al eliminar: " . $e->getMessage()];
        }
    }
}
?>
