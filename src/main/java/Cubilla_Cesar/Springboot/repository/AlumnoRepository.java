package Cubilla_Cesar.Springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Cubilla_Cesar.Springboot.model.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
}

