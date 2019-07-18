package mineli.ricardo.terceirodesafio.repository;

import mineli.ricardo.terceirodesafio.model.Customer;
import mineli.ricardo.terceirodesafio.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
