package senai._9.com.br.Bom.Cafe.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import senai._9.com.br.Bom.Cafe.model.Usuario;
import senai._9.com.br.Bom.Cafe.repository.ProdutoRepository;
import senai._9.com.br.Bom.Cafe.repository.UsuarioRepository;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class PaginaController {
    private final ProdutoRepository produtoRepository;

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll());
        return "index";
    }

    @GetMapping("/historia-do-cafe")
    public String historia() {
        return "historia-do-cafe";
    }

    @GetMapping("/sobre-nos")
    public String sobre() {
        return "sobre-nos";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }
}
