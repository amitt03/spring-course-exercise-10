package springcourse.solutions.exercise10.application;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import springcourse.solutions.exercise10.aop.MethodStartAspect;
import springcourse.solutions.exercise10.aop.MoodChangerAspect;

/**
 * @author Amit Tal
 * @since 3/24/14
 */
@Configuration
@EnableMBeanExport
@EnableAspectJAutoProxy
@ComponentScan({"springcourse.solutions.exercise10.dao", "springcourse.solutions.exercise10.service", "springcourse.solutions.exercise10.util"})
@PropertySource({"classpath:books-analyzer.properties", "classpath:members.properties"})
public class AppConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer configurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public MethodStartAspect methodStartAspect() {
        return new MethodStartAspect();
    }

    @Bean
    MoodChangerAspect moodChangerAspect() {
        return new MoodChangerAspect();
    }
}
