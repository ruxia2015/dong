package com.dong.sitserver.bean;


import com.dong.sitserver.common.PageBean;

public class ResourceBean extends PageBean {
    /**
     * id
     */


    private String id;

    /**
     * 域名
     */

    private String domain;

    /**
     * 注册类型
     */

    private String url;

    /**
     * 资源类型  01 blog
     */

    private String type;

    /**
     * 资源分类
     * 如：主要用于推广什么类型的网站（齿科、车载）
     */
    private String categoryId;

    /**
     * 可以访问状态
     * -1 等待检测   1 可以访问  2  不可以访问
     */


    private String accessState;

    /**
     * 是否可以注册
     * -1 等待    1 可以注册  2 不可以注册
     */


    private String registerState;

    /**
     * 其他状态
     */

    private String otherState;

    /**
     * 备注
     */

    private String remark;


    private String pr;

    /**
     * 资源分类
     * 如：主要用于推广什么类型的网站（齿科、车载）
     */

    private String categoryIds;


    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getPr() {
        return pr;
    }

    public void setPr(String pr) {
        this.pr = pr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccessState() {
        return accessState;
    }

    public void setAccessState(String accessState) {
        this.accessState = accessState;
    }

    public String getRegisterState() {
        return registerState;
    }

    public void setRegisterState(String registerState) {
        this.registerState = registerState;
    }

    public String getOtherState() {
        return otherState;
    }

    public void setOtherState(String otherState) {
        this.otherState = otherState;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
