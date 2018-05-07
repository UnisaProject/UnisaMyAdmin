package za.ac.unisa.myadmin.studyquotation.date.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.unisa.myadmin.studyquotation.date.model.StudyQuoteDateEntity;
import za.ac.unisa.myadmin.studyquotation.date.model.StudyQuoteDateIdentity;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

public interface StudyQuoteDateRepository extends JpaRepository<StudyQuoteDateEntity, StudyQuoteDateIdentity> {

	Optional<StudyQuoteDateEntity> getFirstByTypeAndSemesterPeriodAndAcademicYear(String type, int semesterPeriod, int academicYear);

	Optional<StudyQuoteDateEntity> getFirstByTypeAndSemesterPeriodAndAcademicYearAndFromDateGreaterThanEqualAndToDateLessThanEqual(String type, int semesterPeriod, int academicYear, Instant sysdate, Instant sysDate);

	Optional<StudyQuoteDateEntity> getFirstByTypeAndSemesterPeriodAndAcademicYearAndFromDateGreaterThanEqual(String type, int semesterPeriod, int academicYear, Instant sysDate);
}
