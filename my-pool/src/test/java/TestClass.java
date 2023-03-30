import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author gyf
 * @date 2022-12-14 20:00
 */
public class TestClass {

    @Test
    public void JSONTest(){
        /*JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONObject param = new JSONObject();
        jsonObject1.put("xh",10);
        jsonObject2.put("xm","占山");
        jsonArray.add(jsonObject1);
        jsonArray.add(jsonObject2);
        for (int i = 0; i < jsonArray.size(); i++) {
            Map<String,Object> filterParam = (Map<String, Object>) jsonArray.get(i);
            Iterator<Map.Entry<String,Object>> iter = filterParam.entrySet().iterator();
            int j = 0;
            while(iter.hasNext()){
                Map.Entry<String,Object> entry = iter.next();
                String key = entry.getKey();
                String value = String.valueOf(entry.getValue());
                param.put(key,value);
                j++;
            }
        }
        System.out.println(param.toJSONString());
        //{"xh":"10","xm":"占山"}*/

//        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();

        JSONObject param = new JSONObject();
        jsonObject1.put("xh",10);
        jsonObject1.put("xm","占山");


       /* for (int i = 0; i < jsonArray.size(); i++) {
            Map<String,Object> filterParam = (Map<String, Object>) jsonArray.get(i);
            Iterator<Map.Entry<String,Object>> iter = filterParam.entrySet().iterator();
            int j = 0;
            while(iter.hasNext()){
                Map.Entry<String,Object> entry = iter.next();
                String key = entry.getKey();
                String value = String.valueOf(entry.getValue());
                param.put(key,value);
                j++;
            }
        }*/
        param.putAll(jsonObject1);
        System.out.println(param.toJSONString());
        /*只有一个JSONOBJECT是:{"xh":"10","xm":"占山"}*/

        /*
        输出结果：
        空JSONArray数组长度：0
        空JSONArray数组 isEmpty：true
        * *//*
        JSONArray jsonArray = new JSONArray();
        System.out.println( "空JSONArray数组长度：" + jsonArray.size());
        System.out.println("空JSONArray数组 isEmpty：" + jsonArray.isEmpty());*/
    }

    @Test
    public void JSONObjectTest(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("aggId",1223);
      /* String filetrs = jsonObject.getJSONObject("filters").toJSONString();
       System.out.println(filetrs); //java.lang.NullPointerException*/
        if (jsonObject.getJSONObject("filters")== null){
            System.out.println(true);
        }else {
            System.out.println(false);
        }

    }

    @Test
    public void StringTest(){
        /*String url = "/api/agg/previewDetail/16188844851057664/16182564984253440?pi=${pi}&ps=${ps}";
        String requestUrl = StringUtils.substringBeforeLast(url, "?");
        String[] urlArr = requestUrl.split("/");
        Long aggId = Long.parseLong(urlArr[4]);
        System.out.println(aggId);

        String s = null;
        Integer i = Integer.valueOf(s);
        System.out.println(i);*/

//        String str = "民族字典表\nT_YW_ZDB_MZ";
//        String[] strArr = str.split("\n");
//        for (int i = 0; i < strArr.length; i++) {
//            System.out.println(strArr[i]);
//        }


//        String s = "etl,api";
//        String[] split = s.split(",");
//        for (int i = 0; i < split.length; i++) {
//            if ("etl".equals(split[i])){
//                System.out.println(split[i]);
//            }
//            if ("api".equals(split[i])){
//                System.out.println(split[i]);
//            }
//        }

        String s2 = "1231242321123" + null;
        /*String[] s2split = s2.split("_");
        System.out.println(s2split[0]);
        System.out.println(s2split.length);*/
        String id = "";
        if (s2.contains("_")){
            id = StringUtils.substringAfter(s2, "_");
        }else {
            id = s2;
        }

        System.out.println(id);
    }


    @Test
    public void readingJsonFileTest(){


    }
    @Test
    public void LongListTest(){
        List<Long> ids = new ArrayList<>();
//        ids.add(1123L);
//        ids.add(1123L);
//        ids.add(112L);
//        Set<Long> collect = new HashSet<>(ids);
//        if (collect.size()>1){
//            System.out.println("数据源不同");
//        }else {
//            Long dsId = ids.stream().distinct().collect(Collectors.toList()).get(0);
//            System.out.println(dsId);
//        }
        String string = JSON.toJSONString(ids);
        System.out.println(string);

    }
    @Test
    public void SwitchTest(){

       /* String s = null;
        switch (s){
            case "1":
                System.out.println("1");
                break;
            case "2":
                System.out.println("2");
                break;
            default:
                s = "default";
                System.out.println(s);
        }*/
//
//        /*null转换值的*/
//        Object o1 = null;
//        Object o11 = o1 == null ? o1 : (int) o1;
//        Object o2 = null;
//        Object o22 = o2 == null ? o2 : (int) o2;
//
////        System.out.println(o);
//        if ((o11 == null && o22==null) || ((int)o11!=1 || (int) o22!=1) ){
//            System.out.println(o11);
//            System.out.println(o22);
//        }

//        String s1 = "T_GCS2";
//        String s2 = "t_gcs2";
//        if (s1.equalsIgnoreCase(s2)){
//            System.out.println("相等");
//        }

//        Map map = new HashMap();
//        map.put("id",2);
//        Integer integer = (Integer) map.get("id");
//        System.out.println(integer);

    }

