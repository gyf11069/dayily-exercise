//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
//@ApiModel("对话消息对象")
public class AIMessage {
//    @ApiModelProperty("角色（user：用户；assistant：机器人助手）")
    private String role;
//    @ApiModelProperty("对话内容")
    private String content;
}
