from django.contrib import admin
from django.urls import path
from . import views  # Importo nuestro módulo views, recién creado.

urlpatterns = [
    path("", views.index),
    # indicamos que la vista la queremos mostrar en esa ruta,
    # añadimos a la lista de url, la nuestra, con hola, como nombre de ruta, y nuestro nombre
    # de vista.
    path("admin/", admin.site.urls),
]
