package org.github.omnbmh.cobra.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.github.omnbmh.cobra.base.ResponseBody;
import org.github.omnbmh.cobra.service.UserService;
import org.github.omnbmh.cobra.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Bean
    PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder(10);// 密码强度 10
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    // 设置 路径和角色的匹配关系
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {

                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(cfisms());
                        o.setAccessDecisionManager(new CobraAccessDecisionManager());
                        return o;
                    }
                })
                .and()
                .formLogin().loginPage("/login_p").loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        Object principal = authentication.getPrincipal();
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter out = httpServletResponse.getWriter();
                        httpServletResponse.setStatus(200);
                        ResponseBody rb = ResponseBody.ok("登录成功～");
                        rb.setBody(UserUtils.getCurrentUser());
                        ObjectMapper om = new ObjectMapper();
                        out.write(om.writeValueAsString(rb));
                        out.flush();
                        out.close();
                    }
                })
                .failureHandler(
                        new AuthenticationFailureHandler() {

                            @Override
                            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                                httpServletResponse.setContentType("application/json;charset=utf-8");
                                ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.ok();
                                httpServletResponse.setStatus(401);
                                if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException) {
                                    responseBuilder.body("用户名或密码输入错误。");
                                } else if (e instanceof LockedException) {
                                    responseBuilder.body("账户被锁定，请联系管理员。");
                                } else if (e instanceof DisabledException) {
                                    responseBuilder.body("账户被禁用，请联系管理员。");
                                } else {
                                    responseBuilder.body("登录失败");
                                }
                                PrintWriter out = httpServletResponse.getWriter();
                                out.write(new ObjectMapper().writeValueAsString(responseBuilder.build()));
                                out.flush();
                                out.close();
                            }
                        })
                .permitAll()
                .and()
                .logout().logoutUrl("/logout").permitAll()
                .and()
//                .cors().disable()
                .csrf().disable()
        ;
    }

    // 通过这种方式 可以注入@Autowire
    @Bean
    CobraFilterInvocationSecurityMetadataSource cfisms() {
        return new CobraFilterInvocationSecurityMetadataSource();
    }
}
