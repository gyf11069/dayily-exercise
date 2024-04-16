package com.gyunf.messagealert.utils;


import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class SendWeixinQYH {

    public Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${weixin.sendMessage}")
    String sendMessage;

    @Value("${weixin.validataToken}")
    String validataToken;

    @Value("${weixin.private.sendMessage}")
    String sendMessageForPrivate;

    @Value("${weixin.private.validataToken}")
    String validataTokenForPrivate;

    @Value("${weixin.type}")
    String weiXinType;



    /**
     * 未自动刷新token
     *使用配置文件的 token配置
     * @return
     */
    public void sendMessage(String corp_id, String corp_secret, String agent_id, String users, String content) {
        try {
            //解密
            String encrypt_users = users;
            String decrypt_users = "";
//            for(String _u : encrypt_users.split("\\|")){
//                try {
//                    String u = SM4Util.decryptData_ECB(_u);
//                    if(u==null || "null".equals(u)){
//                        decrypt_users += (!"".equals(decrypt_users)?"|":"")+_u;
//                    }else{
//                        decrypt_users += (!"".equals(decrypt_users)?"|":"")+u;
//                    }
//                } catch (Exception e) {
//                    log.warn(_u+"密码解密失败："+e.getMessage());
//                    decrypt_users += (!"".equals(decrypt_users)?"|":"")+_u;
//                }
//            }
//            users = decrypt_users;

            log.info("正在给：" + users + "发送微信企业号消息");
            JSONObject messageJson = new JSONObject();
            String accessToken = getWeChatAccessToken(corp_id, corp_secret);//获取token
            /*请求体参数组装*/
            messageJson = messageRequestParam(messageJson,weiXinType,agent_id,users,content,accessToken);
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());

            HttpEntity<String> httpEntity = new HttpEntity<String>(messageJson.toString(), headers);
            Map resultMap = new HashMap();
            if ("private".equals(weiXinType)) {
                resultMap = (Map) restTemplate.postForObject(sendMessageForPrivate, httpEntity, Map.class);
            }else {
                resultMap = (Map) restTemplate.postForObject(sendMessage.replace("{ACCESS_TOKEN}", accessToken), httpEntity, Map.class, new Object[0]);
            }
            log.info("微信企业号发送结果：" + JSONObject.fromObject(resultMap));
        } catch (Exception e) {
            log.info("微信企业号发送异常：" + e.getMessage());
        }
    }

    /**
     * 自动刷新token
     * @param corp_id
     * @param corp_secret
     * @param agent_id
     * @param users
     * @param content
     * @param title
     * @param url
     * @param use_type
     * @throws Exception
     * 备注:   msgtype 更改为 text 格式 ，如需消息类型为卡片格式 并且点击可跳转  则设置为 textcard格式  ， 填入title ,description ,url ,取消content
     */
    public void sendMessage(String corp_id, String corp_secret, String agent_id, String users, String content,
                            String title, String url,String use_type)throws Exception {
        //解密
        String encrypt_users = users;
        String decrypt_users = "";
//        for(String _u : encrypt_users.split("\\|")){
//            try {
//                String u = SM4Util.decryptData_ECB(_u);
//                if(u==null || "null".equals(u)){
//                    decrypt_users += (!"".equals(decrypt_users)?"|":"")+_u;
//                }else{
//                    decrypt_users += (!"".equals(decrypt_users)?"|":"")+u;
//                }
//            } catch (Exception e) {
//                log.warn(_u+"密码解密失败："+e.getMessage());
//                decrypt_users += (!"".equals(decrypt_users)?"|":"")+_u;
//            }
//        }
//        users = decrypt_users;


        log.info("正在给：" + users + "发送微信企业号消息");
        JSONObject messageJson = new JSONObject();

        /*获取accessToken*/
        log.info("开始获取accessToken时间戳：" + System.currentTimeMillis());
//        Map<String,Object> cashMap = messageDao.selectWeChatToken(use_type);
        Map<String,Object> cashMap = new HashMap<>();
        String accessToken = "";
        if (null != cashMap && !cashMap.isEmpty() && cashMap.get("token") != null) {
            accessToken = cashMap.get("token") + "";//缓存accessToken
            String accessTokenTimestamp = cashMap.get("token_init_time") + "";//最近缓存时间戳
            String nowTimestamp = String.valueOf((System.currentTimeMillis()/1000));//当前时间戳
            boolean isLost = Long.parseLong(nowTimestamp) > (Long.parseLong(accessTokenTimestamp) + 7000);
            log.info("当前时间戳：" + nowTimestamp + ",最近缓存时间戳：" + accessTokenTimestamp + ",是否失效：" + isLost);
            if (isLost) {
                //缓存失效，重新请求（为防止误差，采取7000秒刷新一次，官方定义：7200秒）
                log.info("检测失效，开始重新缓存.................................");
                accessToken = getWeChatAccessToken(corp_id, corp_secret);//获取token
                if (accessToken!=null){
//                    messageDao.updateWechatToken(accessToken, nowTimestamp,use_type);//更新数据库
                }
            }
        }else {
            String nowTimestamp = String.valueOf(System.currentTimeMillis()/1000);//当前时间戳
            accessToken = getWeChatAccessToken(corp_id, corp_secret);//获取token
            if (accessToken!=null){
//                messageDao.updateWechatToken(accessToken, nowTimestamp,use_type);//数据库里没有token信息，将生成的token插入数据库
            }
        }
        /*请求体参数组装*/
        messageJson = messageRequestParam(messageJson,weiXinType,agent_id,users,content,accessToken);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> httpEntity = new HttpEntity<String>(messageJson.toString(), headers);
        Map resultMap = new HashMap();
        if ("private".equals(weiXinType)) {
            resultMap = (Map) restTemplate.postForObject(sendMessageForPrivate, httpEntity, Map.class);
        }else {
            resultMap = (Map) restTemplate.postForObject(sendMessage.replace("{ACCESS_TOKEN}", accessToken), httpEntity, Map.class, new Object[0]);
        }
        log.info("微信企业号发送结果：" + JSONObject.fromObject(resultMap));
    }

    //发送至多个微信企业号
    public void sendMessageToUserList(String corp_id, String corp_secret, String agent_id, List<String> usersList,
                                      String content, String title, String url, String use_type)throws Exception{
        for (String user : usersList) {
            this.sendMessage(  corp_id,   corp_secret,   agent_id, user,  content,   title,   url,   use_type);
        }
    }

    /**
     * 获取access_token
     *
     * @return
     */
    public String getWeChatAccessToken(String corp_id, String corp_secret) {
        RestTemplate restTemplate = new RestTemplate();
        Map token = new HashMap();
        if ("private".equals(weiXinType)) {
            String uuid = UUID.randomUUID().toString().replaceAll("-","");	//通过UUID生成一个唯一标识
            JSONObject messageJson = new JSONObject();
            messageJson.put("request_id", uuid);
            messageJson.put("grant_type", "client_credentials"); //默认值
            messageJson.put("ucode", corp_id);
            messageJson.put("upwd", corp_secret);
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());

            HttpEntity<String> httpEntity = new HttpEntity<String>(messageJson.toString(), headers);
            token = (Map) restTemplate.postForObject(validataTokenForPrivate, httpEntity, Map.class);
        }else {
            token = (Map) restTemplate.getForObject(validataToken.replace("{CORP_ID}", corp_id).replace("{CORPSECRET}", corp_secret), Map.class, new Object[0]);
        }

        if (token.get("errcode").equals(Integer.valueOf(0))) {
            String accessToken = null;
            if ("private".equals(weiXinType)) {
                accessToken = JSONObject.fromObject(token.get("data")).getString("access_token");
            }else {
                accessToken = token.get("access_token").toString();
            }
            return accessToken;
        }else{
            log.error("token获取失败:{},{}",token.get("errcode"),token.get("errmsg"));
        }
        return null;
    }

    /**
     * 组装消息发送的请求体内容
     * @param messageJson 请求体参数
     * @param type 企业微信部署类型
     * @param agent_id 企业微信应用id
     * @param users 企业微信用户
     * @param content 消息内容
     * @param accessToken 请求token
     * @return
     */
    public JSONObject messageRequestParam(JSONObject messageJson,String type,String agent_id, String users, String content, String accessToken) {
        if ("private".equals(weiXinType)) {
            String uuid = UUID.randomUUID().toString().replaceAll("-","");	//通过UUID生成一个唯一标识
            messageJson.put("request_id", uuid);
            messageJson.put("token", accessToken);
            messageJson.put("agentid", agent_id);
            messageJson.put("userid", users);
            messageJson.put("content", content);
            /*
            * 发送消息类型：文本类型（1）,图文消息（2）,卡片消息（3）,markdown消息（4）,任务卡片消息（5）
            * */
            messageJson.put("type", "1");
        }else {
            messageJson.put("touser", users);
            messageJson.put("msgtype", "text");
            messageJson.put("agentid", agent_id);
            JSONObject text = new JSONObject();
            text.put("content", content);
            messageJson.put("text", text);
            messageJson.put("safe", 0);
            messageJson.put("enable_id_trans", 0);
            messageJson.put("enable_duplicate_check", 0);
            messageJson.put("duplicate_check_interval", 1800);
        }
        return messageJson;
    }

}
