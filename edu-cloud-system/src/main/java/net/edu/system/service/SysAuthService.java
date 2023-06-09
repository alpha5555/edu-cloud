package net.edu.system.service;

import net.edu.system.vo.SysAccountLoginVO;
import net.edu.system.vo.SysTokenVO;
import net.edu.system.vo.SysWeChatLoginVO;

/**
 * 权限认证服务
 *
 * @author 阿沐 babamu@126.com
 */
public interface SysAuthService {

    /**
     * 账号密码登录
     *
     * @param login 登录信息
     */
    SysTokenVO loginByAccount(SysAccountLoginVO login);


    SysTokenVO loginByUnionId(SysWeChatLoginVO login);

    /**
     * 退出登录
     *
     * @param accessToken accessToken
     */
    void logout(String accessToken);

    Boolean checkUserAndPassword(String username, String password);
}
