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

import br.com.fiap.jadv.prospai.dto.CompraDTO;
import br.com.fiap.jadv.prospai.entity.Compra;
import br.com.fiap.jadv.prospai.repository.CompraRepository;
import br.com.fiap.jadv.prospai.service.CompraService;

public class CompraServiceTest {

    @Mock
    private CompraRepository compraRepository;

    @InjectMocks
    private CompraService compraService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarCompras() {
        // Dado
        List<Compra> compras = new ArrayList<>();
        compras.add(new Compra());
        compras.add(new Compra());
        when(compraRepository.findAll()).thenReturn(compras);

        // Quando
        List<CompraDTO> comprasDTO = compraService.listarCompras();

        // Então
        assertEquals(compras.size(), comprasDTO.size());
    }

    @Test
    public void testObterCompraPorId_Existente() {
        // Dado
        Long id = 1L;
        Compra compra = new Compra();
        compra.setId(id);
        when(compraRepository.findById(id)).thenReturn(Optional.of(compra));

        // Quando
        CompraDTO compraDTO = compraService.obterCompraPorId(id);

        // Então
        assertNotNull(compraDTO);
        assertEquals(id, compraDTO.getId());
    }

    @Test
    public void testObterCompraPorId_NaoExistente() {
        // Dado
        Long id = 1L;
        when(compraRepository.findById(id)).thenReturn(Optional.empty());

        // Quando
        CompraDTO compraDTO = compraService.obterCompraPorId(id);

        // Então
        assertNull(compraDTO);
    }

    @Test
    public void testCadastrarCompra() {
        // Dado
        CompraDTO compraDTO = new CompraDTO();
        Compra compraSalva = new Compra();
        when(compraRepository.save(compraSalva)).thenReturn(compraSalva);

        // Quando
        CompraDTO compraCadastrada = compraService.cadastrarCompra(compraDTO);

        // Então
        assertNotNull(compraCadastrada);
    }

    @Test
    public void testAtualizarCompra_Existente() {
        // Dado
        Long id = 1L;
        CompraDTO compraDTO = new CompraDTO();
        compraDTO.setId(id);
        Compra compraExistente = new Compra();
        compraExistente.setId(id);
        when(compraRepository.findById(id)).thenReturn(Optional.of(compraExistente));
        when(compraRepository.save(compraExistente)).thenReturn(compraExistente);

        // Quando
        CompraDTO compraAtualizada = compraService.atualizarCompra(id, compraDTO);

        // Então
        assertNotNull(compraAtualizada);
        assertEquals(id, compraAtualizada.getId());
    }

    @Test
    public void testAtualizarCompra_NaoExistente() {
        // Dado
        Long id = 1L;
        CompraDTO compraDTO = new CompraDTO();
        compraDTO.setId(id);
        when(compraRepository.findById(id)).thenReturn(Optional.empty());

        // Quando
        CompraDTO compraAtualizada = compraService.atualizarCompra(id, compraDTO);

        // Então
        assertNull(compraAtualizada);
    }

    @Test
    public void testExcluirCompra_Existente() {
        // Dado
        Long id = 1L;

        // Quando
        compraService.excluirCompra(id);

        // Então
        verify(compraRepository).deleteById(id);
    }

    @Test
    public void testExcluirCompra_NaoExistente() {
        // Dado
        Long id = 1L;
        when(compraRepository.existsById(id)).thenReturn(false);

        // Quando
        compraService.excluirCompra(id);

        // Então
        verify(compraRepository).deleteById(id);
    }
}
