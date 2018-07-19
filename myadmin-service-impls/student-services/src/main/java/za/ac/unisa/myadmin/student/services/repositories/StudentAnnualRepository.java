package za.ac.unisa.myadmin.student.services.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import za.ac.unisa.myadmin.student.services.jpa.models.StudentAnnualEntity;
import za.ac.unisa.myadmin.student.services.jpa.models.StudentAnnualEntityId;


/**
 * Created by Adrian on 2018-06-06.
 */
public interface StudentAnnualRepository extends JpaRepository<StudentAnnualEntity, StudentAnnualEntityId> {

	public List<StudentAnnualEntity> findByStudentNumber(Integer studentNumber);

	public List<StudentAnnualEntity> findByStudentNumberOrderByAcademicYearDesc(Integer studentNumber);

	public Optional<StudentAnnualEntity> findTopByStudentNumberOrderByAcademicYearDesc(Integer studentNumber);

	public List<StudentAnnualEntity> findByStudentNumberAndAcademicYear(Integer studentNumber, Integer academicYear);

}