    @Test
    public void StrToJosn(){
//       String str = "[\n" +
//               "  {\n" +
//               "    \"entityAlias\": \"CS2319\",\n" +
//               "    \"ref\": {\n" +
//               "      \"id\": \"16725844871540737\",\n" +
//               "      \"name\": \"scsl\",\n" +
//               "      \"title\": \"售出数量\",\n" +
//               "      \"params\": {}\n" +
//               "    },\n" +
//               "    \"aliasName\": \"SCSL\",\n" +
//               "    \"aliasTitle\": \"售出数量\",\n" +
//               "    \"mode\": {\n" +
//               "      \"id\": \"1\",\n" +
//               "      \"title\": \"指标\",\n" +
//               "      \"name\": \"IND\"\n" +
//               "    },\n" +
//               "    \"computed\": false,\n" +
//               "    \"valueType\": {\n" +
//               "      \"id\": \"0\",\n" +
//               "      \"title\": \"字段值\",\n" +
//               "      \"name\": \"COL\"\n" +
//               "    },\n" +
//               "    \"aggFunc\": {\n" +
//               "      \"id\": \"5\",\n" +
//               "      \"title\": \"最大\",\n" +
//               "      \"name\": \"MAX\"\n" +
//               "    },\n" +
//               "    \"type\": {\n" +
//               "      \"id\": \"0\",\n" +
//               "      \"title\": \"文本\",\n" +
//               "      \"amisType\": \"text\",\n" +
//               "      \"flag\": \"C\",\n" +
//               "      \"name\": \"TEXT\"\n" +
//               "    }\n" +
//               "  },\n" +
//               "  {\n" +
//               "    \"entityAlias\": \"GCS231917\",\n" +
//               "    \"ref\": {\n" +
//               "      \"id\": \"16732252243423233\",\n" +
//               "      \"name\": \"mc\",\n" +
//               "      \"title\": \"名称\",\n" +
//               "      \"params\": {}\n" +
//               "    },\n" +
//               "    \"aliasName\": \"MC\",\n" +
//               "    \"aliasTitle\": \"名称\",\n" +
//               "    \"mode\": {\n" +
//               "      \"id\": \"2\",\n" +
//               "      \"title\": \"属性\",\n" +
//               "      \"name\": \"CONST\"\n" +
//               "    },\n" +
//               "    \"computed\": false,\n" +
//               "    \"valueType\": {\n" +
//               "      \"id\": \"0\",\n" +
//               "      \"title\": \"字段值\",\n" +
//               "      \"name\": \"COL\"\n" +
//               "    },\n" +
//               "    \"aggFunc\": {\n" +
//               "      \"id\": \"5\",\n" +
//               "      \"title\": \"最大\",\n" +
//               "      \"name\": \"MAX\"\n" +
//               "    },\n" +
//               "    \"type\": {\n" +
//               "      \"id\": \"0\",\n" +
//               "      \"title\": \"文本\",\n" +
//               "      \"amisType\": \"text\",\n" +
//               "      \"flag\": \"C\",\n" +
//               "      \"name\": \"TEXT\"\n" +
//               "    }\n" +
//               "  },\n" +
//               "  {\n" +
//               "    \"entityAlias\": \"CS2319\",\n" +
//               "    \"ref\": {\n" +
//               "      \"id\": \"16725844871802880\",\n" +
//               "      \"name\": \"kc\",\n" +
//               "      \"title\": \"库存\",\n" +
//               "      \"params\": {}\n" +
//               "    },\n" +
//               "    \"aliasName\": \"KC\",\n" +
//               "    \"aliasTitle\": \"库存\",\n" +
//               "    \"mode\": {\n" +
//               "      \"id\": \"1\",\n" +
//               "      \"title\": \"指标\",\n" +
//               "      \"name\": \"IND\"\n" +
//               "    },\n" +
//               "    \"computed\": false,\n" +
//               "    \"valueType\": {\n" +
//               "      \"id\": \"0\",\n" +
//               "      \"title\": \"字段值\",\n" +
//               "      \"name\": \"COL\"\n" +
//               "    },\n" +
//               "    \"aggFunc\": {\n" +
//               "      \"id\": \"5\",\n" +
//               "      \"title\": \"最大\",\n" +
//               "      \"name\": \"MAX\"\n" +
//               "    },\n" +
//               "    \"type\": {\n" +
//               "      \"id\": \"0\",\n" +
//               "      \"title\": \"文本\",\n" +
//               "      \"amisType\": \"text\",\n" +
//               "      \"flag\": \"C\",\n" +
//               "      \"name\": \"TEXT\"\n" +
//               "    }\n" +
//               "  },\n" +
//               "  {\n" +
//               "    \"entityAlias\": \"CS2319\",\n" +
//               "    \"ref\": {\n" +
//               "      \"id\": \"16725844871540736\",\n" +
//               "      \"name\": \"xh\",\n" +
//               "      \"title\": \"序号\",\n" +
//               "      \"params\": {}\n" +
//               "    },\n" +
//               "    \"aliasName\": \"XH\",\n" +
//               "    \"aliasTitle\": \"序号\",\n" +
//               "    \"mode\": {\n" +
//               "      \"id\": \"0\",\n" +
//               "      \"title\": \"维度\",\n" +
//               "      \"name\": \"DIM\"\n" +
//               "    },\n" +
//               "    \"computed\": false,\n" +
//               "    \"valueType\": {\n" +
//               "      \"id\": \"0\",\n" +
//               "      \"title\": \"字段值\",\n" +
//               "      \"name\": \"COL\"\n" +
//               "    },\n" +
//               "    \"aggFunc\": {\n" +
//               "      \"id\": \"5\",\n" +
//               "      \"title\": \"最大\",\n" +
//               "      \"name\": \"MAX\"\n" +
//               "    },\n" +
//               "    \"type\": {\n" +
//               "      \"id\": \"0\",\n" +
//               "      \"title\": \"文本\",\n" +
//               "      \"amisType\": \"text\",\n" +
//               "      \"flag\": \"C\",\n" +
//               "      \"name\": \"TEXT\"\n" +
//               "    }\n" +
//               "  }\n" +
//               "]";
//
//        JSONArray jsonArray = JSONArray.parseArray(str);
//        for (int i = 0; i < jsonArray.size(); i++) {
//            JSONObject jsonObject = jsonArray.getJSONObject(i);
//            String aliasName = jsonObject.getString("aliasName").toLowerCase();
//            jsonObject.put("aliasName",aliasName);
//        }
//
//        System.out.println(jsonArray.toJSONString());




//        String string = "{\n" +
//                "\t\"code\": \"0\",\n" +
//                "\t\"msg\": \"OK\",\n" +
//                "\t\"data\": {\n" +
//                "\t\t\"id\": \"16781347898191872\",\n" +
//                "\t\t\"catId\": \"0\",\n" +
//                "\t\t\"dsId\": \"1\",\n" +
//                "\t\t\"name\": \"T_GCS23011122_DERIVE9\",\n" +
//                "\t\t\"title\": \"高测试23011122_DERIVE9\",\n" +
//                "\t\t\"mode\": {\n" +
//                "\t\t\t\"id\": \"0\",\n" +
//                "\t\t\t\"title\": \"可视\",\n" +
//                "\t\t\t\"name\": \"VISUAL\"\n" +
//                "\t\t},\n" +
//                "\t\t\"sql\": \"SELECT T_GCS23011122.\\\"xh\\\" AS xh, MAX(T_GCS23011122.\\\"kc\\\") AS kc \\nFROM (SELECT SJCSXX.\\\"xh\\\" AS xh, COUNT(SJCSXX.\\\"sykc\\\") AS kc \\nFROM \\\"t_phone_sell\\\" SJCSXX \\nGROUP BY xh)  GCS23011122 \\nGROUP BY xh\",\n" +
//                "\t\t\"sqlUpdatedAt\": \"1673446161957\",\n" +
//                "\t\t\"visual\": {\n" +
//                "\t\t\t\"aggId\": \"16781347898191872\",\n" +
//                "\t\t\t\"main\": {\n" +
//                "\t\t\t\t\"ref\": {\n" +
//                "\t\t\t\t\t\"id\": \"16781341051252736\",\n" +
//                "\t\t\t\t\t\"name\": \"T_GCS23011122\",\n" +
//                "\t\t\t\t\t\"title\": \"高测试23011122\",\n" +
//                "\t\t\t\t\t\"params\": {}\n" +
//                "\t\t\t\t},\n" +
//                "\t\t\t\t\"alias\": \"GCS23011122\"\n" +
//                "\t\t\t},\n" +
//                "\t\t\t\"joins\": [],\n" +
//                "\t\t\t\"columns\": [\n" +
//                "\t\t\t\t{\n" +
//                "\t\t\t\t\t\"entityAlias\": \"T_GCS23011122\",\n" +
//                "\t\t\t\t\t\"ref\": {\n" +
//                "\t\t\t\t\t\t\"id\": \"16781341050990592\",\n" +
//                "\t\t\t\t\t\t\"name\": \"xh\",\n" +
//                "\t\t\t\t\t\t\"title\": \"序号\",\n" +
//                "\t\t\t\t\t\t\"params\": {}\n" +
//                "\t\t\t\t\t},\n" +
//                "\t\t\t\t\t\"aliasName\": \"xh\",\n" +
//                "\t\t\t\t\t\"aliasTitle\": \"序号\",\n" +
//                "\t\t\t\t\t\"mode\": {\n" +
//                "\t\t\t\t\t\t\"id\": \"0\",\n" +
//                "\t\t\t\t\t\t\"title\": \"维度\",\n" +
//                "\t\t\t\t\t\t\"name\": \"DIM\"\n" +
//                "\t\t\t\t\t},\n" +
//                "\t\t\t\t\t\"computed\": false,\n" +
//                "\t\t\t\t\t\"valueType\": {\n" +
//                "\t\t\t\t\t\t\"id\": \"0\",\n" +
//                "\t\t\t\t\t\t\"title\": \"字段值\",\n" +
//                "\t\t\t\t\t\t\"name\": \"COL\"\n" +
//                "\t\t\t\t\t},\n" +
//                "\t\t\t\t\t\"aggFunc\": {\n" +
//                "\t\t\t\t\t\t\"id\": \"5\",\n" +
//                "\t\t\t\t\t\t\"title\": \"最大\",\n" +
//                "\t\t\t\t\t\t\"name\": \"MAX\"\n" +
//                "\t\t\t\t\t},\n" +
//                "\t\t\t\t\t\"type\": {\n" +
//                "\t\t\t\t\t\t\"id\": \"0\",\n" +
//                "\t\t\t\t\t\t\"title\": \"文本\",\n" +
//                "\t\t\t\t\t\t\"amisType\": \"text\",\n" +
//                "\t\t\t\t\t\t\"flag\": \"C\",\n" +
//                "\t\t\t\t\t\t\"name\": \"TEXT\"\n" +
//                "\t\t\t\t\t}\n" +
//                "\t\t\t\t},\n" +
//                "\t\t\t\t{\n" +
//                "\t\t\t\t\t\"entityAlias\": \"T_GCS23011122\",\n" +
//                "\t\t\t\t\t\"ref\": {\n" +
//                "\t\t\t\t\t\t\"id\": \"16781341050990593\",\n" +
//                "\t\t\t\t\t\t\"name\": \"kc\",\n" +
//                "\t\t\t\t\t\t\"title\": \"库存\",\n" +
//                "\t\t\t\t\t\t\"params\": {}\n" +
//                "\t\t\t\t\t},\n" +
//                "\t\t\t\t\t\"aliasName\": \"kc\",\n" +
//                "\t\t\t\t\t\"aliasTitle\": \"库存\",\n" +
//                "\t\t\t\t\t\"mode\": {\n" +
//                "\t\t\t\t\t\t\"id\": \"1\",\n" +
//                "\t\t\t\t\t\t\"title\": \"指标\",\n" +
//                "\t\t\t\t\t\t\"name\": \"IND\"\n" +
//                "\t\t\t\t\t},\n" +
//                "\t\t\t\t\t\"computed\": false,\n" +
//                "\t\t\t\t\t\"valueType\": {\n" +
//                "\t\t\t\t\t\t\"id\": \"0\",\n" +
//                "\t\t\t\t\t\t\"title\": \"字段值\",\n" +
//                "\t\t\t\t\t\t\"name\": \"COL\"\n" +
//                "\t\t\t\t\t},\n" +
//                "\t\t\t\t\t\"aggFunc\": {\n" +
//                "\t\t\t\t\t\t\"id\": \"5\",\n" +
//                "\t\t\t\t\t\t\"title\": \"最大\",\n" +
//                "\t\t\t\t\t\t\"name\": \"MAX\"\n" +
//                "\t\t\t\t\t},\n" +
//                "\t\t\t\t\t\"type\": {\n" +
//                "\t\t\t\t\t\t\"id\": \"0\",\n" +
//                "\t\t\t\t\t\t\"title\": \"文本\",\n" +
//                "\t\t\t\t\t\t\"amisType\": \"text\",\n" +
//                "\t\t\t\t\t\t\"flag\": \"C\",\n" +
//                "\t\t\t\t\t\t\"name\": \"TEXT\"\n" +
//                "\t\t\t\t\t}\n" +
//                "\t\t\t\t}\n" +
//                "\t\t\t]\n" +
//                "\t\t},\n" +
//                "\t\t\"publishedAt\": \"0\",\n" +
//                "\t\t\"publishedView\": \"0\",\n" +
//                "\t\t\"isIncrement\": {\n" +
//                "\t\t\t\"id\": \"0\",\n" +
//                "\t\t\t\"title\": \"启用\",\n" +
//                "\t\t\t\"name\": \"ENABLED\"\n" +
//                "\t\t},\n" +
//                "\t\t\"status\": {\n" +
//                "\t\t\t\"id\": \"0\",\n" +
//                "\t\t\t\"title\": \"启用\",\n" +
//                "\t\t\t\"name\": \"ENABLED\"\n" +
//                "\t\t},\n" +
//                "\t\t\"recycleId\": \"0\",\n" +
//                "\t\t\"lastHistoryId\": \"16781347902648320\",\n" +
//                "\t\t\"published\": false,\n" +
//                "\t\t\"hasContent\": true\n" +
//                "\t}\n" +
//                "}";
//        JSONObject jsonObject = JSONObject.parseObject(string);
//
//        Integer isIncrement = jsonObject.getJSONObject("data").getJSONObject("isIncrement").getInteger("id");
//        Integer isCreateView = jsonObject.getJSONObject("data").getInteger("publishedView");
//        System.out.println(isIncrement);
//        System.out.println(isCreateView);


        String indCuring = "{\n" +
                "  \"taskName\": \"高测试23011611\",\n" +
                "  \"exe_status\": \"1\",\n" +
                "  \"crontype\": \"1\",\n" +
                "  \"plan_minute\": \"\",\n" +
                "  \"plan_hour\": \"\",\n" +
                "  \"plan_day\": \"\",\n" +
                "  \"plan_month\": \"\",\n" +
                "  \"plan_week\": \"\",\n" +
                "  \"plan_minute_from\": \"\",\n" +
                "  \"plan_minute_to\": \"\",\n" +
                "  \"plan_minute_num\": \"\",\n" +
                "  \"plan_minute_cycle\": \"\",\n" +
                "  \"plan_hour_from\": \"\",\n" +
                "  \"plan_hour_to\": \"\",\n" +
                "  \"plan_hour_num\": \"\",\n" +
                "  \"plan_hour_cycle\": \"\",\n" +
                "  \"plan_day_from\": \"\",\n" +
                "  \"plan_day_to\": \"\",\n" +
                "  \"plan_day_num\": \"\",\n" +
                "  \"plan_day_cycle\": \"\",\n" +
                "  \"plan_month_from\": \"\",\n" +
                "  \"plan_month_to\": \"\",\n" +
                "  \"plan_month_num\": \"\",\n" +
                "  \"plan_month_cycle\": \"\",\n" +
                "  \"plan_week_from\": \"\",\n" +
                "  \"plan_week_to\": \"\",\n" +
                "  \"plan_week_num\": \"\",\n" +
                "  \"plan_week_day\": \"\",\n" +
                "  \"plan_week_last\": \"\",\n" +
                "  \"plan_minute_s\": \"\",\n" +
                "  \"plan_hour_s\": \"\",\n" +
                "  \"plan_day_s\": \"\",\n" +
                "  \"plan_week_days\": \"\",\n" +
                "  \"cron\": \"* * * * *\"\n" +
                "}";

//        String indCuring = null;
        JSONObject indCuringJsonObject = JSONObject.parseObject(indCuring);
        if (indCuringJsonObject == null || indCuringJsonObject.get("exe_status") == null || ("1".equals(indCuringJsonObject.get("exe_status")) )) {
            Long wid = indCuringJsonObject.getLong("wid");
            if (wid == null){
                System.out.println("wid is null");
            }
        }
//        try{
//            /*wid*/
//            Object inds = indCuringJsonObject.get("inds");
//            if (inds == null){
//                System.out.println("inds为null");
//            }
////        System.out.println(inds.toString());
//        }catch (Exception e){
//            e.printStackTrace();
//            System.out.println("程序出错了");
//        }

        System.out.println("运行到这了吗");

    }

