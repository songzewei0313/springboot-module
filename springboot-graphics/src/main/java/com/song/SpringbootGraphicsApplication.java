package com.song;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author song
 */
@SpringBootApplication
@MapperScan("com.song.springbootgraphics.dao")
public class SpringbootGraphicsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootGraphicsApplication.class, args);
    }

}
