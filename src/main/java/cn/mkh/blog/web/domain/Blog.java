package cn.mkh.blog.web.domain;


import cn.mkh.blog.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Blog {

    private Integer id;//ID,唯一键
    private String title;//标题
    private String content;//内容
    private String firstPicture;//首图
    private String flag;//标记(是否原创等)
    private Integer views;//浏览数
    private boolean appreciation;//是否开启打赏
    private boolean shareStatement;//是否开启转载声明
    private boolean commentabled;//是否开启评论
    private boolean published;//是否发布
    private boolean recommend;//是否推荐
    private Date createTime;//发布时间
    private String createTimeStr;//发布时间
    private Date updateTime;//更新时间
    private String updateTimeStr;//更新时间
    private Integer typeId;//类型ID
    private List<Integer> tagIds;//类型ID
    private String description;
    private Type type;//类型

    private List<Tag> tags;//标签

    private User user;//所属用户

    private List<Comment> comments;//评论

    public Blog() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getCreateTimeStr() {
        if(createTime != null){
            createTimeStr = DateUtil.dateToString(createTime, "yyyy-MM-dd HH:mm");
        }
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = DateUtil.dateToString(createTime, "yyyy-MM-dd HH:mm");
    }

    public String getUpdateTimeStr() {
        if(updateTime != null){
            updateTimeStr = DateUtil.dateToString(updateTime, "yyyy-MM-dd HH:mm");
        }
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr =  DateUtil.dateToString(updateTime, "yyyy-MM-dd HH:mm");

    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isCommentabled() {
        return commentabled;
    }

    public void setCommentabled(boolean commentabled) {
        this.commentabled = commentabled;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Integer> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Integer> tagIds) {
        this.tagIds = tagIds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", shareStatement=" + shareStatement +
                ", commentabled=" + commentabled +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", createTimeStr='" + createTimeStr + '\'' +
                ", updateTime=" + updateTime +
                ", updateTimeStr='" + updateTimeStr + '\'' +
                ", typeId=" + typeId +
                ", tagIds=" + tagIds +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", tags=" + tags +
                ", user=" + user +
                ", comments=" + comments +
                '}';
    }
}
