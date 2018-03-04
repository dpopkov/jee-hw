package ru.otus.dpopkov.webstats.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class StatMarker {
    public static final String DEFAULT_NAME = "StatMarker";

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String page;
    private String remoteAddr;
    private String userAgent;
    private Date timestamp;
    private String username;
    private Long previousId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getPreviousId() {
        return previousId;
    }

    public void setPreviousId(Long previousId) {
        this.previousId = previousId;
    }

    @Override
    public String toString() {
        return "StatMarker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", page='" + page + '\'' +
                ", remoteAddr='" + remoteAddr + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", timestamp=" + timestamp +
                ", username='" + username + '\'' +
                ", previousId=" + previousId +
                '}';
    }
}
