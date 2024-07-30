//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Data
//@ApiModel("各业务系统接入信息数据聚合")
public class BusSystemNum {

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

    // 使用CopyOnWriteArrayList替代ArrayList以增强线程安全
//     List<Long> dataBaseWidGroups = new CopyOnWriteArrayList<>();

    public BusSystemNum(Long integrateDataObject, Long integrateDataField, Long integrateDataNum, Long unIntegrateDataObject, Long unIntegrateDataField, Long integrateUnreleaseDataObject, Long integrateUnreleaseDataField,List<Long> dataBaseWidGroups) {
        this.integrateDataObject = integrateDataObject;
        this.integrateDataField = integrateDataField;
        this.integrateDataNum = integrateDataNum;
        this.unIntegrateDataObject = unIntegrateDataObject;
        this.unIntegrateDataField = unIntegrateDataField;
        this.integrateUnreleaseDataObject = integrateUnreleaseDataObject;
        this.integrateUnreleaseDataField = integrateUnreleaseDataField;
        this.dataBaseWidGroups = dataBaseWidGroups;
    }

    public BusSystemNum() {

    }

    // 构造函数等其他必要方法...

    // 提供一个静态方法来创建实例，以简化在reducing操作中的实例创建
//    public static BusSystemNum newInstance(Long integrateDataObject, Long integrateDataField, Long integrateDataNum,
//                                           Long unIntegrateDataObject, Long unIntegrateDataField,
//                                           Long integrateUnreleaseDataObject, Long integrateUnreleaseDataField) {
//        return new BusSystemNum(integrateDataObject, integrateDataField, integrateDataNum,
//                unIntegrateDataObject, unIntegrateDataField,
//                integrateUnreleaseDataObject, integrateUnreleaseDataField);
//    }
}

