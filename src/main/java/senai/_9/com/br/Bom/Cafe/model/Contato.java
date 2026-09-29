package senai._9.com.br.Bom.Cafe.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contatos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Contato {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false) private String nome;
    @Column(nullable=false) private String email;
    @Column(nullable=false) private String assunto;
    @Column(nullable=false, length=2000) private String mensagem;
}
