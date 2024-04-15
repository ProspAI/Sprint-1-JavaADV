package br.com.fiap.jadv.prospai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.jadv.prospai.entity.Interacao;

@Repository
public interface InteracaoRepository extends JpaRepository<Interacao, Long> {
    List<Interacao> findByClienteId(Long clienteId);
}