    @Test
    public void StringBufferToStr(){
        StringBuffer codeLeftJoin = new StringBuffer();
        String sql = "select {} from {} {} where 1=1";
        System.out.println(sql + codeLeftJoin.toString());
    }


    @Test
    public void MapToString(){
        Map map = new HashMap();
        map.put("id",1);
        map.put("limit",null);
        String q = "";
        String str  = "";
        q = (String) map.get("limit");
        if (q == null || "".equals(q) || "null".equals(q)){
            str  =  "select ";
        }else {
            str = "select " + q;
        }

        System.out.println(str);
    }

    @Data
    @Accessors(chain = true)
    public class IndicatorCuring implements Serializable {
        private static final long serialVersionUID = 1;

        private Long wid;

        private String creator;

        private String create_time;

        private String last_modifier;

        private String modify_time;

        private int order_num;

        private String taskName;

        private String taskDesc;

        private String inds;

        private String exe_status;

        private String cron;

        private String plan_second;

        private String plan_second_from;

        private String plan_second_to;

        private String plan_second_num;

        private String plan_second_cycle;

        private String plan_second_s;

        private String plan_minute;

        private String plan_minute_from;

        private String plan_minute_to;

        private String plan_minute_num;

        private String plan_minute_cycle;

        private String plan_minute_s;

