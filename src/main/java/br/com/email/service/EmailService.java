package br.com.email.service;

import br.com.email.model.dto.EmailDTO;

import java.util.List;

public interface EmailService {
    void enviarEmail(EmailDTO emailDTO);

    void enviarEmailLote(List<EmailDTO> listaEmailDTO);
}
