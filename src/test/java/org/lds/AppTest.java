package org.lds;

import static org.junit.Assert.assertTrue;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import org.junit.Test;
import org.lds.dao.UserDao;
import org.lds.pojo.User;
import org.lds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Unit test for simple App.
 */
@Controller
public class AppTest 
{
    @Autowired
    UserDao userDao;
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    @Test
    public void testDB()
    {
    }
}
