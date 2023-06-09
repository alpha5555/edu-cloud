package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.vo.ChoiceProblemVO;
import net.edu.module.query.ChoiceProblemQuery;
import net.edu.module.entity.ChoiceProblemEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 选择题库表
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-05
 */
public interface ChoiceProblemService extends BaseService<ChoiceProblemEntity> {

    PageResult<ChoiceProblemVO> page(ChoiceProblemQuery query);

    ChoiceProblemVO getChoiceProblem(Long problemId);

    void save(ChoiceProblemVO vo);

    void update(ChoiceProblemVO vo);

    void delete(List<Long> idList);

    void updateStatus(Long problemId);


    void updateUsedNum(Long id);

    void updateSubmitTimes(Long id, Boolean isTrue);

    List<String> getChoiceOptions(Long problemId,int flag);

    ChoiceProblemVO getChoiceProblemInfo(Long problemId);

    void importFromExcel(MultipartFile file);
}