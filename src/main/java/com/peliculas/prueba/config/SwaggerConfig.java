package com.peliculas.prueba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.beans.factory.annotation.Autowired;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	/** The Constant PGK_ROUTE_SWAGGER. */
	public static final String PGK_ROUTE_SWAGGER = "com.peliculas.prueba";

    /**
     * Api.
     *
     * @return the docket
     */
	@Autowired
	@Bean
	public Docket swaggerSpringMvcPlugin() {
	    ApiSelectorBuilder builder = new Docket(DocumentationType.SWAGGER_2)
	    		.useDefaultResponseMessages(false)
	            .apiInfo(apiInfo())
	            .select()
	            .apis(RequestHandlerSelectors.basePackage(PGK_ROUTE_SWAGGER))
	            .paths(PathSelectors.any());
	    return builder.build();
	}
	/**
	 * Api info.
	 *
	 * @return the api info
	 */
	private ApiInfo apiInfo() {
		String version = null;
		String title = null;
        return new ApiInfoBuilder()
        		.title(title != null ? title : "Swagger para procesos CORE de Peliculas prueba")
                .description("Gestiona todos los procesos CORE de Peliculas prueba")
                .contact(new Contact("Jose Flores", "https://github.com/jflorescorado", "jflorescorado03@gmail.com"))
                .version(version != null ? version : "1.0")
                .build();
    }
}
