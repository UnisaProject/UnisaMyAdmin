package za.ac.unisa.myadmin.exam.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import za.ac.unisa.myadmin.exam.services.models.ExamPeriodEntity;

public interface ExamPeriodRepository extends JpaRepository<ExamPeriodEntity, Integer> {

}
