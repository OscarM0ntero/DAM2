from django.contrib import admin
from django.urls import path, include
from . import views  # Importo nuestro módulo views, recién creado.
from core.views import home, autor_list, libro_list, autor_create, autor_update

urlpatterns = [
    path("", home),  # Si no ponemos nada entre las comillas directamente
    # entra en nuestra página index.html
    path("admin/", admin.site.urls),
    path("autor/", autor_list),
    path("libro/", libro_list),
    path ('new-autor/', autor_create),
	path ('update-autor/<int:pk>',autor_update) #Hay que pasarle el pk por parámetro. Para realizarlo se hace entre símbolos de <> indicando en primer lugar el tipo del campo, en este caso int y luego el nombre del campo pk.
]

"""
urlpatterns = [
    path("", views.index),
    # indicamos que la vista la queremos mostrar en esa ruta,
    # añadimos a la lista de url, la nuestra, con hola, como nombre de ruta, y nuestro nombre
    # de vista.
    path('home/', views.home), #nueva vista
    path('admin/', admin.site.urls),
	path('tasks/', include('tasks.urls')),
]
"""
