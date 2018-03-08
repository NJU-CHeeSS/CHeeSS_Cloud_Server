package edu.nju.cheess.cloudserver.repository;

import edu.nju.cheess.cloudserver.entity.ApplyJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyJobRepository extends JpaRepository<ApplyJob, Long> {

    List<ApplyJob> findByUserId(Long userId);

    int countByJobId(Integer jobId);

}
