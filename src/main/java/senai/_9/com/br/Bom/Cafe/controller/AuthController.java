package senai._9.com.br.Bom.Cafe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import senai._9.com.br.Bom.Cafe.model.Usuario;
import senai._9.com.br.Bom.Cafe.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "login-modal";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String senha,
            HttpSession session,
            RedirectAttributes redirect) {
        
        var usuario = usuarioRepository.findByEmail(email);
        
        if (usuario.isPresent() && usuario.get().getSenha().equals(senha)) {
            session.setAttribute("usuarioId", usuario.get().getId());
            session.setAttribute("usuarioNome", usuario.get().getNome());
            return "redirect:/perfil";
        } else {
            redirect.addAttribute("erro", "E-mail ou senha inválidos");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
