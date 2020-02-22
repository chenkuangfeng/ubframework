package com.ubsoft.framework.core.dal.mybatis.mapper;

import com.ubsoft.framework.system.user.entity.User;
import com.ubsoft.framework.system.user.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DeleteMapperTest {
    @Autowired
    UserMapper userMapper;
    @Test
    void delete() {
        List<User> users=userMapper.selectAll(User.class);
        User user=users.get(0);
        userMapper.delete(user);

    }
    @Test
    void deleteById() {
        List<User> users=userMapper.selectAll(User.class);
        System.out.println(users.size());
        String id =users.get(0).getId();
        userMapper.deleteById(User.class,id);
        List<User> users2=userMapper.selectAll(User.class);
        System.out.println(users2.size());
    }
    @Test
    void deleteByIds() {
        List<User> users=userMapper.selectAll(User.class);
        System.out.println(users.size());
        String [] ids=new String[users.size()-1];
        int i=0;
        for(User user:users){
            if(i<ids.length-1)
                ids[i]=user.getId();
            i++;

        }
        userMapper.deleteByIds(User.class,ids);
        List<User> users2=userMapper.selectAll(User.class);
        System.out.println(users2.size());
    }
    @Test
    void batchDelete() {
        List<User> users=userMapper.selectAll(User.class);
        System.out.println(users.size());
        userMapper.batchDelete(users);
        List<User> users2=userMapper.selectAll(User.class);
        System.out.println(users2.size());
    }




}