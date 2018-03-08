package edu.nju.cheess.cloudserver.repository;

import edu.nju.cheess.cloudserver.entity.ApplyJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyJobRepository extends JpaRepository<ApplyJob, Long> {

    ApplyJob findByUserIdAndJobId(Long userId, Long JobId);

    List<ApplyJob> findByUserId(Long userId);

    int countByJobId(Long jobId);

}
