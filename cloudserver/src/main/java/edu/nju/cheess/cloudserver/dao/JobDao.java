package edu.nju.cheess.cloudserver.dao;

import edu.nju.cheess.cloudserver.entity.Job;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobDao {

    /**
     * 根据职业id获得职位信息
     *
     * @param id 职位id
     * @return 职位信息
     */
    Job getJobById(Long id);

    /**
     * 根据职业类型获得职位列表
     *
     * @param jobType 职位类型
     * @return 职位列表
     */
    List<Job> getJobByJobType(List<String> jobType);

    /**
     * 根据职业类型和城市获得职位列表
     *
     * @param jobType 职位类型
     * @return 职位列表
     */
    List<Job> getJobByJobTypeAndCity(List<String> jobType, String city);

    /**
     * 根据关键词和条件分页获得职位列表
     *
     * @param keyword  关键词
     * @param pageable 分页信息
     * @return 职业列表
     */
    List<Job> getJobByCondition(String keyword, String location, String education, String time, Pageable pageable);

    /**
     * 根据关键词分页获得职位列表
     *
     * @param keyword  关键词
     * @param pageable 分页信息
     * @return 职业列表
     */
    List<Job> getJobByCondition(String keyword, Pageable pageable);

    /**
     * 获取职业
     *
     * @param pageable 分页信息
     * @return 职业列表
     */
    List<Job> getJobs(Pageable pageable);

    /**
     * 获取推荐职业
     *
     * @param diploma   学历
     * @param skills    技能列表
     * @return          职业列表
     */
    List<Job> getRecommendJobs(String location, String diploma, List<String> skills);

}
