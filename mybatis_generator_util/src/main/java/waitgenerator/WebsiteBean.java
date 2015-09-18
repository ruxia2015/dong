package waitgenerator;

import common.annotation.TableRule;

@TableRule(dao_real_packageName = "com.dong.sitserver.dao",bean_real_packageName = "com.dong.sitserver.bean")
public class WebsiteBean {

    private String id;


    private String domain;


    private String categoryId;


    private String remark;


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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
