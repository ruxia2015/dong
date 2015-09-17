package com.dong.sitserver.bean;


import com.dong.sitserver.common.annotation.ExportHeaderAnnotation;

public class ResourceTgBean {
    /**
     * id
     */


    private String id;

    private String tgId;

    @ExportHeaderAnnotation(headerName = "资源ID")
    private String resourceId;

    @ExportHeaderAnnotation(headerName = "账号")
    private String account;

    @ExportHeaderAnnotation(headerName = "密码")
    private String password;

    @ExportHeaderAnnotation(headerName = "邮箱")
    private String email;

    @ExportHeaderAnnotation(headerName = "推广的站")

    private String tgDomain;


    private String resourceIds;

    @ExportHeaderAnnotation(headerName = "资源域名")
    private String resourceDomain;

    @ExportHeaderAnnotation(isIngore = true)

    private String registerState;

    @ExportHeaderAnnotation(headerName = "资源类型")

    private String resourceTypeName;

    @ExportHeaderAnnotation(headerName = "资源")

    private String resourceUrl;

    @ExportHeaderAnnotation(headerName = "资源分类")

    private String category;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getResourceTypeName() {
        return resourceTypeName;
    }

    public void setResourceTypeName(String resourceTypeName) {
        this.resourceTypeName = resourceTypeName;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getResourceDomain() {
        return resourceDomain;
    }

    public void setResourceDomain(String resourceDomain) {
        this.resourceDomain = resourceDomain;
    }

    public String getRegisterState() {
        return registerState;
    }

    public void setRegisterState(String registerState) {
        this.registerState = registerState;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTgId() {
        return tgId;
    }

    public void setTgId(String tgId) {
        this.tgId = tgId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTgDomain() {
        return tgDomain;
    }

    public void setTgDomain(String tgDomain) {
        this.tgDomain = tgDomain;
    }

}
