package br.com.amorim.supermarket.testutils.generateentitiesunittests.attachment;

import br.com.amorim.supermarket.model.attatchment.Attachment;

import java.util.Random;
import java.util.UUID;

public class AttachmentTest {

    public Attachment generateAttachment() {
        Random random = new Random();
        var randonString = random.nextInt(100000, 199999);

        Attachment attachment = new Attachment();
        attachment.setId(UUID.randomUUID());
        attachment.setName(String.valueOf(randonString));
        attachment.setType(String.valueOf(randonString));
        attachment.setImageData(new byte[0]);
        return attachment;
    }
}
