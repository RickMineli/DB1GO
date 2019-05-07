package mineli.ricardo.terceirodesafio.repository;

import mineli.ricardo.terceirodesafio.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
