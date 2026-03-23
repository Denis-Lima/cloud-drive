package dev.felelima.file_service.interfaces.rest.files;

import dev.felelima.file_service.application.service.UploadRequestService;
import dev.felelima.file_service.interfaces.rest.files.dto.UploadRequest;
import dev.felelima.file_service.interfaces.rest.files.dto.UploadUrlResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {
    private final UploadRequestService uploadService;

    @Operation(summary = "Request a pre-signed URL for uploading a file")
    @PostMapping("/upload-request")
    public UploadUrlResponse requestUpload(@RequestBody @Valid UploadRequest request) {
        return uploadService.requestUpload(request);
    }
}