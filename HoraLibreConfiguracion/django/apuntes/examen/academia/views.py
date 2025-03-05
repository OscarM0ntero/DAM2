from django.shortcuts import render, get_object_or_404, redirect
from academia.forms import EstudianteForm, InscripcionForm
from academia.models import Curso, Estudiante, Inscripcion

# Create your views here.

# Menú con opciones
def inicio(request):
    return render(request, 'inicio.html', {
        'sections': [
            {'name': 'Estudiantes', 'url': 'estudiante_list'},
            {'name': 'Inscripciones', 'url': 'inscripcion_list'},
        ]
    })

# Vista de la lista de estudiantes
def estudiante_list(request):
    estudiantes = Estudiante.objects.all()
    return render(request, 'estudiante_list.html', {'estudiantes': estudiantes})

# Vista del detalle de un estudiante
def estudiante_detail(request, id):
    estudiante = get_object_or_404(Estudiante, id=id)
    #cursos = Curso.objects.filter(id in Inscripcion.objects.filter(estudiante=estudiante).get(id))
    return render(request, 'estudiante_detail.html', {'estudiante': estudiante})

# Vista para agregar un nuevo estudiante
def estudiante_create(request):
    if request.method == 'POST':
        form = EstudianteForm(request.POST)
        if form.is_valid():
            form.save()
            return redirect('estudiante_list')
    else:
        form = EstudianteForm()
    return render(request, 'estudiante_form.html', {'form': form})

# Vista para editar un estudiante
def estudiante_edit(request, id):
    estudiante = get_object_or_404(Estudiante, id=id)
    if request.method == 'POST':
        form = EstudianteForm(request.POST, instance=estudiante)
        if form.is_valid():
            form.save()
            return redirect('estudiante_detail', id=estudiante.id)
    else:
        form = EstudianteForm(instance=estudiante)
    return render(request, 'estudiante_form.html', {'form': form})

# Vista para eliminar un estudiante
def estudiante_delete(request, id):
    estudiante = get_object_or_404(Estudiante, id=id)
    estudiante.delete()
    return redirect('estudiante_list')

# Vista de detalle de inscripcion
def inscripcion_detail(request, id):
    inscripcion = get_object_or_404(Inscripcion, id=id)
    return render(request, 'inscripcion_detail.html', {'inscripcion': inscripcion})

# Vista para registrar una nueva inscripcion
def inscripcion_create(request):
    if request.method == 'POST':
        form = InscripcionForm(request.POST)
        if form.is_valid():
            form.save()
            return redirect('inscripcion_list')
    else:
        form = InscripcionForm()
    return render(request, 'inscripcion_form.html', {'form': form})

# Vista para eliminar una inscripcion
def inscripcion_delete(request, id):
    inscripcion = get_object_or_404(Inscripcion, id=id)
    inscripcion.delete()
    return redirect('inscripcion_list')

# Vista para editar una inscripcion
def inscripcion_edit(request, id):
    inscripcion = get_object_or_404(Inscripcion, id=id)
    if request.method == 'POST':
        form = InscripcionForm(request.POST, instance=inscripcion)
        if form.is_valid():
            form.save()
            return redirect('inscripcion_list')
    else:
        form = InscripcionForm(instance=inscripcion)
    return render(request, 'inscripcion_form.html', {'form': form})

# Vista de la lista de inscripciones
def inscripcion_list(request):
    inscripciones = Inscripcion.objects.all()
    return render(request, 'inscripcion_list.html', {'inscripciones': inscripciones})

#404 
def error_404(request, exception):
    return render(request, '404.html', status=404)