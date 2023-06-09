package net.edu.module.service;


import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.vo.LessonResourceVO;
import net.edu.module.entity.LessonResourceEntity;

import java.util.List;

/**
 * 教学日历资源表
 *
 * @author 马佳浩 babamu@126.com
 * @since 1.0.0 2022-09-15
 */
public interface LessonResourceService extends BaseService<LessonResourceEntity> {



    void save(LessonResourceVO vo);


    void copyFromPlanItem(Long planItemId,Long lessonId);

    List<LessonResourceVO> getLessonResource(Long lessonId);

    void deleteResource(Long id);
}