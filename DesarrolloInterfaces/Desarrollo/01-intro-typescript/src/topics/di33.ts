// La interfaz Alumno con todos sus datos
interface Alumno
{
    nombre: string,
    apellidos: string,
    nacionalidad: string,
    anioNacimieno: number,
    telefono: string,
    email: string,
    modulosMatriculados: string[],
    convalidaFOLEIE: boolean,
    nivelPadel?: "Bajo" | "Medio" | "Alto" | "Profesional",
    centroProcedencia?: string
}

// Creo el primer alumno
let alumno1: Alumno =
{
    nombre: "Oscar",
    apellidos: "Montero Hinojosa",
    nacionalidad: "España",
    anioNacimieno: 2002,
    telefono: "+34666777888",
    email: "oscar@gmail.com",
    modulosMatriculados: ["EIE", "DI", "SGE", "HLC", "AD", "PSP", "PMDM"],
    convalidaFOLEIE: false,
    nivelPadel: "Bajo",
    centroProcedencia: "IES Los Manantiales"
}

// Creo el segundo alumno
let alumno2: Alumno =
{
    nombre: "Daniel",
    apellidos: "Janssen",
    nacionalidad: "España",
    anioNacimieno: 2002,
    telefono: "+34888777666",
    email: "dani@gmail.com",
    modulosMatriculados: ["EIE", "DI", "SGE", "HLC", "AD", "PSP", "PMDM"],
    convalidaFOLEIE: false,
    centroProcedencia: "IES Los Manantiales"
}

// Muestro los alumnos
console.table(alumno1);
console.table(alumno2);
export {};