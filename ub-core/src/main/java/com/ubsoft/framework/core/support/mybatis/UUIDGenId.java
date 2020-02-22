//package com.ubsoft.framework.core.support.mybatis;
//
//import tk.mybatis.mapper.genid.GenId;
//
//import java.util.UUID;
//
//public class  UUIDGenId implements GenId<String> {
//    @Override
//    public String genId(String table, String column) {
//        return UUID.randomUUID().toString().replace("-","").toUpperCase();
//    }
//}
