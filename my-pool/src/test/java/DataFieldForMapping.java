
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Lcx on 2022/1/11.
 */
@Data
//@ApiModel(value = "数据字段")
public class DataFieldForMapping {
	//@ApiModelProperty(value = "wid")
	private Long wid;
	//@ApiModelProperty(value = "数据源Id")
	private Long database_wid;
	//@ApiModelProperty(value = "数据表名")
	private String dataObject;
	//@ApiModelProperty("数据字段英文名")
	private String dataField;
	//@ApiModelProperty("数据字段中文名")
	private String dataFieldName;
	//@ApiModelProperty("字段类型")
	private String fieldType;
	//@ApiModelProperty("字段长度")
	private Integer fieldLength;
	//@ApiModelProperty("字段精度")
	private Integer fieldprecision;
	//@ApiModelProperty("是否主键(1是0否)")
	private Integer is_pk;
	//@ApiModelProperty("允许为空(1是0否)")
	private Integer is_null_able;
	//@ApiModelProperty("引用表英文名")
	private String reference_table;
	//@ApiModelProperty("引用表中文名")
	private String reference_table_name;
	//@ApiModelProperty("引用英文名")
	private String reference_field;
	//@ApiModelProperty("引用名称名")
	private String reference_field_text;
	//@ApiModelProperty("引用中文名")
	private String reference_field_name;
	//@ApiModelProperty("外键表英文名")
	private String foreign_table;
	//@ApiModelProperty("外键表中文名")
	private String foreign_table_name;
	//@ApiModelProperty("外键字段英文名")
	private String foreign_field;
	//@ApiModelProperty("外键字段中文名")
	private String foreign_field_name;
	//@ApiModelProperty("字段描述")
	private String dataFieldDesc;
	//@ApiModelProperty("字段来源说明")
	private String dataRange;
	//@ApiModelProperty("公开类型(0不公开1公开)")
	private Integer open_type;
	//@ApiModelProperty("共享类型(0不共享1共享2有条件共享)")
	private Integer share_type;
	//@ApiModelProperty("字段标准编号")
	private String standard_field_num;
	//@ApiModelProperty(value = "引用参照标准",hidden = true)
	private String quote_standard_field;
	//@ApiModelProperty(value = "引用参照标准编号")
	private String quote_standard_field_num;
	//@ApiModelProperty(value = "引用参照标准名称",hidden = true)
	private String quote_standard_field_name;
	//@ApiModelProperty("参照标准")
	private String reference_standard;
	//@ApiModelProperty("是否启用")
	private Integer status_able;
	//@ApiModelProperty("序号")
	private Integer order_num;
	//@ApiModelProperty(value = "创建人",hidden = true)
	private String creator;
	//@ApiModelProperty(value = "是否核心(1是0否)")
	private Integer is_core;
	//@ApiModelProperty(value = "是否脱敏(1是0否)")
	private Integer need_encrypt;
	//@ApiModelProperty(value = "是否归档(1是0否)")
	private Integer keep_history;
	//@ApiModelProperty(value = "是否存在对标差异(1是0否)")
	private String code_diff;

	//@ApiModelProperty(value = "标识该字段是否需要打开")
	private Boolean openFields ;

	//@ApiModelProperty("技术类别")
	private Integer attr_type;

	//用于质量监测配置
	//@ApiModelProperty(value = "标识字段（质量监测配置展示使用）")
	private String showFields;

	//数据量
	//@ApiModelProperty(value = "数据量")
	private Integer field_row_num;

	//@ApiModelProperty("原始字段")
	private String original_field;

	//@ApiModelProperty("操作：c u d ")
	private String operation;

	//@ApiModelProperty("修改时间")
	private String modify_time;

	//@ApiModelProperty(value = "指标表字段-预览数据明细",hidden = true)
	private Object column;

	//@ApiModelProperty(value = "校验类型")
	private String checkType;

	//@ApiModelProperty(value = "校验规则")
	private String checkRule;

	//@ApiModelProperty(value = "校验规则名称")
	private String checkRuleName;

	/*主题层、标准层集成映射*/
	//@ApiModelProperty("源头表/目标表所属系统")
    List<SourceDataBase> mappingDataBases;
	//@ApiModelProperty("源表/目标表名称 T1,T2,T3")
    List<Map<String, Object>> mappingDataObject;
	//@ApiModelProperty("源表/目标表字段名称 T1,T2,T3")
    List<Map<String, Object>> mappingDataObjectField;
	//@ApiModelProperty(value = "集成任务映射详情wid")
	private Long taskMappingWid;
	//@ApiModelProperty(value = "集成任务wid")
	private Long taskWid;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		DataFieldForMapping dataField1 = (DataFieldForMapping) o;
		return Objects.equals(database_wid, dataField1.database_wid) && Objects.equals(dataObject, dataField1.dataObject) && Objects.equals(dataField, dataField1.dataField) && Objects.equals(dataFieldName, dataField1.dataFieldName) && Objects.equals(fieldType, dataField1.fieldType) && Objects.equals(fieldLength, dataField1.fieldLength) && Objects.equals(is_pk, dataField1.is_pk) && Objects.equals(is_null_able, dataField1.is_null_able) && Objects.equals(reference_table, dataField1.reference_table) && Objects.equals(reference_table_name, dataField1.reference_table_name) && Objects.equals(reference_field, dataField1.reference_field) && Objects.equals(reference_field_name, dataField1.reference_field_name) && Objects.equals(foreign_table, dataField1.foreign_table) && Objects.equals(foreign_table_name, dataField1.foreign_table_name) && Objects.equals(foreign_field, dataField1.foreign_field) && Objects.equals(foreign_field_name, dataField1.foreign_field_name) && Objects.equals(dataFieldDesc, dataField1.dataFieldDesc) && Objects.equals(dataRange, dataField1.dataRange) && Objects.equals(open_type, dataField1.open_type) && Objects.equals(share_type, dataField1.share_type) && Objects.equals(standard_field_num, dataField1.standard_field_num) && Objects.equals(quote_standard_field, dataField1.quote_standard_field) && Objects.equals(quote_standard_field_num, dataField1.quote_standard_field_num) && Objects.equals(quote_standard_field_name, dataField1.quote_standard_field_name) && Objects.equals(reference_standard, dataField1.reference_standard) && Objects.equals(status_able, dataField1.status_able) && Objects.equals(order_num, dataField1.order_num) && Objects.equals(creator, dataField1.creator) && Objects.equals(is_core, dataField1.is_core) && Objects.equals(need_encrypt, dataField1.need_encrypt) && Objects.equals(keep_history, dataField1.keep_history) && Objects.equals(showFields, dataField1.showFields) && Objects.equals(original_field, dataField1.original_field);
	}

	@Override
	public int hashCode() {
		return Objects.hash(database_wid, dataObject, dataField, dataFieldName, fieldType, fieldLength, is_pk, is_null_able, reference_table, reference_table_name, reference_field, reference_field_name, foreign_table, foreign_table_name, foreign_field, foreign_field_name, dataFieldDesc, dataRange, open_type, share_type, standard_field_num, quote_standard_field, quote_standard_field_num, quote_standard_field_name, reference_standard, status_able, order_num, creator, is_core, need_encrypt, keep_history, showFields, original_field);
	}
}
