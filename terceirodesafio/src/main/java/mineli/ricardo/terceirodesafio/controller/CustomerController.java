package mineli.ricardo.terceirodesafio.controller;

import mineli.ricardo.terceirodesafio.dto.CustomerDTO;
import mineli.ricardo.terceirodesafio.dto.OrderDTO;
import mineli.ricardo.terceirodesafio.model.Customer;
import mineli.ricardo.terceirodesafio.model.Order;
import mineli.ricardo.terceirodesafio.service.CustomerService;
import mineli.ricardo.terceirodesafio.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @Autowired
    private OrderService serviceOrder;

    @RequestMapping("/{id}")
    private ResponseEntity<Customer> findById(@PathVariable Long id){
        Customer obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping
    private ResponseEntity<?> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{customerId}/orders")
    private ResponseEntity<?> findCostumerOrders(@PathVariable Long customerId){
        List<Order> orders = service.findCostumerOrders(customerId);
        return ResponseEntity.ok().body(orders);
    }

    @PostMapping
    private ResponseEntity<Void> insert(@RequestBody CustomerDTO objDTO){
        Customer obj = new Customer(objDTO.getName(),objDTO.getPhoneNumber(),objDTO.getEmail());
        service.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/{customerId}/orders")
    private ResponseEntity<Void> insertOrder(@RequestBody OrderDTO objDTO, @PathVariable Long customerId){
        Order obj = new Order(objDTO.getAdress(), objDTO.getPickup(), service.findById(customerId));
        serviceOrder.save(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    private Customer update(@RequestBody CustomerDTO objDTO, @PathVariable Long id){
        Customer obj = service.findById(id);
        obj.setName(objDTO.getName());
        obj.setPhoneNumber(objDTO.getPhoneNumber());
        obj.setEmail(objDTO.getEmail());
        return service.update(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Customer>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="5") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="id") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction) {
        Page<Customer> list = service.findPage(page,linesPerPage,orderBy,direction);
        return ResponseEntity.ok().body(list);
    }
}
