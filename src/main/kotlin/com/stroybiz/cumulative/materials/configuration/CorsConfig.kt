package com.stroybiz.cumulative.materials.configuration


import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

// TODO: Security hole
@Configuration
@EnableWebMvc
class CorsConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "PATCH")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .exposedHeaders("Location", "Accept-Language")
    }
}
