package com.zhongmc.blog.domain;

import java.util.List;

/**
 * Created by ZMC on 2017/1/16.
 */
public class Blog extends BaseEntity {
    private int id;
    private String title;
    private String content;
    private String coverpic;
    private String keywords;
    private int viewnum;
    private String tags;
    List<Tag> tagList ;
    public void setTagList(List<Tag> tagList){this.tagList = tagList;}
    public List<Tag> getTagList(){return this.tagList;}
    public void setKeywords(String keywords){this.keywords = keywords;}
    public String getKeywords(){return this.keywords;}

    public void   setTags(String tags){ this.tags = tags;}
    public String getTags(){return this.tags;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getViewnum() {
        return viewnum;
    }

    public void setViewnum(int viewnum) {
        this.viewnum = viewnum;
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

    public String getCoverpic() {
        return coverpic;
    }

    public void setCoverpic(String coverpic) {
        this.coverpic = coverpic;
    }

    /*@Override
    public void setCreateTime(Date createTime) {
        super.setCreateTime(createTime);
    }

    @Override
    public Date getCreateTime() {
        return super.getCreateTime();
    }

    @Override
    public void setCreateBy(String createBy) {
        super.setCreateBy(createBy);
    }

    @Override
    public String getCreateBy() {
        return super.getCreateBy();
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        super.setUpdateTime(updateTime);
    }

    @Override
    public Date getUpdateTime() {
        return super.getUpdateTime();
    }

    @Override
    public void setUpdateBy(String updateBy) {
        super.setUpdateBy(updateBy);
    }

    @Override
    public String getUpdateBy() {
        return super.getUpdateBy();
    }*/
}
