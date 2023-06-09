package net.edu.module.dao;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.TeachPayEntity;
import net.edu.module.query.TeachPayQuery;
import net.edu.module.vo.StatisticsTeacherVO;
import net.edu.module.vo.TeachPayVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
* 流水账管理
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-09-16
*/
@Mapper
public interface TeachPayDao extends BaseDao<TeachPayEntity> {

    IPage<TeachPayVO> page(Page<TeachPayVO> page, TeachPayQuery query);

    TeachPayVO getPaymentDetail(Long id);

    List<HashMap<String, String>> statisticsAmount();

    List<HashMap<String, String>> statisticsPeople();

    Long statisticsNewPeopleThisMonth();

    Long statisticsNewDealThisMonth();

    Long statisticsNewCommunicateThisMonth();

    Long statisticsNewAuditionThisMonth();

    Long statisticsTotalThisMonth();

    Long statisticsTotalLastMonth();

    StatisticsTeacherVO statisticsTeacher(Long id,String month1,String month2,String month3);
}