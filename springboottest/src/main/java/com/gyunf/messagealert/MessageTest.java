package com.gyunf.messagealert;

import com.gyunf.messagealert.utils.DingTalkMsgUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MessageTest {

    private static final String path = "https://oapi.dingtalk.com/robot/send?access_token=20fa137a13829e45cfed9f1aacff5939e5260bcd29d06690fa61a60547ac7150";
    private static final String secert = "SECf39e19ce3468dc80501438cefbb8bbb95cd8fa3d4a6ea9c9a8f3c718c17600b7";

    // getter and setter

    public static void main(String[] args) {
        Map<String,Object> markdownMap = new HashMap<>();
        markdownMap.put("title","markdown消息测试");
        markdownMap.put("text","# 这是支持markdown的文本 \n## 标题2  \n* 列表1 \nmarkdown@人员的测试消息\n<font color= \"red\" >  @17768675715</font>");

//        "markdown": {
//            "title": "首屏会话透出的展示内容",
//                    "text": "# 这是支持markdown的文本 \n## 标题2  \n* 列表1 \n![alt 啊](https://img.alicdn.com/tps/TB1XLjqNVXXXXc4XVXXXXXXXXXX-170-64.png)"
//        }

        try {
            DingTalkMsgUtil.sendDingTalkMarkDown(path,secert, markdownMap,false,Arrays.asList(new String[]{"17768675715"}));
            System.out.println("消息发送成功！");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("消息发送失败！");
        }


    }
}
