package za.ac.unisa.myadmin.exam.services.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import za.ac.unisa.myadmin.exam.services.jpa.models.ExaminationEntity;
import za.ac.unisa.myadmin.exam.services.jpa.models.ExaminationEntityId;

public interface ExaminationRepository extends JpaRepository<ExaminationEntity, ExaminationEntityId> {

	public List<ExaminationEntity> findByYear(Integer year);

	public List<ExaminationEntity> findByExamPeriodCode(Integer examPeriodCode);

	public List<ExaminationEntity> findByCourseCodeIn(List<String> courseCodes);

	public List<ExaminationEntity> findByYearAndExamPeriodCode(Integer year, Integer examPeriodCode);

	public List<ExaminationEntity> findByYearAndCourseCodeIn(Integer year, List<String> courseCodes);

	public List<ExaminationEntity> findByExamPeriodCodeAndCourseCodeIn(Integer examPeriodCode,
			List<String> courseCodes);

	public List<ExaminationEntity> findByYearAndExamPeriodCodeAndCourseCodeIn(Integer year, Integer examPeriodCode,
			List<String> courseCodes);

}
