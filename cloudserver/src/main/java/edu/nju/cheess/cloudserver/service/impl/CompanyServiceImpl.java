package edu.nju.cheess.cloudserver.service.impl;

import edu.nju.cheess.cloudserver.bean.CompanyAnalyseBean;
import edu.nju.cheess.cloudserver.bean.CompanyInfoBean;
import edu.nju.cheess.cloudserver.bean.CompanyMiniBean;
import edu.nju.cheess.cloudserver.bean.JobInfoBean;
import edu.nju.cheess.cloudserver.dao.CompanyDao;
import edu.nju.cheess.cloudserver.entity.Company;
import edu.nju.cheess.cloudserver.entity.Job;
import edu.nju.cheess.cloudserver.service.CompanyService;
import edu.nju.cheess.cloudserver.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Override
    public CompanyInfoBean getCompanyById(Long companyId) {
        Company company = companyDao.getCompanyById(companyId);
        List<String> keywords = getKeywordsByIntroduction(company.getIntroduction());
        return new CompanyInfoBean(company.getId(), company.getName(), company.getType(),
                company.getIndustry(), company.getSize(), company.getIntroduction(), keywords);
    }

    @Override
    public Page<CompanyMiniBean> findCompanyByKeyword(String keyword, int size, int page) {

        edu.nju.cheess.cloudserver.util.Page<CompanyMiniBean> res = new edu.nju.cheess.cloudserver.util.Page<>();
        res.setSize(size);
        res.setPage(page);

        org.springframework.data.domain.Page<Company> companyPage = companyDao.getCompanyByCondition(keyword,
                new PageRequest(page - 1, size));

        List<CompanyMiniBean> miniBeans = new ArrayList<>();
        for (Company company : companyPage.getContent()) {
            List<String> keywords = getKeywordsByIntroduction(company.getIntroduction());
            miniBeans.add(new CompanyMiniBean(company.getId(), company.getName(), company.getIndustry(), keywords));
        }
        res.setResult(miniBeans);
        res.setTotalCount((int) companyPage.getTotalElements());
        return res;
    }

    @Override
    public List<JobInfoBean> getJobs(Long companyId) {
        List<JobInfoBean> jobInfoBeans = new ArrayList<>();
        List<Job> jobs = companyDao.getJobs(companyId);
        for (Job job : jobs) {
            jobInfoBeans.add(new JobInfoBean(job.getId(), job.getTitle(), job.getCompany(), job.getJobType(),
                    (int) job.getLowMoney(), (int) job.getHighMoney(), job.getLocation(), job.getDate(), job.getEducation(),
                    job.getTotalPeople(), job.getLowExperience(), job.getHighExperience()));
        }

        return jobInfoBeans;
    }

    @Override
    public List<CompanyMiniBean> getPopularCompanies() {
        List<CompanyMiniBean> companyMiniBeans = new ArrayList<>();
        List<Company> companies = companyDao.getPopularCompanies();
        for (Company company : companies) {
            List<String> keywords = getKeywordsByIntroduction(company.getIntroduction());
            companyMiniBeans.add(new CompanyMiniBean(company.getId(), company.getName(), company.getIndustry(), keywords));
        }
        return companyMiniBeans;
    }

    @Override
    public List<CompanyMiniBean> getRelatedCompanies(Long companyId) {
        return null;
    }

    @Override
    public List<CompanyMiniBean> getCompaniesRank(String industry) {
        return null;
    }

    @Override
    public CompanyAnalyseBean getCompanyAnalyse(Long companyId) {
        return null;
    }


    /**
     * 通过企业introduction获得企业关键词
     *
     * @param introduction 企业介绍
     * @return 企业关键词列表
     */
    private List<String> getKeywordsByIntroduction(String introduction) {

        // TODO
        return null;
    }
}
