import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Sets;
import com.gyunf.utils.ChineseTransWordTool;
import com.gyunf.utils.DbUtil;
import com.gyunf.utils.EnumUtils;
import jdk.nashorn.internal.scripts.JS;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.quartz.CronExpression;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import cn.hutool.core.codec.Base64;

public class MacTestClass {

    @Test
    public void Collectiontest() {
        //项目开发中高频使用的 Stream操作 集合的差集 并集 和交集!
        List<Person> aList = new ArrayList<>(Arrays.asList(
                new Person("1","张三"),
                new Person("2","李四"),
                new Person("3","王五")
        ));
        List<Person> bList = new ArrayList<>(Arrays.asList(
                new Person("2","李四"),
                new Person("3","王五"),
                new Person("4","赵六")
        ));

        //aList 与 bList 的交集（在两个集合中都存在的元素）
        List<Person> intersections = aList
                .stream() //获取第一个集合的Stream1
                .filter( //取出Stream1中符合条件的元素组成新的Stream2, Lambda表达1返回值为true时为符合
                        a -> //lambda表达:1，a为lambda表达式1的参数，是Stream1中的每个元素
                                bList.stream() //获取第二个集合的Stream3
                                        .map(Person::getId) //将第二个集合每个元素的id属性取出来，映射成map
                                        .anyMatch( //返回值 (boolean)：Stream4中是否至少有一个元素使
                                            id -> //Lambda表达式2, id为lambda表达式2的参数，是Str
                                                    Objects.equals(a.getId(), id) //判断id的信
                                        )
                )
                .collect(Collectors.toList());//将Stream2转换为List
        System.out.println("----------aList 与 bList 的交集为：");
        System.out.println(intersections);

        //差集
        List<Person> diffList = bList.stream()
                .filter(
                        b -> aList.stream().map(Person::getId)
                                .noneMatch(
                                        id -> Objects.equals(b.getId(), id)
                                )
                )
                .collect(Collectors.toList());

        System.out.println("----------bList 与 aList 的差集为：");
        System.out.println(diffList);

        //差集二

        /*
        * 这段代码的作用是将aList中的元素转换为Map，其中Person的id作为key，Person对象本身作为value。如果出现重复的id，会选择保留后面的元素。
            (1) aList.stream()将aList转换为stream；
            (2) .collect()对stream进行收集并转换成Map；
            (3) Collectors.toMap(Person::getId, Function.identity(),(k1,k2) -> k2)指定Map的收集方式：
                (a) Person::getId作为key的提取方法；
                (b) Function.identity()作为value的提取方法，即value就是stream中的元素本身；
                (c) (k1,k2) -> k2指定出现重复key时的解决策略，即保留后面的元素。
        * */
        Map<String,Object> aMap = aList.stream().collect(
                Collectors.toMap(Person::getId, Function.identity(),(k1,k2) -> k2)
        );
        List<Person> diffEffective = bList.stream().filter(b -> !aMap.containsKey(b.getId())).collect(Collectors.toList());
        System.out.println("----------bList 与 aList 的差集为：");
        System.out.println(diffEffective);

        //方式三：使用Google Guava 工程中的Sets工具包，实现集合的并集/交集/差集
        Set<Integer> sets1 = Sets.newHashSet(1,2,3);
        Set<Integer> sets2 = Sets.newHashSet(1,2,3,4,5);
        //交集
        System.out.println("交集为：");
        Sets.SetView<Integer> intersection = Sets.intersection(sets1,sets2);
        for (Integer i : intersection) {
            System.out.println(i);
        }

        //差集
        System.out.println("差集为：");
        Sets.SetView<Integer> difference = Sets.difference(sets1,sets2);
        for (Integer i : difference) {
            System.out.println(i);
        }

        //并集
        System.out.println("并集为：");
        Sets.SetView<Integer> union = Sets.union(sets1,sets2);
        for (Integer i : union) {
            System.out.println(i);
        }
    }

    @Data
    @AllArgsConstructor
    class Person {
        private String id;
        private String nickName;

    }


