package net.edu.module.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.edu.framework.common.page.PageResult;
import net.edu.module.entity.TeachClassHoursEntity;
import net.edu.module.entity.UserEntity;
import net.edu.module.query.UserQuery;
import net.edu.module.vo.TeachClassHoursVO;
import net.edu.module.vo.UserVO;

import java.util.List;

/**
 * @author weng
 * @date 2023/1/13 - 13:20
 **/
public interface UserService extends IService<UserEntity> {

    PageResult<UserVO> page(UserQuery query);

    void save(UserVO vo);

    void update(UserVO vo);

    void delete(List<Long> idList);

    UserVO getByMobile(String mobile);

    void insertCadet(UserVO vo);

    List<Integer> selectUserStatus(Long id);

    String selectStuNumber();


    TeachClassHoursVO getStudentPay(Long id);

    List<UserVO> selectSaleName();

    void insertTeachClassHours(Long userId);

    String getReferenceName(Long reference);
}