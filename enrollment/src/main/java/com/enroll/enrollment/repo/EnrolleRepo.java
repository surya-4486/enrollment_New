package com.enroll.enrollment.repo;

import com.enroll.enrollment.model.Enrollees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrolleRepo extends JpaRepository<Enrollees, Long> {
}
