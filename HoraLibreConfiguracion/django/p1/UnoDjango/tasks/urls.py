from django.urls import path
from . import views

urlpatterns = [
    path("", views.home, name="tasks-home"),
    path("task/<int:task_id>/", views.task_detail, name="task-detail"),
]
