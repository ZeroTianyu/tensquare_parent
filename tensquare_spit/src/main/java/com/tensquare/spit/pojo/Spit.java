package com.tensquare.spit.pojo;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @author Guoty
 * @create 2019/01/09  23:24
 */
public class Spit {
    @Id
    private String _id;         //id
    private String content;     //内容
    private Date publishtime;   //发布时间
    private String userid;      //发布人iD
    private String nickname;    //发布人姓名
    private Integer visits;     //浏览量
    private Integer thumbup;    //点赞数
    private Integer share;      //分享数
    private Integer comment;    //回复数
    private String state;       //状态
    private String parentid;    //父节点ID


    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public Integer getThumbup() {
        return thumbup;
    }

    public void setThumbup(Integer thumbup) {
        this.thumbup = thumbup;
    }

    public Integer getShare() {
        return share;
    }

    public void setShare(Integer share) {
        this.share = share;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }
}
