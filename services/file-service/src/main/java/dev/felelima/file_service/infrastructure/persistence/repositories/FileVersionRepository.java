package dev.felelima.file_service.infrastructure.persistence.repositories;

import dev.felelima.file_service.domain.model.FileVersion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileVersionRepository extends JpaRepository<FileVersion, UUID> {
}