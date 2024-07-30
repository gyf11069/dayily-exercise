//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
//@ApiModel("各业务系统接入信息")
public class BusSystem {

    //@ApiModelProperty(value = "wid")
    Long wid ;
    //@ApiModelProperty(value = "创建人")
//    String creator;
    //@ApiModelProperty(value = "创建时间")
//    String create_time ;
    //@ApiModelProperty(value = "修改人")
//    String last_modifier ;
    //@ApiModelProperty(value = "修改时间")
//    String modify_time ;
    //@ApiModelProperty(value = "排序号")
//    Integer order_num ;
    //@ApiModelProperty(value = "业务源名称")
    String systemname ;
    //@ApiModelProperty(value = "业务源描述")
    String systemdesc ;
    //@ApiModelProperty(value = "业务源地址")
//    String system_address ;
    //@ApiModelProperty(value = "业务源版本")
//    String system_version ;
    //@ApiModelProperty(value = "开发年份")
//    String system_dev_year ;
    //@ApiModelProperty(value = "开发厂商")
//    String system_dev_company;
    //@ApiModelProperty(value = "业务系统编号")
//    String system_id;
    //@ApiModelProperty(value = "管理员")
//    String admin_user ;
    //@ApiModelProperty(value = "管理员名称")
//    String admin_user_name ;

    //@ApiModelProperty(value = "开发厂商名称")
//    String system_dev_company_name ;

    //@ApiModelProperty(value = "已接入表数")
    Long integrateDataObject ;

    //@ApiModelProperty(value = "已接入字段数")
    Long integrateDataField ;

    //@ApiModelProperty(value = "已接入数据量（条）")
    Long integrateDataNum ;

    //@ApiModelProperty(value = "未接入表数")
    Long unIntegrateDataObject ;

    //@ApiModelProperty(value = "未接入字段数")
    Long unIntegrateDataField ;

    //@ApiModelProperty(value = "已接入但未发布表数")
    Long integrateUnreleaseDataObject ;

    //@ApiModelProperty(value = "已接入但未发布字段数")
    Long integrateUnreleaseDataField ;

    //@ApiModelProperty(value = "已接入业务系统聚合wids")
    List<Long> dataBaseWidGroups;

    public BusSystem(Long wid, String systemname, Long integrateDataObject, Long integrateDataField, Long integrateDataNum, Long unIntegrateDataObject, Long unIntegrateDataField, Long integrateUnreleaseDataObject, Long integrateUnreleaseDataField) {
        this.wid = wid;
        this.systemname = systemname;
        this.integrateDataObject = integrateDataObject;
        this.integrateDataField = integrateDataField;
        this.integrateDataNum = integrateDataNum;
        this.unIntegrateDataObject = unIntegrateDataObject;
        this.unIntegrateDataField = unIntegrateDataField;
        this.integrateUnreleaseDataObject = integrateUnreleaseDataObject;
        this.integrateUnreleaseDataField = integrateUnreleaseDataField;
    }
}

