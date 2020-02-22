package com.ubsoft.framework.core.dal.mybatis.mapper;

import com.ubsoft.framework.core.util.UUIDUtil;
import com.ubsoft.framework.system.user.entity.User;
import com.ubsoft.framework.system.user.entity.Code;

import com.ubsoft.framework.system.user.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class InsertMapperTest {
    @Autowired
    UserMapper userMapper;
    @Test
    void save() {

        User user = new User();
        user.setUserKey(UUIDUtil.randomUUID());
        user.setUserName("chenkuangfeng");
        user.setOrgKey("0001");
        user.setOwnerKey("0003");
        int i=userMapper.save(user);
        System.out.println("save：插入"+i+"记录。");
    }
    @Test
    void batchSave() {
        User user = new User();
        user.setUserKey(UUIDUtil.randomUUID());
        user.setUserName("陈矿峰");
        user.setOrgKey("0001");
        user.setOwnerKey("0001");
        User user2 = new User();
        user2.setUserKey(UUIDUtil.randomUUID());
        user2.setUserName("chenkuaccngfeng");
        user2.setOrgKey("0001");
        user2.setOwnerKey("0001");
        ArrayList<User> lsUser = new ArrayList<User>();
        lsUser.add(user);
        lsUser.add(user2);
        int i =userMapper.batchSave(lsUser);
        System.out.println("batchSave：插入"+i+"记录。");
    }


}