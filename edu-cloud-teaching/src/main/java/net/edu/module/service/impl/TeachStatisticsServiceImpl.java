package net.edu.module.service.impl;

import lombok.AllArgsConstructor;
import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.module.dao.TeachStatisticsDao;
import net.edu.module.vo.TeachStatisticsAddInfoVO;
import net.edu.module.vo.TeachStatisticsInfoVO;
import net.edu.module.service.TeachStatisticsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public  class TeachStatisticsServiceImpl  implements TeachStatisticsService {

    private final TeachStatisticsDao statisticsDao;
    private final RedisUtils redisUtils;


    @Override
    public TeachStatisticsInfoVO getStatisticsInfo() {
        TeachStatisticsInfoVO statisticsInfo=null;
        statisticsInfo= (TeachStatisticsInfoVO) redisUtils.get(RedisKeys.getTeachStatistics());
        if(statisticsInfo==null){
            //最近一次统计的题库总量、用户总量、问题提交总量以及前一天用户总量//统计最近30天用户活跃量//统计最近7天的每天题目提交趋势
           statisticsInfo = statisticsDao.selectStatisticsInfo();
            redisUtils.set(RedisKeys.getTeachStatistics(),statisticsInfo,RedisUtils.HOUR_SIX_EXPIRE);
        }
        statisticsInfo.setTeachStatisticsAddInfoVO(getStatisticsAddInfo());
        return statisticsInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void statisticsHomeInfo(){
        //统计题库日增量/用户日增量/统计问题提交日增量/用户每天活跃量并将统计结果存入统计表中
        statisticsDao.insertStatisticsInfo();
        redisUtils.del(RedisKeys.getTeachStatistics());
    }

    @Override
    public TeachStatisticsAddInfoVO getStatisticsAddInfo() {
        TeachStatisticsAddInfoVO statisticsAddInfoVO=null;
        statisticsAddInfoVO= (TeachStatisticsAddInfoVO) redisUtils.get(RedisKeys.getTeachStatisticsAdd());
        if(statisticsAddInfoVO==null){
            //统计当日增量
            statisticsAddInfoVO = statisticsDao.selectStatisticsAddInfo();
            redisUtils.set(RedisKeys.getTeachStatisticsAdd(),statisticsAddInfoVO,RedisUtils.HOUR_ONE_EXPIRE);
        }
        return statisticsAddInfoVO;
    }

}
