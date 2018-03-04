package com.momentu.momentuapi.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.momentu.momentuapi.security.CustomCorsFilter;
import com.momentu.momentuapi.security.RestAuthenticationEntryPoint;
import com.momentu.momentuapi.security.auth.json.JsonAuthenticationProvider;
import com.momentu.momentuapi.security.auth.json.JsonLoginProcessingFilter;
import com.momentu.momentuapi.security.auth.jwt.JwtAuthenticationProvider;
import com.momentu.momentuapi.security.auth.jwt.JwtTokenAuthenticationProcessingFilter;
import com.momentu.momentuapi.security.auth.jwt.SkipPathRequestMatcher;
import com.momentu.momentuapi.security.auth.jwt.extractor.TokenExtractor;
import org.apache.el.lang.EvaluationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.spi.EvaluationContextExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String JWT_TOKEN_HEADER_KEY = "Authorization";
    public static final String FORM_LOGIN_ENTRY_POINT = "/login";
    public static final String FORM_REGISTER_ENTRY_POINT = "/register";
    public static final String FORM_FORGOT_PASSWORD_ENTRY_POINT = "/forgotPassword";
    public static final String TOKEN_AUTH_ENTRY_POINT = "/**";
    public static final String TOKEN_REFRESH_ENTRY_POINT = "/token";

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    JsonAuthenticationProvider jsonAuthenticationProvider;

    @Autowired
    JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenExtractor tokenExtractor;

    @Autowired
    private ObjectMapper objectMapper;


    protected JsonLoginProcessingFilter buildJsonLoginProcessingFilter() throws Exception {
        JsonLoginProcessingFilter processingFilter =
                new JsonLoginProcessingFilter(FORM_LOGIN_ENTRY_POINT, authenticationSuccessHandler,
                        authenticationFailureHandler, objectMapper);
        processingFilter.setAuthenticationManager(this.authenticationManager);
        return processingFilter;
    }

    protected JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter() throws Exception {
        List<String> pathsToSkip = Arrays.asList(TOKEN_REFRESH_ENTRY_POINT, FORM_LOGIN_ENTRY_POINT,
                FORM_REGISTER_ENTRY_POINT, FORM_FORGOT_PASSWORD_ENTRY_POINT);
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, TOKEN_AUTH_ENTRY_POINT);

        JwtTokenAuthenticationProcessingFilter processingFilter =
                new JwtTokenAuthenticationProcessingFilter(authenticationFailureHandler, tokenExtractor, matcher);
        processingFilter.setAuthenticationManager(this.authenticationManager);

        return processingFilter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    EvaluationContextExtension securityExtension() {
        return new SecurityEvaluationContextExtension();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(jwtAuthenticationProvider);
        auth.authenticationProvider(jsonAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(this.restAuthenticationEntryPoint)
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                        .antMatchers(FORM_REGISTER_ENTRY_POINT).permitAll()
                        .antMatchers(FORM_FORGOT_PASSWORD_ENTRY_POINT).permitAll()
                        .antMatchers(FORM_LOGIN_ENTRY_POINT).permitAll()
                        .antMatchers(TOKEN_REFRESH_ENTRY_POINT).permitAll()
                .and()
                    .authorizeRequests()
                        .antMatchers(TOKEN_AUTH_ENTRY_POINT).authenticated()
                .and()
                    .addFilterBefore(new CustomCorsFilter(), UsernamePasswordAuthenticationFilter.class)
                    .addFilterBefore(buildJsonLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                    .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
