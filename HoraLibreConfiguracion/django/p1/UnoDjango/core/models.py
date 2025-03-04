from django.db import models


# Create your models here.
# Al aplicar esta herencia, Django va a saber que Author es una tabla en la BD
class Author(models.Model):
    name = models.CharField(
        verbose_name="Nombre", max_length=100, default=""  # etiqueta dentro de la tabla
    )
    last_name = models.CharField(verbose_name="Apellido", max_length=150, default="")
    age = models.PositiveSmallIntegerField(
        verbose_name="Edad",
    )

    # Añadimos este código para que en el administrador al acceder a los registros que contiene la tabla aparezca no Author object ID, sino que nos de más información del registro que hay dentro.
    # Con el método __str__, sobreescribimos la información por defecto.Indicamos ahí lo que queramos que nos retorne
    # Ojo, está dentro de la clase
    def __str__(self):
        return f"{self.name} {self.last_name} {self.age}"
        # return self.name


class Book(models.Model):
    title = models.CharField("Título del libro", max_length=255, unique=True)
    # Unique=True , Django no va a permitir introducir dos libros con títulos iguales. Cuando cree este campo en la BD solo permitir valores diferentes.
    cod = models.CharField("Codigo", max_length=15, unique=True)
    author = models.OneToOneField(Author, on_delete=models.CASCADE)
    # Cascade ,de esta forma indicamos que cuando se borre un autor en la Bd, también se borrará el libro.
    
    # Relación uno a uno
    #author = models.OneToOneField(Author, on_delete=models.SET_NULL, null=True)

    # Relación uno a mucho. Un autor puede tener varios libros, pero libro sólo pertenece a un autor.
    # author=models.ForeignKey(Author, on_delete=models.CASCADE)

    # Relación mucho a mucho, se crea una tercera tabla de forma automática.
    author=models.ManyToManyField(Author)
    
    def __str__(self):
            return self.title

    class Meta:
        verbose_name = "Libro"
        verbose_name_plural = "Libros"

        def __str__(self):
            return self.title
