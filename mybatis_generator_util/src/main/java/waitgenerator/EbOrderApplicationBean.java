package waitgenerator;

/**
 * Created by rxia on 2015/9/14.
 */

import common.annotation.TableRule;

/**
 * api订购申请
 */

@TableRule(dao_real_packageName = "cn.iot.m2m.bisserver.dao",bean_real_packageName = "cn.iot.m2m.bisserver.beans.dto")
public class EbOrderApplicationBean {

    /**
     * 申请ID
     */
   private String orderId	;

    /**
     *审批区域
     */
    private String region;

    /**
     *申请集团
     */
    private String   ecCustId	;

    /**
     * 资费ID
     */
    private String    tariffId	;

    /**
     *定价方式ID
     */
    private String  pricId	;


    /**
     *申请人名称
     */
    private String  orderName	;

    /**
     * 能力ID
     *
     */
    private String   ebId	;

    /**
     *联系人名称
     */
    private String   contactName	;

    /**
     *联系电话
     */
    private String  contactPhone	;

    /**
     *邮箱
     */
    private String   contactEmail	;

    /**
     *1:待大区经理审批,2:大区经理审批驳回,3:待部门领导审批,4:领导审批驳回,5:待全网支撑配置,6:待大区二次确认,7:已完成
     */
    private String status	;

    /**
     *创建时间
     */
    private String  createDate	;

    /**
     *修改时间
     */
    private String  updateDate	;

    /**
     *备注
     */
    private String   remark;



    /**
     * 应用Id
     */
    private String appId;

    /**
     * 申请状态，0:新增/修改申请,1:退订申请
     */
    private String applicationStatus;





}
