package com.daki.common.config;

import com.daki.common.jwt.JwtAccessDeniedHandler;
import com.daki.common.jwt.JwtAuthenticationEntryPoint;
import com.daki.common.jwt.JwtSecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final CorsFilter corsFilter;

//    public SecurityConfig(
//            TokenProvider tokenProvider,
//            CorsFilter corsFilter,
//            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
//            JwtAccessDeniedHandler jwtAccessDeniedHandler
//    ) {
//        this.tokenProvider = tokenProvider;
//        this.corsFilter = corsFilter;
//        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
//        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // token??? ???????????? ???????????? ????????? csrf??? disable?????????.
                .csrf().disable()

                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // ????????? ???????????? ?????? ????????? STATELESS??? ??????
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/api/auth/**").permitAll()
                .antMatchers("/api/member/**").hasRole("USER").anyRequest().authenticated()


                //???????????????, ?????????, ??????????????? ????????? ?????? ???????????? ????????? ????????????????????? permitAll?????????
//               .antMatchers("/api/auth/signup").permitAll()
//                .antMatchers("/api/emailCheck/**").permitAll()
//                .antMatchers("/api/nickNameCheck/**").permitAll()
//                .antMatchers("/api/auth/login").permitAll()

                //
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs/**");
        web.ignoring().antMatchers("/swagger.json");
        web.ignoring().antMatchers("/swagger-ui/**");
        web.ignoring().antMatchers("/swagger-ui.html/**");
        web.ignoring().antMatchers("/swagger-resources/**");
        web.ignoring().antMatchers("/webjars/**");
        web.ignoring().antMatchers("/api/auth/**");
        web.ignoring().antMatchers("/api/auth");
        web.ignoring().antMatchers("/api/oauth");
        web.ignoring().antMatchers("/api/oauth/**");

    }
}
