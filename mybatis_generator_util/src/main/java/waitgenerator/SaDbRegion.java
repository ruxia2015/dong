package waitgenerator;


import common.annotation.TableRule;

/**
 * 地市区号对应表
 *
 * @author 曾宪盛
 * @version [版本号, 12-6-27 下午6:25]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */

@TableRule(dao_real_packageName = "cn.iot.m2m.bisserver.dao.region",bean_real_packageName = "cn.iot.m2m.bisserver.beans.dto")
public class SaDbRegion {

    private String name;

    private String provinceid;

    private String regionid;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvinceid() {
        return this.provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid;
    }

    public String getRegionid() {
        return this.regionid;
    }

    public void setRegionid(String regionid) {
        this.regionid = regionid;
    }


}
