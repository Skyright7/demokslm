package com.demo.demokslm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author Gengyu Liu(xmulgy@126.com)
 * @description:
 * @date 2022/10/15 22:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {
    @NotNull
    private String userCaseId;
    @NotNull
    private String userPassword;
}
