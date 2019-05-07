package mineli.ricardo.terceirodesafio.controller;

import mineli.ricardo.terceirodesafio.dto.CustomerDTO;
import mineli.ricardo.terceirodesafio.model.Customer;
import mineli.ricardo.terceirodesafio.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/{id}")
    private ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(customerService.findById(id));
    }

    @RequestMapping
    private ResponseEntity<?> findAll(){
        return ResponseEntity.ok().body(customerService.findAll());
    }

    @PostMapping
    private Customer saveCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.saveCustomer(customerDTO.getName(),customerDTO.getPhoneNumber(),customerDTO.getEmail());
    }

    @PutMapping
    private Customer updateCustomer(@RequestBody Customer customer){
        return customerService.update(customer);
    }
}
