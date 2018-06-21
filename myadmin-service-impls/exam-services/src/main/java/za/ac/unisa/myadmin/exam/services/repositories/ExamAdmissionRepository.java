package za.ac.unisa.myadmin.exam.services.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.ac.unisa.myadmin.exam.services.models.ExamAdmissionEntity;
import za.ac.unisa.myadmin.exam.services.models.ExamAdmissionEntityId;

@Repository
public interface ExamAdmissionRepository extends JpaRepository<ExamAdmissionEntity, ExamAdmissionEntityId> {

	public List<ExamAdmissionEntity> findByYear(Integer year);

	public List<ExamAdmissionEntity> findByExamPeriodCode(Integer examPeriodCode);

	public List<ExamAdmissionEntity> findByExamType(Integer examType);

	public List<ExamAdmissionEntity> findByYearAndExamPeriodCode(Integer year, Integer examPeriodCode);

	public List<ExamAdmissionEntity> findByYearAndExamType(Integer year, Integer examType);

	public List<ExamAdmissionEntity> findByExamPeriodCodeAndExamType(Integer examPeriodCode, Integer examType);

	public List<ExamAdmissionEntity> findByYearAndExamPeriodCodeAndExamType(Integer year, Integer examPeriodCode,
			Integer examType);

}
