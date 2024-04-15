package br.com.fiap.jadv.prospai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.jadv.prospai.dto.ClienteDTO;
import br.com.fiap.jadv.prospai.service.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        List<ClienteDTO> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obterClientePorId(@PathVariable int id) {
        ClienteDTO cliente = clienteService.obterClientePorId(id);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> cadastrarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteCadastrado = clienteService.cadastrarCliente(clienteDTO);
        return new ResponseEntity<>(clienteCadastrado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable int id, @Valid @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteAtualizado = clienteService.atualizarCliente(id, clienteDTO);
        if (clienteAtualizado != null) {
            return ResponseEntity.ok(clienteAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable int id) {
        clienteService.excluirCliente(id);
        return ResponseEntity.noContent().build();
    }
}
