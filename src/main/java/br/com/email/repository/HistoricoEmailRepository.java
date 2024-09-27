package br.com.email.repository;

import br.com.email.model.entity.HistoricoEmail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HistoricoEmailRepository extends JpaRepository<HistoricoEmail, UUID> {
}