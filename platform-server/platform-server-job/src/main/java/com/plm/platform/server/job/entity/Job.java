package com.plm.platform.server.job.entity;

import com.plm.platform.common.core.converter.TimeConverter;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author crystal
 */
@Data
@TableName("t_job")
@Excel("定时任务信息表")
public class Job implements Serializable {

    /**
     * 任务调度参数 key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";
    private static final long serialVersionUID = 400066840871805700L;
    @TableId(value = "JOB_ID", type = IdType.AUTO)
    private Long jobId;
    @TableField("BEAN_NAME")
    @NotBlank(message = "{required}")
    @Size(max = 50, message = "{noMoreThan}")
    @ExcelField(value = "Bean名称")
    private String beanName;
    @TableField("METHOD_NAME")
    @NotBlank(message = "{required}")
    @Size(max = 50, message = "{noMoreThan}")
    @ExcelField(value = "方法名称")
    private String methodName;
    @TableField("PARAMS")
    @Size(max = 50, message = "{noMoreThan}")
    @ExcelField(value = "方法参数")
    private String params;
    @TableField("CRON_EXPRESSION")
    @NotBlank(message = "{required}")
    @ExcelField(value = "Cron表达式")
    private String cronExpression;
    @TableField("STATUS")
    @ExcelField(value = "状态", writeConverterExp = "0=正常,1=暂停")
    private String status;
    @TableField("REMARK")
    @Size(max = 100, message = "{noMoreThan}")
    @ExcelField(value = "备注")
    private String remark;
    @TableField("CREATE_TIME")
    @ExcelField(value = "创建时间", writeConverter = TimeConverter.class)
    private Date createTime;
    private transient String createTimeFrom;
    private transient String createTimeTo;
    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL("0"),
        /**
         * 暂停
         */
        PAUSE("1");

        private String value;

        ScheduleStatus(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}
