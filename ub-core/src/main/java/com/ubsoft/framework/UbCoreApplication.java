package com.ubsoft.framework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.ubsoft.**.mapper")
@SpringBootApplication
public class UbCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(UbCoreApplication.class, args);
    }

}
