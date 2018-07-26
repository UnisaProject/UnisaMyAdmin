package za.ac.unisa.myadmin.student.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.unisa.myadmin.student.services.jpa.models.StudentAcademicRecordEntity;
import za.ac.unisa.myadmin.student.services.jpa.models.StudentAcademicRecordEntityId;

import java.util.List;

public interface StudentAcademicRecordRepository extends JpaRepository<StudentAcademicRecordEntity, StudentAcademicRecordEntityId> {

	public List<StudentAcademicRecordEntity> findByStudentNumber(Integer studentNumber);

	public List<StudentAcademicRecordEntity> findByStudentNumberAndQualificationCode(Integer studentNumber, String qualificationCode);

}
