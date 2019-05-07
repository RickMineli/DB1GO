package mineli.ricardo.terceirodesafio.controller;

import mineli.ricardo.terceirodesafio.model.Customer;
import mineli.ricardo.terceirodesafio.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private ResponseEntity<?> findyId(@PathVariable Long id){
        Customer obj = customerService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

}
