package za.ac.unisa.myadmin.student.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.unisa.myadmin.student.services.models.StudentModuleEnrolmentEntity;
import za.ac.unisa.myadmin.student.services.models.StudentModuleEnrolmentEntityId;

import java.util.List;


/**
 * Created by Adrian on 2018-06-06.
 */
@Repository
public interface StudentModuleEnrolmentRepository extends JpaRepository<StudentModuleEnrolmentEntity, StudentModuleEnrolmentEntityId> {

	public List<StudentModuleEnrolmentEntity> findByStudentNumber(Integer studentNumber);

	public List<StudentModuleEnrolmentEntity> findByStudentNumberAndAcademicYear(Integer studentNumber, Integer academicYear);

	public List<StudentModuleEnrolmentEntity> findByStudentNumberAndAcademicYearAndSemesterPeriodAndStatusCodeIn(Integer studentNumber, Integer year, Integer semester, List<String> statusList);

}
