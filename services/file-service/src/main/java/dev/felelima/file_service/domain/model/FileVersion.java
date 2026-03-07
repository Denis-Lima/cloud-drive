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
@Table(name = "file_versions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileVersion {

    @Id
    private UUID id;

    @Column(name = "file_id")
    private UUID fileId;

    @Column(name = "version_number")
    private Integer versionNumber;

    @Column(name = "object_id")
    private UUID objectId;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    public static FileVersion createNewVersion(File file, StorageObject object) {
        return FileVersion.builder()
                .id(UUID.randomUUID())
                .fileId(file.getId())
                .objectId(object.getId())
                .versionNumber(1)
                .build();
    }
}