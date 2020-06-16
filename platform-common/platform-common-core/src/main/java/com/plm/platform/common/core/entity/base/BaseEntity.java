package com.plm.platform.common.core.entity.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.plm.platform.common.core.converter.TimeConverter;
import com.wuwenze.poi.annotation.ExcelField;

import java.io.Serializable;
import java.util.Date;

/**
 * @author crystal
 * description:
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 8370181269434686927L;

    /**
     * 创建者
     */
    @TableField("create_by")
    @ExcelField(value = "创建者")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @ExcelField(value = "创建时间", writeConverter = TimeConverter.class)
    private Date createTime;

    /**
     * 最后修改者
     */
    @TableField("update_by")
    @ExcelField(value = "最后修改者")
    private String updateBy;

    /**
     * 最后修改时间
     */
    @TableField("update_time")
    @ExcelField(value = "最后修改时间", writeConverter = TimeConverter.class)
    private Date updateTime;

    protected BaseEntity() {
    }

    public BaseEntity(String createBy, Date createTime, String updateBy, Date updateTime) {
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
