from django import forms
from .models import Author, Book, Loan, Publisher

class AuthorForm(forms.ModelForm):
    class Meta:
        model = Author
        fields = ['first_name', 'last_name', 'birth_date']

class BookForm(forms.ModelForm):
    class Meta:
        model = Book
        fields = ['title', 'publisher', 'publication_date', 'authors']

class LoanForm(forms.ModelForm):
    class Meta:
        model = Loan
        fields = ['book', 'borrower', 'loan_date', 'return_date']

class PublisherForm(forms.ModelForm):
    class Meta:
        model = Publisher
        fields = ['name', 'address', 'city', 'state_province', 'country', 'website']