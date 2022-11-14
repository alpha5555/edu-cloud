package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.ArchiveExamEntity;
import net.edu.module.query.ArchiveExamQuery;
import net.edu.module.vo.ArchiveExamVO;

/**
 * @author weng
 * @date 2022/11/10 - 16:25
 **/
public interface ArchiveExamService extends BaseService<ArchiveExamEntity> {
    PageResult<ArchiveExamVO> page(ArchiveExamQuery query);

    Integer insertExam();

    ArchiveExamVO selectExamById(Long id);
}
