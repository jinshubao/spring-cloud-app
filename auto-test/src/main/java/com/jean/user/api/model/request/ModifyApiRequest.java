package com.jean.user.api.model.request;

import com.jean.user.api.model.BaseRequest;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class ModifyApiRequest extends BaseRequest {

    private Long id;

    @NotBlank
    @Length(max = 255)
    private String url;

    @NotBlank
    @Length(max = 255)
    private String method;

    @Length(max = 255)
    private String protocol;

    @Length(max = 255)
    private String host;

    private Integer port;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
