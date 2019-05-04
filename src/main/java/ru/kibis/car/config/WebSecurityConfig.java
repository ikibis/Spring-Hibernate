package ru.kibis.car.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


/* @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("Login")
                .password("Login");
    }*/

    protected void configure(HttpSecurity http) throws Exception {
       // http.
               /* .authorizeRequests()
                .antMatchers(
                        "/index.html",
                        "/ad.html",
                        "/registration.html"
                ).permitAll()
                .antMatchers(HttpMethod.GET,
                        "/photo_servlet**",
                        "/show_photo_servlet**",
                        "/ad_servlet**",
                        "/brand_servlet**",
                        "/user_create_servlet**",
                        "/login**",
                        "/exit**").permitAll()
                .antMatchers(HttpMethod.POST,
                        "/photo_servlet**",
                        "/show_photo_servlet**",
                        "/ad_servlet**",
                        "/brand_servlet**",
                        "/user_create_servlet**",
                        "/login**",
                        "/exit**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();*/
    }
}

/*


    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).dataSource(dataSource)
                .usersByUsernameQuery(
                        "select login, password, TRUE from users where login =?")
                .authoritiesByUsernameQuery(
                        "select login, 'ROLE_ADMIN' from users where login =?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/list*").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/list*").permitAll()
                .anyRequest().permitAll()

                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/list")
                .usernameParameter("login").passwordParameter("password").failureForwardUrl("/fail")
                .and()
                .logout().logoutSuccessUrl("/login?logout")
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .csrf();
    }*/
