package com.spark.springbootlesson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author initiald0824
 */
@SpringBootApplication
public class SpringbootLessonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootLessonApplication.class, args);
        System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow","|{}");
    }

}
