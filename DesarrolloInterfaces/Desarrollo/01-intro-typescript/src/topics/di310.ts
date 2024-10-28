interface Expediente
{
    curso: string;
    notaMedia: number;
};

export class Estudiante
{
	constructor
	(
		public nombre: string,
		public apellidos: string,
		public edad: number,
		public localidad: string,
		public expediente: Expediente[],
		public sexo?: string
	)
	{};
}

const estudiante1 = new Estudiante
(
	'Oscar',
	'Montero Hinojosa',
	21,
	'Torremolinos',
	[
        { curso: "2022-2023", notaMedia: 7.8 },
        { curso: "2023-2024", notaMedia: 8.9 }
	],
	'Masculino'
);

const estudiante2 = new Estudiante
(
	'Juana',
	'De Arco',
	16,
	'Francia',
	[
        { curso: "2022-2023", notaMedia: 7.8 },
        { curso: "2023-2024", notaMedia: 8.9 }
	],
	'Femenino'
);

console.log(estudiante1);
console.log(estudiante2);

export class Instituto
{
	constructor
	(
		public centro: string,
		public localidad: string,
		public estudiante: Estudiante[],
	)
	{};
}

const insti = new Instituto
(
	'IES Playamar',
	'Torremolinos',
	[estudiante1, estudiante2]
);

console.log(insti);
