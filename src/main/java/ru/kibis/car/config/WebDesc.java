package ru.kibis.car.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebDesc extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                SpringRootConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                SpringWebConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{
                "/user_create_servlet",
                "/login",
                "/exit",
                "/user_update_servlet",
                "/ad_create_servlet",
                "/ad_servlet",
                "/my_ads_servlet",
                "/ad_edit_servlet",
                "/photo_servlet",
                "/show_photo_servlet",
                "/brand_servlet"
        };
    }
}



