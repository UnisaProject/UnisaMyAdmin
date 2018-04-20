package za.ac.unisa.myadmin.service.exam.period.date.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.ac.unisa.myadmin.service.exam.period.date.model.ExamPeriodDateEntity;
import za.ac.unisa.myadmin.service.exam.period.date.model.ExamPeriodDateIdentity;

@Repository
public interface ExamPeriodDateRepository extends JpaRepository<ExamPeriodDateEntity, ExamPeriodDateIdentity> {

	public List<ExamPeriodDateEntity> findByYear(Integer year);

	public List<ExamPeriodDateEntity> findByExamPeriodCode(Integer examPeriodCode);

	public List<ExamPeriodDateEntity> findByCourseCodeIn(List<String> courseCodes);

	public List<ExamPeriodDateEntity> findByYearAndExamPeriodCode(Integer year, Integer examPeriodCode);

	public List<ExamPeriodDateEntity> findByYearAndCourseCodeIn(Integer year, List<String> courseCodes);

	public List<ExamPeriodDateEntity> findByExamPeriodCodeAndCourseCodeIn(Integer examPeriodCode,
			List<String> courseCodes);

	public List<ExamPeriodDateEntity> findByYearAndExamPeriodCodeAndCourseCodeIn(Integer year, Integer examPeriodCode,
			List<String> courseCodes);

}
