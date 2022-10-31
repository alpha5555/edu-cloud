package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ArchiveWeightTargetAssessEntity;
import net.edu.module.vo.ArchiveWeightTargetAssessVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* 考核点权重
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-29
*/
@Mapper
public interface ArchiveWeightTargetAssessDao extends BaseDao<ArchiveWeightTargetAssessEntity> {

    List<ArchiveWeightTargetAssessVO> selectAssessByTargetId(Long targetId);

    Integer insertAssessWeight(List<ArchiveWeightTargetAssessVO> assessVOS);
}