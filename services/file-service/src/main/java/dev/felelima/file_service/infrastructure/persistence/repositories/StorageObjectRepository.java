package dev.felelima.file_service.infrastructure.persistence.repositories;

import dev.felelima.file_service.domain.model.StorageObject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StorageObjectRepository extends JpaRepository<StorageObject, UUID> {
}