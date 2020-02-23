package com.ubsoft.framework.core.cache;

import com.ubsoft.framework.core.context.AppContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class ICacheTest {


    @Test
    void get() {
    }

    @Test
    void testGet() {
    }

    @Test
    void put() {
        AppContext.getCache().put("fff","fffff");
    }

    @Test
    void testPut() {
    }

    @Test
    void gets() {
    }

    @Test
    void testGets() {
    }

    @Test
    void remove() {
    }

    @Test
    void testRemove() {
    }

    @Test
    void clean() {
    }

    @Test
    void expire() {
    }
}