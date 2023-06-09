package net.edu.framework.security.filter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.edu.framework.common.utils.UserTokenContext;
import net.edu.framework.security.cache.TokenStoreCache;
import net.edu.framework.security.user.UserDetail;
import net.edu.framework.security.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证过滤器
 *
 * @author 阿沐 babamu@126.com
 */
@Component
@Slf4j
@AllArgsConstructor
public class AuthenticationTokenFilter extends OncePerRequestFilter {
    private final TokenStoreCache tokenStoreCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String accessToken = TokenUtils.getAccessToken(request);
        // accessToken为空，表示未登录
        if (StringUtils.isBlank(accessToken)) {
            chain.doFilter(request, response);
            return;
        }

        // 获取登录用户信息
        UserDetail user = tokenStoreCache.getUser(accessToken);

        if (user == null) {
            chain.doFilter(request, response);
            return;
        }

        // 用户存在
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        //保存用户token
        initUserInfo(accessToken);
        // 新建 SecurityContext
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        chain.doFilter(request, response);
    }

    private void initUserInfo(String accessToken) {
        if (accessToken!=null) {
            try {
                //将Token放入当前线程上下文中
                UserTokenContext.setToken(accessToken);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
    }

}
