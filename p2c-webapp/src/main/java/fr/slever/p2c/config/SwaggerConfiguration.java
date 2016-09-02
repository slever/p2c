package fr.slever.p2c.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Springfox Swagger configuration.
 *
 * Warning! When having a lot of REST endpoints, Springfox can become a
 * performance issue. In that case, you can use a specific Spring profile for
 * this class, so that only front-end developers have access to the Swagger
 * view.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

  private static final Logger LOGGER = LoggerFactory.getLogger(SwaggerConfiguration.class);

  public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";

  /**
   * Swagger Springfox configuration.
   *
   * @return the Swagger Springfox configuration
   */
  @Bean
  public Docket swaggerSpringfoxDocket() {
    LOGGER.debug("Starting Swagger");
    StopWatch watch = new StopWatch();
    watch.start();

    ApiInfo apiInfo = new ApiInfoBuilder().title("P2C REST API").description("P2C REST API")
        .termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
        .license("Apache License Version 2.0").licenseUrl("https://github.com/slever/p2c/blob/master/LICENSE").version("2.0").build();

    Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo).forCodeGeneration(true).genericModelSubstitutes(ResponseEntity.class)
        .ignoredParameterTypes(Pageable.class)
        .ignoredParameterTypes(java.sql.Date.class).directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
        .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
        .directModelSubstitute(java.time.LocalDateTime.class, Date.class).select().paths(regex(DEFAULT_INCLUDE_PATTERN)).build();
    watch.stop();
    LOGGER.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
    return docket;
  }
}
