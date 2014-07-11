package springcourse.solutions.exercise10.controller.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knappsack.swagger4springweb.controller.ApiDocumentationController;
import com.knappsack.swagger4springweb.util.ScalaObjectMapper;
import com.wordnik.swagger.model.ApiInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author Amit Tal
 * @since 1/8/14
 *
 * This configuration integrates with swagger in order to expose REST API documentation.
 * The integration is done using swagger4spring-web plugin (site: https://github.com/wkennedy/swagger4spring-web).
 * This plugin creates a controller under /api/resourceList.
 * If called, the controller will scan the REST APIs and return a swagger json document readable by a swagger-ui.
 *
 * Notice, this configuration is under the "documentation" spring profile.
 */
@Configuration
@PropertySource({"classpath:documentation.properties"})
public class DocumentationConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment environment;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters ) {
        converters.add(converter());
    }

    @Bean
    public HttpMessageConverter converter() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(scalaObjectMapper());
        return mappingJackson2HttpMessageConverter;
    }

    @Bean
    public ObjectMapper scalaObjectMapper() {
        return new ScalaObjectMapper();
    }

    @Bean
    public ApiDocumentationController apiDocumentationController() {
        ApiDocumentationController apiDocumentationController = new ApiDocumentationController();
        apiDocumentationController.setBasePath(environment.getProperty("documentation.services.basePath"));
        apiDocumentationController.setBaseControllerPackage("springcourse.solutions.exercise10.controller");
        apiDocumentationController.setBaseModelPackage("springcourse.solutions.exercise10.model");
        apiDocumentationController.setApiVersion(environment.getProperty("documentation.services.version", "1.0"));
        ApiInfo apiInfo = new ApiInfo("Spring Course Solutions",
                                      "exercise 10",
                                      null, null, null, null);
        apiDocumentationController.setApiInfo(apiInfo);
        return apiDocumentationController;
    }
}
