package za.ac.unisa.myadmin.service.studyquotation.date.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.unisa.myadmin.service.studyquotation.date.model.RegistrationDateEntity;
import za.ac.unisa.myadmin.service.studyquotation.date.model.RegistrationDateIdentity;

import java.util.Date;
import java.util.Optional;

@Repository
public interface StudyQuoteDateRepository extends JpaRepository<RegistrationDateEntity, RegistrationDateIdentity> {

	public Optional<RegistrationDateEntity> getTopByTypeAndSemesterPeriodAndAcademicYear(String type, int semesterPeriod, int academicYear);

	public Optional<RegistrationDateEntity> getTopByTypeAndSemesterPeriodAndAcademicYearAndFromDateLessThanEqualAndToDateGreaterThanEqual(String type, int semesterPeriod, int academicYear, Date sysdate, Date sysDate);

	public Optional<RegistrationDateEntity> getTopByTypeAndSemesterPeriodAndAcademicYearAndFromDateLessThan(String type, int semesterPeriod, int academicYear, Date sysDate);
}