        private String plan_hour;

        private String plan_hour_from;

        private String plan_hour_to;

        private String plan_hour_num;

        private String plan_hour_cycle;

        private String plan_hour_s;

        private String plan_day;

        private String plan_day_from;

        private String plan_day_to;

        private String plan_day_num;

        private String plan_day_cycle;

        private String plan_day_work;

        private String plan_day_s;

        private String plan_month;

        private String plan_month_from;

        private String plan_month_to;

        private String plan_month_num;

        private String plan_month_cycle;

        private String plan_month_s;

        private String plan_week;

        private String plan_week_from;

        private String plan_week_to;

        private String plan_week_num;

        private String plan_week_day;

        private String plan_week_last;

        private String plan_week_days;

        private String plan_year;

        private String plan_year_from;

        private String plan_year_to;

        private Integer crontype;


        private Long dataSourceId;

        private List<String> indsList;


        String isIncrement;

        String isCreateView;

    }

    @Test
    public void judgeOddEven(){
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
//        for (int i = 0; i < list.size(); i++) {
//
//            if ((i+1) % 2 ==0 ){
//                System.out.println(list.get(i));
//            }
//        }

        if (list.size() % 2 == 0){
            System.out.println("偶数项");
        }else {
            System.out.println("奇数项" + list.size());
        }


//        /*java中用什么类型存储-400*/
//        int i = -400;
//        System.out.println(i);

    }


