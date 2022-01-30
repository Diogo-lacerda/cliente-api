package one.digitalinnovation.gof.controller;

import one.digitalinnovation.gof.controller.dto.ClienteRequest;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequestMapping("clientes")
@RestController
public class ClienteRestController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Iterable<Cliente>> buscarTodos(){
        Iterable<Cliente> todosClientes = clienteService.buscarTodos();
        return ResponseEntity.ok(todosClientes);
    }

    @GetMapping("/{id}/")
    public ResponseEntity<Cliente>buscarPorId(@PathVariable ("id") Long id){
        Cliente cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity inserir(@RequestBody @Valid ClienteRequest cliente){
        clienteService.inserir(cliente);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/")
    public ResponseEntity<Cliente>atualizar(@PathVariable("id") Long id, @RequestBody Cliente cliente){
        clienteService.atualizar(id,cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}/")
    public void deletar (@PathVariable("id") Long id){
        clienteService.deletar(id);
    }


}
