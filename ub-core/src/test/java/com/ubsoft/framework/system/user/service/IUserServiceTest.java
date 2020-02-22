package com.ubsoft.framework.system.user.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IUserServiceTest {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    IUserService userService;
    @Test
    void testGet() {
      //User user= userService.get("8abc83307056850e017056850ecb0002");
      //userService.testUser();
      //logger.info(JsonUtil.toJSON(user));



    }
}