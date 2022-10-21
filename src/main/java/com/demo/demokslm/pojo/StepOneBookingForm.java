package com.demo.demokslm.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author Gengyu Liu(xmulgy@126.com)
 * @description:
 * @date 2022/10/21 1:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StepOneBookingForm {
    @NotNull
    private int arrivingTime;
    @NotNull
    private String preferredFloor;
    @NotNull
    private String preferredArea;
}
