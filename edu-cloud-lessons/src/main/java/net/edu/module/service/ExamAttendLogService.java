package net.edu.module.service;

import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.ExamAttendLogEntity;
import net.edu.module.entity.ExamEntity;
import net.edu.module.query.ExamAttendLogQuery;
import net.edu.module.vo.ExamAttendLogVO;

import java.util.List;

/**
 * 课堂签到表
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-15
 */
public interface ExamAttendLogService extends BaseService<ExamAttendLogEntity> {

    List<ExamAttendLogVO> list(ExamAttendLogQuery query);

    Boolean attendance(Long userId, ExamEntity lessonEntity);

    void save(ExamAttendLogVO vo);

    void update(ExamAttendLogVO vo);

    void delete(List<Long> idList);

    void copyUserFromClassUser(List<Long> userList,Long lessonId);

    void updateStudents(ExamAttendLogVO vo);


}