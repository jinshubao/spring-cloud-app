package com.jean.swagger.config;


import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.jean.swagger.properties.DocketProperties;
import com.jean.swagger.properties.SwaggerProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
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

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jinshubao
 */
@EnableSwagger2
@EnableConfigurationProperties(value = {SwaggerProperties.class})
@ConditionalOnProperty(name = "swagger.enable", matchIfMissing = true)
public class SwaggerAutoConfiguration implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Bean
    @ConditionalOnMissingBean(value = {ResponseMessageConfiguration.class})
    ResponseMessageConfiguration responseMessageConfiguration(SwaggerProperties properties) {
        return new ResponseMessageConfiguration(properties);
    }

    @Bean
    @ConditionalOnMissingBean
    public Collection<Docket> docket(ResponseMessageConfiguration responseMessage, SwaggerProperties properties) {
        ConfigurableBeanFactory configurableBeanFactory = (ConfigurableBeanFactory) beanFactory;

        Map<String, DocketProperties> dockets =  properties.getDockets();
        List<Docket> docketList = new ArrayList<>();
        for (DocketProperties docketProperties : dockets.values()) {
            Docket docket = new Docket(DocumentationType.SWAGGER_2);
            docket = docket.apiInfo(apiInfo( properties.getApiInfo()))
                    .enable(docketProperties.isEnable())
                    .groupName(docketProperties.getGroupName())
                    .select()
                    .apis(apis(docketProperties.getApiPackage()))
                    .paths(paths(docketProperties.getPaths()))
                    .build()
                    .pathMapping(docketProperties.getPathPrefix())
//                .directModelSubstitute(LocalDate.class, String.class)
                    .genericModelSubstitutes(DeferredResult.class)
                    .useDefaultResponseMessages(false)
                    .host(docketProperties.getHost())
                    .consumes(docketProperties.getConsumes())
                    .produces(docketProperties.getProduces())
                    .protocols(docketProperties.getProtocols());
            List<ResponseMessage> messages = responseMessage.messages();
            if (!messages.isEmpty()) {
                for (RequestMethod method : RequestMethod.values()) {
                    docket = docket.globalResponseMessage(method, messages);
                }
            }
            configurableBeanFactory.registerSingleton(docket.getGroupName(), docket);
            docketList.add(docket);
        }
        return docketList;
    }

    private Predicate<RequestHandler> apis(String apiPackage) {
        if (StringUtils.hasText(apiPackage)) {
            return RequestHandlerSelectors.basePackage(apiPackage.trim());
        }
        return ApiSelector.DEFAULT.getRequestHandlerSelector();
    }

    private Predicate<String> paths(Set<String> paths) {
        if (paths != null && !paths.isEmpty()) {
            return Predicates.or(paths.stream().map(PathSelectors::regex).collect(Collectors.toSet()));
        }
        return ApiSelector.DEFAULT.getPathSelector();
    }

    private ApiInfo apiInfo(SwaggerProperties.ApiInfoProperties apiInfo) {
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
    UiConfiguration uiConfig(SwaggerProperties properties) {
        SwaggerProperties.UiConfigurationProperties ui = properties.getUi();
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

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
