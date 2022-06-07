//package com.daki.api.oauth.dto.common.config;
//
//import com.daki.api.oauth.dto.common.oauth2.jwt.JwtFilter;
//import com.daki.api.oauth.dto.common.oauth2.jwt.JwtUtil;
//import com.daki.api.oauth.dto.common.oauth2.CustomOauth2Service;
//import com.daki.api.oauth.dto.common.oauth2.handler.OAuth2SuccessHandler;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@RequiredArgsConstructor
//public class OAuthSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    private final CustomOauth2Service customOauth2Service;
//    private final OAuth2SuccessHandler successHandler;
//    private final JwtUtil jwtUtil;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http.httpBasic().disable()
////                .csrf().disable()
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                .and()
////                .authorizeRequests()
////                .anyRequest().authenticated()
////                .and()
////                .addFilterBefore(new JwtFilter(jwtUtil),
////                        UsernamePasswordAuthenticationFilter.class);
////
//        http.oauth2Login()
//                .successHandler(successHandler)
//                .userInfoEndpoint().userService(customOauth2Service);
////
////        http.addFilterBefore(new JwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
//
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/webjars/**");
//        web.ignoring().antMatchers("/home");
//        web.ignoring().antMatchers("/Fbfeed");
//        web.ignoring().antMatchers("/FbId");
//        web.ignoring().antMatchers("/login");
//        web.ignoring().antMatchers("/index");
//    }
//
//
//}
