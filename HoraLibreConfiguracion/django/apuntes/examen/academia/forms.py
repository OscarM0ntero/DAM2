from django import forms
from .models import Curso, Estudiante, Inscripcion


class EstudianteForm(forms.ModelForm):
    class Meta:
        model = Estudiante
        fields = ["nombre", "correo"]


class InscripcionForm(forms.ModelForm):
    class Meta:
        model = Inscripcion
        fields = ["estudiante", "curso"]
