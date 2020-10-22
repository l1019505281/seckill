package org.lds.service;

import com.alibaba.druid.util.StringUtils;
import org.lds.exception.GlobalException;
import org.lds.redis.RedisService;
import org.lds.redis.SecKillUserKey;
import org.lds.util.MD5util;
import org.lds.dao.SecKillUserDao;
import org.lds.pojo.SeckillUser;
import org.lds.pojo.vo.LoginVo;
import org.lds.result.CodeMsg;
import org.lds.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class SeckillUserService {
    public static final String COOKI_NAME_TOKEN = "token";
    @Autowired
    SecKillUserDao secKillUserDao;

    @Autowired
    RedisService redisService;

    public SeckillUser getById(long id) {
        return secKillUserDao.getById(id);
    }

    public SeckillUser getByToken(HttpServletResponse response, String token) {
        if(StringUtils.isEmpty(token)) {
            return null;
        }
        SeckillUser user = redisService.get(SecKillUserKey.token, token, SeckillUser.class);
        //延长有效期
        if(user != null) {
            addCookie(response, token, user);
        }
        return user;
    }

    public SeckillUser getByToken(String token) {
        if(StringUtils.isEmpty(token)) {
            return null;
        }
        SeckillUser user = redisService.get(SecKillUserKey.token, token, SeckillUser.class);
        return user;
    }

    public boolean login(HttpServletResponse response, LoginVo loginVo) {
        if(loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        SeckillUser user = getById(Long.parseLong(mobile));
        if(user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5util.formPassToDBPass(formPass, saltDB);
        if(!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成cookie
        String token	 = UUIDUtil.uuid();
        addCookie(response, token, user);
        return true;
    }
    private void addCookie(HttpServletResponse response, String token, SeckillUser user) {
        redisService.set(SecKillUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        cookie.setMaxAge(SecKillUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