    @Test
    public void IntegerTOintTest(){
//        Integer i = 2;
//        int z = 4;
//        if (i==z){
//            System.out.println("相等");
//        }
//        System.out.println("不相等");

//        String i = ""+"";
//        if (i == null || i.length() == 0){
//            System.out.println("是空字符");
//        }else {
//            System.out.println("不是空字符");
//        }

//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        Date date=new Date();
//        String format = simpleDateFormat.format(date);
//        System.out.println(format);

//          Map map = new HashMap();
//          map.put("id",null);
//          String s = (String) map.get("id");
//          String s2 = valueOf(map.get("id"));
//          System.out.println(s);
//          System.out.println(s2);

        String warnType = "/【】";
        warnType = StringUtils.substringAfter(warnType,"/");
        System.out.println(warnType);


    }

    public static String valueOf(Object obj) {
        return (obj == null) ? "" : obj.toString();
    }

    @Test
    public void ObjectToMapOrJsonObject(){

    }

    @Data
    //("血缘数据表")
    public class DataObjectByBlood {
        //("用于连线所用的key")
        private String key;
        //("血缘分类(1源头,2数据湖,3标准层,4应用层或服务,5应用)")
        private Integer bloodType;
        //("wid")
        private Long wid;
        //("表英文名")
        private String dataObject;
        //("表中文名")
        private String dataObjectName;
        //("数据源Id")
        private Long database_wid;
        //("数据源中文名")
        private String database_name;
        //("数据字段")
//        List<DataFieldByBlood> dataFieldByBloods;
        //("来源系统")
        private String dataBaseSource;
        //("来源部门")
        private String deptSource;
    }

    @Data
   //("数据血缘服务")
    public class DataServiceByBlood {
        //("血缘分类(1源头,2数据湖,3标准层,4应用层或服务,5应用)")
        private Integer bloodType;
        //("服务Id")
        private Long service_wid;
        //("服务名称")
        private String serviceName;
        //("下方应用")
//        private List<DataAppByBlood> serviceApps;
    }

    /**
     * peek map
     */
    @Test
    public void peekAndMapTest() {
        List<String> stringList2 = Arrays.asList("11", "22", "33");

        //支持自定义返回值，将字符串转换为数字
        List<Integer> mapResultList = stringList2.stream().map(s -> Integer.valueOf(s)+10).collect(Collectors.toList());
        System.out.println(mapResultList);

        //可以看到返回值还是List<String>
        List<String> peekResultList = stringList2.stream().peek(s -> s = s + "g").collect(Collectors.toList());
        System.out.println(peekResultList);

        List<User> userList= Stream.of(new User("a"),new User("b"),new User("c")).peek(u->u.setName("gyf")).collect(Collectors.toList());
        userList.forEach(System.out::println);
    }

