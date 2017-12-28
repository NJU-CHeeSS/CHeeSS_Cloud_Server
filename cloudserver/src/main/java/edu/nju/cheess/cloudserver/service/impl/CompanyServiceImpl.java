package edu.nju.cheess.cloudserver.service.impl;

import edu.nju.cheess.cloudserver.bean.CompanyInfoBean;
import edu.nju.cheess.cloudserver.bean.CompanyMiniBean;
import edu.nju.cheess.cloudserver.bean.ResultMessageBean;
import edu.nju.cheess.cloudserver.service.CompanyService;
import edu.nju.cheess.cloudserver.util.Page;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Override
    public CompanyInfoBean getCompanyById(Long companyId) {
        return null;
    }

    @Override
    public Page<CompanyMiniBean> findCompanyByKeyword(String keyword, String order, int size, int page) {
        return null;
    }

    @Override
    public ResultMessageBean follow(Long userId, Long companyId) {
        return null;
    }

    @Override
    public ResultMessageBean cancelFollow(Long userId, Long companyId) {
        return null;
    }

    @Override
    public boolean checkFollow(Long userId, Long companyId) {
        return false;
    }
}
