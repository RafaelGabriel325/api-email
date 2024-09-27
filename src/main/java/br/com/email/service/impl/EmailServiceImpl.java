package br.com.email.service.impl;

import br.com.email.model.dto.AnexoDTO;
import br.com.email.model.dto.EmailDTO;
import br.com.email.model.entity.HistoricoEmail;
import br.com.email.repository.HistoricoEmailRepository;
import br.com.email.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final HistoricoEmailRepository historicoEmailRepository;
    private final JavaMailSender mailSender;

    @Override
    public void enviarEmail(EmailDTO emailDTO) {
        LOGGER.info("Enviando o email com anexo");

        MimeMessage mensagem = mailSender.createMimeMessage();
        HistoricoEmail historico = new HistoricoEmail();
        historico.setDestinatario(emailDTO.getDestinatario());
        historico.setAssunto(emailDTO.getAssunto());
        historico.setConteudo(emailDTO.getConteudo());
        historico.setDataHoraEnvio(LocalDateTime.now());

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mensagem, true);
            helper.setTo(emailDTO.getDestinatario());
            helper.setSubject(emailDTO.getAssunto());
            helper.setText(emailDTO.getConteudo());

            if (emailDTO.getAnexos() != null && !emailDTO.getAnexos().isEmpty()) {
                for (AnexoDTO anexo : emailDTO.getAnexos()) {
                    ByteArrayResource resource = new ByteArrayResource(anexo.getConteudo());
                    helper.addAttachment(anexo.getNome(), resource);
                }
            }

            mailSender.send(mensagem);
            historico.setSucesso(true);
            LOGGER.info("Sucesso ao enviar email com anexo para: " + emailDTO.getDestinatario());
        } catch (MessagingException | MailException exception) {
            historico.setSucesso(false);
            LOGGER.error("Erro ao enviar e-mail para: " + emailDTO.getDestinatario(), exception);
        }

        historicoEmailRepository.save(historico);
    }

    @Override
    public void enviarEmailLote(List<EmailDTO> listaEmailDTO) {
        LOGGER.info("Enviando lote de email com anexo");

        for (EmailDTO emailDTO : listaEmailDTO) {
            try {
                enviarEmail(emailDTO);
                LOGGER.info("Sucesso ao enviar email para: " + emailDTO.getDestinatario());
            } catch (MailException exception) {
                LOGGER.error("Erro ao enviar e-mail para: " + emailDTO.getDestinatario(), exception);
            }
        }
    }
}
