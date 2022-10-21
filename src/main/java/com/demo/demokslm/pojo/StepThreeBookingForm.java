package com.demo.demokslm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author Gengyu Liu(xmulgy@126.com)
 * @description:
 * @date 2022/10/21 2:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StepThreeBookingForm {
    @NotNull
    private int userId;
    @NotNull
    private int seatId;
    @NotNull
    private int startTime;
    @NotNull
    private int endTime;
}
