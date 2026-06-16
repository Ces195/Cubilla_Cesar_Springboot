package Cubilla_Cesar.Springboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Cubilla_Cesar.Springboot.model.Alumno;
import Cubilla_Cesar.Springboot.model.Usuario;
import Cubilla_Cesar.Springboot.repository.AlumnoRepository;
import Cubilla_Cesar.Springboot.repository.UsuarioRepository;

@Controller
@RequestMapping("/alumnos")
public class AlumnoController {

    private final AlumnoRepository alumnoRepository;
    private final UsuarioRepository usuarioRepository;

    public AlumnoController(AlumnoRepository alumnoRepository, UsuarioRepository usuarioRepository) {
        this.alumnoRepository = alumnoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public String listar(Model model) {
        List<Alumno> alumnos = alumnoRepository.findAll();
        List<Usuario> usuarios = usuarioRepository.findAll();
        model.addAttribute("alumnos", alumnos);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("alumnoForm", new Alumno());
        return "alumnos/list";
    }

    @PostMapping("/crear")
    public String crear(@RequestParam Long usuarioId,
                          @ModelAttribute("alumnoForm") Alumno alumnoForm) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + usuarioId));
        alumnoForm.setUsuario(usuario);
        alumnoRepository.save(alumnoForm);
        return "redirect:/alumnos";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        alumnoRepository.deleteById(id);
        return "redirect:/alumnos";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Alumno no encontrado: " + id));
        model.addAttribute("alumnos", alumnoRepository.findAll());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("alumnoForm", alumno);
        return "alumnos/list";
    }

    @PostMapping("/{id}/actualizar")
    public String actualizar(@PathVariable Long id,
                                @RequestParam Long usuarioId,
                                @ModelAttribute("alumnoForm") Alumno alumnoForm) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + usuarioId));
        alumnoForm.setId(id);
        alumnoForm.setUsuario(usuario);
        alumnoRepository.save(alumnoForm);
        return "redirect:/alumnos";
    }
}

