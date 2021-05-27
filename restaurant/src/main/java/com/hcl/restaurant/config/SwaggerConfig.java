package com.hcl.restaurant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hcl.restaurant.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
    @Bean
    UiConfiguration uiConfig() {

        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(false)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build();
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo =new ApiInfo(

                "Restaurant Api Documentation",
                "Restaurant Api is intended to perform restaurant related operations like creating, fetching , updating " +
                          "all the restaurant, deleting details for a specific restaurant",
                "1.0",
                "urn:tos",

                new Contact("Nikhil Bhansali", null, "nikhil.bhansali@hcl.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",

                new ArrayList<>());
                return apiInfo;
    }

}
