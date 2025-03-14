export interface Alumno {
    id: string;
    nombre: string;
    apellidos: string;
    fecha_nacimiento: string;
    linkedin?: string;
    nivel_ingles?: 'A1' | 'A2' | 'B1' | 'B2' | 'C1' | 'C2';
    minusvalia?: number;
    otra_formacion?: string;
    id_unidad_centro: number;
  }
  