package mineli.ricardo.terceirodesafio.controller;


import mineli.ricardo.terceirodesafio.model.Pizza;
import mineli.ricardo.terceirodesafio.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaService service;

    @RequestMapping("/{id}")
    private ResponseEntity<Pizza> findById(@PathVariable Long id){
        Pizza obj = service.findById(id);
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
    public ResponseEntity<Page<Pizza>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="5") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="id") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction) {
        Page<Pizza> list = service.findPage(page,linesPerPage,orderBy,direction);
        return ResponseEntity.ok().body(list);
    }
}
