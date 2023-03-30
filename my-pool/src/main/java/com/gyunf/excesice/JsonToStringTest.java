package com.gyunf.excesice;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author gyf
 * @date 2022-11-09 16:12
 */
public class JsonToStringTest {
    public static void main(String[] args) {
        String string = "[\"张三\",\"李四\"]";
        String string2 = "\"张三\",\"李四\""; //此时不能转成 JSONArray
        JSONArray userIds = JSONObject.parseArray(string2);
        userIds.add("莫哈德");
        string2 = userIds.toJSONString();
        System.out.println(string2);

    }
}
