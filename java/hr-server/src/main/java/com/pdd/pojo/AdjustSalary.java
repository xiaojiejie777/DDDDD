package com.pdd.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author pdd
 * @since 2021-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="AdjustSalary对象", description="")
public class AdjustSalary implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer eid;

    @ApiModelProperty(value = "调薪日期")
    @TableField("asDate")
    private LocalDate asDate;

    @ApiModelProperty(value = "调前薪资")
    @TableField("beforeSalary")
    private Integer beforeSalary;

    @ApiModelProperty(value = "调后薪资")
    @TableField("afterSalary")
    private Integer afterSalary;

    @ApiModelProperty(value = "调薪原因")
    private String reason;

    @ApiModelProperty(value = "备注")
    private String remark;


}
