from django.contrib import admin
from django.urls import path, include
from . import views  # Importo nuestro módulo views, recién creado.
from core.views import home

urlpatterns = [
    path("", home),  # Si no ponemos nada entre las comillas directamente
    # entra en nuestra página index.html
    path("admin/", admin.site.urls),
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
