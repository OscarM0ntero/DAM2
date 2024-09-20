package vehiculo;

public class Vehiculo {
	//Constantes de clase
		public static final double CONSUMO_MEDIO_MIN;
		public static final double CONSUMO_MEDIO_MAX;
		public static final double CAPACIDAD_DEPOSITO_MIN;
		public static final double CAPACIDAD_DEPOSITO_MAX;
		public static final double CONSUMO_ARRANQUE;
		public static final double CONSUMO_MEDIO_DEFAULT;
		public static final double CAPACIDAD_DEPOSITO_DEFAULT;
		
		//Variables de clase
		private static double kilometrosRecorridosEntreTodos;
		private static double consumoRealizadoEntreTodos;
		private static int numVehiculosEncendidos;
		
		//Constantes de objeto
		private final double capacidadDeposito;
		private final double consumoMedio;
		
		//Variables de objeto
		private boolean estadoMotor;
		private double nivelDeposito;
		private double kilometrosRecorridos;
		private double consumoRealizado;
		private double kilometrosTotales;
		private double consumoTotal;
		
		//Instanciación de las constantes de clase
		static {
			CONSUMO_MEDIO_MIN = 2.0;
			CONSUMO_MEDIO_MAX = 20.0;
			CAPACIDAD_DEPOSITO_MIN = 10.0;
			CAPACIDAD_DEPOSITO_MAX = 120.0;
			CONSUMO_ARRANQUE = 0.02;
			CONSUMO_MEDIO_DEFAULT = 5.0;
			CAPACIDAD_DEPOSITO_DEFAULT = 50.0;
			kilometrosRecorridosEntreTodos = 0;
			consumoRealizadoEntreTodos = 0;
			numVehiculosEncendidos = 0;
		}
		
		//Constructores
		public Vehiculo(double deposito, double consumo) {
			if (deposito >= Vehiculo.CAPACIDAD_DEPOSITO_MIN && deposito <= Vehiculo.CAPACIDAD_DEPOSITO_MAX && consumo >= Vehiculo.CONSUMO_MEDIO_MIN && consumo <= Vehiculo.CONSUMO_MEDIO_MAX) {
				this.capacidadDeposito = deposito;
				this.consumoMedio = consumo;
				estadoMotor = false;
				nivelDeposito = 1.0;
				kilometrosRecorridos = 0.0;
				consumoRealizado = 0.0;
				kilometrosTotales = 0.0;
				consumoTotal = 0.0;
			}
			else {
				this.capacidadDeposito = 0.0;
				this.consumoMedio = 0.0;
			}
		}
		public Vehiculo() {
			this(Vehiculo.CAPACIDAD_DEPOSITO_DEFAULT, Vehiculo.CONSUMO_MEDIO_DEFAULT);
		}
		
