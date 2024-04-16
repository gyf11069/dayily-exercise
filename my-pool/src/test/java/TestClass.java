import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.poi.excel.ExcelExtractorUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
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
import java.lang.reflect.Field;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
        List<String> li = new ArrayList<>();
        li.add("a");
        li.add("b");
        li.add("c");

        List<String> l2 = new ArrayList<>();
        l2.add("a");
        l2.add("b");
        l2.add("c");

        l2.removeAll(li);
        if (!l2.isEmpty()) {
            l2.forEach(System.out::println);
        }else {
            System.out.println("l2 集合为空");
        }


        String string = JSONArray.toJSONString(li);
        System.out.println(string);


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

    @Test
    public void stringcontentpooltest() {
        String s = new String("a");
//        s.intern();
        String s2 = "a";
        System.out.println(s == s2);

        String s3 = new String("a") + new String("a");
//        s3.intern();
        String s4 = "aa";
        System.out.println(s3 == s4);
    }

    @Test
    public void getDateTime() {
        Date nowData = new Date();
        int year = nowData.getYear();
        System.out.println(year);

        LocalDate localDate = LocalDate.now();
        int year1 = localDate.getYear();
        System.out.println(year1);

    }

    @Test
    public void emailtest() {
//        String[] s = new String[5];
//        /*邮箱格式校验*/
//        String decrypt_mail = "1234567890@qq.com";
//        boolean isEmail = Validator.isEmail(decrypt_mail);
//        if (isEmail) {
//            System.out.println("邮箱正确");
//            s[0] = decrypt_mail;
//        }else {
//            System.out.println("邮箱错误");
//        }
//        System.out.println("源数组长度" +s.length);
//
//        String[] clones = s.clone();
//        System.out.println("拷贝数组长度" +clones.length);
//        for (int i = 0; i < clones.length; i++) {
//            System.out.println("数组元素" + i + "值为：" + clones[i].toString());
//
//        }

        //List
        List<String> ls = new ArrayList<>();
        /*邮箱格式校验*/
        String decrypt_mail = "1234567890@qq.com";
        boolean isEmail = Validator.isEmail(decrypt_mail);
        if (isEmail) {
            System.out.println("邮箱正确");
            ls.add(decrypt_mail);
        }else {
            System.out.println("邮箱错误");
        }
        String[] strings = ls.toArray(new String[ls.size()]);

        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i].toString());
        }


    }

    @Test
    public void testMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("test", "1");

        String s = map.get("r") == null ? null : String.valueOf(map.get("r"));
        System.out.println(s);

    }

    @Test
    public void ObjectToMaptest() {
        DataObject dataObject = new DataObject();
        dataObject.setCZBZ("cas");
        Map<String,Object> map = new HashMap<>();
        map.put("test","testas");
        dataObject.setParam(map);
        Map<String,Object> map1 = null;
        try {
            map1 = beanToMap(dataObject);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        for (Map.Entry<String,Object> entry : map1.entrySet()) {
            String key = entry.getKey();
            Object value =  entry.getValue();
            System.out.println(key + ":" + value);
            /*
                CZBZ:cas
                param:{test=testas}
                this$0:TestClass@66cd51c3
                IMPORTANT:null
            * */
        }
        System.out.println("==============");
        /* {test=testas} */
        System.out.println(map1.get("param").toString());
        System.out.println("=======JSON=======");
        /* {"CZBZ":"cas","param":{"test":"testas"},"this$0":{}} */
        System.out.println(JSON.toJSONString(map1));

    }

    /**
     * 对象转Map
     * @param object
     * @return
     * @throws IllegalAccessException
     */
    private Map beanToMap(Object object) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(object));
        }
        return map;
    }

    @Data
    private class DataObject {
        String IMPORTANT;
        String CZBZ;
        Map<String,Object> param;
    }
}
