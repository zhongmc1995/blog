package com.zhongmc.blog.domain;

/**
 * Created by ZMC on 2017/1/25.
 */
public class Tag extends BaseEntity{
    private  int id;
    private String tagname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }
}
