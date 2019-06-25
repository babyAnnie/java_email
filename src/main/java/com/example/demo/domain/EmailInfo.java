package com.example.demo.domain;

import java.util.Objects;

public class EmailInfo {
    /**
     * 发件人地址
     */
    private String emailAddress;

    /**
     * 内容
     */
    private String content;

    /**
     * 邮件主题
     * @return
     */
    private String subject;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmailInfo)) {
            return false;
        }
        EmailInfo emailInfo = (EmailInfo) o;
        return Objects.equals(emailAddress, emailInfo.emailAddress) &&
                Objects.equals(content, emailInfo.content) &&
                Objects.equals(subject, emailInfo.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress, content, subject);
    }

    public EmailInfo() {
    }

    @Override
    public String toString() {
        return "EmailInfo{" +
                "emailAddress='" + emailAddress + '\'' +
                ", content='" + content + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
