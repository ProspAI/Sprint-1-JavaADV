package br.com.fiap.jadv.prospai.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.jadv.prospai.dto.ClienteDTO;
import br.com.fiap.jadv.prospai.entity.Cliente;
import br.com.fiap.jadv.prospai.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteDTO> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO obterClientePorId(int id) {
        return clienteRepository.findById((long) id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public ClienteDTO cadastrarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = convertToEntity(clienteDTO);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return convertToDTO(clienteSalvo);
    }

    public ClienteDTO atualizarCliente(int id, ClienteDTO clienteDTO) {
        Cliente clienteExistente = clienteRepository.findById((long) id)
                .orElse(null);
        if (clienteExistente != null) {
            clienteExistente.setNome(clienteDTO.getNome());
            clienteExistente.setEmail(clienteDTO.getEmail());
            clienteExistente.setTelefone(clienteDTO.getTelefone());
            clienteExistente.setEndereco(clienteDTO.getEndereco());
            Cliente clienteAtualizado = clienteRepository.save(clienteExistente);
            return convertToDTO(clienteAtualizado);
        }
        return null;
    }

    public void excluirCliente(int id) {
        clienteRepository.deleteById((long) id);
    }

    private ClienteDTO convertToDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setTelefone(cliente.getTelefone());
        clienteDTO.setEndereco(cliente.getEndereco());
        return clienteDTO;
    }

    private Cliente convertToEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setEndereco(clienteDTO.getEndereco());
        return cliente;
    }
}
