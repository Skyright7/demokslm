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
@TableName("user")
public class User {
    @TableId(value = "userId",type = IdType.AUTO)
    private int userId;

    @NotNull
    @TableField("userCaseId")
    private String userCaseId;

    @NotNull
    @TableField("userpassword")
    private String userPassword;

    @NotNull
    @TableField("userName")
    private String userName;

    @TableField("userEmailAddress")
    private String userEmailAddress;

    @NotNull
    @TableField("userPrivilege")
    private int userPrivilege; //这个要去掉，最后加进token里

    @TableField("userPreferredName")
    private String userPreferredName;

    @TableField("userAddress")
    private String userAddress;

    @TableField("userPhoneNumber")
    private String userPhoneNumber;

    @NotNull
    @TableField("userGender")
    private int userGender;

    @NotNull
    @TableField("userGraduationYear")
    private int userGraduationYear;

    @TableField("userMajor")
    private String userMajor;

    @TableField("userAvatar")
    private int userAvatar;

    @TableField("userTitle")
    private String userTitle;

    @NotNull
    @TableField("userStatus")
    private int userStatus; //这个也不要了，用token来做
}
