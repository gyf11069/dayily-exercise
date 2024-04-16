package com.gyunf.messagealert.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * 钉钉机器人消息推送
 */
public class DingTalkMsgUtil {

    static Logger logger = LoggerFactory.getLogger(DingTalkMsgUtil.class);
    /**
     * 钉钉发送消息，仅文字消息
     *
     * @param path              webhook
     * @param secret            密钥签名,如果设置加签则必须传值，没有则传null
     * @param msg               消息内容
     * @param isAtAll           是否@所有人
     * @param recipientPhoneArr 接收人手机号码集合，当isAtAll为true时，可不传
     * @throws Exception
     */
    public static void sendDingTalkText(String path, String secret, String msg, boolean isAtAll, List<String> recipientPhoneArr) throws Exception {
        if (!StringUtils.isEmpty(secret)) {
            Long timestamp = System.currentTimeMillis();
            String stringToSign = timestamp + "\n" + secret;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
//            String sign = URLEncoder.encode(new String( org.apache.commons.codec.binary.Base64.encodeBase64(signData)), "UTF-8");
            String sign = URLEncoder.encode(new String( Base64.encodeBase64(signData)), "UTF-8");
            path = path + "&" + "timestamp=" + timestamp + "&" + "sign=" + sign;
        }
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
        JSONObject text = new JSONObject();
        text.put("content", msg);
        JSONObject at = new JSONObject();
        at.put("isAtAll", isAtAll);
        if (!isAtAll) {
            at.put("atMobiles", recipientPhoneArr);
        }
        JSONObject body = new JSONObject();
        body.put("msgtype", "text");
        body.put("at", at);
        body.put("text", text);
        final HttpEntity<JSONObject> httpEntity = new HttpEntity<>(body, headers);
        ResponseEntity responseEntity = restTemplate.exchange(path, HttpMethod.POST, httpEntity, String.class);
        logger.info("钉钉发送结果:{}",responseEntity.getBody());
    }

    /**
     * 钉钉发送消息，markdown类型
     *
     * @param path               webhook
     * @param secret             密钥签名,如果设置加签则必须传值，没有则传null
     * @param markdownMsg        markdown消息内容{"title":"","text":""}
     * @param isAtAll            是否@所有人
     * @param recipientPhoneList 接收人手机号码集合，当isAtAll为true时，可不传
     * @throws Exception
     */
    public static void sendDingTalkMarkDown(String path, String secret, Map<String, Object> markdownMsg, boolean isAtAll, List<String> recipientPhoneList) throws Exception {
        if (!StringUtils.isEmpty(secret)) {
            Long timestamp = System.currentTimeMillis();
            String stringToSign = timestamp + "\n" + secret;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
//            String sign = URLEncoder.encode(new String(org.apache.commons.codec.binary.Base64.encodeBase64(signData)), "UTF-8");
            String sign = URLEncoder.encode(new String( Base64.encodeBase64(signData)), "UTF-8");
            path = path + "&" + "timestamp=" + timestamp + "&" + "sign=" + sign;
        }
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
        JSONObject at = new JSONObject();
        at.put("isAtAll", isAtAll);
        if (!isAtAll) {
            at.put("atMobiles", recipientPhoneList);
        }
        JSONObject body = new JSONObject();
        body.put("msgtype", "markdown");
        body.put("at", at);
        body.put("markdown", markdownMsg);
        final HttpEntity<JSONObject> httpEntity = new HttpEntity<>(body, headers);
        ResponseEntity responseEntity = restTemplate.exchange(path, HttpMethod.POST, httpEntity, String.class);
        logger.info("钉钉消息发送结果:{}",responseEntity.getBody());
    }
}
