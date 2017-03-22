package com.zhongmc.blog.domain;

/**
 * Created by zhongmc on 2017/3/22.
 */
public class Theme {
    private int id;
    private String  themename ;
    private String themesdesc;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThemename() {
        return themename;
    }

    public void setThemename(String themename) {
        this.themename = themename;
    }

    public String getThemesdesc() {
        return themesdesc;
    }

    public void setThemesdesc(String themesdesc) {
        this.themesdesc = themesdesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
