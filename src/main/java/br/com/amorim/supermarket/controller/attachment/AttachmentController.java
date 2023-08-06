package br.com.amorim.supermarket.controller.attachment;

import br.com.amorim.supermarket.service.attachment.AttachmentCrudService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.util.UUID;

import static br.com.amorim.supermarket.common.enums.MessagesKeyType.FILE_EXCEEDS_MAXIMUM_SIZE;
import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor

@RestController
@RequestMapping("api/attachment")
@ControllerAdvice
public class AttachmentController {

    private AttachmentCrudService attachmentCrudService;

    @PostMapping
    @ApiIgnore
    public ResponseEntity<?> uploadFile (@RequestParam("image") MultipartFile file) throws IOException {
        String uploadFile = attachmentCrudService.uploadFile(file);
        return ResponseEntity.status(OK).body(uploadFile);
    }

    @GetMapping("/{id}")
    @ApiIgnore
    public ResponseEntity<?> downloadFile (@PathVariable UUID id) throws IOException {
        byte[] images = attachmentCrudService.downloadFileById(id);
        return ResponseEntity.status(OK).contentType(MediaType.valueOf("image/jpeg")).body(images);
    }

    /**
     * Classe que captura uma exceção caso o arquivo ultrapasse o tamanho permitido
     * IMPORTANTE: Toda vez que o tamanho do arquivo for alterado no application.properties, a mensagem deve ser ajustada
     * @return Mensagem informando que o arquivo ultrapassou o limite de tamanho permitido
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<String> handleFileUploadError() {
        return ResponseEntity.status(BAD_REQUEST).body(getString(FILE_EXCEEDS_MAXIMUM_SIZE.message));
    }
}
