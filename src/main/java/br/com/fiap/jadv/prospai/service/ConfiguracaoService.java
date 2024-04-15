package br.com.fiap.jadv.prospai.service;

import org.springframework.stereotype.Service;

@Service
public class ConfiguracaoService {

    public String getConfiguracoes() {
        return "Configurações do sistema recuperadas";
    }
}
