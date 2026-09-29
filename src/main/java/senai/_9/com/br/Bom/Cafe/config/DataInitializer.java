package senai._9.com.br.Bom.Cafe.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import senai._9.com.br.Bom.Cafe.model.Produto;
import senai._9.com.br.Bom.Cafe.repository.ProdutoRepository;
import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final ProdutoRepository repository;

    @Override public void run(String... args) {
        if (repository.count() > 0) return;
        repository.save(Produto.builder().nome("Cerrado Mineiro").descricao("Chocolate, caramelo, castanha").torra("Torra média").preco(new BigDecimal("42.00")).build());
        repository.save(Produto.builder().nome("Alta Mogiana").descricao("Nozes, mel, corpo sedoso").torra("Torra média-escura").preco(new BigDecimal("46.00")).build());
        repository.save(Produto.builder().nome("Sul de Minas").descricao("Doce, amêndoas, final limpo").torra("Torra média").preco(new BigDecimal("38.00")).build());
        repository.save(Produto.builder().nome("Chapada Diamantina").descricao("Frutas amarelas, floral").torra("Torra clara").preco(new BigDecimal("54.00")).build());
        repository.save(Produto.builder().nome("Espírito Santo").descricao("Cacau, especiarias").torra("Torra escura").preco(new BigDecimal("40.00")).build());
        repository.save(Produto.builder().nome("Matas de Rondônia").descricao("Melaço, corpo intenso").torra("Torra escura").preco(new BigDecimal("36.00")).build());
    }
}
