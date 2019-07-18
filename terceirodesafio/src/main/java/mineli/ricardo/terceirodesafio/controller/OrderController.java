package mineli.ricardo.terceirodesafio.controller;


import mineli.ricardo.terceirodesafio.dto.OrderDTO;
import mineli.ricardo.terceirodesafio.model.Order;
import mineli.ricardo.terceirodesafio.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@CrossOrigin("*")
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @RequestMapping("/{id}")
    private ResponseEntity<Order> findById(@PathVariable Long id){
        Order obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping
    private ResponseEntity<?> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Order>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="5") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="id") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction) {
        Page<Order> list = service.findPage(page,linesPerPage,orderBy,direction);
        return ResponseEntity.ok().body(list);
    }
}
