package org.lds.service;

import MD5util.MD5util;
import org.lds.dao.SecKillUserDao;
import org.lds.pojo.SeckillUser;
import org.lds.pojo.vo.LoginVo;
import org.lds.result.CodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class SeckillUserService {
    @Autowired
    SecKillUserDao secKillUserDao;

    public SeckillUser getById(long id) {
        return secKillUserDao.getById(id);
    }

//    public boolean login(HttpServletResponse response, LoginVo loginVo) {
    public CodeMsg login(HttpServletResponse response, LoginVo loginVo) {
        if(loginVo == null) {
            return CodeMsg.MOBILE_EMPTY;
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();

        //判断手机号是否存在
        SeckillUser user = getById(Long.parseLong(mobile));
        if(user == null) {
            return CodeMsg.MOBILE_NOT_EXIST;
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5util.formPassToDBPass(formPass, saltDB);
        System.out.println("dbPass:"+dbPass);
        if(!calcPass.equals(dbPass)) {
            return CodeMsg.PASSWORD_ERROR;
        }
        //生成cookie
//        String token	 = UUIDUtil.uuid();
//        addCookie(response, token, user);
        return CodeMsg.SUCCESS;
    }
}
