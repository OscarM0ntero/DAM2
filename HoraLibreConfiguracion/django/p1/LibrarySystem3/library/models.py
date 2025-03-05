from django.db import models

class Author(models.Model):
    first_name = models.CharField(max_length=100)
    last_name = models.CharField(max_length=100)
    birth_date = models.DateField()
    
    def __str__(self):
        return f"{self.first_name} {self.last_name}"
    
class Publisher(models.Model):
    name = models.CharField(max_length=100)
    address = models.CharField(max_length=200)
    city = models.CharField(max_length=100)
    state_province = models.CharField(max_length=100)
    country = models.CharField(max_length=100)
    website = models.URLField()
    
class Book(models.Model):
    title = models.CharField(max_length=200)
    authors = models.ManyToManyField(Author)
    publisher = models.ForeignKey(Publisher, on_delete=models.CASCADE)
    publication_date = models.DateField()
    
    def __str__(self):
        authors = ", ".join([str(author) for author in self.authors.all()])
        return f"{self.title} por {authors}"
    
class Loan(models.Model):
    book = models.ForeignKey(Book, on_delete=models.CASCADE)
    borrower = models.CharField(max_length=100)
    loan_date = models.DateField()
    return_date = models.DateField(null=True, blank=True)
