import sys
import glob
import os

def xpm42_to_xpm3(input_file, output_file):
    with open(input_file, "r") as f:
        lines = f.readlines()
    
    if not lines[0].startswith("!XPM42"):
        print(f"Error: {input_file} no es un archivo XPM42 válido.")
        return
    
    # Eliminar la línea de cabecera
    lines.pop(0)
    
    # Restaurar la primera línea del XPM3
    first_line = "/* XPM */\nstatic char * xpm_data[] = {\n" + '"' + lines[0].strip()[:-1] + '",\n'
    lines.pop(0)
    
    # Convertir la sección de colores
    color_section = []
    pixel_section = []
    parsing_pixels = False
    
    for line in lines:
        line = line.strip()
        if not parsing_pixels:
            if line.startswith("/* pixels */"):
                parsing_pixels = True
                continue
            
            # Restaurar formato XPM3
            parts = line.split("#")
            if len(parts) == 2:
                color_code = parts[0].strip()
                hex_color = parts[1][:6]  # Hex sin alpha
                color_entry = f'"{color_code} c #{hex_color}",\n'
                color_section.append(color_entry)
            else:
                color_section.append(f'"{line}",\n')
        else:
            pixel_section.append(f'"{line}",\n')
    
    # Generar el archivo de salida
    with open(output_file, "w") as f:
        f.write(first_line)
        f.writelines(color_section)
        f.write("/* pixels */\n")
        f.writelines(pixel_section)
        f.write("};\n")
    
    print(f"Conversión completa: {output_file}")

# Ejemplo de uso con múltiples archivos
if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Uso: python xpm42_to_xpm3.py '*.xpm42'")
    else:
        input_pattern = sys.argv[1]
        for input_file in glob.glob(input_pattern):
            output_file = os.path.splitext(input_file)[0] + ".xpm"
            xpm42_to_xpm3(input_file, output_file)
