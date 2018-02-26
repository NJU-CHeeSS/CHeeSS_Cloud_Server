package edu.nju.cheess.cloudserver.service.impl;

import com.hankcs.hanlp.HanLP;
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

        List<Company> companyList = companyDao.getCompanyByCondition(keyword,
                new PageRequest(page - 1, size));

        List<CompanyMiniBean> miniBeans = new ArrayList<>();
        for (Company company : companyList) {
            List<String> keywords = getKeywordsByIntroduction(company.getIntroduction());
            miniBeans.add(new CompanyMiniBean(company.getId(), company.getName(), company.getIndustry(), keywords));
        }
        res.setResult(miniBeans);
        res.setTotalCount(companyList.size());
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
        Company company = companyDao.getCompanyById(companyId);
        String type=company.getType();
        String industry=company.getIndustry();
        //获得某一类别的公司
        return null;
    }

    @Override
    public List<CompanyMiniBean> getCompaniesRank(String industry) {
        //获得某一行业的公司
        return null;
    }

    @Override
    public CompanyAnalyseBean getCompanyAnalyse(Long companyId) {
        List<Job> jobs=companyDao.getJobs(companyId);
        double maxSalary=jobs.get(0).getHighMoney();
        double minSalary=jobs.get(0).getLowMoney();
        double meanSalary=0;
        for (Job job : jobs) {
            if (job.getHighMoney() > maxSalary) {
                maxSalary = job.getHighMoney();
            }
            if (job.getLowMoney()<minSalary){
                minSalary=job.getLowMoney();
            }
            meanSalary=meanSalary+(job.getHighMoney()+job.getLowMoney())/2.0;
        }
        meanSalary=meanSalary/jobs.size();
        return new CompanyAnalyseBean(maxSalary,minSalary,meanSalary);
    }


    /**
     * 通过企业introduction获得企业关键词
     *
     * @param introduction 企业介绍
     * @return 企业关键词列表
     */
    private List<String> getKeywordsByIntroduction(String introduction) {
        //调用HanLP TextRank算法
        List<String> keywordList = HanLP.extractKeyword(introduction, 3);
        return keywordList;
    }
}
