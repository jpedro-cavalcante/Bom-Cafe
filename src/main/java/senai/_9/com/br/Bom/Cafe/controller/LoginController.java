package senai._9.com.br.Bom.Cafe.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import senai._9.com.br.Bom.Cafe.model.Usuario;
import senai._9.com.br.Bom.Cafe.repository.UsuarioRepository;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UsuarioRepository repository;

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login-modal";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String senha,
                        HttpSession session,
                        RedirectAttributes redirect) {
        return repository.findByEmail(email)
                .filter(u -> u.getSenha().equals(senha))
                .map(u -> {
                    session.setAttribute("usuarioId", u.getId());
                    session.setAttribute("usuarioNome", u.getNome());
                    redirect.addFlashAttribute("sucesso", "Login realizado com sucesso!");
                    return "redirect:/perfil";
                })
                .orElseGet(() -> {
                    redirect.addFlashAttribute("erro", "E-mail ou senha inválidos.");
                    return "redirect:/login";
                });
    }
}
