package br.com.email.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AnexoDTO {
    @NotBlank(message = "O nome do anexo não pode estar em branco")
    @Size(max = 255, message = "O nome do anexo deve ter no máximo 255 caracteres")
    private String nome;

    @NotNull(message = "O conteúdo do anexo não pode ser nulo")
    private byte[] conteudo;

    @NotBlank(message = "O tipo do anexo não pode estar em branco")
    @Size(max = 100, message = "O tipo do anexo deve ter no máximo 100 caracteres")
    private String tipo;
}
