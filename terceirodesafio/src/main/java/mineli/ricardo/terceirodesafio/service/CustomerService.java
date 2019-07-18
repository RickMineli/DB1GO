package mineli.ricardo.terceirodesafio.service;

import mineli.ricardo.terceirodesafio.model.Customer;
import mineli.ricardo.terceirodesafio.model.Order;
import mineli.ricardo.terceirodesafio.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Customer save(Customer obj) {
        return repository.save(obj);
    }

    public Customer findById(Long id) {
        Optional<Customer> obj = repository.findById(id);
        return obj.orElse(null);
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Customer update(Customer customer) {
        return repository.save(customer);
    }

    public Page<Customer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        return repository.findAll(pageRequest);
    }

    public List<Order> findCostumerOrders(Long costumerId) {
        String sql = "SELECT  PIZZA_ORDER.* FROM CUSTOMER INNER JOIN PIZZA_ORDER ON CUSTOMER.ID=PIZZA_ORDER.CUSTOMER_ID WHERE PIZZA_ORDER.CUSTOMER_ID=" + costumerId;
        List<Order> orders = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Order order = new Order((String)row.get("ADDRESS"),(boolean)row.get("PICKUP"),repository.findById((Long)row.get("CUSTOMER_ID")).get());
            order.setId((Long)(row.get("ID")));
            orders.add(order);
        }
        return orders;
    }
}
