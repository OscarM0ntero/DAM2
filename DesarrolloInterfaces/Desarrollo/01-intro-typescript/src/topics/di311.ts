class Persona 
{
    nombre: string;
    apellidos: string;
    edad: number;
    localidad: string;
    sexo?: string;

    constructor(nombre: string, apellidos: string, edad: number, localidad: string, sexo?: string) 
	{
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.localidad = localidad;
        this.sexo = sexo;
    }

    mostrarDatos(): string 
	{
        return `Nombre: ${this.nombre}, Apellidos: ${this.apellidos}, Edad: ${this.edad}, Localidad: ${this.localidad}, Sexo: ${this.sexo ? this.sexo : 'No especificado'}`;
    }
}

class Profesor extends Persona 
{
    centro: string;
    especialidad: string;
    departamento: string;

    constructor(nombre: string, apellidos: string, edad: number, localidad: string, centro: string, especialidad: string, departamento: string, sexo?: string) 
	{
        super(nombre, apellidos, edad, localidad, sexo);
        this.centro = centro;
        this.especialidad = especialidad;
        this.departamento = departamento;
    }

    mostrarDatos(): string 
	{
        return `${super.mostrarDatos()}, Centro: ${this.centro}, Especialidad: ${this.especialidad}, Departamento: ${this.departamento}`;
    }
}

class Alumno extends Persona 
{
    centro: string;
    curso: string;
    asignaturas: string[];

    constructor(nombre: string, apellidos: string, edad: number, localidad: string, centro: string, curso: string, asignaturas: string[], sexo?: string) 
	{
        super(nombre, apellidos, edad, localidad, sexo);
        this.centro = centro;
        this.curso = curso;
        this.asignaturas = asignaturas;
    }

    mostrarDatos(): string 
	{
        return `${super.mostrarDatos()}, Centro: ${this.centro}, Curso: ${this.curso}, Asignaturas: ${this.asignaturas.join(', ')}`;
    }
}

const profesor = new Profesor
(
    'Juan',
    'Pérez',
    40,
    'Madrid',
    'IES Ejemplo',
    'Matemáticas',
    'Ciencias'
);

const alumno = new Alumno
(
    'Ana',
    'Gómez',
    18,
    'Madrid',
    'IES Ejemplo',
    '2º Bachillerato',
    ['Matemáticas', 'Física', 'Química']
);

console.log(profesor.mostrarDatos());
console.log(alumno.mostrarDatos());
