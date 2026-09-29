package senai._9.com.br.Bom.Cafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import senai._9.com.br.Bom.Cafe.model.Usuario;
import senai._9.com.br.Bom.Cafe.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PaginaController {
    private final ProdutoRepository produtoRepository;
    @GetMapping({"/", "/index"}) public String index(Model model) { model.addAttribute("produtos", produtoRepository.findAll()); return "index"; }
    @GetMapping("/historia-do-cafe") public String historia() { return "historia-do-cafe"; }
    @GetMapping("/sobre-nos") public String sobre() { return "sobre-nos"; }
    @GetMapping("/login") public String login() { return "login"; }
    @GetMapping("/cadastro") public String cadastro(Model model) { model.addAttribute("usuario", new Usuario()); return "cadastro"; }
}
