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

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    public CompanyInfoBean getCompanyByName(String name) {
        Company company = companyDao.getCompanyByName(name);
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
        List<CompanyMiniBean> result=new ArrayList<>();
        //获得某一类别的公司
        List<Company> typeCompanies=companyDao.getCompanyByType(type);
        String[] industries=industry.split("/");
        List<Company> temp=typeCompanies;
        for (String industry1 : industries) {
            temp.retainAll(companyDao.getCompanyByIndustry(industry1));
        }
        result.addAll(temp.stream().map(c -> new CompanyMiniBean(c.getId(), c.getName(), c.getIndustry(), getKeywordsByIntroduction(c.getIntroduction()))).collect(Collectors.toList()));
        return result;
    }

    @Override
    public List<CompanyMiniBean> getCompaniesRank(String industry) {
        Map<Integer,Integer> sizes=new HashMap<>();
        //获得某一行业的公司
        List<Company> companies=companyDao.getCompanyByIndustry(industry);
        for (int i=0;i<companies.size();i++) {
            sizes.put(i,getSizeNumber(companies.get(i).getSize()));
        }
        List<Map.Entry<Integer,Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(sizes.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<Integer,Integer>>() {
            //降序排序
            public int compare(Map.Entry<Integer,Integer> o1,
                               Map.Entry<Integer,Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }

        });
        List<CompanyMiniBean> result=new ArrayList<>();
        Company temp=null;
        for(Map.Entry<Integer,Integer> mapping:list){
            temp=companies.get(mapping.getKey());
            if (temp!=null) {
                result.add(new CompanyMiniBean(temp.getId(), temp.getName(), temp.getIndustry(), getKeywordsByIntroduction(temp.getIntroduction())));
            }
        }
        return result;
    }

    @Override
    public CompanyAnalyseBean getCompanyAnalyse(Long companyId) {
        List<Job> jobs = companyDao.getJobs(companyId);
        double maxSalary = jobs.get(0).getHighMoney();
        double minSalary = jobs.get(0).getLowMoney();
        double meanSalary = 0;
        for (Job job : jobs) {
            if (job.getHighMoney() > maxSalary) {
                maxSalary = job.getHighMoney();
            }
            if (job.getLowMoney() < minSalary) {
                minSalary = job.getLowMoney();
            }
            meanSalary = meanSalary + (job.getHighMoney() + job.getLowMoney()) / 2.0;
        }
        meanSalary = meanSalary / jobs.size();
        return new CompanyAnalyseBean(maxSalary, minSalary, meanSalary);
    }

    @Override
    public String getCompanySize(String name) {
        String rawSize = companyDao.getCompanyByName(name).getSize();
        String regex = "[0-9]+人";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(rawSize);
        StringBuilder numStringBuilder = new StringBuilder();

        if (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                numStringBuilder.append(matcher.group(i));
            }

            String numString = numStringBuilder.toString().substring(0, numStringBuilder.toString().length() - 1);
            int num = Integer.valueOf(numString);
            if (num >= 1000) {
                return "大型企业";
            } else if (num >= 100) {
                return "中型企业";
            } else {
                return "小型企业";
            }

        } else {
            return "未知";
        }
    }

    /**
     * 获得企业规模数字
     * @param size
     * @return
     */
    private int getSizeNumber(String size){
        String regex = "[0-9]+人";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(size);
        StringBuilder numStringBuilder = new StringBuilder();

        if (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                numStringBuilder.append(matcher.group(i));
            }

            String numString = numStringBuilder.toString().substring(0, numStringBuilder.toString().length() - 1);
            int num = Integer.valueOf(numString);
            return num;
        }
        return 0;
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
