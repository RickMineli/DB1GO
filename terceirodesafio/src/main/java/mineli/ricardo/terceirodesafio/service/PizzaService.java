package mineli.ricardo.terceirodesafio.service;

import mineli.ricardo.terceirodesafio.model.Pizza;
import mineli.ricardo.terceirodesafio.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository repository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Pizza save(Pizza obj) {
        return repository.save(obj);
    }

    public Pizza findById(Long id) {
        Optional<Pizza> obj = repository.findById(id);
        return obj.orElse(null);
    }

    public List<Pizza> findAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Pizza update(Pizza customer) {
        return repository.save(customer);
    }

    public List<Pizza> findOrderPizzas(Long costumerId) {
        String sql = "SELECT * FROM PIZZA P WHERE PIZZA_ORDER_ID=" + costumerId;
        List<Pizza> pizzas = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Pizza pizza = new Pizza(orderService.findById((Long) row.get("PIZZA_ORDER_ID")));
            pizza.setId((Long) (row.get("ID")));
            pizzas.add(pizza);
        }
        return pizzas;
    }
    public Page<Pizza> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        return repository.findAll(pageRequest);
    }
}

