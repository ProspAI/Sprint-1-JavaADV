package br.com.fiap.jadv.prospai;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.jadv.prospai.dto.ClienteDTO;
import br.com.fiap.jadv.prospai.entity.Cliente;
import br.com.fiap.jadv.prospai.repository.ClienteRepository;
import br.com.fiap.jadv.prospai.service.ClienteService;

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListarClientes() {
        // Dado
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente());
        clientes.add(new Cliente());
        when(clienteRepository.findAll()).thenReturn(clientes);

        // Quando
        List<ClienteDTO> clientesDTO = clienteService.listarClientes();

        // Então
        assertEquals(clientes.size(), clientesDTO.size());
    }

    @Test
    public void testObterClientePorId_Existente() {
        // Dado
        Long id = 1L;
        Cliente cliente = new Cliente();
        cliente.setId(id);
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        // Quando
        ClienteDTO clienteDTO = clienteService.obterClientePorId(id.intValue());

        // Então
        assertNotNull(clienteDTO);
        assertEquals(id, clienteDTO.getId());
    }

    @Test
    public void testObterClientePorId_NaoExistente() {
        // Dado
        Long id = 1L;
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        // Quando
        ClienteDTO clienteDTO = clienteService.obterClientePorId(id.intValue());

        // Então
        assertNull(clienteDTO);
    }

    @Test
    public void testCadastrarCliente() {
        // Dado
        ClienteDTO clienteDTO = new ClienteDTO();
        Cliente clienteSalvo = new Cliente();
        when(clienteRepository.save(clienteSalvo)).thenReturn(clienteSalvo);

        // Quando
        ClienteDTO clienteCadastrado = clienteService.cadastrarCliente(clienteDTO);

        // Então
        assertNotNull(clienteCadastrado);
    }

    @Test
    public void testAtualizarCliente_Existente() {
        // Dado
        Long id = 1L;
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(id);
        Cliente clienteExistente = new Cliente();
        clienteExistente.setId(id);
        when(clienteRepository.findById(id)).thenReturn(Optional.of(clienteExistente));
        when(clienteRepository.save(clienteExistente)).thenReturn(clienteExistente);

        // Quando
        ClienteDTO clienteAtualizado = clienteService.atualizarCliente(id.intValue(), clienteDTO);

        // Então
        assertNotNull(clienteAtualizado);
        assertEquals(id, clienteAtualizado.getId());
    }

    @Test
    public void testAtualizarCliente_NaoExistente() {
        // Dado
        Long id = 1L;
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(id);
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        // Quando
        ClienteDTO clienteAtualizado = clienteService.atualizarCliente(id.intValue(), clienteDTO);

        // Então
        assertNull(clienteAtualizado);
    }

    @Test
    public void testExcluirCliente_Existente() {
        // Dado
        Long id = 1L;

        // Quando
        clienteService.excluirCliente(id.intValue());

        // Então
        verify(clienteRepository).deleteById(id);
    }

    @Test
    public void testExcluirCliente_NaoExistente() {
        // Dado
        Long id = 1L;
        when(clienteRepository.existsById(id)).thenReturn(false);

        // Quando
        clienteService.excluirCliente(id.intValue());

        // Então
        verify(clienteRepository).deleteById(id);
    }
}
