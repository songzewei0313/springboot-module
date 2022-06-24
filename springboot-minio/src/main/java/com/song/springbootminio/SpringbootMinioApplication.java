package com.song.springbootminio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author song
 */
@SpringBootApplication
@EnableSwagger2
public class SpringbootMinioApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMinioApplication.class, args);
    }

}
