package com.jean.swagger.properties;

import springfox.documentation.service.Tag;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

/**
 * @author jinshubao
 */
public class DocketProperties {
    private boolean enable = true;
    private Set<String> protocols = newHashSet();
    private Set<String> produces = newHashSet();
    private Set<String> consumes = newHashSet();
    private Set<Tag> tags = newHashSet();
    private String groupName = "default";
    private String apiPackage;
    private Set<String> paths;
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

    public Set<String> getPaths() {
        return paths;
    }

    public void setPaths(Set<String> paths) {
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

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
