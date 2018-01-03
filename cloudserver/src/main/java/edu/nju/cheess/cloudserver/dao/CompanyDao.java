package edu.nju.cheess.cloudserver.dao;

import edu.nju.cheess.cloudserver.entity.Company;

import java.util.List;

public interface CompanyDao {

    /**
     * 获得企业信息
     *
     * @param id 企业id
     * @return 企业信息
     */
    Company getCompanyById(Long id);

    /**
     * 搜索企业列表
     *
     * @param page   page
     * @param offset offset
     * @return 分页列表
     */
    List<Company> getCompanies(int page, int offset);
}