		//Métodos
		//Métodos para conseguir información (getters)
		public boolean isArrancado() {
			return this.estadoMotor;
		}
		public double getConsumoMedio() {
			return this.consumoMedio;
		}
		public double getCapacidadDeposito() {
			return this.capacidadDeposito;
		}
		public double getNivelCombustible() {
			return this.nivelDeposito;
		}
		public double getDistanciaRecorrida() {
			return this.kilometrosRecorridos;
		}
		public double getDistanciaRecorridaTotal() {
			return this.kilometrosTotales;
		}
		public double getCombustibleConsumido() {
			return this.consumoRealizado;
		}
		public double getCombustibleConsumidoTotal() {
			return this.consumoTotal;
		}
		public double getDistanciaRecorridaFlota() {
			return Vehiculo.kilometrosRecorridosEntreTodos;
		}
		public double getCombustibleConsumidoFlota() {
			return Vehiculo.consumoRealizadoEntreTodos;
		}
		public int getNumVehiculosArrancadosFlota() {
			return Vehiculo.numVehiculosEncendidos;
		}
		//Rellenamos el nivel del depósito del vehículo
		public void repostar(double litros) {
			if (this.estadoMotor == false) {
				if (litros > (this.capacidadDeposito - this.nivelDeposito)) {
					System.out.println("Depósito lleno. Se ha sobrepasado la capacidad del depósito de combustible en " + (litros - (this.capacidadDeposito - this.nivelDeposito)) + "litros.");
					this.nivelDeposito = this.capacidadDeposito;
				}
				else {
					this.nivelDeposito += litros;
				}
			}
			else {
				System.err.println("ERROR: No se puede repostar con el motor arrancado.");
			}
		}
		//Arrancamos el motor del vehículo
		public void arrancar() {
			if (this.estadoMotor == false) {
				if (this.nivelDeposito > Vehiculo.CONSUMO_ARRANQUE) {
					this.estadoMotor = true;
					this.nivelDeposito -= Vehiculo.CONSUMO_ARRANQUE;
					this.consumoRealizado += Vehiculo.CONSUMO_ARRANQUE;
					this.consumoTotal += Vehiculo.CONSUMO_ARRANQUE;
					this.kilometrosRecorridos = 0;
					this.consumoRealizado = 0;
					Vehiculo.numVehiculosEncendidos++;
					Vehiculo.consumoRealizadoEntreTodos += Vehiculo.CONSUMO_ARRANQUE;
				}
				else {
					System.err.println("ERROR: no hay combustible suficiente para arrancar el vehículo.");
				}
			}
			else {
				System.err.println("ERROR: El motor ya está arrancado.");
			}
		}
		//El vehículo realiza un trayecto de cierta distancia y consume el combustible necesario para este trayecto
		public void realizarTrayecto(double distancia) {
			double consumoTrayecto = (this.consumoMedio/100)*distancia;
			double distanciaDeposito = this.nivelDeposito/(this.consumoMedio/100);
			if (distancia > 0) {
				if (this.estadoMotor == true) {
					if (consumoTrayecto < this.nivelDeposito) {
						this.kilometrosRecorridos += distancia;
						this.kilometrosTotales += distancia;
						Vehiculo.kilometrosRecorridosEntreTodos += distancia;
						this.consumoRealizado += consumoTrayecto;
						this.consumoTotal += consumoTrayecto;
						Vehiculo.consumoRealizadoEntreTodos += consumoTrayecto;
						this.nivelDeposito -= consumoTrayecto;
					}
					else {
						System.err.println("No se ha podido finalizar el trayecto completamente. Depósito vacío. Han faltado por recorrer " + (distancia - distanciaDeposito));
						this.kilometrosRecorridos += distanciaDeposito;
						this.kilometrosTotales += distanciaDeposito;
						Vehiculo.kilometrosRecorridosEntreTodos += distanciaDeposito;
						this.consumoRealizado += this.nivelDeposito;
						this.consumoTotal += this.nivelDeposito;
						Vehiculo.consumoRealizadoEntreTodos += this.nivelDeposito;
						this.nivelDeposito = 0.0;
						this.estadoMotor = false;
						Vehiculo.numVehiculosEncendidos--;
					}
				}
				else {
					System.err.println("ERROR: Se ha intentado realizar un trayecto con el motor apagado. No se ha avanzado");
				}
			}
			else {
				System.err.println("ERROR: Se intentó realizar un trayecto negativo.");
			}
		}
		//Apagamos el mototr del vehículo
		public void apagar() {
			if (this.estadoMotor == true) {
				this.estadoMotor = false;
				Vehiculo.numVehiculosEncendidos--;
				this.kilometrosRecorridos = 0;
				this.consumoRealizado = 0;
			}
			else {
				System.err.println("ERROR: el motor ya se encuentra apagado");
			}
		}
		public String toString() {
			String manolo;
			if (this.estadoMotor == true) {
				manolo = "Motor: Encendido - Depósito: " + String.format("%.2f", this.nivelDeposito) + " - Dist: " + String.format("%.2f", this.kilometrosRecorridos) + " - Consumo: " + String.format("%.2f", this.consumoRealizado);
			}
			else {
				manolo = "Motor: Apagado - Depósito: " + String.format("%.2f", this.nivelDeposito) + " - Dist: " + String.format("%.2f", this.kilometrosRecorridos) + " - Consumo: " + String.format("%.2f", this.consumoRealizado);
			}
			return manolo;
		}
}