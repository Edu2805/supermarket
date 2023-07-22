package br.com.amorim.supermarket.service.attachment;

import br.com.amorim.supermarket.model.attatchment.Attachment;
import br.com.amorim.supermarket.repository.attachment.AttachmentRepository;
import br.com.amorim.supermarket.service.common.utils.ImageUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@AllArgsConstructor

@Service
public class AttachmentCrudServiceImpl implements AttachmentCrudService {

    private AttachmentRepository attachmentRepository;

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        attachmentRepository.save(Attachment.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressFile(file.getBytes())).build());

        return "File uploaded successfully : " + file.getOriginalFilename();
    }

    @Override
    public byte[] downloadFileById(UUID uuid) throws IOException {
        byte[] images = new byte[0];
        var dbImageData = attachmentRepository.findById(uuid);
        if (dbImageData.isPresent()) {
            images = ImageUtil.decompressImage(dbImageData.get().getImageData());
        }
        return images;
    }

    @Override
    public void deleteFile(UUID uuid) {
        attachmentRepository.findById(uuid)
                .map(existentAttachment -> {
                    attachmentRepository.delete(existentAttachment);
                    return existentAttachment;
                });
    }
}
