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
import senai._9.com.br.Bom.Cafe.repository.ProdutoRepository;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class PerfilController {
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;

    @GetMapping("/perfil")
    public String perfil(HttpSession session, Model model) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        
        if (usuarioId == null) {
            return "redirect:/login";
        }

        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("favoritos", new ArrayList<>());
        model.addAttribute("pedidos", new ArrayList<>());

        return "perfil-modal";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
