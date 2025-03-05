from django.shortcuts import render, get_object_or_404, redirect
from .models import Author, Publisher, Book, Loan
from .forms import AuthorForm, BookForm, LoanForm, PublisherForm

# Menú con opciones
def home(request):
    return render(request, 'home.html', {
        'sections': [
            {'name': 'Autores', 'url': 'author_list'},
            {'name': 'Libros', 'url': 'book_list'},
            {'name': 'Préstamos', 'url': 'loan_list'},
            {'name': 'Editoriales', 'url': 'publisher_list'},
        ]
    })
    
# Vista de la lista de autores
def author_list(request):
    authors = Author.objects.all()
    return render(request, 'author_list.html', {'authors': authors})

# Vista del detalle de un autor
def author_detail(request, id):
    author = get_object_or_404(Author, id=id)
    books = author.book_set.all()
    return render(request, 'author_detail.html', {'author': author, 'books': books})

# Vista para agregar un nuevo autor
def author_create(request):
    if request.method == 'POST':
        form = AuthorForm(request.POST)
        if form.is_valid():
            form.save()
            return redirect('author_list')
    else:
        form = AuthorForm()
    return render(request, 'author_form.html', {'form': form})

# Vista para editar un autor
def author_edit(request, id):
    author = get_object_or_404(Author, id=id)
    if request.method == 'POST':
        form = AuthorForm(request.POST, instance=author)
        if form.is_valid():
            form.save()
            return redirect('author_detail', id=author.id)
    else:
        form = AuthorForm(instance=author)
    return render(request, 'author_form.html', {'form': form})

# Vista para eliminar un autor
def author_delete(request, id):
    author = get_object_or_404(Author, id=id)
    author.delete()
    return redirect('author_list')

# Vista de la lista de libros
def book_list(request):
    books = Book.objects.all()
    return render(request, 'book_list.html', {'books': books})

# Vista del detalle de un libro
def book_detail(request, id):
    book = get_object_or_404(Book, id=id)
    return render(request, 'book_detail.html', {'book': book})

# Vista para agregar un nuevo libro
def book_create(request):
    if request.method == 'POST':
        form = BookForm(request.POST)
        if form.is_valid():
            form.save()
            return redirect('book_list')
    else:
        form = BookForm()
    return render(request, 'book_form.html', {'form': form})

# Vista para editar un libro
def book_edit(request, id):
    book = get_object_or_404(Book, id=id)
    if request.method == 'POST':
        form = BookForm(request.POST, instance=book)
        if form.is_valid():
            form.save()
            return redirect('book_detail', id=book.id)
    else:
        form = BookForm(instance=book)
    return render(request, 'book_form.html', {'form': form})

# Vista para eliminar un libro
def book_delete(request, id):
    book = get_object_or_404(Book, id=id)
    book.delete()
    return redirect('book_list')


# Vista de la lista de préstamos
def loan_list(request):
    loans = Loan.objects.all()
    return render(request, 'loan_list.html', {'loans': loans})

# Vista de la lista de préstamos
def loan_list(request):
    loans = Loan.objects.all()
    return render(request, 'loan_list.html', {'loans': loans})

# Vista de detalle de editorial
def publisher_detail(request, id):
    publisher = get_object_or_404(Publisher, id=id)
    books = Book.objects.filter(publisher=publisher)
    return render(request, 'publisher_detail.html', {'publisher': publisher, 'books': books})

# Vista de detalle de préstamo
def loan_detail(request, id):
    loan = get_object_or_404(Loan, id=id)
    return render(request, 'loan_detail.html', {'loan': loan})

# Vista para registrar un nuevo préstamo
def loan_create(request):
    if request.method == 'POST':
        form = LoanForm(request.POST)
        if form.is_valid():
            form.save()
            return redirect('loan_list')
    else:
        form = LoanForm()
    return render(request, 'loan_form.html', {'form': form})

# Vista para eliminar un préstamo
def loan_delete(request, id):
    loan = get_object_or_404(Loan, id=id)
    loan.delete()
    return redirect('loan_list')

# Vista para editar un préstamo
def loan_edit(request, id):
    loan = get_object_or_404(Loan, id=id)
    if request.method == 'POST':
        form = LoanForm(request.POST, instance=loan)
        if form.is_valid():
            form.save()
            return redirect('loan_list')
    else:
        form = LoanForm(instance=loan)
    return render(request, 'loan_form.html', {'form': form})

# Vista de la lista de editoriales
def publisher_list(request):
    publishers = Publisher.objects.all()
    return render(request, 'publisher_list.html', {'publishers': publishers})

# Vista para crear una nueva editorial
def publisher_create(request):
    if request.method == 'POST':
        form = PublisherForm(request.POST)
        if form.is_valid():
            form.save()
            return redirect('publisher_list')
    else:
        form = PublisherForm()
    return render(request, 'publisher_form.html', {'form': form})

# Vista para editar una editorial
def publisher_edit(request, id):
    publisher = get_object_or_404(Publisher, id=id)
    if request.method == 'POST':
        form = PublisherForm(request.POST, instance=publisher)
        if form.is_valid():
            form.save()
            return redirect('publisher_list')
    else:
        form = PublisherForm(instance=publisher)
    return render(request, 'publisher_form.html', {'form': form})

# Vista para eliminar una editorial
def publisher_delete(request, id):
    publisher = get_object_or_404(Publisher, id=id)
    publisher.delete()
    return redirect('publisher_list')



#404 
def error_404(request, exception):
    return render(request, '404.html', status=404)