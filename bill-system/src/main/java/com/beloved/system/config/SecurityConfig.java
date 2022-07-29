package com.beloved.system.config;

import com.beloved.system.security.filter.LoginFilter;
import com.beloved.system.security.filter.TokenFilter;
import com.beloved.system.security.handle.AccessDeniedHandlerImpl;
import com.beloved.system.security.handle.AuthenticationEntryPointImpl;
import com.beloved.system.security.handle.AuthenticationFailureHandlerImpl;
import com.beloved.system.security.handle.AuthenticationSuccessHandlerImpl;
import com.beloved.system.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;

    @Autowired
    private AuthenticationSuccessHandlerImpl authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandlerImpl authenticationFailureHandler;

    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandler;

    @Autowired
    private TokenFilter tokenFilter;

    private static final String[] whiteList = {
            "/auth/captcha"
    };
    
    /**
     * 自定义认证逻辑配置到SpringSecurity认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
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

        // 设置认证地址
        loginFilter.setFilterProcessesUrl("/auth/login");
        loginFilter.setUsernameParameter("username");
        loginFilter.setPasswordParameter("password");

        // 设置认证成功处理器
        loginFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);

        // 设置认证失败处理器
        loginFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        
        return loginFilter;
    }

    /**
     * SpringSecurity 核心配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                // 禁用 csrf
                .csrf().disable()
                // 禁用 session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 请求都需要认证才能访问，除白名单
                .authorizeRequests()
                .antMatchers(whiteList).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin();

        // 自定义匿名用户访问无权限资源异常处理器 、 认证用户访问无权限资源异常处理器
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler);

        /*
         * addFilterAt：替换过滤器链中的某个 filter
         * addFilterBefore：放在过滤器链中某个 filter 之前
         * addFilterAfter：放在过滤器链中某个 filter 之后
         */
        // 替换 UsernamePasswordAuthenticationFilter 用来处理表单提交
        http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);

        // 自定义 Token 过滤器
        http.addFilterBefore(tokenFilter, LoginFilter.class);
    }

    /**
     * 强散列哈希加密实现
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
