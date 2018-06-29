package za.ac.unisa.myadmin.student.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.unisa.myadmin.student.services.models.StudentEntity;

/**
 * Created by dev on 2018-06-06.
 */
@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

//	public StudentEntity findByStudentNumber(Integer studentNumber);

//	public List<StudentEntity> findByStudentNumberAndLastAcademicYear(Integer studentNumber, Integer lastAcademicYear);

//	public List<StudentEntity> findByStudentNumberAndLastAcademicYearAndLastAcademicPeriod(Integer studentNumber, Integer lastAcademicYear, Integer lastAcademicPeriod);

	@Query("SELECT se.smartCardIssued FROM StudentEntity se where se.studentNumber = :studentNumber")
	public String getSmartCardIssuedByStudentNumber(@Param("studentNumber") Integer studentNumber);

	@Modifying
	@Query("UPDATE StudentEntity se set se.smartCardIssued = :smartCardIssued where se.studentNumber = :studentNumber")
	public int updatesmartCardIssuedByStudentNumber(@Param("smartCardIssued") String smartCardIssued, @Param("studentNumber") Integer studentNumber);

}
