package com.jean.swagger.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import springfox.documentation.swagger.web.UiConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jinshubao
 */
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {

    private ApiInfoProperties apiInfo = new ApiInfoProperties();

    private Map<String, DocketProperties> dockets = new HashMap<>();

    private List<ResponseMessageProperties> responseMessage = new ArrayList<>();

    private UiConfigurationProperties ui = new UiConfigurationProperties("validatorUrl",
            "none",
            "alpha",
            "schema",
            60000L,
            UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
            true,
            true);

    public ApiInfoProperties getApiInfo() {
        return apiInfo;
    }

    public void setApiInfo(ApiInfoProperties apiInfo) {
        this.apiInfo = apiInfo;
    }

    public Map<String, DocketProperties> getDockets() {
        return dockets;
    }

    public void setDockets(Map<String, DocketProperties> dockets) {
        this.dockets = dockets;
    }

    public List<ResponseMessageProperties> getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(List<ResponseMessageProperties> responseMessage) {
        this.responseMessage = responseMessage;
    }

    public UiConfigurationProperties getUi() {
        return ui;
    }

    public void setUi(UiConfigurationProperties ui) {
        this.ui = ui;
    }

    public static class ApiInfoProperties {
        private String title;
        private String description;
        private String termsOfServiceUrl;
        private String version;
        private ContactProperties contact = new ContactProperties();
        private LicenseProperties license = new LicenseProperties();

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTermsOfServiceUrl() {
            return termsOfServiceUrl;
        }

        public void setTermsOfServiceUrl(String termsOfServiceUrl) {
            this.termsOfServiceUrl = termsOfServiceUrl;
        }

        public ContactProperties getContact() {
            return contact;
        }

        public void setContact(ContactProperties contact) {
            this.contact = contact;
        }


        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public LicenseProperties getLicense() {
            return license;
        }

        public void setLicense(LicenseProperties license) {
            this.license = license;
        }
    }


    public static class LicenseProperties {

        private String name;

        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class ContactProperties {

        private String name;
        private String url;
        private String email;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    public static class ResponseMessageProperties {
        private int code;
        private String message;
        private String responseModel;
        private Map<String, String> headers = new HashMap<>();

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getResponseModel() {
            return responseModel;
        }

        public void setResponseModel(String responseModel) {
            this.responseModel = responseModel;
        }

        public Map<String, String> getHeaders() {
            return headers;
        }

        public void setHeaders(Map<String, String> headers) {
            this.headers = headers;
        }
    }

    public static class UiConfigurationProperties {

        private String validatorUrl;
        private String docExpansion;
        private String apisSorter;
        private String defaultModelRendering;
        private Long requestTimeout;
        private String[] supportedSubmitMethods;
        private boolean jsonEditor;
        private boolean showRequestHeaders;

        public UiConfigurationProperties() {
        }

        public UiConfigurationProperties(String validatorUrl, String docExpansion, String apisSorter, String defaultModelRendering, Long requestTimeout, String[] supportedSubmitMethods, boolean jsonEditor, boolean showRequestHeaders) {
            this.validatorUrl = validatorUrl;
            this.docExpansion = docExpansion;
            this.apisSorter = apisSorter;
            this.defaultModelRendering = defaultModelRendering;
            this.requestTimeout = requestTimeout;
            this.supportedSubmitMethods = supportedSubmitMethods;
            this.jsonEditor = jsonEditor;
            this.showRequestHeaders = showRequestHeaders;
        }

        public String getValidatorUrl() {
            return validatorUrl;
        }

        public void setValidatorUrl(String validatorUrl) {
            this.validatorUrl = validatorUrl;
        }

        public String getDocExpansion() {
            return docExpansion;
        }

        public void setDocExpansion(String docExpansion) {
            this.docExpansion = docExpansion;
        }

        public String getApisSorter() {
            return apisSorter;
        }

        public void setApisSorter(String apisSorter) {
            this.apisSorter = apisSorter;
        }

        public String getDefaultModelRendering() {
            return defaultModelRendering;
        }

        public void setDefaultModelRendering(String defaultModelRendering) {
            this.defaultModelRendering = defaultModelRendering;
        }

        public Long getRequestTimeout() {
            return requestTimeout;
        }

        public void setRequestTimeout(Long requestTimeout) {
            this.requestTimeout = requestTimeout;
        }

        public String[] getSupportedSubmitMethods() {
            return supportedSubmitMethods;
        }

        public void setSupportedSubmitMethods(String[] supportedSubmitMethods) {
            this.supportedSubmitMethods = supportedSubmitMethods;
        }

        public boolean isJsonEditor() {
            return jsonEditor;
        }

        public void setJsonEditor(boolean jsonEditor) {
            this.jsonEditor = jsonEditor;
        }

        public boolean isShowRequestHeaders() {
            return showRequestHeaders;
        }

        public void setShowRequestHeaders(boolean showRequestHeaders) {
            this.showRequestHeaders = showRequestHeaders;
        }
    }
}
