package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ArchiveCourseSummaryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 课程总结
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-11-14
*/
@Mapper
public interface ArchiveCourseSummaryDao extends BaseDao<ArchiveCourseSummaryEntity> {
	
}