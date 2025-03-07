from fastapi import FastAPI, Depends
from sqlalchemy import create_engine, Column, Integer, String, ForeignKey, func
from sqlalchemy.orm import sessionmaker, declarative_base, Session, relationship
from fastapi.staticfiles import StaticFiles
from fastapi.responses import HTMLResponse

import os
BASE_DIR = os.path.dirname(os.path.abspath(__file__))
DATABASE_URL = f"sqlite:///{os.path.join(BASE_DIR, '../biblioteca.db')}"


engine = create_engine(DATABASE_URL, connect_args={"check_same_thread": False})
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
Base = declarative_base()

app = FastAPI()
app.mount("/static", StaticFiles(directory="static"), name="static")

# Modelos
class Lector(Base):
    __tablename__ = "lectores"
    id = Column(Integer, primary_key=True, index=True)
    nombre = Column(String, index=True)
    cod_curso = Column(String, index=True)
    prestamos = relationship("Prestamo", back_populates="lector")

class Libro(Base):
    __tablename__ = "libros"
    id = Column(Integer, primary_key=True, index=True)
    titulo = Column(String, index=True)
    autor = Column(String, index=True)

class Prestamo(Base):
    __tablename__ = "prestamos"
    id = Column(Integer, primary_key=True, index=True)
    id_libro = Column(Integer, ForeignKey("libros.id"))
    id_lector = Column(Integer, ForeignKey("lectores.id"))
    fechaalta = Column(String)
    fechabaja = Column(String, nullable=True)
    lector = relationship("Lector", back_populates="prestamos")

Base.metadata.create_all(bind=engine)

def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

# Servir index.html
@app.get("/", response_class=HTMLResponse)
def serve_index():
    with open("fastAPI/static/index.html", "r") as file:
        return HTMLResponse(content=file.read())

# Endpoint 1: Obtener total de libros prestados por lector
@app.get("/estadisticas/libros-prestados")
def total_libros_prestados(db: Session = Depends(get_db)):
    result = db.query(Lector.nombre, func.count(Prestamo.id).label("total_prestamos"))\
              .join(Prestamo, Lector.id == Prestamo.id_lector)\
              .group_by(Lector.nombre).all()
    return {"data": result}

# Endpoint 2: Actualizar datos de un lector
@app.put("/lectores/{lector_id}")
def actualizar_lector(lector_id: int, nombre: str, cod_curso: str, db: Session = Depends(get_db)):
    lector = db.query(Lector).filter(Lector.id == lector_id).first()
    if not lector:
        return {"error": "Lector no encontrado"}
    lector.nombre = nombre
    lector.cod_curso = cod_curso
    db.commit()
    return {"message": "Lector actualizado correctamente"}

# Endpoint 3: Obtener lista de libros prestados con detalles del lector
@app.get("/libros/prestados")
def libros_prestados(db: Session = Depends(get_db)):
    result = db.query(Libro.titulo, Lector.nombre, Prestamo.fechaalta)\
              .join(Prestamo, Libro.id == Prestamo.id_libro)\
              .join(Lector, Prestamo.id_lector == Lector.id).all()
    return {"data": result}
