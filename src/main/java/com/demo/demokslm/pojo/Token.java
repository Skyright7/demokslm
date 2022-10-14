package com.demo.demokslm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author Gengyu Liu(xmulgy@126.com)
 * @date 2022/10/13 22:14
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("token")
public class Token {
    /* token表的ID */
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    /* userId字段，确定这个token是哪个用户的 */
    @NotNull
    @TableField("userId")
    private int userId;
    /* buildTime字段，通过这个计算过期时间（过期时间间隔暂时为统一固定值） */
    @NotNull
    @TableField("buildTime")
    private int buildTime;
    /* token字段，用来存放token */
    @NotNull
    @TableField("token")
    private String token;

}
