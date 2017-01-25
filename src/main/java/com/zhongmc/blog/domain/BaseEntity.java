package com.zhongmc.blog.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ZMC on 2017/1/16.
 */
public class BaseEntity implements Serializable {
    public BaseEntity(){

    }


    private Date createTime;
    private Date updateTime;
    private String createBy;
    private String updateBy;

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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}
