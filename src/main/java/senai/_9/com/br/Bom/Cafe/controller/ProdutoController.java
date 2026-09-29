package senai._9.com.br.Bom.Cafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import senai._9.com.br.Bom.Cafe.model.Produto;
import senai._9.com.br.Bom.Cafe.repository.ProdutoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public String produtos(Model model) {

        List<Produto> produtos = produtoRepository.findAll();

        // CAFÉS
        model.addAttribute("cafes",
                filtrar(produtos, "Cafés"));

        // BEBIDAS
        model.addAttribute("bebidas",
                filtrar(produtos, "Bebidas"));

        // COMIDAS
        model.addAttribute("comidas",
                filtrar(produtos, "Comidas"));

        // PRODUTOS PARA VENDA
        model.addAttribute("produtosVenda",
                filtrar(produtos, "Produtos para Venda"));

        return "produtos";
    }

    private List<Produto> filtrar(
            List<Produto> produtos,
            String categoria) {

        return produtos.stream()
                .filter(p -> categoria.equals(p.getCategoria()))
                .collect(Collectors.toList());
    }

    @PostMapping("/{id}/selecionar")
    public String selecionarProduto(@PathVariable Long id) {

        produtoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Produto não encontrado"));

        return "redirect:/produtos";
    }
}
