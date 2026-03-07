package dev.felelima.file_service.interfaces.rest.files;

import dev.felelima.file_service.applicatin.service.UploadRequestService;
import dev.felelima.file_service.interfaces.rest.files.dto.UploadRequest;
import dev.felelima.file_service.interfaces.rest.files.dto.UploadUrlResponse;
import io.minio.errors.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {
    private final UploadRequestService uploadService;

    @Operation(summary = "Request a pre-signed URL for uploading a file")
    @PostMapping("/upload-request")
    public UploadUrlResponse requestUpload(@RequestBody @Valid UploadRequest request) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return uploadService.requestUpload(request);
    }
}