    /**
     *  根据SQL语句解析出 字段 和 表名
     * @throws Exception
     */
    @Test
    public void testGetTableAndAliasColumnsFromSql() throws Exception {
        // 测试正常的SQL语句
        String sql1 = "SELECT user.id AS uid, user.name FROM user";
        Map<String, List<String>> result1 = DbUtil.getTableAndAliasColumnsFromSql(sql1);
        Assertions.assertNotNull(result1);
        System.out.println(result1);
        Assertions.assertTrue(result1.get("tables").contains("user"));
        Assertions.assertEquals("user.uid", result1.get("columns").get(0));
        Assertions.assertEquals("user.name", result1.get("columns").get(1));

        // 测试包含多个表和别名的SQL语句
        String sql2 = "SELECT u.id AS uid, u.name AS uname, c.id AS cid, c.name AS cname FROM user u JOIN category c ON u.category_id = c.id";
        Map<String, List<String>> result2 = DbUtil.getTableAndAliasColumnsFromSql(sql2);
        Assertions.assertNotNull(result2);
        System.out.println(result2);
        Assertions.assertTrue(result2.get("tables").containsAll(Arrays.asList("user", "category")));
        Assertions.assertEquals("user.uid", result2.get("columns").get(0));
        Assertions.assertEquals("category.uname", result2.get("columns").get(1));
        Assertions.assertEquals("category.cid", result2.get("columns").get(2));
        Assertions.assertEquals("category.cname", result2.get("columns").get(3));


        // 测试空SQL语句应当抛出异常
        String sql3 = "";
        Assertions.assertThrows(Exception.class, () -> {
            DbUtil.getTableAndAliasColumnsFromSql(sql3);
        });

        // 测试null SQL语句也应当抛出异常
        Assertions.assertThrows(Exception.class, () -> {
            DbUtil.getTableAndAliasColumnsFromSql(null);
        });

        // 测试无法解析的SQL语句应当抛出异常
        String sql4 = "SELECT * FROM non_existing_table";
        Assertions.assertThrows(Exception.class, () -> {
            DbUtil.getTableAndAliasColumnsFromSql(sql4);
        });

        // 测试包含多个表和别名的SQL语句
        String sql5 = "SELECT u.id AS uid, case when u.busnum = 0 then 0 else round(u.SURVEYEDbusnum*100/c.busnum,2) end as surveyed_pre, c.id AS cid, c.name AS cname FROM user u JOIN category c ON u.category_id = c.id";
        Map<String, List<String>> result5 = DbUtil.getTableAndAliasColumnsFromSql(sql5);
        System.out.println("result5"+ result5);
    }

    @Test
    public void StringEncodeTest() {
//        String s = "Gsads";
//        if (s.equalsIgnoreCase("GSADS")) {
//            System.out.println("忽略大小写后匹配成功");
//        }

        String sql = "SELECT RYJBXX.xb AS xb, round( count(DISTINCT RYJBXX.xh)::numeric*100 / t2.xhzs,2) || '%' as bl from usr_data.t_ryjbxx RYJBXX, (SELECT count(xh) as xhzs from usr_data.t_ryjbxx) t2 GROUP BY RYJBXX.xb, t2.xhzs";
//        String sqlEncode = Base64.getEncoder().encodeToString(URLEncoder.encode(sql).getBytes(StandardCharsets.UTF_8));
//        System.out.println(sqlEncode);

    }

    @Test
    void testConvertToQuartzCron_validExpression() {
        String validCronExpression = "0 0 * * * ?";


    }

    @Test
    public void enumClassTest() {
////        System.out.println(Mode.findById(2)); //CONST
//        List<Animal> a = JSONArray.parseArray("[{\"id\": 1," +
//                "\"nickName\":\"cat\"," +
//                "\"mode\":1}]",Animal.class);
//        for (Animal aa : a){
//            System.out.println(aa);
//            System.out.println(aa.getMode()); //DIM
//            System.out.println("==================");
////            aa.setMode();
//        }
//        //
////        Map<Integer, Mode> map = Mode.map;
////        System.out.println(map); //{2=CONST, 0=DIM, 1=IND, 3=TAG}

        /*新转换*/
        AIChat aiChat = JSONObject.parseObject("{\n" +
                "            \"conversation_id\": \"122333\",\n" +
                "                \"messages\": [\n" +
                "            {\"role\":\"user\",\"content\":\"吃撒撒出\"},\n" +
                "            {\"role\":\"user\",\"content\":\"吃撒\"},\n" +
                "            {\"role\":\"user\",\"content\":\"吃撒sa\"}\n" +
                "  ],\n" +
                "            \"quote\": false,\n" +
                "                \"stream\": true\n" +
                "        }",AIChat.class);
//        JSONObject.toJSON()
        System.out.println(JSONArray.toJSON(aiChat.getMessages()).toString());



    }

    @Test
    public void chineseTransWordtest() {
        String name = "可视化测试_fz";
        String allFirstLetter = ChineseTransWordTool.getAllFirstLetter(name);
        System.out.println(allFirstLetter);

    }

    @Test
    public void hutoolUUIdtest() {
        String s = IdUtil.getSnowflake(1, 1).nextIdStr();
        System.out.println(s); //1793556924038516736
    }


