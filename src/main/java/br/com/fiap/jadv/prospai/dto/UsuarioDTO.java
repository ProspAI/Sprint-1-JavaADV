package br.com.fiap.jadv.prospai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "O nome de usuário é obrigatório")
    @Size(max = 100, message = "O nome de usuário deve ter no máximo 100 caracteres")
    private String username;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, max = 255, message = "A senha deve ter entre 6 e 255 caracteres")
    private String password;

    @NotBlank(message = "O papel do usuário é obrigatório")
    private String role;
}
