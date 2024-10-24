import { Instituto, Alumno, Direccion, Ensenanza } from './t9modulo-instituto';

const direccionAlumno1: Direccion = {
    calle: "Calle Falsa 123",
    cp: "28080",
    localidad: "Madrid",
    provincia: "Madrid"
};

const direccionAlumno2: Direccion = {
    calle: "Avenida Inventada 55",
    cp: "27584",
    localidad: "Málaga",
    provincia: "Málaga"
};

const direccionInstituto: Direccion = {
    calle: "Calle Falsa 321",
    cp: "29620",
    localidad: "Torremolinos",
    provincia: "Málaga"
};

const ensenanza1: Ensenanza = {
    nivel: "ESO",
    esObligatoria: true,
    familiaProfesional: null,
    modalidad: "Mañana"
};

const ensenanza2: Ensenanza = {
    nivel: "Bachillerato",
    esObligatoria: false,
    familiaProfesional: null,
    modalidad: "Tarde"
};

const ensenanza3: Ensenanza = {
    nivel: "FP Grado Medio",
    esObligatoria: false,
    familiaProfesional: "Informática",
    modalidad: "Telemático"
};

const alumno1: Alumno = {
    nombre: "Juan Pérez",
    edad: 16,
    sexo: "Masculino",
    ensenanza: ensenanza1,
    direccion: direccionAlumno1
};

const alumno2: Alumno = {
    nombre: "Ana García",
    edad: 18,
    sexo: "Femenino",
    ensenanza: ensenanza2,
    direccion: direccionAlumno2
};

// Crear instituto
const instituto = new Instituto("Instituto Central", "María López", "912345678", "info@institutocentral.com", direccionInstituto);

// Matricular alumnos
instituto.matricularAlumno(alumno1);
instituto.matricularAlumno(alumno2);

// Mostrar alumnos matriculados
instituto.mostrarAlumnos();

// Mostrar dirección del instituto
instituto.mostrarDireccion();
