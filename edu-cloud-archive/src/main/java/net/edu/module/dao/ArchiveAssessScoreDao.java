package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ArchiveAssessScoreEntity;
import net.edu.module.entity.ArchiveTestScoreEntity;
import net.edu.module.vo.ArchiveAssessNameToGoal;
import net.edu.module.vo.ArchiveAssessScoreVO;
import net.edu.module.vo.ArchiveExamAttendLogVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 考试成绩表
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-11-16
 */
@Mapper
public interface ArchiveAssessScoreDao extends BaseDao<ArchiveAssessScoreEntity> {

    List<ArchiveAssessScoreEntity> selectAssessScoreByCourseId(Long summaryId);

    String selectAssessScore(Long assessId,String stuId,Long summaryId);

    List<ArchiveAssessNameToGoal> selectAssessByIds(String courseId,String stuId);
}
