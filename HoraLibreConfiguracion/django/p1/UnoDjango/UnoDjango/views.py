from django.http import HttpResponse #Necesario para poder responder al cliente
#con httpresponse, podemos responder con texto plano o con html
#forma de responder al cliente cuando hace un http
def index (request): # El request captura las peticiones de los clientes
	return HttpResponse ("<h1>hola mundo</h1>")
#Con esto hemos habilitado una vista, pero hay que además darla de alta, en urls.py