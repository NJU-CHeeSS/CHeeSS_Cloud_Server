package edu.nju.cheess.cloudserver.service;

import edu.nju.cheess.cloudserver.bean.CompanyInfoBean;
import edu.nju.cheess.cloudserver.bean.CompanyMiniBean;
import edu.nju.cheess.cloudserver.bean.ResultMessageBean;
import edu.nju.cheess.cloudserver.util.Page;

public interface CompanyService {

    /**
     * 获得企业信息
     *
     * @param companyId 企业id
     * @return 企业信息
     */
    CompanyInfoBean getCompanyById(Long companyId);

    /**
     * 搜索企业列表
     *
     * @param keyword 关键字
     * @param order   按什么排序
     * @param size    每页大小
     * @param page    第几页
     * @return 分页列表
     */
    Page<CompanyMiniBean> findCompanyByKeyword(String keyword, String order, int size, int page);

    /**
     * 关注企业
     *
     * @param userId    用户id
     * @param companyId 企业id
     * @return 关注结果
     */
    ResultMessageBean follow(Long userId, Long companyId);

    /**
     * 取关企业
     *
     * @param userId    用户id
     * @param companyId 企业id
     * @return 取关结果
     */
    ResultMessageBean cancelFollow(Long userId, Long companyId);

    /**
     * 获得是否关注企业
     *
     * @param userId    用户id
     * @param companyId 企业id
     * @return 是否关注企业
     */
    boolean checkFollow(Long userId, Long companyId);
}