    @Data
    @AllArgsConstructor
    static class User{
        private String name;
    }


    /**
     * stream exercise
    * */
    /**
     * 造数据
     *
     * @return
     */
    public static List<Person> generatePerson() {
        List<Person> people = new ArrayList<>(8);
        Person p1 = new Person("小赵", 23);
        Person p2 = new Person("小钱", 12);
        Person p3 = new Person("小孙", 5);
        Person p4 = new Person("小钱", 25);

        people.add(p1);
        people.add(p2);
        people.add(p3);
        people.add(p4);
        return people;
    }
    /**
     * 造数据2
     *
     * @return
     */
    public static List<Person> generatePerson2() {
        List<Person> people = new ArrayList<>(8);
        Person p1 = new Person("小赵", 23);
        Person p2 = new Person("小钱", 12);
        Person p3 = new Person("小孙", 5);
        Person p4 = new Person("小钱", 25);
        Person p5 = new Person("小钱", 25);

        people.add(p1);
        people.add(p2);
        people.add(p3);
        people.add(p4);
        people.add(p5);
        return people;
    }
    /**
     * 实体
     */
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class Person {
        String name;
        int age;
    }

    /**
     * sort -- 排序
     */
    @Test
    public void sortProcess() {
        List<Person> list = generatePerson();
        /*从大到小排序*/
        List<Person> people = list.stream().sorted(Comparator.comparing(Person::getAge).reversed()).collect(Collectors.toList());
        System.out.println("people.age old ==> young" + JSON.toJSONString(people));
    }

    /**
     * findFirst -- 筛选第一个
     */
    @Test
    public void findFirstProcess() {
        List<Person> list = generatePerson();
//        Person person = list.stream().findFirst().get();
        /*从小到大排序*/
        Person person = list.stream().sorted(Comparator.comparing(Person::getAge)).findFirst().get();
        List<Person> p = list.stream().sorted(Comparator.comparing(Person::getAge)).collect(Collectors.toList());
        System.out.println("person = " + JSON.toJSONString(person));
        System.out.println("person list = " + JSON.toJSONString(p));

        List<Person> l = new ArrayList<>();
        /*如果集合为空则返回null，否则返回元素值本身*/
        Person personl = l.stream().sorted(Comparator.comparing(Person::getAge)).findFirst().orElse(null);
        System.out.println("empty = " + JSON.toJSONString(personl));
    }



