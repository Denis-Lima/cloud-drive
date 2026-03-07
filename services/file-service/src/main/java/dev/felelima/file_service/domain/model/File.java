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
@Table(name = "files")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class File {

    @Id
    private UUID id;

    @Column(name = "folder_id")
    private UUID folderId;

    private String filename;

    private boolean deleted = false;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    public File(UUID folderId, String filename) {
        this.id = UUID.randomUUID();
        this.folderId = folderId;
        this.filename = filename;
        this.createdAt = Instant.now();
    }
}