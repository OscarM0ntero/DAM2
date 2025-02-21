from django.http import HttpResponse #Necesario para poder responder al cliente
from django.shortcuts import render #necesario para que funcione la otra forma de visualizar una vista
#con httpresponse, podemos responder con texto plano o con html
#forma de responder al cliente cuando hace un http
def index (request): # El request captura las peticiones de los clientes
	return HttpResponse ("<h1>hola mundo</h1>")
#Con esto hemos habilitado una vista, pero hay que además darla de alta, en urls.py

def home (request): # Pinta una página con render, también hay que darlo de alta en urls.py
	return render(request,'unodjango/index.html') # la página index.html hay que crearla dentro del archivo de configuración de todo proyecto de django, settings.py