package br.com.amorim.supermarket.controller.attachment;

import br.com.amorim.supermarket.service.attachment.AttachmentCrudService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import java.io.IOException;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor

@RestController
@RequestMapping("api/attachment")
@Api("Attachment")
public class AttachmentController {

    private AttachmentCrudService attachmentCrudService;

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Save a attachment")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Attachment successfully saved"),
            @ApiResponse(code = 400, message = "An error occurred while saving the attachment")
    })
    public ResponseEntity<?> uploadFile (@RequestParam("image") @Valid @ApiParam("Parameters for saving the attachment") MultipartFile file) throws IOException {
        String uploadFile = attachmentCrudService.uploadFile(file);
        return ResponseEntity.status(OK).body(uploadFile);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a specific attachment")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Attachment returned successfully"),
            @ApiResponse(code = 404, message = "Attachment not found for given file name")
    })
    public ResponseEntity<?> downloadFile (@PathVariable @ApiParam("Attachment id") UUID id) throws IOException {
        byte[] images = attachmentCrudService.downloadFileById(id);
        return ResponseEntity.status(OK).contentType(MediaType.valueOf("image/jpeg")).body(images);
    }
}
