package za.ac.unisa.myadmin.student.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.unisa.myadmin.student.services.jpa.models.GenericMessageEntity;
import za.ac.unisa.myadmin.student.services.jpa.models.GenericMessageEntityId;

import java.util.List;

public interface GenericMessageRepository extends JpaRepository<GenericMessageEntity, GenericMessageEntityId> {

	public GenericMessageEntity findByProgramAndMessageCodeAndSystemAllIgnoreCase(String program, String messageCode, String system);

	public List<GenericMessageEntity> findBySystemAndMessageTypeAllIgnoreCase(String system, String messageType);

}
