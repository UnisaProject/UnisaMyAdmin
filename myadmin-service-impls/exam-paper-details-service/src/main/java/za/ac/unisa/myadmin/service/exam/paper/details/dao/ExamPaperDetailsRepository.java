package za.ac.unisa.myadmin.service.exam.paper.details.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.ac.unisa.myadmin.service.exam.paper.details.model.ExamPaperDetailsEntity;
import za.ac.unisa.myadmin.service.exam.paper.details.model.ExamPaperDetailsIdentity;

@Repository
public interface ExamPaperDetailsRepository extends JpaRepository<ExamPaperDetailsEntity, ExamPaperDetailsIdentity> {

	public List<ExamPaperDetailsEntity> findByYear(Integer year);

	public List<ExamPaperDetailsEntity> findByCourseCodeIn(List<String> courseCodes);

	public List<ExamPaperDetailsEntity> findByYearAndCourseCodeIn(Integer year, List<String> courseCodes);

}
