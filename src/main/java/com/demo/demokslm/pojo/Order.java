package com.demo.demokslm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("orders")
public class Order {
    @TableId(value = "orderId",type = IdType.AUTO)
    private int orderId;

    @NotNull
    @TableField("orderTime")
    private String orderTime;

    @NotNull
    @TableField("customId")
    private int customId;

    @NotNull
    @TableField("orderItemId")
    private int orderItemId;

    @NotNull
    @TableField("orderStatus")
    private int orderStatus;
}
