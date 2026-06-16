package Cubilla_Cesar.Springboot.security;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();

        // Permitir accesos públicos
        boolean isPublic = path.startsWith("/login") || path.equals("/") || path.equals("/index") || path.startsWith("/css") || path.startsWith("/js") || path.startsWith("/images");

        HttpSession session = req.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("userId") != null;

        if (path.startsWith("/usuarios") || path.startsWith("/alumnos")) {
            if (!loggedIn) {
                res.sendRedirect("/login?error=1");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
