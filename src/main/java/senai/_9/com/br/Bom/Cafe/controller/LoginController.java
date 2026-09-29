package senai._9.com.br.Bom.Cafe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import senai._9.com.br.Bom.Cafe.repository.UsuarioRepository;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UsuarioRepository repository;

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String senha, RedirectAttributes redirect) {
        return repository.findByEmail(email)
                .filter(u -> u.getSenha().equals(senha))
                .map(u -> { redirect.addFlashAttribute("sucesso", "Login realizado com sucesso!"); return "redirect:/"; })
                .orElseGet(() -> { redirect.addFlashAttribute("erro", "E-mail ou senha inválidos."); return "redirect:/login"; });
    }
}