    /*List去重(元素为对象)*/
    @Test
    public void distinctElement(){

        String of = "[\n" +
                "    {\n" +
                "        \"BELONGDEPTS\": [\"EC4FC3158EB8CC21E0530100007F73BE\"],\n" +
                "        \"BELONGDEPTNAMES\": [],\n" +
                "        \"BELONGDEPT\": \"1\",\n" +
                "        \"BELONGDEPTNAME\": \"undefined\",\n" +
                "        \"COLUMNLEN\": \"20\",\n" +
                "        \"COLUMNTYPE\": \"VARCHAR2\",\n" +
                "        \"DATAFIELD\": \"ZGH\",\n" +
                "        \"DATAFIELDNAME\": \"ZGH\",\n" +
                "        \"IMPORTANT\": 0,\n" +
                "        \"ISCANNULL\": \"0\",\n" +
                "        \"ISKEY\": \"0\",\n" +
                "        \"TABLEFIELD\": \"ZGH\",\n" +
                "        \"YYDMB\": \"\",\n" +
                "        \"WID\": \"EC4FC3158EB8CC21E0530100007F73BE\",\n" +
                "        \"XH\": 1,\n" +
                "        \"ISSHARE\": \"1\",\n" +
                "        \"BZ\": null,\n" +
                "        \"CAUSE\": null\n" +
                "    },\n" +
                "    {\n" +
                "        \"BELONGDEPTS\": [\n" +
                "            \"A464FE99E32B4EACE0537D00A8C0A3F9\"\n" +
                "        ],\n" +
                "        \"BELONGDEPTNAMES\": [\n" +
                "            \"教务处\"\n" +
                "        ],\n" +
                "        \"BELONGDEPT\": \"\",\n" +
                "        \"BELONGDEPTNAME\": \"\",\n" +
                "        \"COLUMNLEN\": \"50\",\n" +
                "        \"COLUMNTYPE\": \"VARCHAR2\",\n" +
                "        \"DATAFIELD\": \"XM\",\n" +
                "        \"DATAFIELDDESC\": \"姓名\",\n" +
                "        \"DATAFIELDNAME\": \"XM\",\n" +
                "        \"IMPORTANT\": 0,\n" +
                "        \"ISCANNULL\": 0,\n" +
                "        \"ISKEY\": 0,\n" +
                "        \"TABLEFIELD\": \"XM\",\n" +
                "        \"YYDMB\": \"\",\n" +
                "        \"WID\": \"EC4FC3158EB9CC21E0530100007F73BE\",\n" +
                "        \"XH\": 2,\n" +
                "        \"ISSHARE\": \"1\",\n" +
                "        \"BZ\": null,\n" +
                "        \"CAUSE\": null\n" +
                "    }\n" +
                "]";
        String nf = "[\n" +
                "    {\n" +
                "        \"BELONGDEPTS\": [\"EC4FC3158EB8CC21E0530100007F73BE\"],\n" +
                "        \"BELONGDEPTNAMES\": [],\n" +
                "        \"BELONGDEPT\": \"1\",\n" +
                "        \"BELONGDEPTNAME\": \"undefined\",\n" +
                "        \"COLUMNLEN\": \"20\",\n" +
                "        \"COLUMNTYPE\": \"VARCHAR2\",\n" +
                "        \"DATAFIELD\": \"ZGH\",\n" +
                "        \"DATAFIELDNAME\": \"ZGH\",\n" +
                "        \"IMPORTANT\": 0,\n" +
                "        \"ISCANNULL\": \"0\",\n" +
                "        \"ISKEY\": \"0\",\n" +
                "        \"TABLEFIELD\": \"ZGH\",\n" +
                "        \"YYDMB\": \"\",\n" +
                "        \"WID\": \"EC4FC3158EB8CC21E0530100007F73BE\",\n" +
                "        \"XH\": 1,\n" +
                "        \"ISSHARE\": \"1\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"BELONGDEPTS\": [\n" +
                "            \"A464FE99E32B4EACE0537D00A8C0A3F9\"\n" +
                "        ],\n" +
                "        \"BELONGDEPTNAMES\": [\n" +
                "            \"教务处\"\n" +
                "        ],\n" +
                "        \"BELONGDEPT\": \"\",\n" +
                "        \"BELONGDEPTNAME\": \"\",\n" +
                "        \"COLUMNLEN\": \"50\",\n" +
                "        \"COLUMNTYPE\": \"VARCHAR2\",\n" +
                "        \"DATAFIELD\": \"XM\",\n" +
                "        \"DATAFIELDDESC\": \"姓名\",\n" +
                "        \"DATAFIELDNAME\": \"XM\",\n" +
                "        \"IMPORTANT\": 0,\n" +
                "        \"ISCANNULL\": 0,\n" +
                "        \"ISKEY\": 0,\n" +
                "        \"TABLEFIELD\": \"XM\",\n" +
                "        \"YYDMB\": \"\",\n" +
                "        \"WID\": \"EC4FC3158EB9CC21E0530100007F73BE\",\n" +
                "        \"XH\": 2,\n" +
                "        \"ISSHARE\": \"1\",\n" +
                "        \"BZ\": \"测试备注\",\n" +
                "        \"CAUSE\": \"测试不能提供原因\"\n" +
                "    }\n" +
                "]";

        String od = "{\n" +
                "    \"WID\": \"EC4FC308D27ACC1EE0530100007FF9E3\",\n" +
                "    \"DATAOBJECT\": \"V1525256369776\",\n" +
                "    \"TABLENAME\": \"V1525256369776\",\n" +
                "    \"DATAOBJECTSIGN\": \"1\",\n" +
                "    \"STATUS\": \"On-line\",\n" +
                "    \"SYSTEM\": \"1\",\n" +
                "    \"DATAOBJECTDESC\": \"测试\",\n" +
                "    \"DATAOBJECTNAME\": \"V1525256369776\",\n" +
                "    \"DATAOBJECTTYPE\": \"2\",\n" +
                "    \"IMPORTANT\": 0,\n" +
                "    \"CZBZ\": \"T_JZG_GW\"\n" +
                "}";

        String nd = "{\n" +
                "    \"WID\": \"EC4FC308D27ACC1EE0530100007FF9E3\",\n" +
                "    \"DATAOBJECT\": \"V1525256369776\",\n" +
                "    \"TABLENAME\": \"V1525256369776\",\n" +
                "    \"DATAOBJECTSIGN\": \"1\",\n" +
                "    \"STATUS\": \"On-line\",\n" +
                "    \"SYSTEM\": \"1\",\n" +
                "    \"DATAOBJECTDESC\": \"测试\",\n" +
                "    \"DATAOBJECTNAME\": \"V1525256369776\",\n" +
                "    \"DATAOBJECTTYPE\": \"2\",\n" +
                "    \"IMPORTANT\": 0,\n" +
                "    \"CZBZ\": \"T_JZG_GW\"\n" +
                "}";

        List<Datafield> odatafieldArr = JSONArray.parseArray(of,Datafield.class);
        List<Datafield> ndatafieldArr = JSONArray.parseArray(nf,Datafield.class);
//        List<Datafield> odatafields = odatafieldArr.toJavaList(Datafield.class);
//        List<Datafield> ndatafields = ndatafieldArr.toJavaList(Datafield.class);
        List<Datafield> diffdatafield = new ArrayList<>();

        Dataobject odataobjectObj = JSONObject.parseObject(od,Dataobject.class);
        Dataobject ndataobjectObj = JSONObject.parseObject(nd,Dataobject.class);
        List<Dataobject> odata = new ArrayList<>();
        List<Dataobject> ndata = new ArrayList<>();
        odata.add(odataobjectObj);
        ndata.add(ndataobjectObj);
//        Dataobject odataobject = odataobjectObj.toJavaObject(Dataobject.class);
//        Dataobject ndataobject = ndataobjectObj.toJavaObject(Dataobject.class);

        for (int i = 0; i < ndatafieldArr.size(); i++) {
            Datafield datafield = ndatafieldArr.get(i);
            if (!odatafieldArr.contains(datafield)){
                diffdatafield.add(datafield);
            }
        }
    diffdatafield.forEach(System.out::println);

        System.out.println("===============Object eqauls=============================");
       if (!Objects.equals(odataobjectObj,ndataobjectObj)){
           System.out.println(ndataobjectObj.toString());
       }
        System.out.println("===============List removeAll for datafield=============================");
        ndatafieldArr.removeAll(odatafieldArr);
        ndatafieldArr.forEach(System.out::println);
        System.out.println("===============List removeAll for dataobject============================");
        ndata.removeAll(odata);
        ndata.forEach(System.out::println);


    }


