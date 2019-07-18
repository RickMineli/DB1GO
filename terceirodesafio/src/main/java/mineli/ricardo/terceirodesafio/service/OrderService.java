package mineli.ricardo.terceirodesafio.service;

import mineli.ricardo.terceirodesafio.model.Order;
import mineli.ricardo.terceirodesafio.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public Order save(Order obj) {
        return repository.save(obj);
    }

    public Order findById(Long id) {
        Optional<Order> obj = repository.findById(id);
        return obj.orElse(null);
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Order update(Order customer) {
        return repository.save(customer);
    }

    public Page<Order> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        return repository.findAll(pageRequest);
    }
}

