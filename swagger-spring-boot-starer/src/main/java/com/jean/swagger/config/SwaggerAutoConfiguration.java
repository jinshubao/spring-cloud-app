package com.jean.swagger.config;


import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.jean.swagger.properties.SwaggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.ApiSelector;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@EnableConfigurationProperties(value = {SwaggerProperties.class})
@ConditionalOnProperty(name = "swagger.enable")
@EnableSwagger2
public class SwaggerAutoConfiguration {

    private final SwaggerProperties properties;

    @Autowired
    public SwaggerAutoConfiguration(SwaggerProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean(value = {ResponseMessageConfiguration.class})
    ResponseMessageConfiguration responseMessageConfiguration(SwaggerProperties properties) {
        return new ResponseMessageConfiguration(properties);
    }

    @Bean
    public Docket docket(ResponseMessageConfiguration responseMessage) {
        SwaggerProperties.DocketProperties properties = this.properties.getDocket();
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket = docket.apiInfo(apiInfo())
                .enable(properties.isEnable())
                .groupName(properties.getGroupName())
                .select()
                .apis(apis(properties))
                .paths(paths(properties))
                .build()
                .pathMapping(properties.getPathPrefix())
//                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .host(properties.getHost())
                .consumes(properties.getConsumes())
                .produces(properties.getProduces())
                .protocols(properties.getProtocols());
        List<ResponseMessage> messages = responseMessage.messages();
        if (!messages.isEmpty()) {
            for (RequestMethod method : RequestMethod.values()) {
                docket = docket.globalResponseMessage(method, messages);
            }
        }
        return docket;
    }

    private Predicate<RequestHandler> apis(SwaggerProperties.DocketProperties properties) {
        if (StringUtils.hasText(properties.getApiPackage())) {
            return RequestHandlerSelectors.basePackage(properties.getApiPackage().trim());
        }
        return ApiSelector.DEFAULT.getRequestHandlerSelector();
    }

    private Predicate<String> paths(SwaggerProperties.DocketProperties properties) {
        if (properties.getPaths() != null && !properties.getPaths().isEmpty()) {
            List<String> list = new ArrayList<>();
            list.addAll(properties.getPaths());
            return Predicates.or(list.stream().map(PathSelectors::regex).collect(Collectors.toList()));
        }
        return ApiSelector.DEFAULT.getPathSelector();
    }

    private ApiInfo apiInfo() {
        SwaggerProperties.ApiInfoProperties apiInfo = this.properties.getApiInfo();
        Contact contact = new Contact(apiInfo.getContact().getName(), apiInfo.getContact().getUrl(), apiInfo.getContact().getEmail());
        return new ApiInfoBuilder()
                .title(apiInfo.getTitle())
                .description(apiInfo.getDescription())
                .termsOfServiceUrl(apiInfo.getTermsOfServiceUrl())
                .contact(contact)
                .version(apiInfo.getVersion())
                .license(apiInfo.getLicense().getName())
                .licenseUrl(apiInfo.getLicense().getUrl())
                .build();
    }

    @Bean
    UiConfiguration uiConfig() {
        SwaggerProperties.UiConfigurationProperties ui = this.properties.getUi();
        return new UiConfiguration(
                ui.getValidatorUrl(),               // url
                ui.getDocExpansion(),               // docExpansion          => none | list
                ui.getApisSorter(),                 // apiSorter             => alpha
                ui.getDefaultModelRendering(),      // defaultModelRendering => schema
                ui.getSupportedSubmitMethods(),
                ui.isJsonEditor(),                  // enableJsonEditor      => true | false
                ui.isShowRequestHeaders(),          // showRequestHeaders    => true | false
                ui.getRequestTimeout());            // requestTimeout => in milliseconds, defaults to null (uses jquery xh timeout)

    }
}
