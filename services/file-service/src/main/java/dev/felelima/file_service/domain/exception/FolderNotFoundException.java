package dev.felelima.file_service.domain.exception;

import java.util.UUID;

public class FolderNotFoundException extends NotFoundException {
    public FolderNotFoundException(String message) {
        super(message);
    }
    public FolderNotFoundException(UUID folderId) {
        this("Folder with ID " + folderId + " not found.");
    }
}