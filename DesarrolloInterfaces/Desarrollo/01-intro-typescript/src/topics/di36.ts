interface CentroEducativo {
    centro: string,
    localidad: string,
    codigoCentro: number,
    telefono: string
}


interface Matricula {
    modulosProfesionales: string[],
    cicloFormativo: string,
    centroEducativo: CentroEducativo,
}

interface AlumnoFP
{
    nombre: string,
    apellidos: string,
    anioNacimiento: number,
    nacionalidad: string,
    matricula: Matricula
}

const alumno1: AlumnoFP = {
    nombre: 'Oscar',
    apellidos: 'Montero Hinojosa',
    anioNacimiento: 2002,
    nacionalidad: 'Español',
    matricula:
    {
        modulosProfesionales: ['AD', 'SGE', 'DI'],
        cicloFormativo: 'DAM2',
        centroEducativo:
        {
            centro: 'IES Playamar',
            localidad: 'Torremolinos',
            codigoCentro: 55756,
            telefono: '+34952777777'
        }
    }
}

const alumno2: AlumnoFP = {
    nombre: 'Juan',
    apellidos: 'Perez Gomez',
    anioNacimiento: 2001,
    nacionalidad: 'Español',
    matricula:
    {
        modulosProfesionales: ['HLC', 'PSP', 'AD'],
        cicloFormativo: 'DAM2',
        centroEducativo: alumno1.matricula.centroEducativo
    }
}

// Mostrar los objetos en modo tabla
console.table([alumno1, alumno2]);

// Desestructuración para mostrar los datos específicos solicitados
const { nombre, apellidos, matricula: { cicloFormativo, centroEducativo: { centro } } } = alumno1;
console.log(`Alumno: ${nombre} ${apellidos}`);
console.log(`Ciclo Formativo: ${cicloFormativo}`);
console.log(`Centro: ${centro}`);

export {};