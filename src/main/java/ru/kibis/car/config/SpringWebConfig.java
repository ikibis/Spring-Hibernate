package ru.kibis.car.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class SpringWebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/img/**",
                "/css/**",
                "/js/**")
                .addResourceLocations(
                        "classpath:/static/img/",
                        "classpath:/static/css/",
                        "classpath:/static/js/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/ad.html").setViewName("ad");
        registry.addViewController("/create-ad.html").setViewName("create-ad");
        registry.addViewController("/edit-ad.html").setViewName("edit-ad");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/my-ads.html").setViewName("my-ads");
        registry.addViewController("/profile.html").setViewName("profile");
        registry.addViewController("/registration.html").setViewName("registration");
    }
}