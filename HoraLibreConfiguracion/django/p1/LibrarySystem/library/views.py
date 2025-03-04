from django.shortcuts import render

from library.models import Book, Author, Publisher, Loan

# Create your views here.
def home(request):
    book = Book.objects.all()
    author = Author.objects.all()
    publisher = Publisher.objects.all()
    loan = Loan.objects.all()
    
    return render(request, "index.html", {"books": book, "authors": author, "publishers": publisher, "loans": loan})

def autor(request):
    book = Book.objects.all()
    author = Author.objects.all()
    publisher = Publisher.objects.all()
    loan = Loan.objects.all()
    
    return render(request, "autor.html", {"books": book, "authors": author, "publishers": publisher, "loans": loan})
