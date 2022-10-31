package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.vo.ExamRecordQuery;
import net.edu.module.vo.ExamVO;
import net.edu.module.query.ExamQuery;
import net.edu.module.entity.ExamEntity;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * 考试
 *
 * @author 小樊 babamu@126.com
 * @since 1.0.0 2022-10-09
 */
public interface ExamService extends BaseService<ExamEntity> {

    PageResult<ExamVO> page(ExamQuery query);

    ExamEntity get(Long examId);

    void save(ExamVO vo);

    void update(ExamVO vo);

    void delete(List<Long> idList);

    PageResult<ExamVO> studentPage(ExamQuery query);

    List<ExamVO> getExamingList(Long userId);

    ExamVO getPaper(Long id);

    void updateExamIndex(Long examId);

    void submitPaper(Long examId);

    void exportExam(@Valid ExamRecordQuery query, HttpServletResponse response) throws IOException;
}