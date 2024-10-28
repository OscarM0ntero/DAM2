export class Persona
{
	public nombre: string;
	public direccion: string;
	public altura?: number;
	public edad: number;

	constructor()
	{
		this.nombre = 'Oscar';
		this.direccion = 'Torremolinos';
		this.edad = 21;
	};
}

const goku = new Persona();

console.log(goku);