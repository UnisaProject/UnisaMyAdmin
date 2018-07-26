package za.ac.unisa.myadmin.student.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.unisa.myadmin.student.services.jpa.models.GenericCodeEntity;
import za.ac.unisa.myadmin.student.services.jpa.models.GenericCodeEntityId;

import java.util.List;

public interface GenericCodeRepository extends JpaRepository<GenericCodeEntity, GenericCodeEntityId> {

	public List<GenericCodeEntity> findByGenericCategoryCodeOrderByInUse(Integer genericCategoryCode);

	public List<GenericCodeEntity> findByGenericCategoryCodeOrderByCode(Integer genericCategoryCode);

	public List<GenericCodeEntity> findByGenericCategoryCodeOrderByEnglishDescription(Integer genericCategoryCode);

	public List<GenericCodeEntity> findByGenericCategoryCodeOrderByAfrikaansDescription(Integer genericCategoryCode);

}
