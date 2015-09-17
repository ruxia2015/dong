package waitgenerator;


import common.annotation.TableRule;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author luole
 * @Date 2015/9/16 18:04
 */
@TableRule(dao_real_packageName = "cn.iot.m2m.bisserver.dao",bean_real_packageName = "cn.iot.m2m.bisserver.beans.dto")
public class OperRegion {


    /**
     * 账号
     */
    private String operId;

    /**
     * 区域：多省
     */
    private String regions;

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }

    public String getRegions() {
        return regions;
    }

    public void setRegions(String regions) {
        this.regions = regions;
    }
}
