package waitgenerator;


import common.annotation.TableRule;

@TableRule(dao_real_packageName = "cn.iot.m2m.bisserver.dao",bean_real_packageName = "cn.iot.m2m.bisserver.beans.vo")
public class EbInfoApplyNew {


    //新版能力Id
    private String apiEbId;

    //能力缩写
    private String apiCode;

    /**
     * 能力名称
     */
    private String apiName;


    public String getApiEbId() {
        return apiEbId;
    }

    public void setApiEbId(String apiEbId) {
        this.apiEbId = apiEbId;
    }

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }
}
