package vehiculo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Vehiculo implements Serializable {
    private String matricula;
    private String marca;
    private String modelo;
    private String tipo;

    // Constructor
    public Vehiculo(String matricula, String marca, String modelo, String tipo) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
    }

    // Métodos getters
    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getTipo() {
        return tipo;
    }

    // Método para mostrar información del vehículo
    @Override
    public String toString() {
        return "Vehiculo{" +
                "matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }

    // Método para guardar los vehículos en un archivo de texto
    public static void guardarVehiculos(List<Vehiculo> vehiculos, String archivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(vehiculos);
            System.out.println("Vehículos guardados en el archivo: " + archivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para recuperar los vehículos desde un archivo de texto
    public static List<Vehiculo> cargarVehiculos(String archivo) {
        List<Vehiculo> vehiculos = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            vehiculos = (List<Vehiculo>) ois.readObject();
            System.out.println("Vehículos cargados desde el archivo: " + archivo);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return vehiculos;
    }
}

public class Main {
    public static void main(String[] args) {
        // Crear algunos vehículos
        Vehiculo vehiculo1 = new Vehiculo("1234ABC", "Toyota", "Corolla", "Coche");
        Vehiculo vehiculo2 = new Vehiculo("5678DEF", "Ford", "Transit", "Furgón");
        Vehiculo vehiculo3 = new Vehiculo("9101GHI", "Yamaha", "R6", "Moto");

        // Guardar los vehículos en un archivo
        List<Vehiculo> listaVehiculos = new ArrayList<>();
        listaVehiculos.add(vehiculo1);
        listaVehiculos.add(vehiculo2);
        listaVehiculos.add(vehiculo3);

        String archivo = "vehiculos.txt";
        Vehiculo.guardarVehiculos(listaVehiculos, archivo);

        // Recuperar los vehículos desde el archivo
        List<Vehiculo> vehiculosRecuperados = Vehiculo.cargarVehiculos(archivo);

        // Mostrar los vehículos recuperados
        for (Vehiculo v : vehiculosRecuperados) {
            System.out.println(v);
        }
    }
}
