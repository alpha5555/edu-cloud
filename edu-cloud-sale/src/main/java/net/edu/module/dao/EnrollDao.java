package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.EnrollEntity;
import net.edu.module.query.EnrollQuery;
import net.edu.module.vo.EnrollVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author weng
 * @date 2023/1/13 - 13:21
 **/
@Mapper
public interface EnrollDao extends BaseDao<EnrollEntity> {


    IPage<EnrollVO> selectEnrollByPage(Page<EnrollVO> page, EnrollQuery query);

    void updateStatus(Long oldId,Long newId);

    Long selectEnroll(Long id);

    EnrollEntity getByMobile(@Param("phone") String phone);
}