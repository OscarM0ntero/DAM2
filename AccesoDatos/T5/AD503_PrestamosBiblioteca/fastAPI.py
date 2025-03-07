from fastapi import FastAPI, Depends, HTTPException
from sqlalchemy import create_engine, Column, Integer, String, Date, ForeignKey
from sqlalchemy.orm import sessionmaker, Session, relationship, declarative_base
from pydantic import BaseModel
from typing import List

DATABASE_URL = "mysql+mysqlconnector://biblioteca_user:biblioteca_pass@localhost:3306/biblioteca"

engine = create_engine(DATABASE_URL)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

Base = declarative_base()

# modelos Alchemy


class Curso(Base):
    __tablename__ = "cursos"
    id = Column(Integer, primary_key=True, index=True)
    descripcion = Column(String)

    lectores = relationship("Lector", back_populates="curso")


class Lector(Base):
    __tablename__ = "lectores"
    id = Column(Integer, primary_key=True, index=True)
    nombre = Column(String)
    nombreLogin = Column(String)
    contrasena = Column(String)
    cod_curso = Column(Integer, ForeignKey("cursos.id"))

    curso = relationship("Curso", back_populates="lectores")
    prestamos = relationship("Prestamo", back_populates="lector")


class Libro(Base):
    __tablename__ = "libros"
    id = Column(Integer, primary_key=True, index=True)
    titulo = Column(String, index=True)
    autor = Column(String)
    paginas = Column(Integer)
    genero = Column(String)
    disponible = Column(Integer)

    prestamos = relationship("Prestamo", back_populates="libro")


class Prestamo(Base):
    __tablename__ = "prestamos"
    id = Column(Integer, primary_key=True, index=True)
    id_libro = Column(Integer, ForeignKey("libros.id"))
    id_lector = Column(Integer, ForeignKey("lectores.id"))
    fechaalta = Column(Date)
    fechabaja = Column(Date)

    libro = relationship("Libro", back_populates="prestamos")
    lector = relationship("Lector", back_populates="prestamos")


Base.metadata.create_all(bind=engine)

app = FastAPI()


# dependencia de la base de datos
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()


class CursoCreate(BaseModel):
    descripcion: str


class LibroBase(BaseModel):
    titulo: str
    autor: str
    paginas: int
    genero: str
    disponible: int


class PrestamoBase(BaseModel):
    id_libro: int
    id_lector: int
    fechaalta: str
    fechabaja: str


class PrestamosPorLibro(BaseModel):
    libro_id: int


class LectorCreate(BaseModel):
    nombre: str
    nombreLogin: str
    contrasena: str
    cod_curso: int


# registrar un nuevo curso
@app.post("/cursos/")
def crear_curso(curso: CursoCreate, db: Session = Depends(get_db)):
    db_curso = Curso(**curso.dict())
    db.add(db_curso)
    db.commit()
    db.refresh(db_curso)
    return db_curso


# consultar todos los libros disponibles
@app.get("/libros/disponibles", response_model=List[LibroBase])
def obtener_libros_disponibles(db: Session = Depends(get_db)):
    libros = db.query(Libro).filter(Libro.disponible > 0).all()
    return libros


# consultar los prestamos de un libro especifico
@app.get("/libros/{libro_id}/prestamos", response_model=List[PrestamoBase])
def obtener_prestamos_por_libro(libro_id: int, db: Session = Depends(get_db)):
    prestamos = db.query(Prestamo).filter(Prestamo.id_libro == libro_id).all()
    if not prestamos:
        raise HTTPException(
            status_code=404, detail="No se encontraron préstamos para este libro"
        )
    return prestamos
