package com.gyunf.utils;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.JdbcConstants;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DbUtil {
    private static Logger logger = LoggerFactory.getLogger(DbUtil.class);


    /**
     * 根据传入的数据库url信息 解析出 ip port sid servicename
     *
     * @return JSONObject 包含 {"ip":"数据库ip地址","port":"数据库端口","server":"数据库sid或服务名"}
     */
    public static JSONObject parseDbUrl(String dbUrl) throws Exception {
        JSONObject urlInfo = new JSONObject();
        try {
            //匹配ip，port
            Pattern pattern = Pattern.compile("(((\\d|[A-Za-z])*[.](\\d|[A-Za-z])*)+:(\\d)+)|(localhost:\\d+)");
            Matcher matcher = pattern.matcher(dbUrl);
            String ipAndPort = "";
            while (matcher.find()) {
                ipAndPort = matcher.group();
            }
            urlInfo.put("ip", ipAndPort.split(":")[0]);
            String port = ipAndPort.split(":")[1];
            urlInfo.put("port", port);
            String sid = "";
            if (dbUrl.length() > (dbUrl.indexOf(port) + port.length())){
                String tempStr = dbUrl.substring(dbUrl.indexOf(port) + port.length() + 1);
                if (dbUrl.indexOf("sqlserver") > 0) {
                    tempStr = tempStr.substring(tempStr.indexOf("=") + 1);
                }
                //匹配数据库sid或服务名
                Pattern pattern1 = Pattern.compile("^[A-Za-z|_]+");
                Matcher matcher1 = pattern1.matcher(tempStr);
                while (matcher1.find()) {
                    sid = matcher1.group();
                }
            }
            urlInfo.put("sid", sid);
        } catch (Exception e) {
            logger.error("数据源URL解析报错，请检查数据源URL是否正确 {}",dbUrl);
            throw e;
        }
        return urlInfo;
    }

    /**
     * 解析请求的url
     * @param url  请输入完成url 例如 http://localhost[:8080]/requesturl
     * @return
     */
    public static JSONObject parasUrl(String url){
        JSONObject urlInfo = new JSONObject();
        String http ="",host ="",port="",uri="",param="";
        http = url.substring(0,url.indexOf(":"));
        url = url.substring(url.indexOf("//")+2);
        host = url.substring(0,url.indexOf("/"));
        if (host.indexOf(":")>0){
            port = host.substring(host.indexOf(":") + 1);
            host = host.substring(0, host.indexOf(":"));
        }
        uri = url.substring(url.indexOf("/") );
        if (uri.indexOf("?")>0){
            param = uri.substring(uri.indexOf("?") + 1);
            uri = uri.substring(0,uri.indexOf("?"));
        }
        urlInfo.put("http", http);
        urlInfo.put("host", host);
        urlInfo.put("port", port);
        urlInfo.put("uri", uri);
        urlInfo.put("param", param);
        return urlInfo;
    }


    /**
     * 根据 sql 解析出sql中包含的表 ，sql需要为可执行sql
     * @param sql
     * @return
     */

    public static List<String> getTableFromSql(String sql)throws Exception{

        if (StringUtils.isBlank(sql)){
            throw new Exception("sql不能为空");
        }
        try {
            SQLStatementParser parser = new MySqlStatementParser(sql);
            SQLStatement sqlStatement = parser.parseStatement();
            MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
            sqlStatement.accept(visitor);
            Map<TableStat.Name, TableStat> tables = visitor.getTables();
            List<String> allTableName = new LinkedList<>();
            for (TableStat.Name t : tables.keySet()) {
                allTableName.add(t.getName());
            }
            return allTableName;
        }catch (Exception e){
            throw new Exception("sql解析错误:" + e.getMessage());
        }

    }


    /**
     * 此方法输出的sql解析出来的字段是原字段名 ，不是 as 后的名称,若要取sql中 as后的名称 请使用 getTableAndAliasColumnsFromSql 方法
     * 根据sql 获取执行的表名 字段名
     * ⚠️ select *  from table   语句解析不出来 columns
     * @param sql
     * @return  Map : tables 表名  ,columns 字段名 :[table.column]
     * @throws Exception
     */
    public static Map<String,List<String>> getTableAndColumnsFromSql(String sql)throws Exception{
        Map<String, List<String>> data = new HashMap<>();
        if (StringUtils.isBlank(sql)){
            throw new Exception("sql不能为空");
        }
        try {
            SQLStatementParser parser = new MySqlStatementParser(sql);
            SQLStatement sqlStatement = parser.parseStatement();
            MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
            sqlStatement.accept(visitor);
            Map<TableStat.Name, TableStat> tables = visitor.getTables();
            List<String> allTableName = new LinkedList<>();
            for (TableStat.Name t : tables.keySet()) {
                allTableName.add(t.getName());
            }
            data.put("tables",allTableName);
            Iterator<TableStat.Column> iterator = visitor.getColumns().iterator();
            List<String> columns = new LinkedList<>();
            while (iterator.hasNext()){
                columns.add(iterator.next().getFullName());
            }
            data.put("columns", columns);
        }catch (Exception e){
            throw new Exception("sql解析错误:" + e.getMessage());
        }
        return data;
    }


    /**
     * 根据sql 获取执行的表名 字段名
     * ⚠️ select *  from table   语句解析不出来 columns
     * @param sql
     * @return  Map : tables 表名  ,columns 字段名 :[table.column]
     * @throws Exception
     */
    public static Map<String,List<String>> getTableAndAliasColumnsFromSql(String sql)throws Exception{
        if (StringUtils.isBlank(sql)){
            throw new Exception("sql不能为空");
        }
        Map<String,List<String>> data =  getTableAndColumnsFromSql(sql);
        List<String> columns = data.get("columns");
        try {
           List<SQLStatement> sqlStatements = SQLUtils.parseStatements(sql, JdbcConstants.POSTGRESQL);
            for (SQLStatement sqlStatement : sqlStatements) {
                SQLSelectQueryBlock sqlSelectQuery =(SQLSelectQueryBlock)((SQLSelectStatement) sqlStatement).getSelect().getQuery();
                List<SQLSelectItem> selectList = sqlSelectQuery.getSelectList();
                for (int i = 0; i < selectList.size(); i++) {
                    SQLSelectItem sqlSelectItem = selectList.get(i);
                    if (StringUtils.isNotBlank(sqlSelectItem.getAlias())){
                        String column =  columns.get(i);
                         columns.set(i,column.split("\\.")[0]+"."+sqlSelectItem.getAlias());
                     }
                }
            }
        }catch (Exception e){
            throw new Exception("sql解析错误:" + e.getMessage());
        }
        return data;
    }


    /**
     * 自动适配是否以主从模式读取湖、仓数据
     * @param url 需转换的url
     * @param type 转换类型，write 写入，read 读取
     */
    public static String getUrlInSlaveMode(String dbUseMode ,String url,String type){
        if (url.split(",").length < 2){
            return url;
        }
        if (url.indexOf("?") < 0 ){
            url += "?";
        }
        if (!url.endsWith("?")){
            url += "&";
        }
        if (!"cluster".equals(dbUseMode)){
            //如果未启用集群，且配置了3个ip，那集群视为高可用
            url = url + "targetServerType=master&loadBalanceHosts=false";
            logger.info("未启用集群，多IP高可用Url地址 {}",url);
        }else{
            if ("read".equals(type)) {
                url = url + "targetServerType=preferSlave&loadBalanceHosts=true";
                logger.info("启用集群，从库Url地址 {}",url);
            }
            if ("write".equals(type)) {
                url = url + "targetServerType=master&loadBalanceHosts=false";
                logger.info("启用集群，主库Url地址 {}",url);
            }
        }
        return url;
    }

    //适配各种数据库的分页查询数据
    public static String getExeSqlForPage(String sqlParam, int page,int pagesize,String dbType, net.sf.json.JSONObject paramOrder){
        String orderBySql =  "";
        if (paramOrder!=null && paramOrder.size()>0){
            Set<String> keySet = paramOrder.keySet();
            Iterator<String> key = keySet.iterator();
            while (key.hasNext()){
                String keyValue = key.next();
                if(!"".equals(orderBySql)){
                    orderBySql +=  ","  ;
                }
                orderBySql += (  keyValue +" "+ paramOrder.get(keyValue));
            }
        }
        if (dbType.toLowerCase().contains("oracle")){
            sqlParam="select * from (select tempa.*,rownum as pagerownumber from (" + sqlParam + ") tempa ) tempb where pagerownumber <= " + page * pagesize + " and pagerownumber >" + (page - 1) * pagesize;
        }else if (dbType.toLowerCase().contains("postgresql") || dbType.toLowerCase().contains("gauss200") ){
            sqlParam="select * from (select tempa.*,row_number()over("+(StringUtils.isEmpty(orderBySql)?"":" order by "+orderBySql)+") as pagerownumber from (" + sqlParam + ") tempa ) tempb where pagerownumber <= " + page * pagesize + " and pagerownumber >" + (page - 1) * pagesize;
        }else if (dbType.toLowerCase().contains("microsoft sql server")){
            sqlParam="SELECT * from ( SELECT TEMP.*,row_number() OVER(order by "+(StringUtils.isEmpty(orderBySql)?"TEMPTEXT":orderBySql)+") pagerownumber FROM ( SELECT C.*,'' AS TEMPTEXT FROM ( "+sqlParam+" ) C ) TEMP ) TEMPB where TEMPB.pagerownumber BETWEEN "+(page - 1) * pagesize+" and "+page * pagesize;
        }else if (dbType.toLowerCase().contains("mysql")){
            sqlParam="SELECT * from (  "+sqlParam+"  ) TEMP limit "+(page - 1) * pagesize+","+pagesize;
        }
        return sqlParam;
    }

    public static void main(String[] args) {
        try {
            System.out.println( parseDbUrl("jdbc:mysql://10.3.11.73:3306"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
