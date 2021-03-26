package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehouse.entity.Attachment;
import uz.pdp.appwarehouse.entity.AttachmentContent;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.AttachmentContentRepository;
import uz.pdp.appwarehouse.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {

    final AttachmentRepository attachmentRepository;
    final AttachmentContentRepository attachmentContentRepository;

    public AttachmentService(AttachmentRepository attachmentRepository, AttachmentContentRepository attachmentContentRepository) {
        this.attachmentRepository = attachmentRepository;
        this.attachmentContentRepository = attachmentContentRepository;
    }

    public Result add(MultipartHttpServletRequest request) throws IOException {

        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {
            String name = file.getName();
            long size = file.getSize();
            String contentType = file.getContentType();

            Attachment attachment = new Attachment();
            attachment.setName(name);
            attachment.setSize(size);
            attachment.setContentType(contentType);

            Attachment savedAttachment = attachmentRepository.save(attachment);

            AttachmentContent attachmentContent = new AttachmentContent();
            attachmentContent.setMainContent(file.getBytes());
            attachmentContent.setAttachment(savedAttachment);

            attachmentContentRepository.save(attachmentContent);

            return new Result("Attachment added", true);
        }
        return new Result("Error in server", false);
    }

    public List<Attachment> get() {

        return attachmentRepository.findAll();

    }

    public void getById(Integer id, HttpServletResponse response) throws IOException {

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {

            Attachment attachment = optionalAttachment.get();

            Optional<AttachmentContent> contentOptional = attachmentContentRepository.findByAttachmentId(id);
            if (contentOptional.isPresent()) {

                AttachmentContent attachmentContent = contentOptional.get();
                response.setHeader("Content-Disposition", "attachment;filename=\"" + attachment.getName() + "\"");
                response.setContentType(attachment.getContentType());

                FileCopyUtils.copy(attachmentContent.getMainContent(), response.getOutputStream());
            }

        }

    }

    public Result delete(Integer id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            attachmentRepository.delete(attachment);
            Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachment(attachment);
            if (optionalAttachmentContent.isPresent()) {
                AttachmentContent attachmentContent = optionalAttachmentContent.get();
                attachmentContentRepository.delete(attachmentContent);
                return new Result("Attachment deleted", true);

            }
        }
        return new Result("Such attachment not found", false);
    }

    public Result update(Integer id, MultipartHttpServletRequest request) throws IOException {

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            Iterator<String> fileNames = request.getFileNames();
            MultipartFile file = request.getFile(fileNames.next());
            if (file != null) {
                String name = file.getName();
                long size = file.getSize();
                String contentType = file.getContentType();

                attachment.setName(name);
                attachment.setSize(size);
                attachment.setContentType(contentType);

                Attachment savedAttachment = attachmentRepository.save(attachment);

                AttachmentContent attachmentContent = new AttachmentContent();
                attachmentContent.setMainContent(file.getBytes());
                attachmentContent.setAttachment(savedAttachment);

                attachmentContentRepository.save(attachmentContent);

                return new Result("Attachment updated", true);
            }
        }
        return new Result("Such attachment not found", false);
    }
}
