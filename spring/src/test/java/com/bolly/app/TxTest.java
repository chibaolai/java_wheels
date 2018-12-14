package com.bolly.app;

import com.bolly.app.config.TxConfig;
import com.bolly.app.entity.User;
import com.bolly.app.service.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TxTest {

    @Test
    public void testInsert() {
        final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TxConfig.class);
        UserService userService = applicationContext.getBean(UserService.class);
        User user = new User();
        user.setAge(30);
        user.setName("张三");
        userService.insert(user);

        int ret = userService.queryForInt();
        System.out.println(ret);
    }
}
