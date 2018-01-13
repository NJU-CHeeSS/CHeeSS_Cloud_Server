package edu.nju.cheess.cloudserver.dao;

import edu.nju.cheess.cloudserver.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobDao {

    /**
     * 获得职位信息
     *
     * @param id 职位id
     * @return 职位信息
     */
    Job getJobById(Long id);

    /**
     * 根据关键词和条件分页获得职位列表
     *
     * @param keyword
     * @param pageable
     * @return 企业信息列表
     */
    Page<Job> getJobByCondition(String keyword, Pageable pageable);

    /**
     * 搜索职位列表
     *
     * @param page   page
     * @param offset offset
     * @return 分页列表
     */
    List<Job> getJobs(int page, int offset);
}
