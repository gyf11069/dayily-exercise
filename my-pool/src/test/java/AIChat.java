
import lombok.Data;

import java.util.List;

@Data
//@ApiModel("对话参数对象")
public class AIChat {
//    @ApiModelProperty("对话id")
    private String conversationId;
//    @ApiModelProperty("是否引用，默认值为 false")
    private Boolean quote = false;
//    @ApiModelProperty("是否使用流式响应，默认值为 true")
    private Boolean stream = true;
//    @ApiModelProperty("对话id")
    private List<AIMessage> messages;
}
