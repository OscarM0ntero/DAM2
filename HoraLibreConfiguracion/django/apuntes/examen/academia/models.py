from django.db import models


# Create your models here.
class Curso(models.Model):
    nombre = models.CharField(max_length=150)
    descripcion = models.TextField()

    def __str__(self):
        return self.nombre


class Estudiante(models.Model):
    nombre = models.CharField(max_length=100)
    correo = models.EmailField(unique=True)

    def __str__(self):
        return self.nombre


class Inscripcion(models.Model):
    estudiante = models.ForeignKey(Estudiante, on_delete=models.CASCADE)
    curso = models.ForeignKey(Curso, on_delete=models.CASCADE)
    fecha_inscripcion = models.DateField(auto_now_add=True)

    def __str__(self):
        return f"{self.estudiante} {self.curso} {self.fecha_inscripcion}"
