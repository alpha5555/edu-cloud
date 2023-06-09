package net.edu.module.service;


import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.LessonAttendLogEntity;
import net.edu.module.entity.LessonEntity;
import net.edu.module.query.LessonAttendLogQuery;
import net.edu.module.vo.LessonAttendLogVO;
import net.edu.module.vo.LessonStudentVO;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

/**
 * 课堂签到表
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-15
 */
public interface LessonAttendLogService extends BaseService<LessonAttendLogEntity> {

    List<LessonAttendLogVO> list(LessonAttendLogQuery query);


    Boolean attendance(Long userId, LessonEntity lessonEntity);

    void save(LessonAttendLogVO vo);

    void update(LessonAttendLogVO vo);

    void delete(List<Long> idList);

    void copyUserFromClassUser(List<Long> userList,Long lessonId);

    void updateStudents(LessonAttendLogVO vo);

    void insertLessonList(LessonStudentVO vo);

    void deleteLessonList(LessonStudentVO vo);

    void giveOrResetLikes(Long lessonId,Long stuId,Integer type);
}