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
@NoArgsConstructor
@AllArgsConstructor
@TableName("seat")
public class Seat {
    @TableId(value = "seatId",type = IdType.AUTO)
    private int seatId;

    @NotNull
    @TableField("seatType")
    private int seatType;

    @NotNull
    @TableField("seatOccupancy")
    private int seatOccupancy;

    @NotNull
    @TableField("seatPosition")
    private String seatPosition;

    @NotNull
    @TableField("seatAvailableTime")
    private String seatAvailableTime;
}
