package Cubilla_Cesar.Springboot.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RandomDataInitializer {

    // Deshabilitado para evitar fallos al iniciar si MySQL no está listo o si falla el DDL.
    // Si necesitas reactivarlo, elimina el bloque comentado de init() y quita este comentario.

    /*
    @Bean
    CommandLineRunner init(UsuarioRepository usuarioRepository, AlumnoRepository alumnoRepository) {
        return args -> {
            if (usuarioRepository.count() == 0) {
                Usuario u = new Usuario("Usuario Demo", "demo", "1234", "demo@local");
                usuarioRepository.save(u);

                Alumno a1 = new Alumno("Alumno 1", "MAT-001", u);
                Alumno a2 = new Alumno("Alumno 2", "MAT-002", u);

                alumnoRepository.save(a1);
                alumnoRepository.save(a2);
            }
        };
    }
    */
}


