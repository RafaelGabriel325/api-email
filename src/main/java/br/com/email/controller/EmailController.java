package br.com.email.controller;

import br.com.email.model.dto.EmailDTO;
import br.com.email.service.EmailService;
import br.com.email.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

import static br.com.email.shared.constant.ConstantUrl.*;


@RestController
@RequestMapping(EMAIL_BASE)
@Tag(name = "Email", description = "Endpoints para envio de e-mails")
public class EmailController implements Serializable {
    private final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping(value = EMAIL_ENVIAR_EMAIL,
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Enviar e-mail para um único destinatário",
            description = "Enviar e-mail para um único destinatário, passando um JSON, XML ou YML em represetação do email",
            tags = {"E-mail"},
            responses = {
                    @ApiResponse(description = "Sucess", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EmailDTO.class))),
                    @ApiResponse(description = "Bad Resquet", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<Void> enviarEmail(@Valid @RequestBody EmailDTO emailDTO) {
        LOGGER.debug("Recebida requisição para enviar e-mail para:" + emailDTO.getDestinatario());
        emailService.enviarEmail(emailDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = EMAIL_ENVIAR_EMAIL_LOTE,
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Enviar e-mails em lote",
            description = "Enviar vários e-mails de uma vez, passando um array de JSON, XML ou YML em represetação dos e-mails",
            tags = {"E-mail"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EmailDTO.class))),
                    @ApiResponse(description = "Requisição inválida", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Não autorizado", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content),
            })
    public ResponseEntity<Void> enviarEmailLote(@Valid @RequestBody List<EmailDTO> listaEmail) {
        LOGGER.debug("Recebida requisição para enviar e-mails em lote. Quantidade de e-mails:" + listaEmail.size());
        emailService.enviarEmailLote(listaEmail);
        return ResponseEntity.ok().build();
    }

}
