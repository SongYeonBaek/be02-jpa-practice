package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateConfig {
    //호출되기 전까지 객체가 생성될 일 없는 메소드이므로 @Bean을 이용해서 객체가 등록되게 함
    @Bean
    public JdbcTemplate JdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
