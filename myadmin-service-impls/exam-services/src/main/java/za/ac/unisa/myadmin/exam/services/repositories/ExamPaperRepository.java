package za.ac.unisa.myadmin.exam.services.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import za.ac.unisa.myadmin.exam.services.jpa.models.ExamPaperEntity;
import za.ac.unisa.myadmin.exam.services.jpa.models.ExamPaperEntityId;

public interface ExamPaperRepository extends JpaRepository<ExamPaperEntity, ExamPaperEntityId> {

	public List<ExamPaperEntity> findByYear(Integer year);

	public List<ExamPaperEntity> findByCourseCodeIn(List<String> courseCodes);

	public List<ExamPaperEntity> findByYearAndCourseCodeIn(Integer year, List<String> courseCodes);

}
