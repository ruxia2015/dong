package com.dong.sitserver.bean;


/**
 * 资源类型bean
 */
public class ResourceTypeBean {

    //id
    private String id;


    //类型名称
    private String name;

    /**
     * 备注信息
     */
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
