package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.LessonProblemEntity;
import net.edu.module.query.LessonProblemQuery;
import net.edu.module.vo.LessonProblemVO;
import net.edu.module.vo.ProblemPaperItemEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 课堂练习表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-15
*/
@Mapper
public interface LessonProblemDao extends BaseDao<LessonProblemEntity> {

    int insertProblemList(@Param("list") List<ProblemPaperItemEntity> problemList, Integer paperType, Long lessonId);

    List<LessonProblemVO> selectLessonProblem(@Param("query") LessonProblemQuery query);

    int insertProblemListByTeacher(@Param("list") List<ProblemPaperItemEntity> problemList,Long lessonId);

    IPage<LessonProblemVO> selectUnfinishedPage(Page<LessonProblemVO> ipage, Long userId);
}