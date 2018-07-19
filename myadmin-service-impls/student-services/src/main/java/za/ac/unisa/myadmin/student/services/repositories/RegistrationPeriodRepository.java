package za.ac.unisa.myadmin.student.services.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import za.ac.unisa.myadmin.student.services.jpa.models.RegistrationPeriodEntity;
import za.ac.unisa.myadmin.student.services.jpa.models.RegistrationPeriodEntityId;

public interface RegistrationPeriodRepository extends JpaRepository<RegistrationPeriodEntity, RegistrationPeriodEntityId> {

	public List<RegistrationPeriodEntity> findByAcademicYear(Integer year);

	public List<RegistrationPeriodEntity> findBySemesterPeriod(Integer semester);

	public List<RegistrationPeriodEntity> findByType(String type);

	public List<RegistrationPeriodEntity> findByAcademicYearAndSemesterPeriod(Integer year, Integer semester);

	public List<RegistrationPeriodEntity> findByAcademicYearAndType(Integer year, String type);

	public List<RegistrationPeriodEntity> findBySemesterPeriodAndType(Integer semester, String type);

	public List<RegistrationPeriodEntity> findByAcademicYearAndSemesterPeriodAndType(Integer year, Integer semester,
			String type);

	public List<RegistrationPeriodEntity> findByAcademicYearAndEffectiveDateAfterAndExpirationDateBefore(Integer year,
			Date effectiveDate, Date expirationDate);

	public List<RegistrationPeriodEntity> findBySemesterPeriodAndEffectiveDateAfterAndExpirationDateBefore(
			Integer semester, Date effectiveDate, Date expirationDate);

	public List<RegistrationPeriodEntity> findByTypeAndEffectiveDateAfterAndExpirationDateBefore(String type,
			Date effectiveDate, Date expirationDate);

	public List<RegistrationPeriodEntity> findByAcademicYearAndSemesterPeriodAndEffectiveDateAfterAndExpirationDateBefore(
			Integer year, Integer semester, Date effectiveDate, Date expirationDate);

	public List<RegistrationPeriodEntity> findByAcademicYearAndTypeAndEffectiveDateAfterAndExpirationDateBefore(
			Integer year, String type, Date effectiveDate, Date expirationDate);

	public List<RegistrationPeriodEntity> findBySemesterPeriodAndTypeAndEffectiveDateAfterAndExpirationDateBefore(
			Integer semester, String type, Date effectiveDate, Date expirationDate);

	public List<RegistrationPeriodEntity> findByAcademicYearAndSemesterPeriodAndTypeAndEffectiveDateAfterAndExpirationDateBefore(
			Integer year, Integer semester, String type, Date effectiveDate, Date expirationDate);

	public List<RegistrationPeriodEntity> findByAcademicYearAndSemesterPeriodAndTypeAndExpirationDateBefore(
			Integer year, Integer semester, String type, Date expirationDate);

}
