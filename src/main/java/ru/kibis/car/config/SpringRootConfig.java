package ru.kibis.car.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan("ru.kibis.car")
@ImportResource("classpath*:*spring-context.xml")
public class SpringRootConfig {
}
