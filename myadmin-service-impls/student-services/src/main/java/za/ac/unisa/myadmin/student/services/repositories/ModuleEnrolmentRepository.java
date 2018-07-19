package za.ac.unisa.myadmin.student.services.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import za.ac.unisa.myadmin.student.services.jpa.models.ModuleEnrolmentEntity;
import za.ac.unisa.myadmin.student.services.jpa.models.ModuleEnrolmentEntityId;


/**
 * Created by Adrian on 2018-06-06.
 */
public interface ModuleEnrolmentRepository extends JpaRepository<ModuleEnrolmentEntity, ModuleEnrolmentEntityId> {

	public List<ModuleEnrolmentEntity> findByStudentNumber(Integer studentNumber);

	public List<ModuleEnrolmentEntity> findByStudentNumberAndAcademicYear(Integer studentNumber, Integer academicYear);

	public List<ModuleEnrolmentEntity> findByStudentNumberAndAcademicYearAndSemesterPeriodInAndStatusCodeIn(Integer studentNumber, Integer year, List<Integer> semesterList, List<String> statusList);
}
