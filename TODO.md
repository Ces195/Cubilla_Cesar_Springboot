# TODO - Web Java (Spring Boot + Maven) con MySQL

## Objetivo
Crear una web Spring Boot conectada a MySQL `registro_alumnos`, con tablas `usuarios` y `alumnos`, donde **un usuario carga muchos alumnos** (1 usuario -> N alumnos) e implementar CRUD para **ambos**.

## Plan ejecutable
- [ ] 1) Actualizar `pom.xml` si es necesario (dependencias para web/MVC/JPA ya están; validar).
- [x] 2) Configurar conexión MySQL en `src/main/resources/application.properties`.

- [x] 3) Crear entidades JPA `Usuario` y `Alumno` con relación @OneToMany/@ManyToOne.

- [x] 4) Crear repositorios `UsuarioRepository` y `AlumnoRepository`.

- [x] 5) Crear controladores MVC para CRUD (listar + crear + eliminar; editable agregado luego si quieres):
  - [x] Usuarios
  - [x] Alumnos

- [ ] 6) Crear vistas Thymeleaf en `src/main/resources/templates/`.
- [ ] 7) Verificar arranque y comportamiento (mvnw spring-boot:run) y revisar que genere/actualice tablas.

