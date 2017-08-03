package com.jean.swagger.config;


import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.jean.swagger.properties.SwaggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Header;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.ApiSelector;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
@EnableConfigurationProperties(value = {SwaggerProperties.class})
@ConditionalOnProperty(name = "swagger.enable")
@EnableSwagger2
public class SwaggerAutoConfiguration {

    private final SwaggerProperties properties;

    private final TypeResolver typeResolver;

    @Autowired
    public SwaggerAutoConfiguration(SwaggerProperties properties, TypeResolver typeResolver) {
        this.properties = properties;
        this.typeResolver = typeResolver;
    }

    //TODO 多个docket如何处理？
    @Bean
    public Docket docket() {
        SwaggerProperties.DocketProperties properties = this.properties.getDocket();
        Predicate<String> paths = ApiSelector.DEFAULT.getPathSelector();
        if (StringUtils.hasText(properties.getPaths())) {
            List<Predicate<String>> collect = Arrays.stream(properties.getPaths().split(","))
                    .filter(StringUtils::hasText)
                    .map(String::trim)
                    .map(PathSelectors::regex)
                    .collect(Collectors.toList());
            paths = Predicates.or(collect);
        }
        Predicate<RequestHandler> apis = ApiSelector.DEFAULT.getRequestHandlerSelector();
        if (StringUtils.hasText(properties.getApiPackage())) {
            apis = RequestHandlerSelectors.basePackage(properties.getApiPackage().trim());
        }

        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket = docket.apiInfo(apiInfo())
                .groupName(properties.getGroupName())
                .select()
                .apis(apis)
                .paths(paths)
                .build()
                .pathMapping(properties.getPathPrefix())
//                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .host(properties.getHost())
                .consumes(properties.getConsumes())
                .produces(properties.getProduces())
                .protocols(properties.getProtocols());
        List<ResponseMessage> messages = messages();
        if (!messages.isEmpty()) {
            docket = docket.globalResponseMessage(RequestMethod.GET, messages);
            docket = docket.globalResponseMessage(RequestMethod.HEAD, messages);
            docket = docket.globalResponseMessage(RequestMethod.POST, messages);
            docket = docket.globalResponseMessage(RequestMethod.DELETE, messages);
            docket = docket.globalResponseMessage(RequestMethod.PUT, messages);
            docket = docket.globalResponseMessage(RequestMethod.OPTIONS, messages);
            docket = docket.globalResponseMessage(RequestMethod.TRACE, messages);
        }

        return docket;
    }

    private ApiInfo apiInfo() {
        SwaggerProperties.ApiInfoProperties apiInfo = properties.getApiInfo();
        return new ApiInfoBuilder()
                .title(apiInfo.getTitle())
                .description(apiInfo.getDescription())
                .termsOfServiceUrl(apiInfo.getTermsOfServiceUrl())
                .contact(new Contact(apiInfo.getContact().getName(), apiInfo.getContact().getUrl(), apiInfo.getContact().getEmail()))
                .version(apiInfo.getVersion())
                .license(apiInfo.getLicense().getName())
                .licenseUrl(apiInfo.getLicense().getUrl())
                .build();
    }

    private List<ResponseMessage> messages() {
        List<ResponseMessage> messages = new ArrayList<>();
        List<SwaggerProperties.ResponseMessageProperties> propertiesMessages = properties.getResponseMessage();

        for (SwaggerProperties.ResponseMessageProperties message : propertiesMessages) {
            Map<String, String> messageHeaders = message.getHeaders();
            Map<String, Header> headers = new HashMap<>();
            for (Map.Entry<String, String> entry : messageHeaders.entrySet()) {
                headers.put(entry.getKey(), new Header(entry.getKey(), "", new ModelRef(entry.getValue())));
            }
            ResponseMessage responseMessage = new ResponseMessageBuilder()
                    .code(message.getCode())
                    .message(message.getMessage())
                    .responseModel(new ModelRef(message.getResponseModel()))
                    .headersWithDescription(headers)
                    .build();
            messages.add(responseMessage);
        }
        return messages;
    }


    @Bean
    public Docket systemApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfo("系统端点",
                        "系统端点",
                        "",
                        "",
                        null,
                        "",
                        "",
                        Collections.emptyList()))
                .groupName("SYSTEM")
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.springframework"))
                .paths(PathSelectors.any())
                .build();
//                .pathMapping("/")
//                .directModelSubstitute(LocalDate.class, String.class)
//                .genericModelSubstitutes(ResponseEntity.class)
//                .alternateTypeRules(newRule(typeResolver.resolve(DeferredResult.class,
//                        typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
//                        typeResolver.resolve(WildcardType.class)))
//                .useDefaultResponseMessages(false)
//                .globalResponseMessage(RequestMethod.GET,
//                        newArrayList(new ResponseMessageBuilder()
//                                .code(500)
//                                .message("500 message")
//                                .responseModel(new ModelRef("Error"))
//                                .build()));
//                .securitySchemes(newArrayList(apiKey()))
//                .securityContexts(newArrayList(securityContext()))
//                .enableUrlTemplating(true)
//                .globalOperationParameters(
//                        newArrayList(new ParameterBuilder()
//                                .name("someGlobalParameter")
//                                .description("Description of someGlobalParameter")
//                                .modelRef(new ModelRef("string"))
//                                .parameterType("query")
//                                .required(false)
//                                .build()))
//                .tags(new Tag("Pet Service", "All apis relating to pets"))
//                .additionalModels(typeResolver.resolve(AdditionalModel.class));
    }

    /*private ApiKey apiKey() {
        return new ApiKey("mykey", "api_key", "header");
    }*/

    /*private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/user.*"))
                .build();
    }*/

    /*List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(
                new SecurityReference("mykey", authorizationScopes));
    }*/

    /*@Bean
    SecurityConfiguration security() {
        return new SecurityConfiguration(
                "test-app-client-id",
                "test-app-client-secret",
                "test-app-realm",
                "test-app",
                "apiKey",
                ApiKeyVehicle.HEADER,
                "api_key",
                "," *//*scope separator*//*);
    }*/

    /*@Bean
    UiConfiguration uiConfig() {
        return new UiConfiguration(
                "validatorUrl",// url
                "none",       // docExpansion          => none | list
                "alpha",      // apiSorter             => alpha
                "schema",     // defaultModelRendering => schema
                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
                false,        // enableJsonEditor      => true | false
                true,         // showRequestHeaders    => true | false
                60000L);      // requestTimeout => in milliseconds, defaults to null (uses jquery xh timeout)
    }*/

}
