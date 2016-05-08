package ucm.fdi.tfg.fileupload.business.control;

import org.springframework.data.jpa.repository.JpaRepository;

import ucm.fdi.tfg.fileupload.business.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

}