package br.com.fiap.jadv.prospai.repository;

import br.com.fiap.jadv.prospai.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
    List<Compra> findByClienteId(Long clienteId);
}
