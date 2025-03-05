"""
URL configuration for examen project.

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/5.1/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from academia import views


urlpatterns = [
    path('', views.inicio, name='home'),
    path('admin/', admin.site.urls),
    path('estudiantes/', views.estudiante_list, name='estudiante_list'),
    path('inscripciones/', views.inscripcion_list, name='inscripcion_list'),

    # Estudiantes
    path('estudiantes/', views.estudiante_list, name='estudiante_list'),
    path('estudiantes/<int:id>/', views.estudiante_detail, name='estudiante_detail'),
    path('estudiantes/nuevo/', views.estudiante_create, name='estudiante_create'),
    path('estudiantes/<int:id>/editar/', views.estudiante_edit, name='estudiante_edit'),
    path('estudiantes/<int:id>/borrar/', views.estudiante_delete, name='estudiante_delete'),
    
    # Inscripciones
    path('inscripciones/', views.inscripcion_list, name='inscripcion_list'),
    path('inscripciones/<int:id>/', views.inscripcion_detail, name='inscripcion_detail'),
    path('inscripciones/nuevo/', views.inscripcion_create, name='inscripcion_create'),
    path('inscripciones/<int:id>/editar/', views.inscripcion_edit, name='inscripcion_edit'),
    path('inscripciones/<int:id>/borrar/', views.inscripcion_delete, name='inscripcion_delete'),
]
handler404 = 'academia.views.error_404'