package za.ac.unisa.myadmin.service.exam.period.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.ac.unisa.myadmin.service.exam.period.model.ExamPeriodEntity;

@Repository
public interface ExamPeriodRepository extends JpaRepository<ExamPeriodEntity, Integer> {

}
