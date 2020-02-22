package com.ubsoft.framework.core.dal.mybatis.mapper;

import com.ubsoft.framework.core.util.JsonUtil;
import com.ubsoft.framework.core.util.UUIDUtil;
import com.ubsoft.framework.system.user.entity.User;
import com.ubsoft.framework.system.user.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UpdateMapperTest {

    @Autowired
    UserMapper userMapper;
    @Test
    void update() {
        List<User> users=userMapper.selectAll(User.class);
        User user=users.get(0);
        user.setUserName(UUIDUtil.randomUUID());
        int i= userMapper.update(user);
        System.out.println("update:"+i+"记录.");
        System.out.println(JsonUtil.toJSON(user));
        i= userMapper.update(user);
        System.out.println("update:"+i+"记录.");
        System.out.println(JsonUtil.toJSON(user));
    }
    @Test
    void updateSelective() {
        List<User> users=userMapper.selectAll(User.class);
        User user=users.get(0);
        System.out.println(JsonUtil.toJSON(user));
        user.setUserName(UUIDUtil.randomUUID());
        int i= userMapper.update(user);
        System.out.println("update:"+i+"记录.");
        System.out.println(JsonUtil.toJSON(user));
        i= userMapper.updateOption("userName",user);
        System.out.println("update:"+i+"记录.");
        System.out.println(JsonUtil.toJSON(user));
    }
}