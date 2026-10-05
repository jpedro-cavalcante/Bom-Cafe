package senai._9.com.br.Bom.Cafe.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
        name = "pedido_produtos",
        joinColumns = @JoinColumn(name = "pedido_id"),
        inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataPedido;

    @Column
    private LocalDateTime dataFinalizacao;

    @Column(length = 500)
    private String observacoes;

    @PrePersist
    protected void onCreate() {
        if (this.dataPedido == null) {
            this.dataPedido = LocalDateTime.now();
        }
        if (this.status == null) {
            this.status = StatusPedido.EM_ATENDIMENTO;
        }
    }

    public enum StatusPedido {
        EM_ATENDIMENTO("Em atendimento"),
        FINALIZADOS("Finalizados");

        private final String descricao;

        StatusPedido(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }
}
