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

import br.com.fiap.jadv.prospai.dto.CompraDTO;
import br.com.fiap.jadv.prospai.service.CompraService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/compras")
public class ComprasController {

    private final CompraService compraService;

    @Autowired
    public ComprasController(CompraService compraService) {
        this.compraService = compraService;
    }

    @GetMapping
    public ResponseEntity<List<CompraDTO>> listarCompras() {
        List<CompraDTO> compras = compraService.listarCompras();
        return ResponseEntity.ok(compras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraDTO> obterCompraPorId(@PathVariable Long id) {
        CompraDTO compraDTO = compraService.obterCompraPorId(id);
        if (compraDTO != null) {
            return ResponseEntity.ok(compraDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CompraDTO> cadastrarCompra(@Valid @RequestBody CompraDTO compraDTO) {
        CompraDTO compraCadastrada = compraService.cadastrarCompra(compraDTO);
        return new ResponseEntity<>(compraCadastrada, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompraDTO> atualizarCompra(@PathVariable Long id, @Valid @RequestBody CompraDTO compraDTO) {
        CompraDTO compraAtualizada = compraService.atualizarCompra(id, compraDTO);
        if (compraAtualizada != null) {
            return ResponseEntity.ok(compraAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCompra(@PathVariable Long id) {
        compraService.excluirCompra(id);
        return ResponseEntity.noContent().build();
    }
}
