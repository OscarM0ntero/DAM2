type Expediente = {
    curso: string;
    notaMedia: number;
};

type Alumno = {
    nombre: string;
    apellidos: string;
    modulosProfesionales: string[];
    expedientes: Expediente[];
};

const listaAlumnos: Alumno[] = [
    {
        nombre: "Marcos",
        apellidos: "Morales",
        modulosProfesionales: ["Programación", "Bases de Datos", "Redes"],
        expedientes: [
            { curso: "2022-2023", notaMedia: 5.5 },
            { curso: "2023-2024", notaMedia: 7.0 }
        ]
    },
    {
        nombre: "Oscar",
        apellidos: "Montero Hinojosa",
        modulosProfesionales: ["Desarrollo Web", "Sistemas Operativos", "Seguridad"],
        expedientes: [
            { curso: "2022-2023", notaMedia: 8.7 },
            { curso: "2023-2024", notaMedia: 9.1 }
        ]
    },
    {
        nombre: "Daniel",
        apellidos: "Janssen Gil",
        modulosProfesionales: ["Marketing", "Contabilidad", "Recursos Humanos"],
        expedientes: [
            { curso: "2022-2023", notaMedia: 6.5 },
            { curso: "2023-2024", notaMedia: 7.2 }
        ]
    }
];

const [, segundoAlumno] = listaAlumnos;
const { nombre, apellidos, modulosProfesionales, expedientes } = segundoAlumno;
const [primerModulo] = modulosProfesionales;
const [, segundoExpediente] = expedientes;
const { curso, notaMedia } = segundoExpediente;

console.log(`Nombre y Apellidos: ${nombre} ${apellidos}`);
console.log(`Primer Módulo Profesional: ${primerModulo}`);
console.log(`Curso del segundo expediente: ${curso}, Nota Media: ${notaMedia}`);


export {};