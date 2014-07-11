package springcourse.exercises.exercise10.application;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author Amit Tal
 * @since 3/24/14
 */
@Configuration
@EnableMBeanExport
@ComponentScan({"springcourse.exercises.exercise10.dao", "springcourse.exercises.exercise10.service", "springcourse.exercises.exercise10.util"})
@PropertySource({"classpath:books-analyzer.properties", "classpath:members.properties"})
public class AppConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer configurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
