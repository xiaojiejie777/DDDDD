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
@ApiModel(value="EmployeeRemove对象", description="")
public class EmployeeRemove implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer eid;

    @ApiModelProperty(value = "调动后部门")
    @TableField("afterDepId")
    private Integer afterDepId;

    @ApiModelProperty(value = "调动后职位")
    @TableField("afterJobId")
    private Integer afterJobId;

    @ApiModelProperty(value = "调动日期")
    @TableField("removeDate")
    private LocalDate removeDate;

    @ApiModelProperty(value = "调动原因")
    private String reason;

    private String remark;


}
