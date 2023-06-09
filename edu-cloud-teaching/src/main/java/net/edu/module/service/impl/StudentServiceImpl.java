package net.edu.module.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import net.edu.framework.common.constant.Constant;
import net.edu.framework.common.exception.ServerException;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.framework.common.utils.TreeUtils;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.api.EduSaleApi;
import net.edu.module.convert.UserConvert;
import net.edu.module.dao.UserDao;
import net.edu.module.dao.UserRoleDao;
import net.edu.module.entity.UserEntity;
import net.edu.module.entity.UserRoleEntity;
import net.edu.module.query.RoleUserQuery;
import net.edu.module.query.UserQuery;
import net.edu.module.service.UserRoleService;
import net.edu.module.service.StudentService;
import net.edu.module.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.*;

import static net.edu.framework.common.utils.RedisUtils.MIN_EXPIRE;

/**
 * 用户管理
 *
 * @author 阿沐 babamu@126.com
 */
@Service
@AllArgsConstructor
public class StudentServiceImpl extends BaseServiceImpl<UserDao, UserEntity> implements StudentService {

    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;
    private final RedisUtils redisUtils;
    private final EduSaleApi eduSaleApi;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public PageResult<UserVO> selectStudentList(UserQuery query) {
//        try {
//            query.setRealName(java.net.URLDecoder.decode
//                    (query.getRealName(),"utf-8"));
//        } catch (Exception e){
//            e.printStackTrace();
//        }
        Page<UserVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<UserVO> list = userDao.selectStudentList(page,query);

        return new PageResult<>(list.getRecords(), page.getTotal());
    }

    private Map<String, Object> getParams(UserQuery query) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", query.getUsername());
        params.put("mobile", query.getMobile());
        params.put("gender", query.getGender());
        params.put("roleId", query.getRoleId());
        params.put("realName", query.getRealName());

        // 数据权限
        params.put(Constant.DATA_SCOPE, getDataScope("t1", null));

        return params;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long save(UserVO vo) {
        UserEntity entity = UserConvert.INSTANCE.convert(vo);
        if (entity.getMobile()==null){
            throw new ServerException("请填写手机号");
        }
        if (entity.getOrgId()==null){
            throw new ServerException("请填写机构id");
        }
        // 判断用户名是否存在
        UserEntity user = baseMapper.getByUsername(entity.getUsername());
        if (user != null) {
            throw new ServerException("用户名已经存在");
        }

        // 判断手机号是否存在
        user = baseMapper.getByMobile(entity.getMobile());
        if (user != null) {
            throw new ServerException("手机号已经存在");
        }

        // 保存用户
        baseMapper.insert(entity);

        // 保存用户角色关系
        userRoleService.saveOrUpdate(entity.getId(), vo.getRoleIdList());

        //建立用户课时记录
        eduSaleApi.insertTeachClassHours(entity.getId());

        return entity.getId();
    }

    @Override
    public void update(UserVO vo) {
        UserEntity entity = UserConvert.INSTANCE.convert(vo);

        // 判断用户名是否存在
        UserEntity user = baseMapper.getByUsername(entity.getUsername());
        if (user != null && !user.getId().equals(entity.getId())) {
            throw new ServerException("用户名已经存在");
        }

        // 判断手机号是否存在
        user = baseMapper.getByMobile(entity.getMobile());
        if (user != null && !user.getId().equals(entity.getId())) {
            throw new ServerException("手机号已经存在");
        }

        // 更新用户
        updateById(entity);

        // 更新用户角色关系
        userRoleService.saveOrUpdate(entity.getId(), vo.getRoleIdList());

    }

    @Override
    public void delete(List<Long> idList) {
        // 删除用户
        removeByIds(idList);
        // 删除用户角色关系
        userRoleService.deleteByUserIdList(idList);

    }

    @Override
    public UserVO getByMobile(String mobile) {
        UserEntity user = baseMapper.getByMobile(mobile);

        return UserConvert.INSTANCE.convert(user);
    }

    @Override
    public void updatePassword(Long id, String newPassword) {
        // 修改密码
        UserEntity user = getById(id);
        user.setPassword(newPassword);

        updateById(user);
    }

    @Override
    public PageResult<UserVO> roleUserPage(RoleUserQuery query) {
        // 查询参数
        Map<String, Object> params = getParams(query);
        params.put("roleId", query.getRoleId());

        // 分页查询
        IPage<UserEntity> page = getPage(query);
        params.put(Constant.PAGE, page);

        // 数据列表
        List<UserEntity> list = baseMapper.getRoleUserList(params);

        return new PageResult<>(UserConvert.INSTANCE.convertList(list), page.getTotal());
    }

    @Override
    public void updateSubmitCorrectTimes(Long userId, Integer correct) {
        userDao.updateSubmitCorrectTimes(userId,correct);
    }

    @SneakyThrows
    @Override
    public void studentFromExcel(MultipartFile file) {
        List<Long> list1 = new ArrayList<>();
        list1.add(2L);
        List<UserVO> list= EasyExcel.read(file.getInputStream()).head(UserVO.class).sheet().headRowNumber(3).doReadSync();
        String number = userDao.selectStuNumber();
        setRedisNumber(number);//循环前将当前最大学号加入redis
        Long saleId = userRoleDao.selectSaleId();
        for (UserVO vo:list){
            String stuNumber = getStuNumber();
            vo.setStuNumber(stuNumber);
            vo.setUsername(stuNumber);
            vo.setRoleIdList(list1);
            vo.setStatus(1);
            vo.setPassword("123456");
            vo.setSaleId(saleId);
            vo.setPassword(passwordEncoder.encode(vo.getPassword()));
            save(vo);
        }
    }

    /**
     * 获取学生id
     * @param unionId
     * @return
     */
    @Override
    public String getStudentId(String unionId){
        return userDao.getStudentId(unionId);
    }

    @Override
    public List<OrgVo> getOrgList() {
//        List<OrgEntity> menuList= (List<OrgEntity>) redisUtils.get(RedisKeys.getOrgKey(), RedisUtils.MIN_TEN_EXPIRE);
        List<OrgVo> menuList = baseMapper.getOrgList();
        System.out.println(menuList);
//            redisUtils.set(RedisKeys.getOrgKey(),menuList,RedisUtils.MIN_TEN_EXPIRE);
        return TreeUtils.build(menuList, Constant.ROOT);
    }

    @Override
    public PageResult<TeachStudentVO> getStudents(StudentsVO vo) {
        Page<TeachStudentVO> page = new Page<>(vo.getPage() , vo.getSize());
        IPage<TeachStudentVO> list = userDao.selectStudents(page , vo);
        return new PageResult<>(list.getRecords() , page.getTotal());
    }

    private String getStuNumber(){
        String stuNumber = (String) redisUtils.get("number");
        System.out.println("get方法"+stuNumber);
        long l = Long.parseLong(stuNumber) + 1;
        setRedisNumber(String.valueOf(l));
        if(stuNumber!=null ||stuNumber!=""){
            stuNumber = String.valueOf(l);
        }
        else {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            System.out.println(year);
            stuNumber = (year+"001");
        }

        return stuNumber;
    }

    private void setRedisNumber(String stuNumber){
        redisUtils.set("number",stuNumber,MIN_EXPIRE);
    }

    @Override
    public List<TeachStudentVO> getStuByLessonId(Long id) {
        return userDao.selectStudentsByLessonId(id);
    }


}
