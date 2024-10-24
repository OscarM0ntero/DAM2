// Interfaz Dirección
export interface Direccion {
    calle: string;
    cp: string;
    localidad: string;
    provincia: string;
}

// Interfaz Enseñanza
export interface Ensenanza {
    nivel: string;
    esObligatoria: boolean;
    familiaProfesional: string | null;
    modalidad: "Mañana" | "Tarde" | "Telemático"; 
	
}

// Interfaz Alumno
export interface Alumno {
    nombre: string;
    edad: number;
    sexo: "Masculino" | "Femenino" | "Otro";
    ensenanza: Ensenanza;
    direccion: Direccion;
}

// Clase Instituto
export class Instituto {
    nombre: string;
    director: string;
    telefono: string;
    correo: string;
    direccion: Direccion;
    listadoAlumnos: Alumno[] = [];

    constructor(nombre: string, director: string, telefono: string, correo: string, direccion: Direccion) {
        this.nombre = nombre;
        this.director = director;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    // Matricular un alumno
    matricularAlumno(alumno: Alumno) {
        this.listadoAlumnos.push(alumno);
    }

    // Mostrar todos los alumnos del instituto
    mostrarAlumnos(): void {
        console.log("Listado de alumnos:");
        this.listadoAlumnos.forEach(({ nombre, ensenanza, direccion: { localidad } }) => {
            console.log(`Nombre: ${nombre}, Nivel: ${ensenanza.nivel}, Localidad: ${localidad}`);
        });
    }

    // Mostrar la dirección completa del instituto
    mostrarDireccion(): void {
        const { calle, cp, localidad, provincia } = this.direccion;
        console.log(`Dirección del instituto: ${calle}, ${cp}, ${localidad}, ${provincia}`);
    }
}
