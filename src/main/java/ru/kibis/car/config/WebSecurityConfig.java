package ru.kibis.car.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger LOGGER = LogManager.getLogger(WebSecurityConfig.class.getName());

    @Override
    protected void configure(HttpSecurity http) {
        try {
            http
                    .authorizeRequests()
                    .antMatchers(
                            "/index.html",
                            "/ad.html",
                            "/registration.html"
                    ).permitAll()
                    .antMatchers(HttpMethod.POST,
                            "/ad_servlet",
                            "/brand_servlet",
                            "/login",
                            "/exit"
                    ).permitAll()
                //    .anyRequest().authenticated()
                 //   .and()
                //    .logout().logoutSuccessUrl("/index.html")
                    .and()
                    .exceptionHandling().accessDeniedPage("/index.html")
                    .and()
                    .csrf();
        } catch (Exception e) {
            System.out.println(1);
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void configure(WebSecurity web) {
        try {
            web
                    .ignoring()
                    .antMatchers(
                            "/static/**",
                            "/js/*",
                            "/css/*",
                            "/images/*"
                    ).and()
                    .ignoring()
                    .antMatchers(HttpMethod.GET,
                            "/ad_servlet",
                            "/ad_create_servlet",
                            "/photo_servlet",
                            "/show_photo_servlet"
                    ).and()
                    .ignoring()
                    .antMatchers(HttpMethod.POST,
                            "/ad_servlet",
                            "/brand_servlet",
                            "/login",
                            "/exit"
                    );
        } catch (Exception e) {
            System.out.println(2);
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) {
        try {
            auth.jdbcAuthentication().dataSource(dataSource)
                    .usersByUsernameQuery("select login, password"
                            + " from users where username=?")
                    .passwordEncoder(new BCryptPasswordEncoder());
        } catch (Exception e) {
            System.out.println(3);
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
    }

}