package net.edu.module.dao;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ArchiveAssessEntity;
import net.edu.module.query.ArchiveAssessQuery;
import net.edu.module.vo.ArchiveAssessByCourseIdVo;
import net.edu.module.vo.ArchiveAssessVO;
import net.edu.module.vo.ArchivePointAndTargetVO;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

/**
* 考核点
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-26
*/
@Mapper
public interface ArchiveAssessDao extends BaseDao<ArchiveAssessEntity> {

    IPage<ArchiveAssessVO> selectArchiveAssessByPage(Page<ArchiveAssessVO> page, ArchiveAssessQuery query);

    List<ArchiveAssessVO> selectName();

    Integer insertArchiveAccess1(ArchiveAssessEntity vo);

    ArchiveAssessVO selectArchiveAssessById(Long id);

    void updateArchiveAssess1(ArchiveAssessVO vo);

    void updateArchiveAssess2(ArchiveAssessVO vo);

    List<ArchiveAssessVO> selectAssessByCourseId(Long courseId);

    void insertArchiveAssess1(ArchiveAssessVO vo);

    void updateArchiveAssess3(ArchiveAssessVO vo);

    ArchiveAssessVO selectSummaryStep2(String courseId);

    List<ArchiveAssessByCourseIdVo> selectAssessBycourseId(String courseId);

    void updateByCourseId(Integer courseId, Integer assessId);
    void updateByTargetId(Integer courseId, Integer assessId);

    void insertAssessWeight(ArchiveAssessByCourseIdVo archiveAssessByCourseIdVo);

    List<ArchiveAssessByCourseIdVo> selectAssessByTargetId(String targetId);

    String selectArchiveAssessIdJuge(Integer assessId, Integer targetId);


    void updateAssessWeight(ArchiveAssessByCourseIdVo archiveAssessByCourseIdVo);

    void updateEvaluation(ArchivePointAndTargetVO assess);

    BigDecimal selectWeightSum(ArchiveAssessByCourseIdVo assess);

    Integer selectTargetByCourseId(Integer courseId);

    Integer selectAssessSum(ArchiveAssessByCourseIdVo assess);

    List<BigDecimal> selectTargetWeightArr(ArchiveAssessByCourseIdVo assess);

    List<Integer> selectTargetId(Integer courseId);

    List<BigDecimal> selectWeightByT(Integer integer);

    List<Integer> selectAssessId(ArchiveAssessByCourseIdVo assess);

    BigDecimal selectWeight(Integer targetId, Integer assessId);

    List<String> selectAssessName(Integer courseId);

    List<String> selectTargetName(Integer courseId);

    void deleteAssess(Long courseId, Long targetId,Long assessId);

    Integer insertAssessManner(ArchiveAssessByCourseIdVo assess);

    void updateAssessManner(ArchiveAssessByCourseIdVo assess);

    String selectArchiveMannerJuge(Integer assessId, Integer targetId);

    BigDecimal selectMannerWeight(String courseId);

    Integer selectAssessNumPq(String courseId);

    Integer selectAssessSumPq(String courseId);

    List<String> selectAssessNamePq(String courseId);

    List<BigDecimal> selectTargetWeightArrPq(String courseId);

    String selectArchiveNowMannerId();
    List<ArchiveAssessVO> selectWeightById(String id);
}
