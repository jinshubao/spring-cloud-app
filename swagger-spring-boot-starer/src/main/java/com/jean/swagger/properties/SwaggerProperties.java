package com.jean.swagger.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import springfox.documentation.service.Tag;

import java.util.*;

import static com.google.common.collect.Sets.newHashSet;

@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {

    private boolean enable = true;

    private ApiInfoProperties apiInfo = new ApiInfoProperties();

    private DocketProperties docket = new DocketProperties();

    private List<ResponseMessageProperties> responseMessage = new ArrayList<>();

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public ApiInfoProperties getApiInfo() {
        return apiInfo;
    }

    public void setApiInfo(ApiInfoProperties apiInfo) {
        this.apiInfo = apiInfo;
    }

    public DocketProperties getDocket() {
        return docket;
    }

    public void setDocket(DocketProperties docket) {
        this.docket = docket;
    }

    public List<ResponseMessageProperties> getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(List<ResponseMessageProperties> responseMessage) {
        this.responseMessage = responseMessage;
    }

    public static class ApiInfoProperties {
        private String title;
        private String description;
        private String termsOfServiceUrl;
        private String version;
        private ContactProperties contact;
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

    public static class DocketProperties {
        private Set<String> protocols = newHashSet();
        private Set<String> produces = newHashSet();
        private Set<String> consumes = newHashSet();
        private Set<Tag> tags = newHashSet();
        private String groupName;
        private String apiPackage;
        private String paths;
        private String pathPrefix;
        private String host = "";

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getApiPackage() {
            return apiPackage;
        }

        public void setApiPackage(String apiPackage) {
            this.apiPackage = apiPackage;
        }

        public String getPathPrefix() {
            return pathPrefix;
        }

        public void setPathPrefix(String pathPrefix) {
            this.pathPrefix = pathPrefix;
        }

        public String getPaths() {
            return paths;
        }

        public void setPaths(String paths) {
            this.paths = paths;
        }

        public Set<String> getProtocols() {
            return protocols;
        }

        public void setProtocols(Set<String> protocols) {
            this.protocols = protocols;
        }

        public Set<String> getProduces() {
            return produces;
        }

        public void setProduces(Set<String> produces) {
            this.produces = produces;
        }

        public Set<String> getConsumes() {
            return consumes;
        }

        public void setConsumes(Set<String> consumes) {
            this.consumes = consumes;
        }

        public Set<Tag> getTags() {
            return tags;
        }

        public void setTags(Set<Tag> tags) {
            this.tags = tags;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
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


    public static class ResponseMessageBean {

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
}
