from django.shortcuts import render

# importamos los modelos a utilizar
from core.models import Author, Book
from core.forms import AutorForm

def home(request):
    # autor=Author.objects.all() #Aquí tendría a los autores pero hay que enviarlos al index.html
    # return render(request,'index.html',{'authors':autor}) #haciendo uso del diccionario, enviamos el contenido de la tabla author a la nuestro plantilla index.html
    # book = Book.objects.all()
    # return render(request, "index.html", {"books": book})
    return render(request, "index.html")


def autor_list(request):
    autor = Author.objects.all()
    return render(request, "autor.html", {"authors": autor})


def libro_list(request):
    libro = Book.objects.all()
    return render(request, "libro.html", {"books": libro})

def autor_create (request):
    return render(request, 'create_autor.html', {'autor_form': AutorForm}) #Si tenemos las páginas dentro de alguna carpeta dentro de template, entonces habría que especificar el nombre de la carpeta Ej:
    # return render(request, 'nombre_carpeta/create_autor.html')
    #Hemos añadido el contexto para pasarlo como parámetro a la página create_autor.html