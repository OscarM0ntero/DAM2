"""
URL configuration for LibrarySystem project.

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
from django.views.generic import RedirectView
from library import views
from django.conf.urls import handler404

urlpatterns = [
    # Home
    path('', views.home),
    path('home/', views.home, name='home'),
    
    # Admin
    path('admin/', admin.site.urls),
 
    # Autores
    path('authors/', views.author_list, name='author_list'),
    path('authors/<int:id>/', views.author_detail, name='author_detail'),
    path('authors/new/', views.author_create, name='author_create'),
    path('authors/<int:id>/edit/', views.author_edit, name='author_edit'),
    path('authors/<int:id>/delete/', views.author_delete, name='author_delete'),
    
    # Libros
    path('books/', views.book_list, name='book_list'),
    path('books/<int:id>/', views.book_detail, name='book_detail'),
    path('books/new/', views.book_create, name='book_create'),
    path('books/<int:id>/edit/', views.book_edit, name='book_edit'),
    path('books/<int:id>/delete/', views.book_delete, name='book_delete'),
    
    # Préstamos
    path('loans/', views.loan_list, name='loan_list'),
    path('loans/<int:id>/', views.loan_detail, name='loan_detail'),
    path('loans/new/', views.loan_create, name='loan_create'),
    path('loans/<int:id>/edit/', views.loan_edit, name='loan_edit'),
    path('loans/<int:id>/delete/', views.loan_delete, name='loan_delete'),

    # Editoriales
    path('publishers/', views.publisher_list, name='publisher_list'),
    path('publishers/new/', views.publisher_create, name='publisher_create'),
    path('publishers/<int:id>/edit/', views.publisher_edit, name='publisher_edit'),
    path('publishers/<int:id>/delete/', views.publisher_delete, name='publisher_delete'),
    path('publishers/<int:id>/', views.publisher_detail, name='publisher_detail'),
    
]
handler404 = 'library.views.error_404'