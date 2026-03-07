package dev.felelima.file_service.applicatin.service;

import dev.felelima.file_service.domain.exception.FolderNotFoundException;
import dev.felelima.file_service.domain.exception.InvalidDataException;
import dev.felelima.file_service.domain.model.File;
import dev.felelima.file_service.domain.model.FileVersion;
import dev.felelima.file_service.domain.model.StorageObject;
import dev.felelima.file_service.infrastructure.persistence.repositories.FileRepository;
import dev.felelima.file_service.infrastructure.persistence.repositories.FileVersionRepository;
import dev.felelima.file_service.infrastructure.persistence.repositories.FolderRepository;
import dev.felelima.file_service.infrastructure.persistence.repositories.StorageObjectRepository;
import dev.felelima.file_service.infrastructure.storage.MinioStorageService;
import dev.felelima.file_service.interfaces.rest.files.dto.UploadRequest;
import dev.felelima.file_service.interfaces.rest.files.dto.UploadUrlResponse;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadRequestService {
    private final FileRepository fileRepository;
    private final FolderRepository folderRepository;
    private final FileVersionRepository versionRepository;
    private final StorageObjectRepository objectRepository;
    private final MinioStorageService storageService;

    @Transactional
    public UploadUrlResponse requestUpload(UploadRequest request) {
        StorageObject object = createStorageObject(request.size(), request.contentType());

        File file = createFile(request.folderId(), request.filename());

        createFileVersion(file, object);

        String uploadUrl = storageService.generateUploadUrl(object.getStorageKey());

        return new UploadUrlResponse(file.getId(), uploadUrl);
    }

    private void createFileVersion(File file, StorageObject object) {
        FileVersion version = FileVersion.createNewVersion(file, object);

        versionRepository.save(version);
    }

    private File createFile(UUID folderId, String filename) {
        if (Strings.isBlank(filename)) {
            throw new InvalidDataException("Filename must not be blank");
        }
        if (!folderRepository.existsById(folderId)) {
            throw new FolderNotFoundException(folderId);
        }

        File file = new File(folderId, filename);

        return fileRepository.save(file);
    }

    private StorageObject createStorageObject(Long size, String contentType) {
        if (size == null || size < 0) {
            throw new InvalidDataException("Size must be a non-negative number");
        }
        if (Strings.isBlank(contentType)) {
            throw new InvalidDataException("Content type must not be blank");
        }
        StorageObject object = new StorageObject(size, contentType);

        return objectRepository.save(object);
    }
}