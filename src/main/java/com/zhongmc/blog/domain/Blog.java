package com.zhongmc.blog.domain;

/**
 * Created by ZMC on 2017/1/16.
 */
public class Blog extends BaseEntity {
    private int id;
    private int typeid;
    private String title;
    private String content;
    private String coverpic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
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

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", typeid=" + typeid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", coverpic='" + coverpic + '\'' +
                '}';
    }
}
