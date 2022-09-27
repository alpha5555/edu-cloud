package net.edu.module.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import lombok.AllArgsConstructor;
import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.framework.common.utils.Result;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.api.EduTeachApi;
import net.edu.module.convert.LessonAttendLogConvert;
import net.edu.module.dao.LessonAttendLogDao;
import net.edu.module.entity.LessonAttendLogEntity;
import net.edu.module.entity.LessonEntity;
import net.edu.module.query.LessonAttendLogQuery;
import net.edu.module.service.LessonAttendLogService;
import net.edu.module.service.LessonService;
import net.edu.module.vo.LessonAttendLogVO;
import net.edu.module.vo.LessonVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static java.lang.Math.abs;

/**
 * 课堂签到表
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-15
 */
@Service
@AllArgsConstructor
 public class LessonAttendLogServiceImpl extends BaseServiceImpl<LessonAttendLogDao, LessonAttendLogEntity> implements LessonAttendLogService {

    private final RedisUtils redisUtils;
    private final LessonAttendLogDao lessonAttendLogDao;

    @Override
    public List<LessonAttendLogVO> list(LessonAttendLogQuery query) {
        
        List<LessonAttendLogVO> list=null;
        list= (List<LessonAttendLogVO>) redisUtils.get(RedisKeys.getLessonAttendLog(query.getLessonId()),RedisUtils.MIN_TEN_EXPIRE);
        if(CollectionUtil.isEmpty(list)){
            list = baseMapper.selectStudentsList(query);
            redisUtils.set(RedisKeys.getLessonAttendLog(query.getLessonId()),list,RedisUtils.MIN_TEN_EXPIRE);
        }
        return list;
    }


    //名单校验加签到
    @Override
    public Boolean attendance(Long userId,LessonEntity lessonEntity) {
        Date date=new Date();
        List<LessonAttendLogVO> userList=list(new LessonAttendLogQuery(lessonEntity.getId()));
        if(!CollectionUtil.isEmpty(userList)){
            for (LessonAttendLogVO vo:userList){
                if(vo.getStuId().equals(userId)){
                    if(lessonEntity.getBeginTime().getTime()>date.getTime() && lessonEntity.getEndTime().getTime()<date.getTime()){
                        //不在上课范围，直接进入课堂
                        return true;
                    }
                    //在课堂范围则签到
                    if(vo.getStatus()!=1){
                        vo.setStatus(1);
                        vo.setCheckinTime(new Date());
                        LessonAttendLogEntity entity = LessonAttendLogConvert.INSTANCE.convert(vo);
                        updateById(entity);
                        redisUtils.set(RedisKeys.getLessonAttendLog(lessonEntity.getId()),userList,RedisUtils.MIN_TEN_EXPIRE);
                    }
                    return true;
                }
            }
        }
        return false ;
    }


    @Override
    public void save(LessonAttendLogVO vo) {
        LessonAttendLogEntity entity = LessonAttendLogConvert.INSTANCE.convert(vo);
        baseMapper.insert(entity);
        redisUtils.del(RedisKeys.getLessonAttendLog(vo.getLessonId()));

    }

    @Override
    public void update(LessonAttendLogVO vo) {
        LessonAttendLogEntity entity = LessonAttendLogConvert.INSTANCE.convert(vo);
        updateById(entity);
        redisUtils.del(RedisKeys.getLessonAttendLog(vo.getLessonId()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public void copyUserFromClassUser(List<Long> userList,Long lessonId) {
        if(!CollectionUtil.isEmpty(userList)){
            baseMapper.insertUserList(userList,lessonId);
        }
        redisUtils.del(RedisKeys.getLessonAttendLog(lessonId));
    }

    @Override
    public void updateStudents(LessonAttendLogVO vo) {
        vo.setUpdateTime(new Date());
        if(vo.getStuId() == null){
            vo.setStuId(SecurityUser.getUserId());
        }
        //根据学生id和课堂id找到唯一的记录进行修改
        lessonAttendLogDao.updateStudents(vo);
        redisUtils.del(RedisKeys.getLessonAttendLog(vo.getLessonId()));
    }





}