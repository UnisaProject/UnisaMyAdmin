package za.ac.unisa.myadmin.student.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.unisa.myadmin.student.services.jpa.models.QualificationEntity;

import java.util.List;

public interface QualificationRepository extends JpaRepository<QualificationEntity, String> {

	public List<QualificationEntity> findByQualificationGroupName(String qualificationGroupName);

	public List<QualificationEntity> findByAcademicYear(Integer year);

	public List<QualificationEntity> findByType(String type);

	public List<QualificationEntity> findByCategoryCode(Integer type);

	public List<QualificationEntity> findByFacultyCode(Integer facultyCode);

	public List<QualificationEntity> findByFacultyCodeAndDepartmentCode(Integer facultyCode, Integer departmentCode);

	public List<QualificationEntity> findByAcademicDepartmentCodeAndFacultyCodeAndDepartmentCode(Integer AcademicDepartmentCode, Integer facultyCode, Integer departmentCode);

}
