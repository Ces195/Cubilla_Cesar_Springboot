package Cubilla_Cesar.Springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Cubilla_Cesar.Springboot.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByUsername(String username);

}

