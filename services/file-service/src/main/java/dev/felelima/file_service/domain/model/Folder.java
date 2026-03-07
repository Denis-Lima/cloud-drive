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
@Table(name = "folders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Folder {

    @Id
    private UUID id;

    private String name;

    @Column(name = "parent_id")
    private UUID parentId;

    @Column(columnDefinition = "ltree")
    private String path;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;
}