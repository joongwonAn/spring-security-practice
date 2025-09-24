package com.springboot.config;

import com.springboot.member.InMemoryMemberService;
import com.springboot.member.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfiguration {
    @Bean
    public MemberService inMemoryMemberService() {
        return new InMemoryMemberService();
    }
}
