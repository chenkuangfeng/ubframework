package com.ubsoft.framework.core.dal.mybatis.mapper;

import com.ubsoft.framework.core.dal.model.Bio;
import com.ubsoft.framework.core.util.JsonUtil;
import com.ubsoft.framework.system.user.entity.User;
import com.ubsoft.framework.system.user.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SelectMapperTest {

    @Autowired
    UserMapper userMapper;
    @Test
    void Select(){
        System.out.println("----------------------select ALL--------------------------------------");
        List<User> users=userMapper.selectAll(User.class);
        System.out.println("执行完毕,共"+users.size()+"条记录");
        System.out.println("----------------------select--------------------------------------");
        users=userMapper.select(User.class,"userKey","1");
        System.out.println("执行完毕,共"+users.size()+"条记录");
        System.out.println("----------------------selectOption--------------------------------------");
        users=userMapper.selectOption(User.class,"userKey,userName","userKey,userName",new Object[]{"8abc83307056850e017056850f1a0004","chenkuangfeng"});
        System.out.println("执行完毕,共"+users.size()+"条记录");
    }
    @Test
    void get() {
        User user=  userMapper.get(User.class,"402807817052f888017052f888ce0002");
        System.out.println(JsonUtil.toJSON(user));
    }
    @Test
    void queryBio(){
        String sql="select * from sa_user where id =#{p.id}";
        Map map=new HashMap();
        map.put("id","40280781705883540170588355050002");
       List<Bio> bio= userMapper.queryBio(sql,map);
       System.out.println(JsonUtil.toJSON(bio));

    }
    @Test
    void query(){
        String sql="select * from sa_user where id =#{p.id}";
        Map map=new HashMap();
        map.put("id","40280781705883540170588355050002");
        List<User> users= userMapper.query(sql,map);
        System.out.println(JsonUtil.toJSON(users));

    }


}