import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.poi.excel.ExcelExtractorUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.gyunf.utils.JsonTreeObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import javax.swing.text.StyledEditorKit;
import java.io.*;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//import net.sf.json.JSONObject;

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

//        String s2 = "1231242321123" + null;
//        /*String[] s2split = s2.split("_");
//        System.out.println(s2split[0]);
//        System.out.println(s2split.length);*/
//        String id = "";
//        if (s2.contains("_")){
//            id = StringUtils.substringAfter(s2, "_");
//        }else {
//            id = s2;
//        }
//
//        System.out.println(id);

        /*7-9 二级部门号:获取数字9*/
//        String s1 = "7-9 二级部门号";
//        String[] s = s1.split(" ");
//        String num = StringUtils.substringAfter(s[0], "-");
//        System.out.println(num);

        String userSql = "SELECT USERID,MAIL,TEL FROM T_UDS_USER WHERE DEPT = ? AND ";
        StringBuilder filterSql = new StringBuilder();
        String[] recipientType = {"DEPT","ASSET"};
        for (String type : recipientType) {
            if ("DEPT".equals(type)) {
                filterSql.append(" OR DEPTPRINCIPAL = '1'");
            }
            if ("ASSET".equals(type)) {
                filterSql.append(" OR ASSETPRINCIPAL = '1'");
            }
        }
        /*去除第一个OR*/
        if (filterSql.length() > 0) {
            filterSql = new StringBuilder(filterSql.substring(3, filterSql.length()));
        }

        userSql = userSql + filterSql.toString();
        System.out.println(userSql);
    }


    @Test
    public void readingJsonFileTest(){


    }

    /**
     * 空集合测试类
     */
    @Test
    public void LongListTest(){
        List<Long> ids = new ArrayList<>();
        List<Long> newId = new ArrayList<>();
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
        System.out.println("空集合的size大小"+ids.size());

        for (Long id : ids) {
            newId.add(id);
        }

        String string = JSON.toJSONString(newId);
        System.out.println("空集合复制给空集合" + string);
        
//        String string = JSON.toJSONString(ids);
//        System.out.println(string);

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

//        String s = "";
//        String s1 = null;
//        if (Objects.equals(s,s1)){
//            System.out.println("null object equals");
//        }
//        if (null == null){
//            System.out.println("null==null");
//        }
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

    @Test
    public void dateTest(){
//        Calendar date = Calendar.getInstance();
//        String year = String.valueOf(date.get(Calendar.YEAR));
//        System.out.println(year);

        /*Date时间比较*/
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startTime = sdf.parse("2023-11-06");
            Date endTime = sdf.parse("2023-11-17");
            Date nowDate = new Date();
            if ( nowDate.after(startTime) && nowDate.before(endTime)) {
                System.out.println("nowDate在startTime~endTime时间区间内");
            }

        }catch (Exception e ) {

        }

    }

    @Test
    public void fileReaderTest() throws IOException {
        FileReader fr = null;
        List<String> list = new ArrayList<>();
        try {
            fr = new FileReader("E:\\IdeaProjects\\train\\my-pool\\src\\test\\java\\fileReader.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        int begin = 0;
        list.add("(" + line + ")");
        br.close();
        fr.close();
        list.stream().forEach(System.out::println);

    }

    @Test
    public void StringToDatetest() throws ParseException {
        Map<String,Object> map = new HashMap<>();
        map.put("SENDTIME",null);
        String sendTime = map.get("SENDTIME") == null ? "null" : String.valueOf(map.get("SENDTIME"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date sendDate = sdf.parse(sendTime);
        System.out.println(sendDate.toString());
    }

    @Test
    public void ObjectTojsonstr(){
        DataServiceByBlood dataServiceByBlood = new DataServiceByBlood();
        dataServiceByBlood.setBloodType(1);
        dataServiceByBlood.setService_wid(12233L);
        dataServiceByBlood.setServiceName("测试");
        String str = JSON.toJSONString(dataServiceByBlood);
        System.out.println(str);
        /*
        * 输出：{"bloodType":1,"serviceName":"测试","service_wid":12233}
        * */
    }

    @Test
    public void StrTest(){
//        String S = "JZG";
//        String[] dataobject = S.split(",");
//        System.out.println(dataobject[0]);

        List<String> sourceDataObjects = new ArrayList<>();
        String sourceDataObject = "";
//        if (data.getSourceDataObject().size() > 0) {
//            for (Map<String, Object> map : data.getSourceDataObject()) {
                String sourceDataObjectStr = String.valueOf("dataobject");
                sourceDataObjects.add(sourceDataObjectStr);
//            }
//        }
        /*获取源头表、字段信息*/
        if (sourceDataObjects.size() > 0) {
            /*当只有一个元素dataobject时，输出的内容是：dataobject*/
            sourceDataObject = sourceDataObjects.stream().map(String::valueOf).collect(Collectors.joining(","));
        }
        System.out.println(sourceDataObject);

    }

    @Test
    public void ListMapInDataObject(){
        String testStr = " [\n" +
                "    {\n" +
                "      \"wid\": \"18355834970903552\",\n" +
                "      \"database_wid\": \"12679637446445056\",\n" +
                "      \"dataObject\": \"ods_cjsthdsj\",\n" +
                "      \"dataField\": \"xxjgdm\",\n" +
                "      \"dataFieldName\": \"学校机构代码\",\n" +
                "      \"fieldType\": \"VARCHAR\",\n" +
                "      \"fieldLength\": \"36\",\n" +
                "      \"fieldprecision\": null,\n" +
                "      \"is_pk\": null,\n" +
                "      \"is_null_able\": null,\n" +
                "      \"reference_table\": null,\n" +
                "      \"reference_table_name\": null,\n" +
                "      \"reference_field\": null,\n" +
                "      \"reference_field_text\": null,\n" +
                "      \"reference_field_name\": null,\n" +
                "      \"foreign_table\": null,\n" +
                "      \"foreign_table_name\": null,\n" +
                "      \"foreign_field\": null,\n" +
                "      \"foreign_field_name\": null,\n" +
                "      \"dataFieldDesc\": null,\n" +
                "      \"dataRange\": null,\n" +
                "      \"open_type\": null,\n" +
                "      \"share_type\": null,\n" +
                "      \"standard_field_num\": null,\n" +
                "      \"quote_standard_field\": null,\n" +
                "      \"quote_standard_field_num\": null,\n" +
                "      \"quote_standard_field_name\": null,\n" +
                "      \"reference_standard\": null,\n" +
                "      \"status_able\": null,\n" +
                "      \"order_num\": null,\n" +
                "      \"creator\": null,\n" +
                "      \"is_core\": null,\n" +
                "      \"need_encrypt\": null,\n" +
                "      \"keep_history\": null,\n" +
                "      \"code_diff\": null,\n" +
                "      \"openFields\": null,\n" +
                "      \"attr_type\": null,\n" +
                "      \"showFields\": null,\n" +
                "      \"field_row_num\": null,\n" +
                "      \"original_field\": null,\n" +
                "      \"operation\": null,\n" +
                "      \"modify_time\": null,\n" +
                "      \"column\": null,\n" +
                "      \"checkType\": null,\n" +
                "      \"checkRule\": null,\n" +
                "      \"checkRuleName\": null,\n" +
                "      \"mappingDataBases\": [\n" +
                "        {\n" +
                "          \"id\": \"S002\",\n" +
                "          \"sourceDataBaseWid\": \"12678213153639424\",\n" +
                "          \"sourceDataBaseName\": \"科研系统\",\n" +
                "          \"dataSource\": \"12678213153639424\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"mappingDataObject\": [\n" +
                "        {\n" +
                "          \"dataobjectname\": \"测试\",\n" +
                "          \"dataobject\": \"t_test\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"mappingDataObjectField\": [\n" +
                "        {\n" +
                "          \"wid\": \"17294374989520896\",\n" +
                "          \"datafieldname\": \"c1\",\n" +
                "          \"database_wid\": \"12678213153639424\",\n" +
                "          \"datafield\": \"c1\",\n" +
                "          \"dataobject\": \"t_test\",\n" +
                "          \"order_num\": \"1\",\n" +
                "          \"fieldtype\": \"int4\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"taskMappingWid\": \"19355553762584576\",\n" +
                "      \"taskWid\": \"19174668581147648\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"wid\": \"18355834970903552\",\n" +
                "      \"database_wid\": \"12679637446445056\",\n" +
                "      \"dataObject\": \"ods_cjsthdsj\",\n" +
                "      \"dataField\": \"xxjgdm\",\n" +
                "      \"dataFieldName\": \"学校机构代码\",\n" +
                "      \"fieldType\": \"VARCHAR\",\n" +
                "      \"fieldLength\": \"36\",\n" +
                "      \"fieldprecision\": null,\n" +
                "      \"is_pk\": null,\n" +
                "      \"is_null_able\": null,\n" +
                "      \"reference_table\": null,\n" +
                "      \"reference_table_name\": null,\n" +
                "      \"reference_field\": null,\n" +
                "      \"reference_field_text\": null,\n" +
                "      \"reference_field_name\": null,\n" +
                "      \"foreign_table\": null,\n" +
                "      \"foreign_table_name\": null,\n" +
                "      \"foreign_field\": null,\n" +
                "      \"foreign_field_name\": null,\n" +
                "      \"dataFieldDesc\": null,\n" +
                "      \"dataRange\": null,\n" +
                "      \"open_type\": null,\n" +
                "      \"share_type\": null,\n" +
                "      \"standard_field_num\": null,\n" +
                "      \"quote_standard_field\": null,\n" +
                "      \"quote_standard_field_num\": null,\n" +
                "      \"quote_standard_field_name\": null,\n" +
                "      \"reference_standard\": null,\n" +
                "      \"status_able\": null,\n" +
                "      \"order_num\": null,\n" +
                "      \"creator\": null,\n" +
                "      \"is_core\": null,\n" +
                "      \"need_encrypt\": null,\n" +
                "      \"keep_history\": null,\n" +
                "      \"code_diff\": null,\n" +
                "      \"openFields\": null,\n" +
                "      \"attr_type\": null,\n" +
                "      \"showFields\": null,\n" +
                "      \"field_row_num\": null,\n" +
                "      \"original_field\": null,\n" +
                "      \"operation\": null,\n" +
                "      \"modify_time\": null,\n" +
                "      \"column\": null,\n" +
                "      \"checkType\": null,\n" +
                "      \"checkRule\": null,\n" +
                "      \"checkRuleName\": null,\n" +
                "      \"mappingDataBases\": [\n" +
                "        {\n" +
                "          \"id\": \"S002\",\n" +
                "          \"sourceDataBaseWid\": \"12678213153639424\",\n" +
                "          \"sourceDataBaseName\": \"科研系统\",\n" +
                "          \"dataSource\": \"12678213153639424\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"mappingDataObject\": [\n" +
                "        {\n" +
                "          \"dataobjectname\": \"测试\",\n" +
                "          \"dataobject\": \"t_test\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"mappingDataObjectField\": [\n" +
                "        {\n" +
                "          \"wid\": \"17294374993453056\",\n" +
                "          \"datafieldname\": \"c1\",\n" +
                "          \"database_wid\": \"12678213153639424\",\n" +
                "          \"datafield\": \"c1\",\n" +
                "          \"dataobject\": \"t_test\",\n" +
                "          \"order_num\": \"1\",\n" +
                "          \"fieldtype\": \"int4\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"taskMappingWid\": \"19355553762846720\",\n" +
                "      \"taskWid\": \"19174668581147648\"\n" +
                "    }\n" +
                "  ]";
        List<DataFieldForMapping> dataFieldForMappings = JSONArray.parseArray(testStr,DataFieldForMapping.class);



        List<Map<String, Object>> mappingDataObjectField = new ArrayList<>();
        /*判断目标表和目标源是否不同*/
        List<Map<String, Object>> mappingDataObjects = new ArrayList<>();
        List<Long> mappingDataBases = new ArrayList<>();
        for (DataFieldForMapping dataFieldForMapping : dataFieldForMappings) {
            Map<String, Object> dataObjectMap = dataFieldForMapping.getMappingDataObject().get(0);
            Long sourceDataBaseWid = dataFieldForMapping.getMappingDataBases().get(0).getSourceDataBaseWid();
            mappingDataBases.add(sourceDataBaseWid);
            mappingDataObjects.add(dataObjectMap);

            /*判断字段是否重复*/
            List<Map<String, Object>> dataObjectField = dataFieldForMapping.getMappingDataObjectField();
            if (mappingDataObjectField.contains(dataObjectField.get(0))){
                System.out.println("目标字段重复请重新配置");

            }else {
                mappingDataObjectField.addAll(dataObjectField);
            }
        }
        List<Long> mappingDataBase = mappingDataBases.stream().distinct().collect(Collectors.toList());
        List<Map<String,Object>> mappingDataObject = mappingDataObjects.stream().distinct().collect(Collectors.toList());
        if (mappingDataBase.size() > 1 || mappingDataObject.size() > 1){
            System.out.println("目标表、目标系统不一致请重新配置");
        }

        System.out.println("没有重复数据");

    }


    @Test
    public void arrayToList(){

        List<String> stdDataObject = new ArrayList<>();
//        if (!"1".equals(String.valueOf(serviceBloodTemp.get("apply_database_wid")))){
            List<Map<String,Object>> dataObjectByThemeDataObject = new ArrayList<>();
            Map<String,Object> map1 = new HashMap<>();
            map1.put("bztable","bztable,bztable2,bztable3");
            Map<String,Object> map2 = new HashMap<>();
            map2.put("bztable","bztable4");
            dataObjectByThemeDataObject.add(map1);
            dataObjectByThemeDataObject.add(map2);
            for (Map<String,Object> map : dataObjectByThemeDataObject){
                String[] bztables = map.get("bztable").toString().split(",");
                List<String> list = Arrays.asList(bztables);
                stdDataObject.addAll(list);
            }
//        }
        stdDataObject.stream().forEach(System.out::println);
    }

    @Test
    public void streamForMap(){
        List<Map<String, Object>> themeTaskInfos = new ArrayList<>();
        Map<String, Object> m1 = new HashMap<>();
        m1.put("task_wid",112222L);
        Map<String, Object> m2 = new HashMap<>();
        m2.put("task_wid",2223332L);
        themeTaskInfos.add(m1);
        themeTaskInfos.add(m2);

        List<Long> taskWids = themeTaskInfos.stream().map(map -> (Long) map.get("task_wid")).collect(Collectors.toList());
        taskWids.stream().forEach(System.out::println);
    }

    @Test
    public void streamForMapPro(){
//        JSONObject jsonObject = JSONObject.parseObject("\"relation\": [{\n" +
//                "    \"source\": \"1687933776472\",\n" +
//                "    \"target\": \"1687933777886\"\n" +
//                "  }, {\n" +
//                "    \"source\": \"1687933777886\",\n" +
//                "    \"target\": \"1687933772571\"\n" +
//                "  }]");
        JSONObject jsonObject = JSONObject.parseObject("\"relation\": []");

//        List<String> targetIds = jsonObject.getJSONArray("relation").stream().map(obj->JSONObject.fromObject(obj).getString("target")).collect(Collectors.toList());

    }

    @Test
    public void createUUID(){
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        System.out.println(uuid);
        String kafkaTopic = "_DT_" + uuid;

        System.out.println(kafkaTopic);


        System.out.println(StringUtils.substringAfterLast(kafkaTopic,"_"));
    }
    @Test
    public void kongstring(){
//        String yesterdayTaskStatus = "";
//        String taskExecutionStatus = "";
//        if (!"E".equals(yesterdayTaskStatus) && "E".equals(taskExecutionStatus)){
//            System.out.println("进入判断");
//        }
//        String e = "{\"result\":true}";
//        JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(e);
//        Boolean flag = (Boolean) jsonObject.get("result");
//        System.out.println(flag);



//        JSONObject result = new JSONObject();
//        result.put("code", "0");
//        result.put("msg", "操作失败");
//        JSONObject result2 = new JSONObject();
//        result2.put("code", "1");
//        result2.put("msg", "操作成功");
//        result = result2;
//        System.out.println(result.toJSONString());

      /*  boolean contains = Arrays.asList(
                "/api/drm/standardManager/standardNorm/querySubsetCatalogTree",
                "/api/drm/standardManager/standardNorm/standardNormManager",
                "/api/drp/resourceCatalog/resourceListManager/getreleaseTypeIsEnable"
        ).contains("/api/drm/standardManager/standardNorm/standardNormManager");

        System.out.println(contains);*/

//        /*奇偶数判断*/
//        int i = 13;
//        System.out.println("取运算" + i % 2);
//        if (i % 2 == 0){
//            System.out.println("是偶数");
//        }else {
//            System.out.println("是奇数");
//        }


        /*List<String> 转为 String[]*/
        List<String> li = new ArrayList<>();
        li.add("qwq");
        li.add("pkm");
        li.add("rfv");
        String[] strings = li.toArray(new String[0]);
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }
    }

    @Test
    public void mapToSting(){
        Map<String,String> recipientMap = new HashMap<>();
        if (recipientMap.isEmpty()) {
            recipientMap.put("userIds",null);
            recipientMap.put("userNames",null);
        }
        String userIds = recipientMap.get("userIds");
        String userNames = recipientMap.get("userNames");
        if (userIds == null) {
            System.out.println("userId是null"); //userId是null
        }
        System.out.println("userId=" + userIds +"; useNames=" + userNames); //userId=null; useNames=null


//        Map m = new HashMap<>();
//        Map data = new HashMap<>();
//        m.put("a","123");
//        data.put("data",m);
////        System.out.println(data.toString()); //{data={a=123}}
//        String dataStr = data.get("data").toString(); //{a=123}
//
//        System.out.println(dataStr);
//        String a = JSONObject.parseObject(dataStr).getString("a");
//        System.out.println(a);
////        String a = JSONObject.fromObject(dataStr).getString("a");

    }

    @Test
    public void listDistinct() {
        Student student1 = new Student("旺财", "男");
        Student student2 = new Student("韩梅梅", "男");
        Student student3 = new Student("大藏獒", "男");

        Dog dog1 = new Dog("旺财", 1);
        Dog dog2 = new Dog("小花够", 1);
        Dog dog3 = new Dog("小强", 1);
        Dog dog4 = new Dog("小奶狗", 1);
        Dog dog5 = new Dog("大藏獒", 1);

        List<Student> stuList = new ArrayList<>();
        stuList.add(student1);
        stuList.add(student2);
        stuList.add(student3);

        List<Dog> dogList = new ArrayList<>();
        dogList.add(dog1);
        dogList.add(dog2);
        dogList.add(dog3);
        dogList.add(dog4);
        dogList.add(dog5);

        /*
        * 输出结果：
        * student = Student{name='旺财', sex='男'}
        * student = Student{name='大藏獒', sex='男'}
        * */
//        stuList = stuList.stream().filter( s -> dogList.stream().filter(f -> f.getName().equals(s.getName())).findAny().isPresent()).collect(Collectors.toList());

        /*
        * 输出结果：
        * student = Student{name='韩梅梅', sex='男'}
        * */
        stuList = stuList.stream().parallel().filter(student -> dogList.stream().noneMatch(dog -> Objects.equals(student.getName(),dog.getName()))).collect(Collectors.toList());


        for (Student student :stuList) {
            System.out.println("student = "  + student);
        }
    }

    class Student {
        private String name;
        private String sex;

        public Student(String name, String sex) {
            this.name = name;
            this.sex = sex;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", sex='" + sex + '\'' +
                    '}';
        }
    }

    class Dog {
        private String name;
        private int age;

        public Dog(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Test
    public void StringForNull(){
        String str = "";
        System.out.println("str的length:" + str.length()); //str的length:0
        if (org.apache.commons.lang3.StringUtils.isNotBlank(str)) {
            System.out.println("str不为空字符串");
        }

        if (StringUtils.isEmpty(str)) {
            System.out.println("str为null或\"\"");
        }
    }

    @Test
    public void splitListDynamic() {

        Map<String,Object> m = new HashMap();
        for (int i = 0; i < 2; i++) {
            m.put("QWEI"+i,i);
            m.put("PIP"+i,i);

        }
        Set set = m.keySet();
        List<String> list = new ArrayList<>(set);

        List<List<String>> partition = Lists.partition(list, 1000);
        System.out.println("partition = " + partition);

    }

    @Test
    public void time() throws ParseException {
        //将指定格式的时间转换为时间戳：
        String time = "2023-09-11 08:01:00";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//首先定义待转换的时间格式
        Date date = format.parse(time);//将带转换的时间字符串转换为date类型，然后使用getTime即可获取对应的时间戳

//如果是Date类型的时间，直接使用date.getTime就可以获得其对应的毫秒级时间戳：
        Long timeNum = date.getTime();//Date类中就保存有毫秒时间戳变量
//        System.out.println(timeNum);


//        long between = DateUtil.between(date, new Date(), DateUnit.MINUTE);
//        System.out.println(between);

        /*根据时间戳计算时分秒*/
        long betHourForAfter = 0L;
        long betMinForAfter = 0L;
        long betSecForAfter = 0L;
        long betafter = 6341966;
        betHourForAfter = (betafter) / (1000 * 60 * 60);
        betMinForAfter = (betafter - betHourForAfter * (1000 * 60 * 60)) / (1000 * 60);
        betSecForAfter = (betafter - betHourForAfter * (1000 * 60 * 60) - betMinForAfter * (1000 * 60)) / (1000);
        /*
        * 若 betafter = 6953783 则输出：1时55分53秒
        * */
        System.out.println(betHourForAfter + "时" + betMinForAfter + "分" + betSecForAfter + "秒");
    }

    @Test
    public void DateTest() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();
        /*主数据版本号*/
        String version = "v" + sdf.format(date);
        System.out.println(version);
    }

    @Test
    public void listremove2() {
        final List<String> basicFieldList = Arrays.asList("dke_op_ts", "dke_op_type", "wid", "clrq", "czlx");
        final List<String> fieldList = Arrays.asList("dm", "mc", "cc", "ls", "dke_op_ts", "dke_op_type", "wid", "clrq", "czlx");
        List<String>  newfieldList = fieldList.stream().filter(f -> !basicFieldList.contains(f)).collect(Collectors.toList());
        for (int i = 0; i < newfieldList.size(); i++) {
            System.out.println(newfieldList.get(i));
        }
//        Date date = new Date(value * MILLIS_PER_DAY); //2019.05.13
    }

    @Test
    public void kafkaintToDate() {
        int value = 18029;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
        long SHANGHAI = 8 * 60 * 60 * 1000;
//        Date date = new Date(value * MILLIS_PER_DAY + SHANGHAI);
        Date date = new Date(value * MILLIS_PER_DAY );
        String format = sdf.format(date);
        System.out.println(format);
    }


    @Test
    public void strReplace() {
        /*字符串替换*/
//        String str = "[\"GaoYunFei\",\"TESTDEV\",\"ljwan\"]";
//        String replaceStr = str.replace("]", ",");
//        System.out.println(replaceStr);
//
//        String newStr = "[\"GaoYunFei\",\"TESTDEV\",\"ljwan\",\"gyf\",\"l\"]";
//        String newRecipient = newStr.replace(replaceStr,"[");
//        System.out.println("去除指定Str后：" + newRecipient );

//        String s = "t_person_info|t_person_info_copy";
//        String[] data = s.split("|");
//        for (int i = 0; i < data.length; i++) {
//            System.out.println(data[i]);
//        }
//
//        System.out.println("输出二===========");
//        String[] data2 = s.split("\\|");
//        for (int i = 0; i < data2.length; i++) {
//            System.out.println(data2[i]);
//        }
//
//        /*
//        * 输出结果：
//        *
//        t
//        _
//        p
//        e
//        r
//        s
//        o
//        n
//        _
//        i
//        n
//        f
//        o
//        |
//        t
//        _
//        p
//        e
//        r
//        s
//        o
//        n
//        _
//        i
//        n
//        f
//        o
//        _
//        c
//        o
//        p
//        y
//        输出二===========
//        t_person_info
//        t_person_info_copy
//        *
//        * */

        /*字符串匹配*/
        String source = "," + "s028_t_xsxx1,t_xsxx1,t_new_nsxx" + ",";
        String dataObject = "," + "s028_t_xsxx1" + ",";
        if (source.contains(dataObject)) {
            System.out.println("包含" + dataObject);
        }
//集合去重函数
//        Collections.disjoint();

    }

    @Test
    public void StringBuildTest() {
        StringBuilder dataObjectFieldSql = new StringBuilder();
        boolean flag = true;
        for (int i = 0; i < 2; i++) {
            if (flag) {
                dataObjectFieldSql.append("qew").append(" UNION ");
            }
        }
        if (dataObjectFieldSql.toString().isEmpty()) {
            System.out.println("输出空字符串");
        }else{
            dataObjectFieldSql = new StringBuilder(dataObjectFieldSql.substring(0, dataObjectFieldSql.length() - 6));
            System.out.println(dataObjectFieldSql);
            System.out.println("输出字符串:" + dataObjectFieldSql.toString());
        }


    }

    @Test
    public void intArray(){
       int[] c = new int[]{};
       String str = "1234";
       if (str.contains("1")) {
           c = newObject(c,1);
       }
        if (str.contains("2")) {
            c = newObject(c,2);
        }
        if (str.contains("8")) {
            c = newObject(c,3);
        }
        if (str.contains("4")) {
            c = newObject(c,4);
        }
        for (int i = 0; i < c.length; i++) {
            System.out.println(c[i]);
        }

    }

    //数组累加
    public static int[] newObject(int[] ob, int str){
        if(ob == null){
            return new int[]{str};
        }else{
            int[] newOb = new int[ob.length+1];
            for(int i=0;i<newOb.length-1;i++){
                newOb[i]=ob[i];
            }
            newOb[ob.length] = str;
            return newOb;
        }
    }


    @Test
    public void StrReplace() {
        String message = "您好，\n" +
                "\n" +
                "根据学校【关于学校数据资源目录2023年检的通知】工作安排（http://42.244.8.19:8100/uds/#/rescatalog），统筹学校数据资源建设,更好的利用数据对学校各项事业赋能，推动校内业务发展，我处将开展数据资源目录建设相关工作，主要包括以下几个方面的工作内容：\n" +
                "\n" +
                "\n" +
                "\n" +
                "1、数据资源目录变动更新（如各单位目录有变更，需要再次确权和数据维护）\n" +
                "\n" +
                "2、数据资源目录年检工作\n" +
                "\n" +
                "3、数据资源目录2023年度考核工作\n" +
                "\n" +
                "\n" +
                "附件是我们整理的年度数据资源考核要求、数据资源目录工作方案和数据资源服务平台年检功能的使用手册。请各部门登录数据服务平台进行年检（http://42.244.8.19:8100/uds/#/rescatalog ，账号和密码为统一身份认证号码（如无法登录或无部门年检权限可联系我们进行权限变更），查看系统当中的数据资源目录情况，完成年检工作。\n" +
                "\n" +
                "后续我处将基于最新的数据资源目录进行相关的年终考核工作。\n" +
                "\n" +
                "对于年检工作和考核工作有任何问题，也可向数信处进行反馈。反馈请联系 张三 电话 111111 邮箱：111111@163.com\n" +
                "\n" +
                "\n" +
                "\n" +
                "祝身体健康 工作顺利";
        if (message != null){
            if (message.contains("\n")) {
                message = message.replaceAll("\n", "<br/>");
            }
            if (message.contains("\t")) {
                message = message.replaceAll("\t","&nbsp;");
            }

        }
        System.out.println(message);
    }


    @Test
    public void StrReplaces(){
//        String name = "何文隆(邮件发送失败、)、福州信息职业技术学院(邮件发送失败、微信发送失败、)、test()";
//
//        String s = name.replaceAll("\\、\\)", "\\)").replaceAll("\\(\\)", "");
//        System.out.println(s);

//        String result = "申请成功的资产数：搜索,";
//        if (StringUtils.endsWith(result,",")) {
//            result = result.substring(0, result.length() - 1);
//        }
//        System.out.println(result);

//        String hidemenus="['dataSensitiveLevel','dataWorthLevel']";
//        JSONArray jsonArray = JSONArray.parseArray(hidemenus);
//        System.out.println(JSON.toJSONString(jsonArray));
//        for (int i = 0; i < jsonArray.size(); i++) {
//            System.out.println(jsonArray.get(i));
//        }


//
//        String dataBaseWid = StringUtils.substringBefore("1-t_person_info_copy", "-");
//        String dataObject = StringUtils.substringAfter("1-t_person_info_copy", "-");
//
//        System.out.println(dataBaseWid + "\n" + dataObject); // 1 t_person_info_copy


//        String sign = "serv,123212," + "12131," + "data,dadaonjl";
//        String[] signSplit = sign.split(",");
//        Long serviceWid = Long.valueOf(signSplit[2]);
//        Long appId = Long.valueOf(signSplit[1]);
//        String dataobject = signSplit[3];
//        System.out.println(serviceWid + "\n" + appId + "\n" + dataobject);
//        /* 输出结果
//        * 12131
//            123212
//            data
//        * */

        String dataObjectString = "";
        String substring = dataObjectString.substring(0, dataObjectString.length() - 1);
        System.out.println(substring);
    }

    @Test
    public void JsonToClassTest(){
        String jsonstr = "{ \"datas\":[{\"id\":\"1\",\"sourceDataBaseWid\":12,\"sourceDataBaseName\":\"test1\",\"dataSource\":22},{\"id\":\"2\",\"sourceDataBaseWid\":22,\"sourceDataBaseName\":\"test2\",\"dataSource\":29}] }";
        JSONObject jsonObject = JSONObject.parseObject(jsonstr);
        List<SourceDataBase> datas = jsonObject.getJSONArray("datas").toJavaList(SourceDataBase.class);
        datas.forEach(System.out::println);
        /*
        *结果输出：
        * SourceDataBase(id=1, sourceDataBaseWid=12, sourceDataBaseName=test1, dataSource=22)
        * SourceDataBase(id=2, sourceDataBaseWid=22, sourceDataBaseName=test2, dataSource=29)
        * */

    }

    @Test
    public void listRemoveOrAdd() {
        List<Map<String,Object>> list = new ArrayList<>();
        List<Map<String,Object>> newlist = new ArrayList<>();
        Map<String,Object> index = new HashMap<>();
        index.put("sourcedataobject", "source1");
        index.put("source_database_wid", "source_database_wid1");
        index.put("target_database_wid", "target_database_wid1");
        index.put("targetdataobject", "targetdataobject1");

        Map<String,Object> index2 = new HashMap<>();
        index2.put("sourcedataobject", "source2");
        index2.put("source_database_wid", "source_database_wid2");
        index2.put("target_database_wid", "target_database_wid2");
        index2.put("targetdataobject", "targetdataobject2");

        Map<String,Object> index3 = new HashMap<>();
        index3.put("sourcedataobject", "source3");
        index3.put("source_database_wid", "source_database_wid3");
        index3.put("target_database_wid", "target_database_wid3");
        index3.put("targetdataobject", "targetdataobject3");
        list.add(index);
        list.add(index2);
        list.add(index3);

        list.forEach(System.out::println);

        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> temp = list.get(i);
            String sourceDatabaseWid = String.valueOf(temp.get("source_database_wid"));
            String sourcedataobject = String.valueOf(temp.get("sourcedataobject"));
            String targetDatabaseWid = String.valueOf(temp.get("target_database_wid"));
            String targetdataobject = String.valueOf(temp.get("targetdataobject"));
            if (sourcedataobject.contains("source1")) {
                Map<String,Object> in = new HashMap<>();
                in.put("sourcedataobject", "source1——in1");
                in.put("source_database_wid", "source_database_wid1——in1");
                in.put("target_database_wid", "target_database_wid1——in1");
                in.put("targetdataobject", "targetdataobject1——in1");
                Map<String,Object> in2 = new HashMap<>();
                in2.put("sourcedataobject", "source1——in2");
                in2.put("source_database_wid", "source_database_wid1——in2");
                in2.put("target_database_wid", "target_database_wid1——in2");
                in2.put("targetdataobject", "targetdataobject1——in2");
                newlist.add(in);
                newlist.add(in2);
            }else {
                newlist.add(temp);
            }

        }


        System.out.println("===========替换后血缘=============");

        newlist.forEach(System.out::println);


    }

    @Test
    public void assertNullTest() {
        String s = null;
        s = "111";
        assert s != null;
        if (s.equals("1")) {
            System.out.println(s);
        }

    }

    @Test
    public void listRetainAll(){
        List<String> l1 = new ArrayList<String>(Arrays.asList(new String[]{"111", "111", "2232", "212133", "w21q2w"}));
        List<String> l2 = new ArrayList<String>(Arrays.asList(new String[]{"12232","1121", "2123133","31212", "7777","8888", "9999","2111", "3443"}));
        l1.retainAll(l2);
        l1.forEach(System.out::println);
    }

    @Test
    public void listMapRange(){
        Map<String,List<Map<String,Object>>> mapList = new HashMap<>();
        List<Map<String,Object>> l1 = new ArrayList<>();
        Map<String,Object> m1 = new HashMap<>();
        m1.put("databasename","数据湖");
        m1.put("data","1");
        l1.add(m1);
        Map<String,Object> m2 = new HashMap<>();
        m2.put("databasename","数据湖");
        m2.put("data","2");
        l1.add(m2);
        Map<String,Object> m3 = new HashMap<>();
        m3.put("databasename","数据");
        m3.put("data","3");
        l1.add(m3);
        Map<String,Object> m4 = new HashMap<>();
        m4.put("databasename","数据");
        m4.put("data","4");
        l1.add(m4);

        Map<String,Object> m5 = new HashMap<>();
        m5.put("databasename","数据湖");
        m5.put("data","5");
        l1.add(m5);
        Map<String,Object> m6 = new HashMap<>();
        m6.put("databasename","数据");
        m6.put("data","6");
        l1.add(m6);

//
//        Map<String,Object> m2 = new HashMap<>();
//        m2.put("databasename","数据");
//        m2.put("data","5");
//        m2.put("databasename","数据");
//        m2.put("data","6");
//        m2.put("databasename","数据");
//        m2.put("data","7");
//        m2.put("databasename","数据");
//        m2.put("data","8");
        mapList = l1.stream().collect(Collectors.groupingBy(task -> String.valueOf(task.get("databasename"))));
        if (!mapList.isEmpty()) {
            Iterator<Map.Entry<String, List<Map<String, Object>>>> iterator = mapList.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, List<Map<String, Object>>> next = iterator.next();
                System.out.print("键：" + next.getKey() + "," + "值：");
                next.getValue().forEach(System.out::print);
                System.out.println();
//                System.out.println("删除集合");
//                iterator.remove();
            }
        }

        if (!mapList.isEmpty()) {
            System.out.println("第二次循环");
            Iterator<Map.Entry<String, List<Map<String, Object>>>> iterator = mapList.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, List<Map<String, Object>>> next = iterator.next();
                System.out.print("键：" + next.getKey() + "," + "值：");
                next.getValue().forEach(System.out::print);
//                System.out.println();
//                System.out.println("删除集合");
//                mapList.remove("数据");
            }
        }

//        List<Map<String, Object>> list = mapList.get("数据");
//        if (list == null) {
//            System.out.println("list集合为空");
//        }else {
//            System.out.println("集合不为空");
//            list.forEach(System.out::print);
//        }


    }

    @Test
    public void arrayForeach() {
        String columnMapping = "";
        if (StringUtils.isNotBlank(columnMapping)) {
            String[] columnMappingSplit = columnMapping.split(",");
            for (int j = 0; j < columnMappingSplit.length - 1 ; j++) {

            }
        }
    }

    //数组参数转为List<String>集合
    @Test
    public void arrayStringToList() {
        String arrayString = "{\n" +
                "  \"retcode\": 0,\n" +
                "  \"retmsg\": \"success\",\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"name\": \"OpenAI\",\n" +
                "      \"logo\": \"\",\n" +
                "      \"tags\": \"LLM,TEXT EMBEDDING,SPEECH2TEXT,MODERATION\",\n" +
                "      \"status\": \"1\",\n" +
                "      \"id\": \"5ecbf6f0-4d65-41b6-b77e-f169dc5f2376\",\n" +
                "      \"create_time\": 1720602086519,\n" +
                "      \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "      \"update_time\": 1720602086519,\n" +
                "      \"update_date\": \"2024-07-10T17:01:21.401890\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Tongyi-Qianwen\",\n" +
                "      \"logo\": \"\",\n" +
                "      \"tags\": \"LLM,TEXT EMBEDDING,SPEECH2TEXT,MODERATION\",\n" +
                "      \"status\": \"1\",\n" +
                "      \"id\": \"640d2e3c-2ed4-4a6c-9e71-92c8d4e195b9\",\n" +
                "      \"create_time\": 1720602086541,\n" +
                "      \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "      \"update_time\": 1720602086541,\n" +
                "      \"update_date\": \"2024-07-10T17:01:21.401890\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"ZHIPU-AI\",\n" +
                "      \"logo\": \"\",\n" +
                "      \"tags\": \"LLM,TEXT EMBEDDING,SPEECH2TEXT,MODERATION\",\n" +
                "      \"status\": \"1\",\n" +
                "      \"id\": \"28a0f347-1409-44b4-b3c3-ae80a8ca2931\",\n" +
                "      \"create_time\": 1720602086556,\n" +
                "      \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "      \"update_time\": 1720602086557,\n" +
                "      \"update_date\": \"2024-07-10T17:01:21.401890\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Ollama\",\n" +
                "      \"logo\": \"\",\n" +
                "      \"tags\": \"LLM,TEXT EMBEDDING,SPEECH2TEXT,MODERATION\",\n" +
                "      \"status\": \"1\",\n" +
                "      \"id\": \"30b7eedc-0dae-4229-9aea-8fd154e8e85d\",\n" +
                "      \"create_time\": 1720602086571,\n" +
                "      \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "      \"update_time\": 1720602086571,\n" +
                "      \"update_date\": \"2024-07-10T17:01:21.401890\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Moonshot\",\n" +
                "      \"logo\": \"\",\n" +
                "      \"tags\": \"LLM,TEXT EMBEDDING\",\n" +
                "      \"status\": \"1\",\n" +
                "      \"id\": \"fe2be056-ae9d-49dc-92d5-5a047dda46d7\",\n" +
                "      \"create_time\": 1720602086584,\n" +
                "      \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "      \"update_time\": 1720602086584,\n" +
                "      \"update_date\": \"2024-07-10T17:01:21.401890\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Xinference\",\n" +
                "      \"logo\": \"\",\n" +
                "      \"tags\": \"LLM,TEXT EMBEDDING,SPEECH2TEXT,MODERATION\",\n" +
                "      \"status\": \"1\",\n" +
                "      \"id\": \"8a87fcca-bb87-43e1-8019-4765b1c1e234\",\n" +
                "      \"create_time\": 1720602086612,\n" +
                "      \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "      \"update_time\": 1720602086612,\n" +
                "      \"update_date\": \"2024-07-10T17:01:21.401890\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"DeepSeek\",\n" +
                "      \"logo\": \"\",\n" +
                "      \"tags\": \"LLM\",\n" +
                "      \"status\": \"1\",\n" +
                "      \"id\": \"a8fb96bb-1557-4598-926e-b77addebac1d\",\n" +
                "      \"create_time\": 1720602086640,\n" +
                "      \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "      \"update_time\": 1720602086640,\n" +
                "      \"update_date\": \"2024-07-10T17:01:21.401890\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"VolcEngine\",\n" +
                "      \"logo\": \"\",\n" +
                "      \"tags\": \"LLM, TEXT EMBEDDING\",\n" +
                "      \"status\": \"1\",\n" +
                "      \"id\": \"80c43c92-4725-491c-8f71-013a43c9f9ec\",\n" +
                "      \"create_time\": 1720602086654,\n" +
                "      \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "      \"update_time\": 1720602086654,\n" +
                "      \"update_date\": \"2024-07-10T17:01:21.401890\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"BaiChuan\",\n" +
                "      \"logo\": \"\",\n" +
                "      \"tags\": \"LLM,TEXT EMBEDDING\",\n" +
                "      \"status\": \"1\",\n" +
                "      \"id\": \"cc4daa3e-4c28-47a4-b684-0a7a960e4f70\",\n" +
                "      \"create_time\": 1720602086667,\n" +
                "      \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "      \"update_time\": 1720602086667,\n" +
                "      \"update_date\": \"2024-07-10T17:01:21.401890\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Jina\",\n" +
                "      \"logo\": \"\",\n" +
                "      \"tags\": \"TEXT EMBEDDING, TEXT RE-RANK\",\n" +
                "      \"status\": \"1\",\n" +
                "      \"id\": \"6c24672a-ace1-485f-a194-93f8beed17e4\",\n" +
                "      \"create_time\": 1720602086681,\n" +
                "      \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "      \"update_time\": 1720602086681,\n" +
                "      \"update_date\": \"2024-07-10T17:01:21.401890\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"MiniMax\",\n" +
                "      \"logo\": \"\",\n" +
                "      \"tags\": \"LLM,TEXT EMBEDDING\",\n" +
                "      \"status\": \"1\",\n" +
                "      \"id\": \"91b65791-39eb-4c03-9eaa-aa7c7f604eb5\",\n" +
                "      \"create_time\": 1720602086708,\n" +
                "      \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "      \"update_time\": 1720602086708,\n" +
                "      \"update_date\": \"2024-07-10T17:01:21.401890\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Mistral\",\n" +
                "      \"logo\": \"\",\n" +
                "      \"tags\": \"LLM,TEXT EMBEDDING\",\n" +
                "      \"status\": \"1\",\n" +
                "      \"id\": \"db1bed6b-ca53-4cf5-aba2-bf832a314c26\",\n" +
                "      \"create_time\": 1720602086722,\n" +
                "      \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "      \"update_time\": 1720602086722,\n" +
                "      \"update_date\": \"2024-07-10T17:01:21.401890\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Azure-OpenAI\",\n" +
                "      \"logo\": \"\",\n" +
                "      \"tags\": \"LLM,TEXT EMBEDDING,SPEECH2TEXT,MODERATION\",\n" +
                "      \"status\": \"1\",\n" +
                "      \"id\": \"896623b8-8e5e-4a3e-8a60-8cdd16971b88\",\n" +
                "      \"create_time\": 1720602086736,\n" +
                "      \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "      \"update_time\": 1720602086736,\n" +
                "      \"update_date\": \"2024-07-10T17:01:21.401890\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Bedrock\",\n" +
                "      \"logo\": \"\",\n" +
                "      \"tags\": \"LLM,TEXT EMBEDDING\",\n" +
                "      \"status\": \"1\",\n" +
                "      \"id\": \"62d387c9-8247-4b22-9c8a-b9d96c14e4b9\",\n" +
                "      \"create_time\": 1720602086749,\n" +
                "      \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "      \"update_time\": 1720602086749,\n" +
                "      \"update_date\": \"2024-07-10T17:01:21.401890\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(arrayString);
        if (jsonObject != null && "0".equals(jsonObject.getString("retcode"))) {
            JSONArray jsonArray = JSONArray.parseArray(jsonObject.getString("data"));
            if (!jsonArray.isEmpty()) {
                List<Map> list = jsonArray.toJavaList(Map.class);
                List<String> name = list.stream().map(map -> map.get("name") == null ? null : String.valueOf(map.get("name"))).collect(Collectors.toList());
                System.out.println("name List:");
                name.forEach(System.out::println);
            }
            System.out.println("name List is empty");
        }

    }

    //多个数组转换成目录树结构
    @Test
    public void dataArryToTree() {
        String str = "{\n" +
                "  \"retcode\": 0,\n" +
                "  \"retmsg\": \"success\",\n" +
                "  \"data\": {\n" +
                "    \"OpenAI\": [\n" +
                "      {\n" +
                "        \"llm_name\": \"gpt-4o\",\n" +
                "        \"mdl_type\": \"chat,image2text\",\n" +
                "        \"fid\": \"OpenAI\",\n" +
                "        \"max_tokens\": 128000,\n" +
                "        \"tags\": \"LLM,CHAT,128K\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"c3dc6317-05c0-4963-a935-e52324144851\",\n" +
                "        \"create_time\": 1720602086764,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602086764,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"gpt-3.5-turbo\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"OpenAI\",\n" +
                "        \"max_tokens\": 4096,\n" +
                "        \"tags\": \"LLM,CHAT,4K\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"f14f1c5c-abda-46c6-b329-26a335d38507\",\n" +
                "        \"create_time\": 1720602086783,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602086783,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"gpt-3.5-turbo-16k-0613\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"OpenAI\",\n" +
                "        \"max_tokens\": 16385,\n" +
                "        \"tags\": \"LLM,CHAT,16k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"4a18789f-ffdb-4402-be6e-96f06d3a609f\",\n" +
                "        \"create_time\": 1720602086797,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602086797,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"text-embedding-ada-002\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"OpenAI\",\n" +
                "        \"max_tokens\": 8191,\n" +
                "        \"tags\": \"TEXT EMBEDDING,8K\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"1e2f26bc-07f4-4d39-9620-2ed76e35a5c4\",\n" +
                "        \"create_time\": 1720602086810,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602086810,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"text-embedding-3-small\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"OpenAI\",\n" +
                "        \"max_tokens\": 8191,\n" +
                "        \"tags\": \"TEXT EMBEDDING,8K\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"e0807022-70e6-4a1a-be89-cf5f4677d0b0\",\n" +
                "        \"create_time\": 1720602086824,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602086824,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"text-embedding-3-large\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"OpenAI\",\n" +
                "        \"max_tokens\": 8191,\n" +
                "        \"tags\": \"TEXT EMBEDDING,8K\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"09024e48-560f-4bcf-a7ff-7756953df7c4\",\n" +
                "        \"create_time\": 1720602086838,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602086838,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"whisper-1\",\n" +
                "        \"mdl_type\": \"speech2text\",\n" +
                "        \"fid\": \"OpenAI\",\n" +
                "        \"max_tokens\": 26214400,\n" +
                "        \"tags\": \"SPEECH2TEXT\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"5b4c6e5d-8a33-4706-a5f2-ad8d931cdef8\",\n" +
                "        \"create_time\": 1720602086852,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602086852,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"gpt-4\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"OpenAI\",\n" +
                "        \"max_tokens\": 8191,\n" +
                "        \"tags\": \"LLM,CHAT,8K\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"bec54f9c-f249-4c1c-be45-1760c2aa1360\",\n" +
                "        \"create_time\": 1720602086866,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602086866,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"gpt-4-turbo\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"OpenAI\",\n" +
                "        \"max_tokens\": 8191,\n" +
                "        \"tags\": \"LLM,CHAT,8K\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"17820281-a00c-4797-8877-0f22d1b5837c\",\n" +
                "        \"create_time\": 1720602086879,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602086879,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"gpt-4-32k\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"OpenAI\",\n" +
                "        \"max_tokens\": 32768,\n" +
                "        \"tags\": \"LLM,CHAT,32K\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"a7e8e7ff-0dcb-4954-9a2e-502ca4c655b1\",\n" +
                "        \"create_time\": 1720602086893,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602086893,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"gpt-4-vision-preview\",\n" +
                "        \"mdl_type\": \"image2text\",\n" +
                "        \"fid\": \"OpenAI\",\n" +
                "        \"max_tokens\": 765,\n" +
                "        \"tags\": \"LLM,CHAT,IMAGE2TEXT\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"aa11859a-ee62-44f9-8b20-6cc2225e9808\",\n" +
                "        \"create_time\": 1720602086909,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602086909,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      }\n" +
                "    ],\n" +
                "    \"Tongyi-Qianwen\": [\n" +
                "      {\n" +
                "        \"llm_name\": \"qwen-turbo\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Tongyi-Qianwen\",\n" +
                "        \"max_tokens\": 8191,\n" +
                "        \"tags\": \"LLM,CHAT,8K\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"1520d3ef-9c5c-453b-864c-b95aad018bc0\",\n" +
                "        \"create_time\": 1720602086923,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602086923,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"qwen-plus\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Tongyi-Qianwen\",\n" +
                "        \"max_tokens\": 32768,\n" +
                "        \"tags\": \"LLM,CHAT,32K\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"d2ade535-b0c5-48a7-b2c3-308c2b618d26\",\n" +
                "        \"create_time\": 1720602086937,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602086937,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"qwen-max-1201\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Tongyi-Qianwen\",\n" +
                "        \"max_tokens\": 5899,\n" +
                "        \"tags\": \"LLM,CHAT,6K\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"3d328b69-2c09-4973-a5ed-c432433ca4c6\",\n" +
                "        \"create_time\": 1720602086951,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602086951,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"text-embedding-v2\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"Tongyi-Qianwen\",\n" +
                "        \"max_tokens\": 2048,\n" +
                "        \"tags\": \"TEXT EMBEDDING,2K\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"ed80a080-05ab-4a32-af0a-b907836cd7d0\",\n" +
                "        \"create_time\": 1720602086965,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602086965,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"paraformer-realtime-8k-v1\",\n" +
                "        \"mdl_type\": \"speech2text\",\n" +
                "        \"fid\": \"Tongyi-Qianwen\",\n" +
                "        \"max_tokens\": 26214400,\n" +
                "        \"tags\": \"SPEECH2TEXT\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"40a62012-73b4-4f6d-8ccb-3c8621a679fd\",\n" +
                "        \"create_time\": 1720602086979,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602086979,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"qwen-vl-max\",\n" +
                "        \"mdl_type\": \"image2text\",\n" +
                "        \"fid\": \"Tongyi-Qianwen\",\n" +
                "        \"max_tokens\": 765,\n" +
                "        \"tags\": \"LLM,CHAT,IMAGE2TEXT\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"fbf513bb-85b7-42ed-b917-d48b47265c12\",\n" +
                "        \"create_time\": 1720602086997,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602086997,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      }\n" +
                "    ],\n" +
                "    \"ZHIPU-AI\": [\n" +
                "      {\n" +
                "        \"llm_name\": \"glm-3-turbo\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"ZHIPU-AI\",\n" +
                "        \"max_tokens\": 128000,\n" +
                "        \"tags\": \"LLM,CHAT,\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"f1d61c12-c8e2-4815-870b-12b8fdc423f5\",\n" +
                "        \"create_time\": 1720602087011,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087011,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"glm-4\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"ZHIPU-AI\",\n" +
                "        \"max_tokens\": 128000,\n" +
                "        \"tags\": \"LLM,CHAT,\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"ad54c001-fcbf-421c-ba24-0366a5d897ee\",\n" +
                "        \"create_time\": 1720602087026,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087026,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"glm-4v\",\n" +
                "        \"mdl_type\": \"image2text\",\n" +
                "        \"fid\": \"ZHIPU-AI\",\n" +
                "        \"max_tokens\": 2000,\n" +
                "        \"tags\": \"LLM,CHAT,IMAGE2TEXT\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"eb54a138-f9a7-4793-8277-5e5cee56ac50\",\n" +
                "        \"create_time\": 1720602087040,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087040,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"embedding-2\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"ZHIPU-AI\",\n" +
                "        \"max_tokens\": 512,\n" +
                "        \"tags\": \"TEXT EMBEDDING\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"1eaaf4b7-dc28-447d-94c9-efae4a1eaf93\",\n" +
                "        \"create_time\": 1720602087054,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087054,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": true\n" +
                "      }\n" +
                "    ],\n" +
                "    \"Moonshot\": [\n" +
                "      {\n" +
                "        \"llm_name\": \"moonshot-v1-8k\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Moonshot\",\n" +
                "        \"max_tokens\": 7900,\n" +
                "        \"tags\": \"LLM,CHAT,\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"cd2a8a43-f6ac-48a3-a59a-8b956fc8d964\",\n" +
                "        \"create_time\": 1720602087067,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087067,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"moonshot-v1-32k\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Moonshot\",\n" +
                "        \"max_tokens\": 32768,\n" +
                "        \"tags\": \"LLM,CHAT,\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"f44cf006-5e38-4ba3-898b-d87613be7177\",\n" +
                "        \"create_time\": 1720602087082,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087082,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"moonshot-v1-128k\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Moonshot\",\n" +
                "        \"max_tokens\": 128000,\n" +
                "        \"tags\": \"LLM,CHAT\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"7bab5f43-b14a-44e7-9ae9-630f981c613a\",\n" +
                "        \"create_time\": 1720602087095,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087095,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      }\n" +
                "    ],\n" +
                "    \"FastEmbed\": [\n" +
                "      {\n" +
                "        \"llm_name\": \"BAAI/bge-small-en-v1.5\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"FastEmbed\",\n" +
                "        \"max_tokens\": 512,\n" +
                "        \"tags\": \"TEXT EMBEDDING,\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"551a10a3-b464-4f50-bd8d-ebb4ec13037f\",\n" +
                "        \"create_time\": 1720602087110,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087110,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"BAAI/bge-small-zh-v1.5\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"FastEmbed\",\n" +
                "        \"max_tokens\": 512,\n" +
                "        \"tags\": \"TEXT EMBEDDING,\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"cafaf09d-a53b-43e9-ac2a-3ed1a9b45058\",\n" +
                "        \"create_time\": 1720602087125,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087125,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"BAAI/bge-base-en-v1.5\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"FastEmbed\",\n" +
                "        \"max_tokens\": 512,\n" +
                "        \"tags\": \"TEXT EMBEDDING,\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"8b70c19b-f535-4ee1-ae47-039ef49da958\",\n" +
                "        \"create_time\": 1720602087139,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087139,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"BAAI/bge-large-en-v1.5\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"FastEmbed\",\n" +
                "        \"max_tokens\": 512,\n" +
                "        \"tags\": \"TEXT EMBEDDING,\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"7d001854-9869-4a1d-9c37-a818603e6e0a\",\n" +
                "        \"create_time\": 1720602087153,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087153,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"sentence-transformers/all-MiniLM-L6-v2\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"FastEmbed\",\n" +
                "        \"max_tokens\": 512,\n" +
                "        \"tags\": \"TEXT EMBEDDING,\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"77c48f49-d5ee-4eaf-ac4c-d615b6df2b1b\",\n" +
                "        \"create_time\": 1720602087168,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087168,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"nomic-ai/nomic-embed-text-v1.5\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"FastEmbed\",\n" +
                "        \"max_tokens\": 8192,\n" +
                "        \"tags\": \"TEXT EMBEDDING,\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"7edce662-7324-498c-b28f-da5831f2c361\",\n" +
                "        \"create_time\": 1720602087182,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087182,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"jinaai/jina-embeddings-v2-small-en\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"FastEmbed\",\n" +
                "        \"max_tokens\": 2147483648,\n" +
                "        \"tags\": \"TEXT EMBEDDING,\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"778afa72-7d01-43d8-ae67-8aff059658bd\",\n" +
                "        \"create_time\": 1720602087197,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087197,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"jinaai/jina-embeddings-v2-base-en\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"FastEmbed\",\n" +
                "        \"max_tokens\": 2147483648,\n" +
                "        \"tags\": \"TEXT EMBEDDING,\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"7d46bfdd-09a8-40df-9349-f9d6727cdd5f\",\n" +
                "        \"create_time\": 1720602087211,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087211,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": true\n" +
                "      }\n" +
                "    ],\n" +
                "    \"Youdao\": [\n" +
                "      {\n" +
                "        \"llm_name\": \"maidalun1020/bce-embedding-base_v1\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"Youdao\",\n" +
                "        \"max_tokens\": 512,\n" +
                "        \"tags\": \"TEXT EMBEDDING,\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"48f19e2b-f2d7-4849-98b7-75785f99eb3a\",\n" +
                "        \"create_time\": 1720602087224,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087224,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"maidalun1020/bce-reranker-base_v1\",\n" +
                "        \"mdl_type\": \"rerank\",\n" +
                "        \"fid\": \"Youdao\",\n" +
                "        \"max_tokens\": 512,\n" +
                "        \"tags\": \"RE-RANK, 512\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"4108b7b1-e0f4-4f91-9e6a-fff1b5f389b7\",\n" +
                "        \"create_time\": 1720602087238,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087238,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": true\n" +
                "      }\n" +
                "    ],\n" +
                "    \"DeepSeek\": [\n" +
                "      {\n" +
                "        \"llm_name\": \"deepseek-chat\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"DeepSeek\",\n" +
                "        \"max_tokens\": 32768,\n" +
                "        \"tags\": \"LLM,CHAT,\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"0b86745b-5d5c-41a8-8485-8af27a452cec\",\n" +
                "        \"create_time\": 1720602087252,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087252,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"deepseek-coder\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"DeepSeek\",\n" +
                "        \"max_tokens\": 16385,\n" +
                "        \"tags\": \"LLM,CHAT,\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"9101b8c9-91be-4e3a-bff7-dae95e284a34\",\n" +
                "        \"create_time\": 1720602087265,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087265,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      }\n" +
                "    ],\n" +
                "    \"VolcEngine\": [\n" +
                "      {\n" +
                "        \"llm_name\": \"Skylark2-pro-32k\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"VolcEngine\",\n" +
                "        \"max_tokens\": 32768,\n" +
                "        \"tags\": \"LLM,CHAT,32k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"605722d9-bc19-4d65-9803-94b3b74b9dda\",\n" +
                "        \"create_time\": 1720602087279,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087279,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"Skylark2-pro-4k\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"VolcEngine\",\n" +
                "        \"max_tokens\": 4096,\n" +
                "        \"tags\": \"LLM,CHAT,4k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"90f7ec91-1434-4df4-a2e5-2d4693a94b64\",\n" +
                "        \"create_time\": 1720602087295,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087295,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      }\n" +
                "    ],\n" +
                "    \"BaiChuan\": [\n" +
                "      {\n" +
                "        \"llm_name\": \"Baichuan2-Turbo\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"BaiChuan\",\n" +
                "        \"max_tokens\": 32768,\n" +
                "        \"tags\": \"LLM,CHAT,32K\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"dc94dfa1-17af-42be-b70c-5a3c1beec588\",\n" +
                "        \"create_time\": 1720602087309,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087309,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"Baichuan2-Turbo-192k\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"BaiChuan\",\n" +
                "        \"max_tokens\": 196608,\n" +
                "        \"tags\": \"LLM,CHAT,192K\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"6efc797a-4d68-4add-bea8-8429e6c4f728\",\n" +
                "        \"create_time\": 1720602087323,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087323,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"Baichuan3-Turbo\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"BaiChuan\",\n" +
                "        \"max_tokens\": 32768,\n" +
                "        \"tags\": \"LLM,CHAT,32K\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"4b4ef412-bd68-4fb7-bfff-2feb5f8412ae\",\n" +
                "        \"create_time\": 1720602087337,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087337,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"Baichuan3-Turbo-128k\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"BaiChuan\",\n" +
                "        \"max_tokens\": 131072,\n" +
                "        \"tags\": \"LLM,CHAT,128K\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"0023f911-9272-48bf-91b6-be070e8f0bee\",\n" +
                "        \"create_time\": 1720602087350,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087350,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"Baichuan4\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"BaiChuan\",\n" +
                "        \"max_tokens\": 131072,\n" +
                "        \"tags\": \"LLM,CHAT,128K\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"ca41cb85-0cd0-4055-ad28-7f0982f49b3f\",\n" +
                "        \"create_time\": 1720602087364,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087364,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"Baichuan-Text-Embedding\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"BaiChuan\",\n" +
                "        \"max_tokens\": 512,\n" +
                "        \"tags\": \"TEXT EMBEDDING\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"7ed51866-622c-4644-922b-bed06fd1d9d7\",\n" +
                "        \"create_time\": 1720602087378,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087378,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      }\n" +
                "    ],\n" +
                "    \"Jina\": [\n" +
                "      {\n" +
                "        \"llm_name\": \"jina-reranker-v1-base-en\",\n" +
                "        \"mdl_type\": \"rerank\",\n" +
                "        \"fid\": \"Jina\",\n" +
                "        \"max_tokens\": 8196,\n" +
                "        \"tags\": \"RE-RANK,8k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"c1775547-b4c4-4352-9fa4-9c07dce855d5\",\n" +
                "        \"create_time\": 1720602087391,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087391,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"jina-reranker-v1-turbo-en\",\n" +
                "        \"mdl_type\": \"rerank\",\n" +
                "        \"fid\": \"Jina\",\n" +
                "        \"max_tokens\": 8196,\n" +
                "        \"tags\": \"RE-RANK,8k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"40da1cde-a4f3-429d-ae4e-3e248d250972\",\n" +
                "        \"create_time\": 1720602087405,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087405,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"jina-reranker-v1-tiny-en\",\n" +
                "        \"mdl_type\": \"rerank\",\n" +
                "        \"fid\": \"Jina\",\n" +
                "        \"max_tokens\": 8196,\n" +
                "        \"tags\": \"RE-RANK,8k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"3a16b281-67d9-47f2-b814-3c4a80fb7ca6\",\n" +
                "        \"create_time\": 1720602087419,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087419,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"jina-colbert-v1-en\",\n" +
                "        \"mdl_type\": \"rerank\",\n" +
                "        \"fid\": \"Jina\",\n" +
                "        \"max_tokens\": 8196,\n" +
                "        \"tags\": \"RE-RANK,8k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"654a8707-8209-4b08-9e88-ffa8e28ceaab\",\n" +
                "        \"create_time\": 1720602087433,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087433,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"jina-embeddings-v2-base-en\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"Jina\",\n" +
                "        \"max_tokens\": 8196,\n" +
                "        \"tags\": \"TEXT EMBEDDING\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"8672dce8-3965-4faf-a038-d91f6d8fbba5\",\n" +
                "        \"create_time\": 1720602087446,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087446,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"jina-embeddings-v2-base-de\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"Jina\",\n" +
                "        \"max_tokens\": 8196,\n" +
                "        \"tags\": \"TEXT EMBEDDING\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"c5e8dfba-33c0-429e-bad4-b8c0ee0b0066\",\n" +
                "        \"create_time\": 1720602087460,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087460,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"jina-embeddings-v2-base-es\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"Jina\",\n" +
                "        \"max_tokens\": 8196,\n" +
                "        \"tags\": \"TEXT EMBEDDING\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"36ded98b-0e68-4841-89f6-8ff1187e3a29\",\n" +
                "        \"create_time\": 1720602087474,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087474,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"jina-embeddings-v2-base-code\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"Jina\",\n" +
                "        \"max_tokens\": 8196,\n" +
                "        \"tags\": \"TEXT EMBEDDING\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"bb4c5390-c077-4240-ad3d-eff9c866bc92\",\n" +
                "        \"create_time\": 1720602087488,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087488,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"jina-embeddings-v2-base-zh\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"Jina\",\n" +
                "        \"max_tokens\": 8196,\n" +
                "        \"tags\": \"TEXT EMBEDDING\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"b2f38106-fafd-43f2-b6be-4819693c6ae0\",\n" +
                "        \"create_time\": 1720602087502,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087502,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      }\n" +
                "    ],\n" +
                "    \"BAAI\": [\n" +
                "      {\n" +
                "        \"llm_name\": \"BAAI/bge-large-zh-v1.5\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"BAAI\",\n" +
                "        \"max_tokens\": 1024,\n" +
                "        \"tags\": \"TEXT EMBEDDING,\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"c94b5007-5a0c-4934-b877-15b843cbea57\",\n" +
                "        \"create_time\": 1720602087518,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087518,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"BAAI/bge-reranker-v2-m3\",\n" +
                "        \"mdl_type\": \"rerank\",\n" +
                "        \"fid\": \"BAAI\",\n" +
                "        \"max_tokens\": 2048,\n" +
                "        \"tags\": \"RE-RANK,2k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"e3d07025-43cb-409e-afe0-31158ebae8e2\",\n" +
                "        \"create_time\": 1720602087532,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087532,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": true\n" +
                "      }\n" +
                "    ],\n" +
                "    \"MiniMax\": [\n" +
                "      {\n" +
                "        \"llm_name\": \"abab6.5-chat\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"MiniMax\",\n" +
                "        \"max_tokens\": 8192,\n" +
                "        \"tags\": \"LLM,CHAT,8k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"e39a8921-10ac-4544-9f6c-c97a0d24413d\",\n" +
                "        \"create_time\": 1720602087545,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087545,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"abab6.5s-chat\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"MiniMax\",\n" +
                "        \"max_tokens\": 245760,\n" +
                "        \"tags\": \"LLM,CHAT,245k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"2696087c-6edf-48aa-9946-25637bb329bf\",\n" +
                "        \"create_time\": 1720602087559,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087559,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"abab6.5t-chat\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"MiniMax\",\n" +
                "        \"max_tokens\": 8192,\n" +
                "        \"tags\": \"LLM,CHAT,8k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"d47df8da-cc67-4c11-b47a-d5d3aa3a4a0f\",\n" +
                "        \"create_time\": 1720602087573,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087573,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"abab6.5g-chat\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"MiniMax\",\n" +
                "        \"max_tokens\": 8192,\n" +
                "        \"tags\": \"LLM,CHAT,8k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"da84ded6-d35d-42dc-94ed-a52c2327e5cb\",\n" +
                "        \"create_time\": 1720602087586,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087586,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"abab5.5-chat\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"MiniMax\",\n" +
                "        \"max_tokens\": 16384,\n" +
                "        \"tags\": \"LLM,CHAT,16k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"2224aeb1-b93c-4e08-a156-84873bdece11\",\n" +
                "        \"create_time\": 1720602087602,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087602,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"abab5.5s-chat\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"MiniMax\",\n" +
                "        \"max_tokens\": 8192,\n" +
                "        \"tags\": \"LLM,CHAT,8k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"44bde59c-88ca-4e7e-81ba-34819446b46e\",\n" +
                "        \"create_time\": 1720602087616,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087616,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      }\n" +
                "    ],\n" +
                "    \"Mistral\": [\n" +
                "      {\n" +
                "        \"llm_name\": \"open-mixtral-8x22b\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Mistral\",\n" +
                "        \"max_tokens\": 64000,\n" +
                "        \"tags\": \"LLM,CHAT,64k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"6f245ab2-56cf-4c3f-b626-0c9a09503b92\",\n" +
                "        \"create_time\": 1720602087629,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087629,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"open-mixtral-8x7b\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Mistral\",\n" +
                "        \"max_tokens\": 32000,\n" +
                "        \"tags\": \"LLM,CHAT,32k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"3e3506cb-5983-4a42-9bbc-0fee484d081f\",\n" +
                "        \"create_time\": 1720602087644,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087644,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"open-mistral-7b\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Mistral\",\n" +
                "        \"max_tokens\": 32000,\n" +
                "        \"tags\": \"LLM,CHAT,32k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"64e3df18-6f68-4c55-8a9e-608e884cc6d3\",\n" +
                "        \"create_time\": 1720602087657,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087657,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"mistral-large-latest\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Mistral\",\n" +
                "        \"max_tokens\": 32000,\n" +
                "        \"tags\": \"LLM,CHAT,32k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"e68a3c6f-c0e7-45c1-a434-218333bfc7cf\",\n" +
                "        \"create_time\": 1720602087671,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087671,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"mistral-small-latest\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Mistral\",\n" +
                "        \"max_tokens\": 32000,\n" +
                "        \"tags\": \"LLM,CHAT,32k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"a5caff6b-ede3-41f7-a25d-9085d7fa7dc5\",\n" +
                "        \"create_time\": 1720602087687,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087687,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"mistral-medium-latest\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Mistral\",\n" +
                "        \"max_tokens\": 32000,\n" +
                "        \"tags\": \"LLM,CHAT,32k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"a50ba81e-1a70-42f3-9a30-ec21c69c1fa3\",\n" +
                "        \"create_time\": 1720602087700,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087700,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"codestral-latest\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Mistral\",\n" +
                "        \"max_tokens\": 32000,\n" +
                "        \"tags\": \"LLM,CHAT,32k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"9cca020b-550a-42a9-a060-c4c55ede40a4\",\n" +
                "        \"create_time\": 1720602087714,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087714,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"mistral-embed\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"Mistral\",\n" +
                "        \"max_tokens\": 8192,\n" +
                "        \"tags\": \"LLM,CHAT,8k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"cf0f7e3a-ab2e-42e0-abf2-fdec51fbee8a\",\n" +
                "        \"create_time\": 1720602087727,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087727,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      }\n" +
                "    ],\n" +
                "    \"Azure-OpenAI\": [\n" +
                "      {\n" +
                "        \"llm_name\": \"azure-gpt-4o\",\n" +
                "        \"mdl_type\": \"chat,image2text\",\n" +
                "        \"fid\": \"Azure-OpenAI\",\n" +
                "        \"max_tokens\": 128000,\n" +
                "        \"tags\": \"LLM,CHAT,128K\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"1b81d463-214a-450d-9251-77834c32caac\",\n" +
                "        \"create_time\": 1720602087741,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087741,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"azure-gpt-35-turbo\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Azure-OpenAI\",\n" +
                "        \"max_tokens\": 4096,\n" +
                "        \"tags\": \"LLM,CHAT,4K\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"7f42e3e1-26de-4ea8-9931-dffa6a17c232\",\n" +
                "        \"create_time\": 1720602087755,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087755,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"azure-gpt-35-turbo-16k\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Azure-OpenAI\",\n" +
                "        \"max_tokens\": 16385,\n" +
                "        \"tags\": \"LLM,CHAT,16k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"a5725596-ea0f-4461-a718-50da60f88c2b\",\n" +
                "        \"create_time\": 1720602087769,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087769,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"azure-text-embedding-ada-002\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"Azure-OpenAI\",\n" +
                "        \"max_tokens\": 8191,\n" +
                "        \"tags\": \"TEXT EMBEDDING,8K\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"7bdca174-2d2e-4200-b9eb-7efec44f2bbb\",\n" +
                "        \"create_time\": 1720602087782,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087782,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"azure-text-embedding-3-small\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"Azure-OpenAI\",\n" +
                "        \"max_tokens\": 8191,\n" +
                "        \"tags\": \"TEXT EMBEDDING,8K\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"30d7e037-b9e4-46c8-bb91-48e6eef04c43\",\n" +
                "        \"create_time\": 1720602087796,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087796,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"azure-text-embedding-3-large\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"Azure-OpenAI\",\n" +
                "        \"max_tokens\": 8191,\n" +
                "        \"tags\": \"TEXT EMBEDDING,8K\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"a2887ccb-37f5-45a9-8e2b-96537e7f6317\",\n" +
                "        \"create_time\": 1720602087810,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087810,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"azure-whisper-1\",\n" +
                "        \"mdl_type\": \"speech2text\",\n" +
                "        \"fid\": \"Azure-OpenAI\",\n" +
                "        \"max_tokens\": 26214400,\n" +
                "        \"tags\": \"SPEECH2TEXT\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"323ed8bc-156c-4358-86cb-5e1b7c8324b1\",\n" +
                "        \"create_time\": 1720602087825,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087825,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"azure-gpt-4\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Azure-OpenAI\",\n" +
                "        \"max_tokens\": 8191,\n" +
                "        \"tags\": \"LLM,CHAT,8K\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"20f8e56f-2aad-40d9-ab31-09d05242c75a\",\n" +
                "        \"create_time\": 1720602087840,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087840,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"azure-gpt-4-turbo\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Azure-OpenAI\",\n" +
                "        \"max_tokens\": 8191,\n" +
                "        \"tags\": \"LLM,CHAT,8K\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"cf29e01c-c17f-4c1d-a479-3d9a9956e21c\",\n" +
                "        \"create_time\": 1720602087853,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087853,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"azure-gpt-4-32k\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Azure-OpenAI\",\n" +
                "        \"max_tokens\": 32768,\n" +
                "        \"tags\": \"LLM,CHAT,32K\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"21e93ff4-f155-4b50-9ecc-6eb1975d6b3e\",\n" +
                "        \"create_time\": 1720602087869,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087869,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"azure-gpt-4-vision-preview\",\n" +
                "        \"mdl_type\": \"image2text\",\n" +
                "        \"fid\": \"Azure-OpenAI\",\n" +
                "        \"max_tokens\": 765,\n" +
                "        \"tags\": \"LLM,CHAT,IMAGE2TEXT\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"103e70b4-8d39-4ee7-8f62-080861f35947\",\n" +
                "        \"create_time\": 1720602087884,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087884,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      }\n" +
                "    ],\n" +
                "    \"Bedrock\": [\n" +
                "      {\n" +
                "        \"llm_name\": \"ai21.j2-ultra-v1\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Bedrock\",\n" +
                "        \"max_tokens\": 8191,\n" +
                "        \"tags\": \"LLM,CHAT,8k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"7f74569d-2a5c-4e99-8e73-1dc21c12f3db\",\n" +
                "        \"create_time\": 1720602087897,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087897,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"ai21.j2-mid-v1\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Bedrock\",\n" +
                "        \"max_tokens\": 8191,\n" +
                "        \"tags\": \"LLM,CHAT,8k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"479ce9a5-1d8d-4bf5-a97d-e1c4a9026eee\",\n" +
                "        \"create_time\": 1720602087911,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087911,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"cohere.command-text-v14\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Bedrock\",\n" +
                "        \"max_tokens\": 4096,\n" +
                "        \"tags\": \"LLM,CHAT,4k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"8a61b865-e4bd-42a0-bdb3-4159c2e63291\",\n" +
                "        \"create_time\": 1720602087924,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087924,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"cohere.command-light-text-v14\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Bedrock\",\n" +
                "        \"max_tokens\": 4096,\n" +
                "        \"tags\": \"LLM,CHAT,4k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"e17680fc-0ac6-4ea1-8f1c-5d8066c6b7b9\",\n" +
                "        \"create_time\": 1720602087938,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087938,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"cohere.command-r-v1:0\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Bedrock\",\n" +
                "        \"max_tokens\": 131072,\n" +
                "        \"tags\": \"LLM,CHAT,128k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"ad4ae215-4cdc-4f78-bcbb-4625bafa0b20\",\n" +
                "        \"create_time\": 1720602087952,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087952,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"cohere.command-r-plus-v1:0\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Bedrock\",\n" +
                "        \"max_tokens\": 128000,\n" +
                "        \"tags\": \"LLM,CHAT,128k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"03ba3b4b-3406-45e1-8a1f-3be90212c6c0\",\n" +
                "        \"create_time\": 1720602087965,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087965,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"anthropic.claude-v2\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Bedrock\",\n" +
                "        \"max_tokens\": 102400,\n" +
                "        \"tags\": \"LLM,CHAT,100k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"c0406049-460b-4b3b-b333-4e37e5ee15e7\",\n" +
                "        \"create_time\": 1720602087979,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087979,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"anthropic.claude-v2:1\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Bedrock\",\n" +
                "        \"max_tokens\": 204800,\n" +
                "        \"tags\": \"LLM,CHAT,200k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"b87d9aa6-f024-4a77-8f4a-fe26a42fb31f\",\n" +
                "        \"create_time\": 1720602087992,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602087992,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"anthropic.claude-3-sonnet-20240229-v1:0\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Bedrock\",\n" +
                "        \"max_tokens\": 204800,\n" +
                "        \"tags\": \"LLM,CHAT,200k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"1a7ef4e6-40a3-4200-8eb5-7be61f106d98\",\n" +
                "        \"create_time\": 1720602088006,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602088006,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"anthropic.claude-3-5-sonnet-20240620-v1:0\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Bedrock\",\n" +
                "        \"max_tokens\": 204800,\n" +
                "        \"tags\": \"LLM,CHAT,200k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"ee38840b-51eb-4bc0-8f25-6b5ef39bb269\",\n" +
                "        \"create_time\": 1720602088019,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602088019,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"anthropic.claude-3-haiku-20240307-v1:0\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Bedrock\",\n" +
                "        \"max_tokens\": 204800,\n" +
                "        \"tags\": \"LLM,CHAT,200k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"f92d1993-f526-45aa-8ae3-676b2912054f\",\n" +
                "        \"create_time\": 1720602088035,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602088035,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"anthropic.claude-3-opus-20240229-v1:0\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Bedrock\",\n" +
                "        \"max_tokens\": 204800,\n" +
                "        \"tags\": \"LLM,CHAT,200k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"e41d998a-b932-40c3-8be6-3521409f181d\",\n" +
                "        \"create_time\": 1720602088049,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602088049,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"anthropic.claude-instant-v1\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Bedrock\",\n" +
                "        \"max_tokens\": 102400,\n" +
                "        \"tags\": \"LLM,CHAT,100k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"ddc733f3-15d6-440e-9343-1f2a4cd63dc1\",\n" +
                "        \"create_time\": 1720602088062,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602088062,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"amazon.titan-text-express-v1\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Bedrock\",\n" +
                "        \"max_tokens\": 8192,\n" +
                "        \"tags\": \"LLM,CHAT,8k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"f9916086-f198-47d4-9eb9-facb678ba0db\",\n" +
                "        \"create_time\": 1720602088076,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602088076,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"amazon.titan-text-premier-v1:0\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Bedrock\",\n" +
                "        \"max_tokens\": 32768,\n" +
                "        \"tags\": \"LLM,CHAT,32k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"2d596b5a-1d6b-4f25-a27e-f9718e65e296\",\n" +
                "        \"create_time\": 1720602088089,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602088089,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"amazon.titan-text-lite-v1\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Bedrock\",\n" +
                "        \"max_tokens\": 4096,\n" +
                "        \"tags\": \"LLM,CHAT,4k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"b117bb63-23db-45d5-a7de-ce80527bf798\",\n" +
                "        \"create_time\": 1720602088107,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602088107,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"meta.llama2-13b-chat-v1\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Bedrock\",\n" +
                "        \"max_tokens\": 4096,\n" +
                "        \"tags\": \"LLM,CHAT,4k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"3f69a570-a7dc-451d-880c-e8a799f88524\",\n" +
                "        \"create_time\": 1720602088120,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602088120,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"meta.llama2-70b-chat-v1\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Bedrock\",\n" +
                "        \"max_tokens\": 4096,\n" +
                "        \"tags\": \"LLM,CHAT,4k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"7acb768e-428d-4277-92af-c99253380021\",\n" +
                "        \"create_time\": 1720602088135,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602088135,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"meta.llama3-8b-instruct-v1:0\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Bedrock\",\n" +
                "        \"max_tokens\": 8192,\n" +
                "        \"tags\": \"LLM,CHAT,8k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"965f56c9-a21f-4b73-bf5e-d78fe2e5ff85\",\n" +
                "        \"create_time\": 1720602088149,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602088149,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"meta.llama3-70b-instruct-v1:0\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Bedrock\",\n" +
                "        \"max_tokens\": 8192,\n" +
                "        \"tags\": \"LLM,CHAT,8k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"1990775f-d7b6-4f1f-a9d9-64cc26e52fba\",\n" +
                "        \"create_time\": 1720602088162,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602088162,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"mistral.mistral-7b-instruct-v0:2\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Bedrock\",\n" +
                "        \"max_tokens\": 8192,\n" +
                "        \"tags\": \"LLM,CHAT,8k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"ed99e317-4293-4d1d-9f4d-6909de6ade85\",\n" +
                "        \"create_time\": 1720602088176,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602088176,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"mistral.mixtral-8x7b-instruct-v0:1\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Bedrock\",\n" +
                "        \"max_tokens\": 4096,\n" +
                "        \"tags\": \"LLM,CHAT,4k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"4f25b25e-d374-43f6-b1de-39282a6746fe\",\n" +
                "        \"create_time\": 1720602088189,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602088189,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"mistral.mistral-large-2402-v1:0\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Bedrock\",\n" +
                "        \"max_tokens\": 8192,\n" +
                "        \"tags\": \"LLM,CHAT,8k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"4400944f-22db-44de-8bf4-98aa5bbc50ce\",\n" +
                "        \"create_time\": 1720602088203,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602088203,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"mistral.mistral-small-2402-v1:0\",\n" +
                "        \"mdl_type\": \"chat\",\n" +
                "        \"fid\": \"Bedrock\",\n" +
                "        \"max_tokens\": 8192,\n" +
                "        \"tags\": \"LLM,CHAT,8k\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"13352e38-9926-4941-86cb-d625101588f2\",\n" +
                "        \"create_time\": 1720602088216,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602088216,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"amazon.titan-embed-text-v2:0\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"Bedrock\",\n" +
                "        \"max_tokens\": 8192,\n" +
                "        \"tags\": \"TEXT EMBEDDING\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"d8360349-2c65-4f6e-a084-ac263738c001\",\n" +
                "        \"create_time\": 1720602088230,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602088230,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"cohere.embed-english-v3\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"Bedrock\",\n" +
                "        \"max_tokens\": 2048,\n" +
                "        \"tags\": \"TEXT EMBEDDING\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"49040a42-7418-446e-9ee3-d0536d826ceb\",\n" +
                "        \"create_time\": 1720602088243,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602088243,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"llm_name\": \"cohere.embed-multilingual-v3\",\n" +
                "        \"mdl_type\": \"embedding\",\n" +
                "        \"fid\": \"Bedrock\",\n" +
                "        \"max_tokens\": 2048,\n" +
                "        \"tags\": \"TEXT EMBEDDING\",\n" +
                "        \"status\": \"1\",\n" +
                "        \"id\": \"768bfeb0-7503-4930-9c42-feaab207c157\",\n" +
                "        \"create_time\": 1720602088257,\n" +
                "        \"create_date\": \"2024-07-10T17:01:21.401772\",\n" +
                "        \"update_time\": 1720602088257,\n" +
                "        \"update_date\": \"2024-07-10T17:01:21.401890\",\n" +
                "        \"available\": false\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
        List<JsonTreeObject> modelTree = new ArrayList<>();
        JSONObject jsonObject = JSONObject.parseObject(str);
        if (jsonObject != null && "0".equals(jsonObject.getString("retcode"))) {
            JSONObject jsonObjectData = JSONObject.parseObject(jsonObject.getString("data"));
            for (Map.Entry<String, Object> entry : jsonObjectData.entrySet()) {
                JsonTreeObject pObject = new JsonTreeObject();
                List<JsonTreeObject> children = new ArrayList<>();
                pObject.setId(entry.getKey());
                pObject.setText(entry.getKey());
                JSONArray valueArray = JSONArray.parseArray(entry.getValue().toString());
                if (!valueArray.isEmpty()) {
                    List<Map> list = valueArray.toJavaList(Map.class);
                    list.forEach(map -> {
                        JsonTreeObject cObject = new JsonTreeObject();
                        cObject.setId(map.get("llm_name") == null ? null : map.get("llm_name").toString());
                        cObject.setText(map.get("llm_name") == null ? null : map.get("llm_name").toString());
                        cObject.setPid(map.get("fid") == null ? null : map.get("fid").toString());
                        children.add(cObject);
                    });
                }
                pObject.setChildren(children);
                modelTree.add(pObject);
                //System.out.println("key="+entry.getKey()+"\tvalue="+entry.getValue());
            }

            System.out.println(JSON.toJSONString(modelTree));


//            JSONArray jsonArray = JSONArray.parseArray(jsonObject.getString("data"));
//            if (!jsonArray.isEmpty()) {
//                List<Map> list = jsonArray.toJavaList(Map.class);
//                company = list.stream().map(map -> map.get("name") == null ? null : String.valueOf(map.get("name"))).collect(Collectors.toList());
//            }
        }
    }
 }
