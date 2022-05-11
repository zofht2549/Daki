//package com.daki.api.oauth.dto.common.config;
//
//import org.springframework.context.annotation.Configuration;
//
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class OAuthCorsConfig implements WebMvcConfigurer {
//    private final long MAX_AGE_SECS = 3600;
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOriginPatterns("*")
//                .allowedHeaders("*")
//                .allowedMethods("*")
//                .allowCredentials(true)
//                .maxAge(MAX_AGE_SECS);
//
//    }
//}
