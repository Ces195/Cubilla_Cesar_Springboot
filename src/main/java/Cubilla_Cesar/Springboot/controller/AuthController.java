package Cubilla_Cesar.Springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Cubilla_Cesar.Springboot.model.Usuario;
import Cubilla_Cesar.Springboot.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final UsuarioRepository usuarioRepository;

    public AuthController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("loginError", true);
        }
        model.addAttribute("usuarioLogin", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute("usuarioLogin") LoginForm form, HttpSession session, Model model) {
        Usuario usuario = usuarioRepository.findByEmail(form.getEmail()).orElse(null);

        if (usuario == null || !Boolean.TRUE.equals(usuario.getActivo()) || form.getPassword() == null) {
            model.addAttribute("loginError", true);
            return "login";
        }

        // Password validación demo (no cifrado) contra columna `password`
        if (!usuario.getPassword().equals(form.getPassword())) {
            model.addAttribute("loginError", true);
            return "login";
        }

        session.setAttribute("userId", usuario.getId());
        session.setAttribute("userNombre", usuario.getNombre());
        return "redirect:/index";
    }

    @PostMapping("/logout")
    public String doLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }

    public static class LoginForm {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}

