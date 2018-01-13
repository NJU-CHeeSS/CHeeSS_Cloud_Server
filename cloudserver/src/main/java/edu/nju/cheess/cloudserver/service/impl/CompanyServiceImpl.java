package edu.nju.cheess.cloudserver.service.impl;

import edu.nju.cheess.cloudserver.bean.CompanyInfoBean;
import edu.nju.cheess.cloudserver.bean.CompanyMiniBean;
import edu.nju.cheess.cloudserver.dao.CompanyDao;
import edu.nju.cheess.cloudserver.entity.Company;
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
        return new CompanyInfoBean(company.getId(), company.getName(), company.getType(),
                company.getIndustry(), company.getSize(), company.getIntroduction());
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
            miniBeans.add(new CompanyMiniBean(company.getId(), company.getName(), company.getIndustry()));
        }
        res.setResult(miniBeans);
        res.setTotalCount((int) companyPage.getTotalElements());
        return res;
    }
}