    @Test
    public void listremove(){
//        List<String> li = new ArrayList<>();
//        li.add("a");
//        li.add("b");
//        li.add("c");
//
//        List<String> l2 = new ArrayList<>();
//        l2.add("b");
//        l2.add("c");
//        l2.add("d");
//
//        l2.removeAll(li);
//        l2.forEach(System.out::println);
//
//        String string = JSONArray.toJSONString(li);
//        System.out.println(string);


//        Map<String,Object> m1 = new HashMap<>();
//        Map<String,Object> m2 = new HashMap<>();
//
//        m1.put("id",1);
//        m1.put("name",null);
//        m1.put("data","数据");
//
//        m2.put("id",1);
//        m2.put("name","莫哈德");
//        m2.put("data",null);
//
//        if (Objects.equals(m1.get("id"),m2.get("id"))){
//            System.out.println("id equals");
//        }
//
//        if (Objects.equals(m1.get("name"),m2.get("name"))){
//            System.out.println("name equals");
//        }
//
//        if (Objects.equals(m1.get("data"),m2.get("data"))){
//            System.out.println("data equals");
//        }

        String s = "";
        String s1 = null;
        if (Objects.equals(s,s1)){
            System.out.println("null object equals");
        }
        if (null == null){
            System.out.println("null==null");
        }
    }

    @Test
    public void nulltoJsonArray(){
//        Map datafield = new HashMap();
//        datafield.put("BELONGDEPT",null);
//        JSONArray belongdepts = JSONArray.parseArray(String.valueOf(datafield.get("BELONGDEPT")));
//        System.out.println(JSON.toJSONString(belongdepts));
//        if (belongdepts.size()>0) {
//            System.out.println("belong size 大于0 ");
//        }

        Map datafield = new HashMap();
//        datafield.put("BELONGDEPT",null);
        JSONObject belongdepts = JSONObject.parseObject(JSONObject.toJSONString(datafield));
        System.out.println(JSON.toJSONString(belongdepts));
        if (belongdepts.size()>0) {
            System.out.println("belong size 大于0 ");
        }

    }

    /**
     * 根据相同的id进行groupby分组，并且将相同id的数据相加
     */
    @Test
    public void streamgroupsum(){
        List<AssetClassify> li = new ArrayList<>();
        AssetClassify middle1 = new AssetClassify();
        middle1.setNAME("学工");
        middle1.setCATALOGID("1");
        middle1.setNUM(100);
        AssetClassify middle2 = new AssetClassify();
        middle2.setNAME("学工");
        middle2.setCATALOGID("1");
        middle2.setNUM(500);
        AssetClassify middle3 = new AssetClassify();
        middle3.setNAME("体育");
        middle3.setCATALOGID("2");
        middle3.setNUM(200);
        AssetClassify middle4 = new AssetClassify();
        middle4.setNAME("体育");
        middle4.setCATALOGID("2");
        middle4.setNUM(900);

        li.add(middle1);
        li.add(middle2);
        li.add(middle3);
        li.add(middle4);


//        Map<String, IntSummaryStatistics> collect = li.stream().collect(Collectors.groupingBy(AssetClassify::getNAME, Collectors.summarizingInt(AssetClassify::getNUM)));
        Map<String, List<AssetClassify>> listMap = li.stream().collect(Collectors.groupingBy(AssetClassify::getCATALOGID));
        List<Object> collect = listMap.entrySet().stream().map( m ->{
            AssetClassify asset = m.getValue().get(0);
            asset.setNUM(m.getValue().stream().collect(Collectors.summingInt(AssetClassify::getNUM)));
            return asset;
        }).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Data
    static class AssetClassify {
        private String CATALOGID;
        private String NAME;
        private Integer NUM;
    }


    @Test
    public void listelementunique(){
//        List<String> l1 = Arrays.asList("人事处", "教务处");
//        List<String> l2 = Arrays.asList("教务处", "教务处","信息中心");
//        Set s = new HashSet();
//        s.addAll(l1);
//        s.addAll(l2);
//        s.stream().forEach(System.out::println);

//        List<Map<String,Object>> l3 = new ArrayList<>();
//        List<Map<String,Object>> l4 = new ArrayList<>();
//        Map<String,Object> m1 = new HashMap<>();
//        Map<String,Object> m11 = new HashMap<>();
//        Map<String,Object> m21 = new HashMap<>();
//        Map<String,Object> m22 = new HashMap<>();
//        Map<String,Object> m23 = new HashMap<>();
//        Map<String,Object> m24 = new HashMap<>();
//        m1.put("DEPTNAME","人事处");
//        l3.add(m1);
//        m11.put("DEPTNAME","教务处");
//        l3.add(m11);
//        m21.put("DEPTNAME","教务处");
//        l4.add(m21);
//        m22.put("DEPTNAME","教务处");
//        l4.add(m22);
//        m23.put("DEPTNAME","人事处");
//        l4.add(m23);
//        m24.put("DEPTNAME","信息中心");
//        l4.add(m24);
//
//        Set<String> ls1 = new HashSet<>();
//        for (Map<String,Object> map:l3){
//            ls1.add(String.valueOf(map.get("DEPTNAME")));
//        }
//        for (Map<String,Object> map:l4){
//            ls1.add(String.valueOf(map.get("DEPTNAME")));
//        }
//        l3.stream().forEach(System.out::println);
//        System.out.println("=====================");
//        l4.stream().forEach(System.out::println);
//        System.out.println("=====================");
//        ls1.stream().forEach(System.out::println);



        List<String> l1 = new ArrayList<>();
        if (l1.isEmpty()){
            System.out.println("list is no elment");
        }

    }


}
