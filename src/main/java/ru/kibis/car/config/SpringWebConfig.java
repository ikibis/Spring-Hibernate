package ru.kibis.car.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("ru.kibis.car.controller")
public class SpringWebConfig implements WebMvcConfigurer {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/webjars/", "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/js/", "classpath:/public/"
    };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("ad.html").setViewName("ad");
        registry.addViewController("create-ad.html").setViewName("create-ad");
        registry.addViewController("edit-ad.html").setViewName("edit-ad");
        registry.addViewController("index.html").setViewName("index");
        registry.addViewController("my-ads.html").setViewName("my-ads");
        registry.addViewController("profile.html").setViewName("profile");
        registry.addViewController("registration.html").setViewName("registration");
    }
}