package edu.nju.cheess.cloudserver.dao.impl;

import edu.nju.cheess.cloudserver.dao.HBaseHelper;
import edu.nju.cheess.cloudserver.dao.JobDao;
import edu.nju.cheess.cloudserver.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JobDaoImpl implements JobDao {

    @Autowired
    private HBaseHelper hBaseHelper;

    @Override
    public Job getJobById(Long id) {
        return null;
    }

    @Override
    public List<Job> getJobs(int page, int offset) {
        return null;
    }
}
