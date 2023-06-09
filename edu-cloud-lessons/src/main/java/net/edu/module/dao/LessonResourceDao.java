package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.LessonResourceEntity;
import net.edu.module.vo.LessonResourceVO;
import net.edu.module.vo.TeachPlanItemResourceVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 教学日历资源表
*
* @author 马佳浩 babamu@126.com
* @since 1.0.0 2022-09-15
*/
@Mapper
public interface LessonResourceDao extends BaseDao<LessonResourceEntity> {
    List<LessonResourceVO> selectLessonResource(@Param("lessonId") Long lessonId);


    void insertResourceList(@Param("list") List<TeachPlanItemResourceVO> list, Long lessonId);

    int deleteResource(@Param("id") Long id);
}