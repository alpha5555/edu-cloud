package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ExamEntity;
import net.edu.module.query.ExamQuery;
import net.edu.module.vo.ExamVO;
import net.edu.module.vo.WxExamArrangementVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 考试
 *
 * @author 小樊 babamu@126.com
 * @since 1.0.0 2022-10-09
 */
@Mapper
public interface ExamDao extends BaseDao<ExamEntity> {

    IPage<ExamVO> page(Page<ExamVO> page, @Param("query") ExamQuery query);

    IPage<ExamVO> studentPage(Page<ExamVO> page, @Param("query") ExamQuery query) ;

    List<ExamVO> getExamingList(@Param("userId") Long userId);


    List<WxExamArrangementVO> selectExamArrangement(@Param("vo") ExamVO vo);


    Long insertExam(@Param("vo") ExamVO vo);

    List<String> selectExamClass(Long examId);

    void insertExamClass(Long id, Long classId);

    List<ExamVO> selectAbilityExam();

    List<Long> selectAbilityExamId(Long userId);

    void updateUserAbilityId(Long examId,Long abilityId,Integer score);

    List<ExamVO> selectExamListByClassId(Long classId);

}
