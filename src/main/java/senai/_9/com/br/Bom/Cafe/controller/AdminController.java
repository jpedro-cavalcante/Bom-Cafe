package senai._9.com.br.Bom.Cafe.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import senai._9.com.br.Bom.Cafe.model.Pedido;
import senai._9.com.br.Bom.Cafe.model.Usuario;
import senai._9.com.br.Bom.Cafe.repository.PedidoRepository;
import senai._9.com.br.Bom.Cafe.repository.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    /**
     * Verifica se o usuário tem permissão de gerente
     */
    private boolean isGerente(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        return usuario != null && usuario.getRole() == Usuario.Role.GERENTE;
    }

    /**
     * Dashboard administrativo - exibe estatísticas gerais
     */
    @GetMapping
    public String dashboard(Model model, HttpSession session) {
        if (!isGerente(session)) {
            return "redirect:/login";
        }

        long totalPedidos = pedidoRepository.count();
        long pedidosEmAtendimento = pedidoRepository.findByStatus(Pedido.StatusPedido.EM_ATENDIMENTO).size();
        long pedidosFinalizados = pedidoRepository.findByStatus(Pedido.StatusPedido.FINALIZADOS).size();

        model.addAttribute("totalPedidos", totalPedidos);
        model.addAttribute("pedidosEmAtendimento", pedidosEmAtendimento);
        model.addAttribute("pedidosFinalizados", pedidosFinalizados);

        return "admin/dashboard";
    }

    /**
     * Lista todos os pedidos em atendimento
     */
    @GetMapping("/pedidos/em-atendimento")
    public String pedidosEmAtendimento(Model model, HttpSession session) {
        if (!isGerente(session)) {
            return "redirect:/login";
        }

        List<Pedido> pedidos = pedidoRepository.findByStatus(Pedido.StatusPedido.EM_ATENDIMENTO);
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("statusAtivo", "em-atendimento");

        return "admin/pedidos";
    }

    /**
     * Lista todos os pedidos finalizados
     */
    @GetMapping("/pedidos/finalizados")
    public String pedidosFinalizados(Model model, HttpSession session) {
        if (!isGerente(session)) {
            return "redirect:/login";
        }

        List<Pedido> pedidos = pedidoRepository.findByStatus(Pedido.StatusPedido.FINALIZADOS);
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("statusAtivo", "finalizados");

        return "admin/pedidos";
    }

    /**
     * Exibe detalhes de um pedido específico
     */
    @GetMapping("/pedidos/{id}")
    public String detalhePedido(@PathVariable Long id, Model model, HttpSession session) {
        if (!isGerente(session)) {
            return "redirect:/login";
        }

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        model.addAttribute("pedido", pedido);
        return "admin/detalhe-pedido";
    }

    /**
     * Atualiza o status de um pedido para finalizado
     */
    @PostMapping("/pedidos/{id}/finalizar")
    public String finalizarPedido(@PathVariable Long id, 
                                   @RequestParam(required = false) String observacoes,
                                   HttpSession session, 
                                   RedirectAttributes redirectAttributes) {
        if (!isGerente(session)) {
            return "redirect:/login";
        }

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.setStatus(Pedido.StatusPedido.FINALIZADOS);
        pedido.setDataFinalizacao(LocalDateTime.now());
        if (observacoes != null && !observacoes.isEmpty()) {
            pedido.setObservacoes(observacoes);
        }

        pedidoRepository.save(pedido);

        redirectAttributes.addFlashAttribute("sucesso", "Pedido finalizado com sucesso!");
        return "redirect:/admin/pedidos/em-atendimento";
    }

    /**
     * Retorna um pedido para atendimento
     */
    @PostMapping("/pedidos/{id}/reabrir")
    public String reabrirPedido(@PathVariable Long id, 
                                HttpSession session, 
                                RedirectAttributes redirectAttributes) {
        if (!isGerente(session)) {
            return "redirect:/login";
        }

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.setStatus(Pedido.StatusPedido.EM_ATENDIMENTO);
        pedido.setDataFinalizacao(null);

        pedidoRepository.save(pedido);

        redirectAttributes.addFlashAttribute("sucesso", "Pedido reaberido com sucesso!");
        return "redirect:/admin/pedidos/finalizados";
    }

    /**
     * Deleta um pedido
     */
    @PostMapping("/pedidos/{id}/deletar")
    public String deletarPedido(@PathVariable Long id, 
                                HttpSession session, 
                                RedirectAttributes redirectAttributes) {
        if (!isGerente(session)) {
            return "redirect:/login";
        }

        pedidoRepository.deleteById(id);

        redirectAttributes.addFlashAttribute("sucesso", "Pedido removido com sucesso!");
        return "redirect:/admin/pedidos/em-atendimento";
    }

    /**
     * Lista todos os pedidos
     */
    @GetMapping("/pedidos")
    public String todosPedidos(Model model, HttpSession session) {
        if (!isGerente(session)) {
            return "redirect:/login";
        }

        List<Pedido> pedidos = pedidoRepository.findAllByOrderByDataPedidoDesc();
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("statusAtivo", "todos");

        return "admin/pedidos";
    }
}
