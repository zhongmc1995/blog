package com.zhongmc.blog.domain;

/**
 * Created by ZMC on 2017/1/25.
 */
public class Tag extends BaseEntity{
    private  int id;
    private String tagname;
    private String tagicon;
    private int blognum = 0;
    public  void setBlognum(int blognum){this.blognum = blognum;}
    public int getBlognum(){return this.blognum;}

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

    public void setTagicon(String tagicon) {
        this.tagicon = tagicon;
    }

    public String getTagicon() {
        return tagicon;
    }
}
