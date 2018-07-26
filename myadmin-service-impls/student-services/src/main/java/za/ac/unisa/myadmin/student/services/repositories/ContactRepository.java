package za.ac.unisa.myadmin.student.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.unisa.myadmin.student.services.jpa.models.ContactEntity;
import za.ac.unisa.myadmin.student.services.jpa.models.ContactEntityId;

import java.util.List;

public interface ContactRepository extends JpaRepository<ContactEntity, ContactEntityId> {
	public List<ContactEntity> findByReferenceNumber(Integer referenceNumber);


}
