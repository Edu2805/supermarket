package br.com.amorim.supermarket.controller.attachment;

import br.com.amorim.supermarket.service.attachment.AttachmentCrudService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

import java.io.IOException;
import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor

@RestController
@RequestMapping("api/attachment")
public class AttachmentController {

    private AttachmentCrudService attachmentCrudService;

    @PostMapping
    @ApiIgnore
    public ResponseEntity<?> uploadFile (@RequestParam("image") @Valid MultipartFile file) throws IOException {
        String uploadFile = attachmentCrudService.uploadFile(file);
        return ResponseEntity.status(OK).body(uploadFile);
    }

    @GetMapping("/{id}")
    @ApiIgnore
    public ResponseEntity<?> downloadFile (@PathVariable UUID id) throws IOException {
        byte[] images = attachmentCrudService.downloadFileById(id);
        return ResponseEntity.status(OK).contentType(MediaType.valueOf("image/jpeg")).body(images);
    }
}
