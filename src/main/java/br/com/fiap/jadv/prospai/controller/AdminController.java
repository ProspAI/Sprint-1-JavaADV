package br.com.fiap.jadv.prospai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.jadv.prospai.dto.UsuarioDTO;
import br.com.fiap.jadv.prospai.service.ConfiguracaoService;
import br.com.fiap.jadv.prospai.service.DesempenhoService;
import br.com.fiap.jadv.prospai.service.UsuarioService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final ConfiguracaoService configuracaoService;
    private final UsuarioService usuarioService;
    private final DesempenhoService desempenhoService;

    @Autowired
    public AdminController(ConfiguracaoService configuracaoService, UsuarioService usuarioService,
                           DesempenhoService desempenhoService) {
        this.configuracaoService = configuracaoService;
        this.usuarioService = usuarioService;
        this.desempenhoService = desempenhoService;
    }

    @GetMapping("/configuracao")
    public String getConfiguracaoSistema() {
        // Aqui você pode chamar o serviço de configuração para recuperar as configurações do sistema
        return configuracaoService.getConfiguracoes();
    }

    @GetMapping("/usuarios")
    public List<UsuarioDTO> listarUsuarios() {
        // Aqui você pode chamar o serviço de usuário para listar todos os usuários do sistema
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/desempenho")
    public String monitorarDesempenho() {
        // Aqui você pode chamar o serviço de desempenho para monitorar e retornar informações sobre o desempenho do sistema
        return desempenhoService.monitorarDesempenho();
    }

    // Endpoint para registrar um novo usuário pelo administrador
    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioDTO> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        // Chama o serviço para registrar o novo usuário
        UsuarioDTO novoUsuario = usuarioService.registrarUsuario(usuarioDTO, "admin");
        // Verifica se o registro foi bem-sucedido
        if (novoUsuario != null) {
            // Retorna o novo usuário registrado e o status HTTP 201 Created
            return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
        } else {
            // Se a operação foi negada, retorna o status HTTP 403 Forbidden
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
