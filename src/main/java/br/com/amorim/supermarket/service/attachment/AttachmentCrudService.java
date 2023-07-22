package br.com.amorim.supermarket.service.attachment;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface AttachmentCrudService {

    String uploadFile(MultipartFile file) throws IOException;
    byte[] downloadFileById(UUID uuid) throws IOException;

    void deleteFile(UUID uuid);
}
