package waitgenerator;


import common.annotation.TableRule;

/**
 * 用户.
 * <p/>
 * 对应数据库表SA_SR_OPER
 *
 * @author liuguo
 */
@TableRule(dao_real_packageName = "cn.iot.m2m.bisserver.dao",bean_real_packageName = "cn.iot.m2m.bisserver.beans.dto")
public class Oper {


    /**
     * 账号
     */
    private String operId;

    /**
     * 省份编码
     */
    private String provinceId;

    /**
     * 操作员姓名
     */
    private String operName;


    /**
     * 手机号
     */
    private String phone;

    /**
     * 办公电话
     */
    private String officeTel;

    /**
     * 传真号
     */
    private String fax;

    /**
     * 邮箱
     */
    private String email;


    /**
     * 帐号的状态0、正常1、销户2、被锁定
     */
    private String status;


    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
