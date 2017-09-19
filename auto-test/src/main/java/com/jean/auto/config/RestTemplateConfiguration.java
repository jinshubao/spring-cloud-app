package com.jean.auto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class RestTemplateConfiguration {

    @Primary
    @Bean(name = "restTemplate")
    RestTemplate restTemplate() {
        return newRestTemplate();
    }

    private RestTemplate newRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
        for (HttpMessageConverter<?> item : converterList) {
            if (item.getClass() == StringHttpMessageConverter.class) {
                item = new StringHttpMessageConverter(StandardCharsets.UTF_8);
            }
            /*
            if (item.getClass() == MappingJackson2HttpMessageConverter.class) {
                MappingJackson2HttpMessageConverter converter = (MappingJackson2HttpMessageConverter)item ;
                ObjectMapper mapper = converter.getObjectMapper();
                mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
            }
            */
        }
        return restTemplate;
    }

}
