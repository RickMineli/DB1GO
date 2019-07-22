package mineli.ricardo.terceirodesafio.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import mineli.ricardo.terceirodesafio.model.Customer;
import mineli.ricardo.terceirodesafio.model.Order;
import mineli.ricardo.terceirodesafio.model.Pizza;
import mineli.ricardo.terceirodesafio.repository.CustomerRepository;
import mineli.ricardo.terceirodesafio.repository.OrderRepository;
import mineli.ricardo.terceirodesafio.repository.PizzaRepository;
import mineli.ricardo.terceirodesafio.service.CustomerService;
import mineli.ricardo.terceirodesafio.service.OrderService;
import mineli.ricardo.terceirodesafio.service.PizzaService;
import org.junit.Before;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository mockRepository;

    @MockBean
    private OrderRepository mockOrderRepository;

    @MockBean
    private OrderService mockOrderService;

    @MockBean
    private PizzaRepository mockPizzaRepository;

    @Before
    public void setup() {
        Customer customer = new Customer("Ricardo", "123456789", "rick@hotmail.com");
        customer.setId(1L);
        when(mockRepository.findById(1L)).thenReturn(Optional.of(customer));
    }

    @Test
    public void findByAdd_OK() throws Exception {
        mockMvc.perform(get("/customers/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Ricardo")))
                .andExpect(jsonPath("$.phoneNumber", is("123456789")))
                .andExpect(jsonPath("$.email", is("rick@hotmail.com")));
        verify(mockRepository, times(1)).findById(1L);
    }

    @Test
    public void getAll_OK() throws Exception {
        Customer customer1 = new Customer("Ricardo", "123456789", "rick@hotmail.com");
        customer1.setId(1L);
        Customer customer2 = new Customer("Mario", "123456789", "mario@hotmail.com");
        customer2.setId(2L);
        Customer customer3 = new Customer("Gabriela", "123456789", "gabriela@hotmail.com");
        customer3.setId(3L);
        Customer customer4 = new Customer("Mariana", "123456789", "mariana@hotmail.com");
        customer4.setId(4L);
        List<Customer> customers = Arrays.asList(customer1, customer2, customer3, customer4);

        when(mockRepository.findAll()).thenReturn(customers);

        mockMvc.perform(get("/customers"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Ricardo")))
                .andExpect(jsonPath("$[0].phoneNumber", is("123456789")))
                .andExpect(jsonPath("$[0].email", is("rick@hotmail.com")))
                .andExpect(jsonPath("$[3].id", is(4)))
                .andExpect(jsonPath("$[3].name", is("Mariana")))
                .andExpect(jsonPath("$[3].phoneNumber", is("123456789")))
                .andExpect(jsonPath("$[3].email", is("mariana@hotmail.com")));

        verify(mockRepository, times(1)).findAll();
    }

    @Test
    public void findByIdNotFound_404() throws Exception {
        mockMvc.perform(get("/customers/5")).andExpect(status().isNotFound());
    }

    @Test
    public void saveCustomer_OK() throws Exception {
        Customer expectedCustomer = new Customer("Ricardo", "123456789", "rick@hotmail.com");
        expectedCustomer.setId(1L);
        when(mockRepository.save(any(Customer.class))).thenReturn(expectedCustomer);

        mockMvc.perform(post("/customers")
                .content("{\"name\":\"Ricardo\",\"phoneNumber\":\"123456789\",\"email\":\"rick@hotmail.com\"}")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(mockRepository, times(1)).save(any(Customer.class));

        mockMvc.perform(get("/customers/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Ricardo")))
                .andExpect(jsonPath("$.phoneNumber", is("123456789")))
                .andExpect(jsonPath("$.email", is("rick@hotmail.com")));
        verify(mockRepository, times(1)).findById(1L);
    }

    @Test
    public void updateCustomer_OK() throws Exception {

        Customer updateCustomer = new Customer("Ricardo", "123456789", "rick@hotmail.com");
        updateCustomer.setId(1L);
        when(mockRepository.save(any(Customer.class))).thenReturn(updateCustomer);

        mockMvc.perform(put("/customers/1")
                .content(om.writeValueAsString(updateCustomer))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(mockRepository, times(1)).save(any(Customer.class));

        mockMvc.perform(get("/customers/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Ricardo")))
                .andExpect(jsonPath("$.phoneNumber", is("123456789")))
                .andExpect(jsonPath("$.email", is("rick@hotmail.com")));
        verify(mockRepository, times(2)).findById(1L);
    }

    @Test
    public void deleteCustomer_OK() throws Exception {
        doNothing().when(mockRepository).deleteById(1L);
        mockMvc.perform(delete("/customers/1"))
                .andExpect(status().isOk());

        verify(mockRepository, times(1)).deleteById(1L);
    }

    @Test
    public void findCostumerOrders_OK() throws Exception {

        Customer customer = new Customer("Ricardo", "123456789", "rick@hotmail.com");
        customer.setId(1L);

        Order order1 = new Order("Av Brasil", true, customer);
        order1.setId(1L);
        Order order2 = new Order("Av Brasil", false, customer);
        order2.setId(2L);

        List<Order> orders = Arrays.asList(order1, order2);

        when(mockOrderService.findCostumerOrders(1L)).thenReturn(orders);

        mockMvc.perform(get("/customers/1/orders"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].address", is("Av Brasil")))
                .andExpect(jsonPath("$[0].pickup", is(true)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].address", is("Av Brasil")))
                .andExpect(jsonPath("$[1].pickup", is(false)));

        verify(mockOrderService, times(1)).findCostumerOrders(1L);
    }

    @Test
    public void insertOrder_OK() throws Exception {
        Customer customer = new Customer("Ricardo", "123456789", "rick@hotmail.com");
        customer.setId(1L);

        Order order = new Order("Av Brasil", false, customer);
        order.setId(1L);

        when(mockOrderRepository.save(any(Order.class))).thenReturn(order);

        mockMvc.perform(post("/customers/1/orders")
                .content("{\"address\":\"Casa\",\"pickup\": false,\"toppings\": [\"HAM\",\"PEPPERONI\"]}")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(mockOrderService, times(1)).save(any(Order.class));

        when(mockOrderService.findCostumerOrders(1L)).thenReturn(Collections.singletonList(order));

        mockMvc.perform(get("/customers/1/orders"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].address", is("Av Brasil")))
                .andExpect(jsonPath("$[0].pickup", is(false)));
        verify(mockOrderService, times(1)).findCostumerOrders(1L);
    }
}