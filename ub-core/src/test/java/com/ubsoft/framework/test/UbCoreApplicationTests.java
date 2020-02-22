package com.ubsoft.framework.test;

import com.ubsoft.framework.core.conf.AppConfig;
import com.ubsoft.framework.core.context.AppContext;
import com.ubsoft.framework.core.dal.mybatis.mapper.BaseMapper;
import com.ubsoft.framework.core.dal.mybatis.provider.BaseProvider;
import com.ubsoft.framework.core.dal.support.EntityHelper;
import org.apache.ibatis.builder.annotation.ProviderSqlSource;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@SpringBootTest
class UbCoreApplicationTests {
    private static final XMLLanguageDriver languageDriver = new XMLLanguageDriver();
    @Autowired
    BaseMapper userMapper;
    @Autowired
    AppConfig appConfig;
    @Test
    void contextLoads() {
    }

    void TestConfig(){
        System.out.println(appConfig.getProperty("spring.cache.type"));
        System.out.println(appConfig.getProperty("Test2"));

    }

    /** 原理替换 ProviderSqlSource**/
    void testDymicSql() throws InvocationTargetException, IllegalAccessException {
        SqlSessionTemplate bean= (SqlSessionTemplate)AppContext.getBean(SqlSessionTemplate.class);
        Collection collection =bean.getConfiguration().getMappedStatements();
        Iterator var4 = (new ArrayList(collection)).iterator();

        while(var4.hasNext()) {
            Object object = var4.next();
            if (object instanceof MappedStatement) {
                MappedStatement ms = (MappedStatement)object;
                if ( ms.getSqlSource() instanceof ProviderSqlSource) {

                    //获取mappper
                    Class<?> mapperClass= EntityHelper.getMapperClass(ms.getId());
                    //通过mapper获取provider
                    BaseProvider provid=EntityHelper.getProvider(mapperClass);
                    if(provid==null){
                        continue;
                    }
                    Map<String,Method> methods =provid.getMethodMap();
                    String methodStr=EntityHelper.getMethodName(ms.getId());
                    Method method=methods.get(methodStr);
                    /**调用provid生成sql
                     * 问题卡在参数上面，通用mapper用 MappedStatement 作为参数，entity通过getId获取方法的参数和返回这作为entity
                     * 目前我这边是写死的。                     *
                     * **/
                    String xmlSql = (String)method.invoke(provid, new HashMap());
                    SqlSource sqlSource= languageDriver.createSqlSource(ms.getConfiguration(), "<script>\n\t" + xmlSql + "</script>", (Class)null);
                    MetaObject msObject = SystemMetaObject.forObject(ms);
                    msObject.setValue("sqlSource", sqlSource);

                   // SqlSource sqlSource = this.createSqlSource(ms, xmlSql);
                    //ms.setSqlSource(ms, sqlSource);
                    System.out.println(ms.getId());

                }
            }
        }


    }

}
