package br.com.fiap.jadv.prospai.dto;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CompraDTO {

    private Long id;

    @NotNull(message = "O ID do cliente é obrigatório")
    private Long clienteId;

    private String produto;

    @NotNull(message = "A quantidade é obrigatória")
    @Positive(message = "A quantidade deve ser maior que zero")
    private Integer quantidade;

    @NotNull(message = "O valor é obrigatório")
    @Positive(message = "O valor deve ser maior que zero")
    private BigDecimal valor;

    private Date dataCompra;

	public void setDataCompra(Date date) {
		// TODO Auto-generated method stub
		
	}
}
