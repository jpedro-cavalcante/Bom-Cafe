package senai._9.com.br.Bom.Cafe.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import senai._9.com.br.Bom.Cafe.model.Usuario;
import senai._9.com.br.Bom.Cafe.repository.UsuarioRepository;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class PerfilController {
    private final UsuarioRepository usuarioRepository;

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
}
