package za.ac.unisa.myadmin.exam.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.ac.unisa.myadmin.exam.services.models.ExamPeriodEntity;

@Repository
public interface ExamPeriodRepository extends JpaRepository<ExamPeriodEntity, Integer> {

}
