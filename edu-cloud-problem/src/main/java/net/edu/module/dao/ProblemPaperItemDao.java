package net.edu.module.dao;


import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ProblemPaperItemEntity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
* 问题卷关联问题表
*
* @author 樊磊 
* @since 1.0.0 2022-09-06
*/
@Mapper
public interface ProblemPaperItemDao extends BaseDao<ProblemPaperItemEntity> {
     List<ProblemPaperItemEntity> selectPageRecords(Long paperId);

     int delete(Long paperId);

     int insert(@Param("list") List<ProblemPaperItemEntity> list);
}