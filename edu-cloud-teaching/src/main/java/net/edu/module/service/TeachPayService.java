package net.edu.module.service;


import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.vo.TeachPayVO;
import net.edu.module.query.TeachPayQuery;
import net.edu.module.entity.TeachPayEntity;

import java.util.List;

/**
 * 流水账管理
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-09-16
 */
public interface TeachPayService extends BaseService<TeachPayEntity> {

    PageResult<TeachPayVO> page(TeachPayQuery query);

    void save(TeachPayVO vo);

    void update(TeachPayVO vo);

    void delete(List<Long> idList);

    TeachPayVO getPaymentDetail(Long id);
}