    @Test
    public void compareToTest() {
        Map<String,String> map1 = new HashMap<>();
        map1.put("text", "【GCStest1】test1");

        Map<String,String>  map2 = new HashMap<>();
        map2.put("text", "【GCStest2】test2");

        Map<String,String>  map3 = new HashMap<>();
        map3.put("text", "【GCStest1】test1"); // Intentionally identical to map1 for equality test

        List<Map<String,String>> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);
        list.add(map3);

        System.out.println("比较前==========");
        list.forEach(System.out::println);

        Collections.sort(list, new Comparator<Map<String, String>>() {
            @Override
            public int compare(Map<String, String> o1, Map<String, String> o2) {
                String text1 = o1.get("text").substring(o1.get("text").indexOf("【"), o1.get("text").indexOf("】"));
                String text2 = o2.get("text").substring(o2.get("text").indexOf("【"), o2.get("text").indexOf("】"));
                if (text1.compareTo(text2) == 0){
                    return -1;
                }
                return text1.compareTo(text2);
            }
        });
        System.out.println("比较后==========");
        list.forEach(System.out::println);
    }

    @Test
    public void listTest() {
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> map2 = new HashMap<>();
        map.put("wid",121331);
        map.put("listnames","1,sasc,sc,21331");
        map2.put("wid",121331);
        map2.put("listnames",null);
        List<Map<String,Object>> testList = new ArrayList<>();
        testList.add(map);
        String listnames = testList.stream().map(m -> m.get("listnames") == null ? "":m.get("listnames").toString()).collect(Collectors.joining(","));
        System.out.println(listnames);

    }

    @Test
    public void testBase64() {
        String str = "U05VI3NqemwyMDIx";
        String basedecode = Base64.decodeStr(str);
        System.out.println("basedecode:" + basedecode);
        String encode = com.gyunf.utils.Base64.encode(basedecode.getBytes());
        System.out.println("加密："+ encode);
        String decode = com.gyunf.utils.Base64.decode(encode);
        System.out.println("解密："+ decode);
    }

    @Test 
    public void testBase64Encoder() {
            
    List<BusSystem> pageList = Arrays.asList(
            new BusSystem(1L,"SystemA", 10L, 2L, 3L, 4L,21L,31L,12L),
            new BusSystem(2L,"SystemB", 20L, 5L, 6L, 7L,21L,31L,12L),
            new BusSystem(3L,"SystemA", 15L, 3L, 4L, 5L,21L,31L,12L),
            new BusSystem(4L,"SystemB", 5L, 3L, 4L, 5L,21L,31L,12L),
            new BusSystem(5L,"SystemB", 1L, 3L, 4L, 5L,21L,31L,12L)
    );

    Map<String, BusSystemNum> BusSystemNum = aggregateDataByNames(pageList);

    BusSystemNum.forEach((name, data) ->
            System.out.println("Name: " + name + ", Aggregated Data: " + data));
    }

    public Map<String, BusSystemNum> aggregateDataByNames(List<BusSystem> pageList) {
        BiFunction<BusSystemNum, BusSystem, BusSystemNum> accumulator = (acc, bs) -> {
            acc.integrateDataObject += bs.getIntegrateDataObject();
            acc.integrateDataField += bs.getIntegrateDataField();
            acc.integrateDataNum += bs.getIntegrateDataNum();
            acc.unIntegrateDataObject += bs.getUnIntegrateDataObject();
            acc.unIntegrateDataField += bs.getUnIntegrateDataField();
            acc.integrateUnreleaseDataObject += bs.getIntegrateUnreleaseDataObject();
            acc.integrateUnreleaseDataField += bs.getIntegrateUnreleaseDataField();
            List<Long> dataBaseWidGroups = acc.dataBaseWidGroups;
            dataBaseWidGroups.add(bs.getWid());
            acc.dataBaseWidGroups = dataBaseWidGroups ;
            return acc;
        };
        // 检查pageList是否为空，避免NullPointerException
        if (pageList == null || pageList.isEmpty()) {
            return Collections.emptyMap();
        }

//        return pageList.stream()
//                .collect(Collectors.groupingBy(
//                        BusSystem::getName,
//                        Collectors.reducing(
//                                new BusSystemNum(), // 创建初始的AggregatedData实例
//                                (bs) -> {
//                                    try {
//                                        return new BusSystemNum(
//                                                bs.getIntegrateDataField(),
//                                                bs.getIntegrateDataNum(),
//                                                bs.getUnIntegrateDataObject(),
//                                                bs.getUnIntegrateDataField(),
//                                                bs.getIntegrateUnreleaseDataObject(),
//                                                bs.getIntegrateUnreleaseDataField(),
//                                                new ArrayList<>()
//                                        );
//                                    } catch (NoSuchFieldException e) {
//                                        throw new RuntimeException(e);
//                                    }
//                                },
//                                accumulator)
//                ));
        return null;
    }
}
