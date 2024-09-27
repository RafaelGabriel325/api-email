package br.com.email.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EmailDTO {

    @NotBlank(message = "O destinatário do e-mail não pode estar em branco")
    @Email(message = "O destinatário do e-mail deve ser um endereço de e-mail válido")
    @Size(max = 255, message = "O destinatário do e-mail deve ter no máximo 255 caracteres")
    private String destinatario;

    @NotBlank(message = "O assunto do e-mail não pode estar em branco")
    @Size(max = 255, message = "O assunto do e-mail deve ter no máximo 255 caracteres")
    private String assunto;

    @NotBlank(message = "O conteúdo do e-mail não pode estar em branco")
    private String conteudo;

    @Valid
    private List<AnexoDTO> anexos; //
}