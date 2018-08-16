package za.ac.unisa.myadmin.student.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.unisa.myadmin.student.services.jpa.models.EmailLogEntity;
import za.ac.unisa.myadmin.student.services.jpa.models.EmailLogEntityId;

import java.util.List;

public interface EmailLogRepository extends JpaRepository<EmailLogEntity, EmailLogEntityId> {

	public List<EmailLogEntity> findByRecipient(String referenceNumber);

	public List<EmailLogEntity> findByEmailAddress(String emailAddress);

	public List<EmailLogEntity> findByRecipientAndEmailAddress(String referenceNumber, String emailAddress);

	public List<EmailLogEntity> findByRecipientAndEmailType(String referenceNumber, String emailType);

	public List<EmailLogEntity> findByProgramAndEmailTypeAndReqSystemAndReqProgramAllIgnoreCase(String program, String emailType, String reqSystem, String regProgram);

	public List<EmailLogEntity> findByRecipientAndProgramAndEmailTypeAndReqSystemAndReqProgramAndBodyAllIgnoreCase(String recipient, String program, String emailType, String reqSystem, String regProgram, String emailBody);

}
