console.log('02-objeto-interfaz')

interface Personaje
{
    nombre: String,
    pv: number,
    habilidades: string[],
    procedencia?: string
}

let heroe: Personaje =
{
    nombre: 'Goku',
    pv: 100,
    habilidades: ['Fuerza', 'Velocidad'],
    procedencia: 'Namek'
}

console.table(heroe);

export {};