package za.ac.unisa.myadmin.student.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.unisa.myadmin.student.services.models.StudentEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by Adrian on 2018-06-06.
 */
@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

	public List<StudentEntity> findBySurnameIgnoreCase(String surname);

	public List<StudentEntity> findByFirstNamesIgnoreCase(String firstNames);

	public List<StudentEntity> findBySurnameAndFirstNamesAllIgnoreCase(String surname, String firstNames);

	public List<StudentEntity> findByIdentityNumberIgnoreCase(String identityNumber);

	public List<StudentEntity> findByPassportNumberIgnoreCase(String passportNumber);

	public List<StudentEntity> findBySurnameAndFirstNamesAndBirthDateAllIgnoreCase(String surname, String firstNames, Date dateOfBirth);

	public List<StudentEntity> findBySurnameAndFirstNamesAndBirthDateAndIdentityNumberAllIgnoreCase(String surname, String firstNames, Date dateOfBirth, String identityNumber);

	public List<StudentEntity> findBySurnameAndFirstNamesAndBirthDateAndPassportNumberAllIgnoreCase(String surname, String firstNames, Date dateOfBirth, String passportNumber);

	@Query("SELECT se.smartCardIssued FROM StudentEntity se where se.studentNumber = :studentNumber")
	public String getSmartCardIssuedByStudentNumber(@Param("studentNumber") Integer studentNumber);

	@Modifying
	@Query("UPDATE StudentEntity se set se.smartCardIssued = :smartCardIssued where se.studentNumber = :studentNumber")
	public int updatesmartCardIssuedByStudentNumber(@Param("smartCardIssued") String smartCardIssued, @Param("studentNumber") Integer studentNumber);

}
