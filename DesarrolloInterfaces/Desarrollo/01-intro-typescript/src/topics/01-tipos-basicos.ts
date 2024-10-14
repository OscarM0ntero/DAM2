console.log('01-tipos-basicos')
const nombre: string = "Goku";
let puntosVida: number | string = 100;
let puntosVida2: number | "FULL" = 100;

puntosVida = "FULL";
puntosVida = 90;

console.log(nombre, puntosVida, puntosVida2);

export {};