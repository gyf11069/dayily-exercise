package com.gyunf.messagealert;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class MessConfig {

    //"WID 新增时为空"
    private Long wid;

    //"创建者"
    public String creator;
    //"创建时间"
    public String create_time;
    //"最后修改者"
    public String last_modifier;
    //"最后修改时间"
    public String modify_time;
    //"排序号"
    public Integer order_num;
    //"邮箱服务"
    public String smtp_server;
    //"邮箱端口"
    public String smtp_port;
    //"发件箱账号"
    public String sendmail;
    //"发件箱密码"
    public String sendmailpass;
    //"启用邮箱"
    public boolean usemail;
    //"微信agent_id"
    public String agent_id;
    //"微信corp_id"
    public String corp_id;
    //"微信corp_secret"
    public String corp_secret;
    //"启用微信"
    public boolean usewechat;
    //"使用类型 QUALITY / INTEGRATE", required = true
    public String use_type;
    //"钉钉webhook"
    private String webhook;
    //"钉钉签名"
    private String secret;
    //"是否使用钉钉"
    public boolean usedingding;

    //"是否使用其他消息配置"
    public boolean useotherconfig;

    /**
     *
     * {
     *   "useType":"已对接的方式",
     *   "config":{厂商具体数据},
     *   "acceptUser":[消息接收用户,不指定的话使用管理员id]
     * }
     *
     */
    //"其他消息配置(接收前端）"
    private String otherMsgConfig;

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getLast_modifier() {
        return last_modifier;
    }

    public void setLast_modifier(String last_modifier) {
        this.last_modifier = last_modifier;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

    public Integer getOrder_num() {
        return order_num;
    }

    public void setOrder_num(Integer order_num) {
        this.order_num = order_num;
    }

    public String getSmtp_server() {
        return smtp_server;
    }

    public void setSmtp_server(String smtp_server) {
        this.smtp_server = smtp_server;
    }

    public String getSmtp_port() {
        return smtp_port;
    }

    public void setSmtp_port(String smtp_port) {
        this.smtp_port = smtp_port;
    }

    public String getSendmail() {
        return sendmail;
    }

    public void setSendmail(String sendmail) {
        this.sendmail = sendmail;
    }

    public boolean isUsemail() {
        return usemail;
    }

    public void setUsemail(boolean usemail) {
        this.usemail = usemail;
    }

    public String getAgent_id() {
        return agent_id;
    }

    public void setAgent_id(String agent_id) {
        this.agent_id = agent_id;
    }

    public String getCorp_id() {
        return corp_id;
    }

    public void setCorp_id(String corp_id) {
        this.corp_id = corp_id;
    }

    public String getCorp_secret() {
        return corp_secret;
    }

    public void setCorp_secret(String corp_secret) {
        this.corp_secret = corp_secret;
    }

    public boolean isUsewechat() {
        return usewechat;
    }

    public void setUsewechat(boolean usewechat) {
        this.usewechat = usewechat;
    }

    public String getUse_type() {
        return use_type;
    }

    public void setUse_type(String use_type) {
        this.use_type = use_type;
    }

    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public boolean isUsedingding() {
        return usedingding;
    }

    public void setUsedingding(boolean usedingding) {
        this.usedingding = usedingding;
    }

    public boolean isUseotherconfig() {
        return useotherconfig;
    }

    public void setUseotherconfig(boolean useotherconfig) {
        this.useotherconfig = useotherconfig;
    }

    public String getOtherMsgConfig() {
        return otherMsgConfig;
    }

    public void setOtherMsgConfig(String otherMsgConfig) {
        this.otherMsgConfig = otherMsgConfig;
    }

    public JSONObject getOther_config() {
        return other_config;
    }

    public void setOther_config(JSONObject other_config) {
        this.other_config = other_config;
    }

    //"其他消息配置(后端查询"
    private JSONObject other_config;


    public String getSendmailpass() {
        return sendmailpass;
    }

    public void setSendmailpass(String sendmailpass ) {
        this.sendmailpass = sendmailpass;
    }
}
