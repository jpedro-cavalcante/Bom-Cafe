package senai._9.com.br.Bom.Cafe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import senai._9.com.br.Bom.Cafe.model.Contato;
import senai._9.com.br.Bom.Cafe.repository.ContatoRepository;

@Controller
@RequiredArgsConstructor
public class ContatoController {
    private final ContatoRepository repository;

    @GetMapping("/contato") public String contato(Model model) { model.addAttribute("contato", new Contato()); return "contato"; }

    @PostMapping("/contato")
    public String enviar(@ModelAttribute Contato contato, RedirectAttributes redirect) {
        repository.save(contato);
        redirect.addFlashAttribute("sucesso", "Mensagem enviada! Responderemos em breve.");
        return "redirect:/contato";
    }
}
