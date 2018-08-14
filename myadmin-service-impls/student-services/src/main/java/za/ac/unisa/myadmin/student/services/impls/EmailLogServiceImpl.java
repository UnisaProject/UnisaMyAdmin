package za.ac.unisa.myadmin.student.services.impls;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.generic.dto.EmailLogInfo;
import za.ac.unisa.myadmin.generic.services.EmailLogService;
import za.ac.unisa.myadmin.student.services.jpa.models.EmailLogEntity;
import za.ac.unisa.myadmin.student.services.repositories.EmailLogRepository;

import java.util.Comparator;

/**
 * Created by Adrian on 2018-07-30.
 */
public class EmailLogServiceImpl implements EmailLogService {

	private EmailLogRepository emailLogRepository;

	public void setEmailLogRepository(EmailLogRepository emailLogRepository) {
		this.emailLogRepository = emailLogRepository;
	}

	@Override
	public EmailLogInfo getLastestEmailRequestForAcademicRecord(String recipient, String program, String emailType, String reqSystem, String reqProgram, String emailBody) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		try {
			return emailLogRepository.findByRecipientAndProgramAndEmailTypeAndReqSystemAndReqProgramAndBodyAllIgnoreCase(recipient, program, emailType, reqSystem, reqProgram, emailBody)
				.stream()
				.map(EmailLogEntity::toDto)
				.max(Comparator.comparing(EmailLogInfo::getDateSent))
				.orElse(null);
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}
}
