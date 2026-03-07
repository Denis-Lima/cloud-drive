package dev.felelima.file_service.interfaces.rest.files.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record UploadRequest(@NotNull UUID folderId, @NotBlank String filename, @NotNull @PositiveOrZero Long size, String contentType) {
}