package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.AbilityEntity;
import net.edu.module.query.AbilityQuery;
import net.edu.module.vo.*;

import java.util.List;

public interface AbilityPointService   {


    AbilityMapVO getAbilityMap(Long id,Long userId);


    void save(List<String> list,Long id);

    void update(AbilityPointVO vo);

    void delete(Long id);


    void deleteRelated(Long id);

    void saveRelated(AbilityRelatedVO vo);


    void updateList(List<AbilityPointVO> list);

}
