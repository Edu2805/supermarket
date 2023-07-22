package br.com.amorim.supermarket.repository.attachment;

import br.com.amorim.supermarket.model.attatchment.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {

    Optional<Attachment> findByName(String fileName);
}
