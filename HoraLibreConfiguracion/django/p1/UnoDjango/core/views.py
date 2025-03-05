from django.shortcuts import render, redirect

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


def autor_create(request):
    if request.method == "GET":
        return render(
            request, "create_autor.html", {"autor_form": AutorForm}
        )  # Si tenemos las páginas dentro de alguna carpeta dentro de template, entonces habría que especificar el nombre de la carpeta return Ej:render(request, 'nombre_carpeta/create_autor.html')
    # Hemos añadido el contexto para pasarlo como parámetro a la página create_autor.html
    if request.method == "POST":
        # Estaremos aqui en esta opción cuando le damos a guardar. Para obtener la información que envía el formulario:
        # EN request.POST es dónde tenemos toda esa información. De hecho si imprimimos esa variable la podemos ver.
        form = AutorForm(data=request.POST)
    # Hemos creado una instancia de un form de Django al que le estamos pasando los datos usando la variable data, de esta manera entiende que se va a registrar una información (que va a ser un diccionario)
    if (
        form.is_valid
    ):  # Realizará de forma automática las validaciones que se han establecido en el modelo
        form.save()  # Guarda en la BD. En pocas lineas hemos validado y guardado.
        return redirect(
            "/autor/"
        )  # Hay que importar el redirect en shortcuts. Debe redireccionar al listado de autores.
    else:
        # Debo volver a crear una instancia del forma para dar la opción de poder escribir otra vez los datos, con los mismos datos que ha introducido para que vea qué cual puede ser el error.
        form = AutorForm(data=request.POST)
        return render(
            request, "create_autor.html", {"autor_form": AutorForm}
        )  # Es como si fuera un GET


def autor_update(request, pk=None):  # Recibe la clave del autor que queremos actualizar.
    autor = Author.objects.get(
        pk=pk
    )  # Nos buscará el registro que coincida con la clave que le pongamos aquí.En el caso en el que no lo encuentre, va a generar un error, con lo que hay que capturar excepción
    # Existe otra opción que es con el filter, pero para grandes cantidades de datos el get es más óptimo
    # autor=Author.objects.filter(pk=pk).first Esta sería la otra forma
    if request.method == "GET":
        author_form = AutorForm(
            instance=autor
        )  # todo form basado en un modelo tiene este atributo que hace referencia a la instancia de un objeto de la BD. Al pasarle este objeto, Django toma los atributos de éste y asociarlo a su campo html correspondiente que le estamos generando con el Form.
        return render(
            request, "update_autor.html", {"author": autor, "author_form": author_form}
        )  # si la página la tenemos dentro de template/core, hay que poner core/update_autor.html
    # Le pasamos también la variable author_form para obtener los valores que deseamos editar.
    if request.method == "POST":
        author_form = AutorForm(
            data=request.POST, instance=autor
        )  # Combinamos obtene información así como asociarla a una instancia. De esta forma Django entiende que es una actualización
    # El resto del proceso será igual que en la creación de autores.
    if author_form.is_valid():
        author_form.save()
        return redirect("/autor/")  # Se realiza igual que en la creación
    else:  # Creo nueva instancia con la misma información que tenía y además pinto la información en el template
        author_form = AutorForm(data=request.POST, instance=autor)
        return render(
            request, "update_autor.html", {"author": autor, "author_form": author_form}
        )
