package mineli.ricardo.terceirodesafio.service;

import mineli.ricardo.terceirodesafio.model.Customer;
import mineli.ricardo.terceirodesafio.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(String name, String phoneNumber, String email ){
        return customerRepository.save(new Customer(name, phoneNumber, email));
    }

    public Customer findById(Long id){
        Optional<Customer> obj = customerRepository.findById(id);
        return obj.orElse(null);
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }
}
