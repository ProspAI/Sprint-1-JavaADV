package br.com.fiap.jadv.prospai.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class InteracaoDTO {

    private Long id;

    @NotNull(message = "O ID do cliente é obrigatório")
    private Long clienteId;

    private Date dataInteracao;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 1000, message = "A descrição deve ter no máximo 1000 caracteres")
    private String descricao;
}
