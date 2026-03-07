package dev.felelima.file_service.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "objects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StorageObject {

    @Id
    private UUID id;

    @Column(name = "storage_key")
    private String storageKey;

    private Long size;

    @Column(name = "content_type")
    private String contentType;

    private String checksum;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    public StorageObject(Long size, String contentType) {
        this.id = UUID.randomUUID();
        this.storageKey = "files/" + id;
        this.size = size;
        this.contentType = contentType;
    }
}