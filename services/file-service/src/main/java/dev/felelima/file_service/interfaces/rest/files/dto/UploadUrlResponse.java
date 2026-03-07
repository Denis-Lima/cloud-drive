package dev.felelima.file_service.interfaces.rest.files.dto;

import java.util.UUID;

public record UploadUrlResponse(UUID fileId, String uploadUrl) {
}