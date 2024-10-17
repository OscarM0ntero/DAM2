/*
    ===== Código de TypeScript =====
*/
// He creado la interfaz Address para poder guardar la dirección
interface Address {
    calle: string,
    pais: string,
    ciudad: string,
}

// He creado la interfaz SuperHero para poder declarar la constante de este tipo
interface SuperHero
{
    name: String,
    age: number,
    address: Address,
    showAddress(): string   // He indicado la función de tipo string dentro de la interfaz
}

const superHeroe: SuperHero = {
    name: 'Spiderman',
    age: 30,
    address: {
        calle: 'Main St',
        pais: 'USA',
        ciudad: 'NY'
    },
    showAddress() {
        return this.name + ', ' + this.address.ciudad + ', ' + this.address.pais;
    }
}


const address = superHeroe.showAddress();
console.log( address );

export {};