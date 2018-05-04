package za.ac.unisa.myadmin.studyquotation.date.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.unisa.myadmin.studyquotation.date.model.StudyQuoteDateEntity;
import za.ac.unisa.myadmin.studyquotation.date.model.StudyQuoteDateIdentity;

import java.util.Date;

public interface StudyQuoteDateRepository extends JpaRepository<StudyQuoteDateEntity, StudyQuoteDateIdentity> {

	StudyQuoteDateEntity getFirstByTypeAndSemesterPeriodAndAcademicYear(String type, int semesterPeriod, int academicYear);

	StudyQuoteDateEntity getFirstByTypeAndSemesterPeriodAndAcademicYearAndFromDateGreaterThanEqualAndToDateLessThanEqual(String type, int semesterPeriod, int academicYear, Date sysdate, Date sysDate);

	StudyQuoteDateEntity getFirstByTypeAndSemesterPeriodAndAcademicYearAndFromDateGreaterThanEqual(String type, int semesterPeriod, int academicYear, Date sysdate, Date sysDate);
}
