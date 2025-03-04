from django.shortcuts import render

# importamos los modelos a utilizar
from core.models import Author, Book


def home(request):
    # autor=Author.objects.all() #Aquí tendría a los autores pero hay que enviarlos al index.html
    book = Book.objects.all()
    # return render(request,'index.html',{'authors':autor}) #haciendo uso del diccionario, enviamos el contenido de la tabla author a la nuestro plantilla index.html
    return render(request, "index.html", {"books": book})
