package senai._9.com.br.Bom.Cafe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import senai._9.com.br.Bom.Cafe.model.Usuario;
import senai._9.com.br.Bom.Cafe.repository.UsuarioRepository;

@Controller
@RequiredArgsConstructor
public class CadastroController {
    private final UsuarioRepository repository;

    @PostMapping("/cadastro")
    public String cadastrar(@ModelAttribute Usuario usuario, RedirectAttributes redirect) {
        if (repository.findByEmail(usuario.getEmail()).isPresent()) {
            redirect.addFlashAttribute("erro", "Este e-mail já está cadastrado.");
            return "redirect:/cadastro";
        }
        repository.save(usuario);
        redirect.addFlashAttribute("sucesso", "Conta criada com sucesso!");
        return "redirect:/login";
    }
}
