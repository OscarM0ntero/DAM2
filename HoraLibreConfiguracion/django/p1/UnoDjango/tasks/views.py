from django.shortcuts import render

# Create your views here.
tasks = [
    {
        "id": 1,
        "title": "Aprender Django",
        "description": "Estudiar los fundamentos de Django",
    },
    {
        "id": 2,
        "title": "Practicar Python",
        "description": "Resolver ejercicios de Python",
    },
    {
        "id": 3,
        "title": "Leer Documentación",
        "description": "Leer la documentación oficial de Django",
    },
]


def home(request):
    context = {"tasks": tasks}
    return render(request, "tasks/home.html", context)


def task_detail(request, task_id):
    task = next((task for task in tasks if task["id"] == task_id), None)
    if task:
        return render(request, "tasks/task_detail.html", {"task": task})
    else:
        return render(request, "tasks/task_not_found.html", status=404)
