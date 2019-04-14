package com.tooto.xishui.entity.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tooto.xishui.entity.LoginStatus;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 * @author Tooto
 * @className SessionUser
 * @Description SessionUser Desc
 * @since 2019/4/14 16:49
 */
public class SessionUser implements Serializable {
    private static final long serialVersionUID = -3948903856725857866L;
    /**
     * accessToken
     */
    private String accessToken;
    private String sessionId;
    /**
     * 用户id，平台唯一
     */
    private String userId = "anonymous";
    /**
     * 用户账号
     */
    private String accountId = "anonymous";
    /**
     * 用户名
     */
    private String userName = "anonymous";
    /**
     * 邮箱
     */
    private String email;
    /**
     * 客户端IP
     */
    private String ip = "Unknown";
    /**
     * 语言环境
     */
    private Locale locale = Locale.getDefault();
    /**
     * 应用代码
     */
    private String appId = "Unknown";
    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 登陆状态：1：登陆成功 2：登陆失败 3：多租户，需要传入租户代码
     */
    private LoginStatus loginStatus;
    /**
     * 退出成功的地址 主要用户单点登录场景下使用
     */
    private String logoutUrl;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public LoginStatus getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(LoginStatus loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    @JsonIgnore
    public String getUserInfo() {
        return toString();
    }

    @JsonIgnore
    public boolean isAnonymous() {
        return StringUtils.isBlank(getAccessToken());
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(userName).append(" [ ").append(" | ").append(accountId).append(" ] ");
        return str.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SessionUser)) {
            return false;
        }
        SessionUser that = (SessionUser)o;
        return Objects.equals(getAccessToken(), that.getAccessToken()) && Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccessToken(), getUserId());
    }
}
