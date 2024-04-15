package br.com.fiap.jadv.prospai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.jadv.prospai.dto.CompraDTO;
import br.com.fiap.jadv.prospai.entity.Cliente;
import br.com.fiap.jadv.prospai.entity.Compra;
import br.com.fiap.jadv.prospai.repository.CompraRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompraService {

    private final CompraRepository compraRepository;

    @Autowired
    public CompraService(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    public List<CompraDTO> listarCompras() {
        List<Compra> compras = compraRepository.findAll();
        return compras.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CompraDTO obterCompraPorId(Long id) {
        Optional<Compra> compraOptional = compraRepository.findById(id);
        return compraOptional.map(this::convertToDTO).orElse(null);
    }

    public CompraDTO cadastrarCompra(CompraDTO compraDTO) {
        Compra compra = convertToEntity(compraDTO);
        Compra compraSalva = compraRepository.save(compra);
        return convertToDTO(compraSalva);
    }

    public CompraDTO atualizarCompra(Long id, CompraDTO compraDTO) {
        Optional<Compra> compraOptional = compraRepository.findById(id);
        if (compraOptional.isPresent()) {
            Compra compraExistente = compraOptional.get();
            // Atualiza os campos da compra com base no DTO recebido
            compraExistente.setProduto(compraDTO.getProduto());
            compraExistente.setQuantidade(compraDTO.getQuantidade());
            compraExistente.setValor(compraDTO.getValor());
            compraExistente.setDataCompra();

            Compra compraAtualizada = compraRepository.save(compraExistente);
            return convertToDTO(compraAtualizada);
        }
        return null;
    }

    public void excluirCompra(Long id) {
        compraRepository.deleteById(id);
    }

    private CompraDTO convertToDTO(Compra compra) {
        CompraDTO compraDTO = new CompraDTO();
        compraDTO.setId(compra.getId());
        compraDTO.setClienteId(compra.getCliente() != null ? compra.getCliente().getId() : null);
        compraDTO.setProduto(compra.getProduto());
        compraDTO.setQuantidade(compra.getQuantidade());
        compraDTO.setValor(compra.getValor());
        compraDTO.setDataCompra(compra.getDataCompra());
        return compraDTO;
    }

    private Compra convertToEntity(CompraDTO compraDTO) {
        Compra compra = new Compra();
        // Atribui o cliente à compra se o ID do cliente estiver presente no DTO
        if (compraDTO.getClienteId() != null) {
            Cliente cliente = new Cliente();
            cliente.setId(compraDTO.getClienteId());
            compra.setCliente(cliente);
        }
        compra.setProduto(compraDTO.getProduto());
        compra.setQuantidade(compraDTO.getQuantidade());
        compra.setValor(compraDTO.getValor());
        compra.setDataCompra();
        return compra;
    }
}
