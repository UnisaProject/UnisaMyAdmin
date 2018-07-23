package za.ac.unisa.myadmin.student.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.unisa.myadmin.student.services.models.StudentAnnualEntity;
import za.ac.unisa.myadmin.student.services.models.StudentAnnualEntityId;

import java.util.List;
import java.util.Optional;


/**
 * Created by Adrian on 2018-06-06.
 */
@Repository
public interface StudentAnnualRepository extends JpaRepository<StudentAnnualEntity, StudentAnnualEntityId> {

	public List<StudentAnnualEntity> findByStudentNumber(Integer studentNumber);

	public List<StudentAnnualEntity> findByStudentNumberOrderByAcademicYearDesc(Integer studentNumber);

	public Optional<StudentAnnualEntity> findTopByStudentNumberOrderByAcademicYearDesc(Integer studentNumber);

	public List<StudentAnnualEntity> findByStudentNumberAndAcademicYear(Integer studentNumber, Integer academicYear);

}
