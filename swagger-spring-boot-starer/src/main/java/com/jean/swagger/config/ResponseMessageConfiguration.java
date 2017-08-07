package com.jean.swagger.config;

import com.jean.swagger.properties.SwaggerProperties;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Header;
import springfox.documentation.service.ResponseMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseMessageConfiguration {

    private SwaggerProperties properties;

    public ResponseMessageConfiguration(SwaggerProperties properties) {
        this.properties = properties;
    }

    public List<ResponseMessage> messages() {
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

}
