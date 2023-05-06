
import lombok.Data;

/**
 * @author gyf
 * @date 2023-04-26 17:18
 */
@Data
//@ApiModel("数据集成任务映射业务源头信息")
public class SourceDataBase {
    ////@ApiModelProperty("源头表/目标表所属系统sys_label")
    String id;
    //@ApiModelProperty("源头表/目标表所属系统")
    Long sourceDataBaseWid;
    //@ApiModelProperty("源头表/目标表所属系统名称")
    String sourceDataBaseName;
    //@ApiModelProperty("源头表/目标表所属系统(贴源层：0、标准层：1、其他)")
    Long dataSource;

}
