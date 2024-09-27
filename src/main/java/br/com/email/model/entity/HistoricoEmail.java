package br.com.email.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "tab_historico_email")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class HistoricoEmail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID uuid;

    @Column(name = "destinatario", length = 255, nullable = false)
    private String destinatario;

    @Column(name = "assunto", length = 255, nullable = false)
    private String assunto;

    @Column(name = "conteudo", columnDefinition = "TEXT", nullable = false)
    private String conteudo;

    @Column(name = "data_hora_envio", nullable = false)
    private LocalDateTime dataHoraEnvio;

    @Column(name = "sucesso", nullable = false)
    private Boolean sucesso;
}
