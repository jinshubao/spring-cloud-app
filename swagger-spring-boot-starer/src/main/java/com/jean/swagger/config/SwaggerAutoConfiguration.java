package com.jean.swagger.config;


import com.jean.swagger.properties.ApiInfoProperties;
import com.jean.swagger.properties.DocketProperties;
import com.jean.swagger.properties.SwaggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
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
    public Docket petApi() {
        DocketProperties docket = properties.getDocket();
        List<ResponseMessage> messages = new ArrayList<>();
        ResponseMessage message1 = new ResponseMessageBuilder().code(200).message("操作成功").responseModel(new ModelRef("操作成功")).build();
        ResponseMessage message2 = new ResponseMessageBuilder().code(400).message("非法请求").responseModel(new ModelRef("非法请求")).build();
        ResponseMessage message3 = new ResponseMessageBuilder().code(501).message("如请求路径拼写不正确").responseModel(new ModelRef("如请求路径拼写不正确")).build();
        ResponseMessage message4 = new ResponseMessageBuilder().code(502).message("服务器过载引起的错误").responseModel(new ModelRef("服务器过载引起的错误")).build();
        messages.add(message1);
        messages.add(message2);
        messages.add(message3);
        messages.add(message4);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName(docket.getGroupName())
                .select()
                .apis(RequestHandlerSelectors.basePackage(docket.getBasePackage()))
                .paths(PathSelectors.any()).build()
                .pathMapping(docket.getPathMapping())
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.POST, messages);
    }

    private ApiInfo apiInfo() {
        ApiInfoProperties apiInfo = properties.getApiInfo();
        com.jean.swagger.properties.Contact contact = apiInfo.getContact();
        return new ApiInfoBuilder().title(apiInfo.getTitle()).
                description(apiInfo.getDescription())
                .termsOfServiceUrl(apiInfo.getTermsOfServiceUrl())
                .contact(new Contact(contact.getName(), contact.getUrl(), contact.getEmail()))
                .version(apiInfo.getVersion())
                .license(apiInfo.getLicense())
                .licenseUrl(apiInfo.getLicenseUrl())
                .build();
    }
}
