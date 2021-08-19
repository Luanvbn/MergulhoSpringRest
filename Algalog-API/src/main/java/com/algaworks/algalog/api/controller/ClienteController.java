package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    @GetMapping("/{ClienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long ClienteId) {
        return clienteRepository.findById(ClienteId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{ClienteId}")
    public ResponseEntity<Cliente> atualizar (@PathVariable Long ClienteId,@Valid @RequestBody Cliente cliente){
        if(!clienteRepository.existsById(ClienteId)){
            return ResponseEntity.notFound().build();
        }

        cliente.setId(ClienteId);
        cliente = clienteRepository.save(cliente);

        return ResponseEntity.ok(cliente);

    }

    @DeleteMapping("/{ClienteId}")
    public ResponseEntity<Void> remover (@PathVariable Long ClienteId){
        if(!clienteRepository.existsById(ClienteId)){
            return ResponseEntity.notFound().build();
        }
        clienteRepository.deleteById(ClienteId);

        return ResponseEntity.noContent().build();

    }
}
