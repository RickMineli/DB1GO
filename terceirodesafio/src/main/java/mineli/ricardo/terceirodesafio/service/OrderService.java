package mineli.ricardo.terceirodesafio.service;

import mineli.ricardo.terceirodesafio.exceptions.ObjectNotFoundException;
import mineli.ricardo.terceirodesafio.model.Order;
import mineli.ricardo.terceirodesafio.repository.OrderRepository;
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
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Order save(Order obj) {
        return repository.save(obj);
    }

    public Order findById(Long id) {
        Optional<Order> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(id, Order.class.getSimpleName()));
    }

    public List<Order> findCostumerOrders(Long costumerId) {
        String sql = "SELECT * FROM PIZZA_ORDER P WHERE P.CUSTOMER_ID=" + costumerId;
        List<Order> orders = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Order order = new Order((String)row.get("ADDRESS"),(boolean)row.get("PICKUP"),customerService.findById((Long)row.get("CUSTOMER_ID")));
            order.setId((Long)(row.get("ID")));
            orders.add(order);
        }
        return orders;
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

