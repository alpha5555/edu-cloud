package net.edu.system.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.system.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 角色管理
 * 
 * @author 阿沐 babamu@126.com
 */
@Mapper
public interface SysRoleDao extends BaseDao<SysRoleEntity> {

    /**
     * 根据用户ID，获取用户最大的数据范围
     */
    Integer getDataScopeByUserId(@Param("userId") Long userId);

}
