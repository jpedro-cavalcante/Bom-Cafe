package senai._9.com.br.Bom.Cafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senai._9.com.br.Bom.Cafe.model.Pedido;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByStatus(Pedido.StatusPedido status);
    List<Pedido> findAllByOrderByDataPedidoDesc();
}
