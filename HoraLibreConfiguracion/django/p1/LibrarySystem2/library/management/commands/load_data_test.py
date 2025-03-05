from django.core.management.base import BaseCommand
from django.utils import timezone
from library.models import Author, Publisher, Book, Loan
from datetime import timedelta
from faker import Faker
import random

class Command(BaseCommand):
    help = 'Carga datos de prueba en la base de datos'

    def handle(self, *args, **kwargs):
        try:
            # Limpiar datos existentes
            self.stdout.write('Limpiando datos existentes...')
            Loan.objects.all().delete()
            Book.objects.all().delete()
            Author.objects.all().delete()
            Publisher.objects.all().delete()

            # Crear una instancia de Faker
            fake = Faker()

            # Crear editoriales
            self.stdout.write('Creando editoriales...')
            publishers = []
            for _ in range(5):  # Crear 5 editoriales aleatorias
                publisher = Publisher(
                    name=fake.company(),
                    address=fake.address(),
                    city=fake.city(),
                    state_province=fake.state(),
                    country=fake.country(),
                    website=fake.url()
                )
                publishers.append(publisher)
            Publisher.objects.bulk_create(publishers)

            # Crear autores
            self.stdout.write('Creando autores...')
            authors = []
            for _ in range(10):  # Crear 10 autores aleatorios
                author = Author(
                    first_name=fake.first_name(),
                    last_name=fake.last_name(),
                    birth_date=fake.date_of_birth(minimum_age=30, maximum_age=80)
                )
                authors.append(author)
            Author.objects.bulk_create(authors)

            # Crear libros
            self.stdout.write('Creando libros...')
            books = []
            for _ in range(30):  # Crear 30 libros aleatorios
                publisher = random.choice(publishers)
                author = random.choice(authors)
                book = Book(
                    title=fake.bs(),  # Título aleatorio
                    publisher=publisher,
                    publication_date=fake.date_this_century(),
                )
                book.save()  # Guardamos el libro primero para luego agregar autores
                book.authors.add(author)
                books.append(book)
            
            # Crear préstamos
            self.stdout.write('Creando préstamos...')
            loans = []
            for book in books:
                # Generar préstamos aleatorios para cada libro
                for _ in range(random.randint(1, 3)):  # Cada libro tiene entre 1 y 5 préstamos
                    loan_date = fake.date_this_year()
                    # Decidir aleatoriamente si el préstamo está pendiente o tiene fecha de devolución
                    if random.choice([True, False]):  # Si True, préstamo sin fecha de devolución
                        return_date = None  # Préstamo pendiente
                    else:
                        return_date = loan_date + timedelta(days=random.randint(5, 30))  # Fecha de devolución aleatoria
                    
                    loan = Loan(
                        book=book,
                        borrower=fake.name(),
                        loan_date=loan_date,
                        return_date=return_date  # Puede ser None si el préstamo está pendiente
                    )
                    loans.append(loan)

            Loan.objects.bulk_create(loans)

            self.stdout.write(self.style.SUCCESS('Datos de prueba cargados exitosamente'))

        except Exception as e:
            self.stdout.write(self.style.ERROR(f'Error al cargar datos: {str(e)}'))
