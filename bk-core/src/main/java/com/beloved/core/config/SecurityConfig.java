package com.beloved.core.config;

import com.alibaba.fastjson.JSONObject;
import com.beloved.core.security.filter.LoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User.withUsername("admin").password("{noop}123").roles("admin").build());
        return userDetailsManager;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    /**
     * 暴露 AuthenticationManager 到工厂
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 自定义 LoginFilter 注入
     * @return
     * @throws Exception
     */
    @Bean
    public LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();

        // 设置 AuthenticationManager
        loginFilter.setAuthenticationManager(authenticationManagerBean());

        return loginFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated() //所有请求必须认证
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutUrl("/logout")
                .and()
                .csrf().disable();

        /*
         * addFilterAt：替换过滤器链中的某个 filter
         * addFilterBefore：放在过滤器链中某个 filter 之前
         * addFilterAfter：放在过滤器链中某个 filter 之后
         */
        http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
