package br.com.fiap.jadv.prospai.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.jadv.prospai.dto.UsuarioDTO;
import br.com.fiap.jadv.prospai.entity.Usuario;
import br.com.fiap.jadv.prospai.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Método para registrar um novo usuário, apenas permitido para o administrador
    public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO, String role) {
        // Verifica se o papel do usuário que está fazendo a solicitação é o administrador
        if (role.equals("admin")) {
            // Converte o DTO em uma entidade Usuario
            Usuario novoUsuario = convertToEntity(usuarioDTO);
            // Salva o novo usuário no banco de dados
            Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);
            // Converte e retorna o novo usuário em DTO
            return convertToDTO(usuarioSalvo);
        } else {
            // Se o papel do usuário não for administrador, retorna null indicando que a operação não é permitida
            return null;
        }
    }

    private UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setUsername(usuario.getUsername());
        usuarioDTO.setPassword(usuario.getPassword());
        usuarioDTO.setRole(usuario.getRole());
        return usuarioDTO;
    }

    private Usuario convertToEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setRole(usuarioDTO.getRole());
        return usuario;
    }
}
