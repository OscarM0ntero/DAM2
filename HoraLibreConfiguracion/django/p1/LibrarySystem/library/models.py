from django.db import models


# Al aplicar esta herencia, Django va a saber que Author es una tabla en la BD
class Author(models.Model):
    first_name = models.CharField(verbose_name="Nombre", max_length=100, default="")
    last_name = models.CharField(verbose_name="Apellido", max_length=150, default="")
    birth_date = models.DateField(verbose_name="Fecha nacimiento")

    def __str__(self):
        return f"{self.first_name} {self.last_name}"

class Publisher(models.Model):
    name = models.CharField(verbose_name="Nombre", max_length=100, default="")
    address = models.CharField(verbose_name="Dirección", max_length=200, default="")
    city = models.CharField(verbose_name="Ciudad", max_length=50, default="")
    state_province = models.CharField(verbose_name="Estado/Provincia", max_length=50, default="")
    country = models.CharField(verbose_name="País", max_length=50, default="")
    website = models.URLField(verbose_name="Website", max_length=200, default="")

    def __str__(self):
        return self.name

class Book(models.Model):
    title = models.CharField(verbose_name="Nombre", max_length=100, default="")
    authors = models.ManyToManyField(Author)
    publisher = models.ForeignKey(Publisher, on_delete=models.CASCADE)
    publication_date = models.DateField(verbose_name="Fecha publicación")

    def __str__(self):
        return self.title
    
class Loan(models.Model):
    book = models.ForeignKey(Book, on_delete=models.CASCADE)
    borrower = models.CharField(verbose_name="Bibliotecario", max_length=100, default="")
    loan_date = models.DateField(verbose_name="Fecha préstamo")
    return_date = models.DateField(verbose_name="Fecha devolución", blank=True, null=True)

    def __str__(self):
        return f"{self.book} {self.borrower} {self.loan_date} {self.return_date}"