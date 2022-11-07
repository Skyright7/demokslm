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
@TableName("message")
public class MessageCard {
    //设计控制消息卡片的类
    @TableId(value = "messageId",type = IdType.AUTO)
    private int messageId;

    @NotNull
    @TableField("imageId")
    private int imageId; //静态库中这个头图的image的id

    @NotNull
    @TableField("receiverId")
    private int receiverId; //静态库中这个头图的image的id

    @NotNull
    @TableField("header")
    private String header; //message标题

    @NotNull
    @TableField("sender")
    private String sender; //message的送信人

    @NotNull
    @TableField("snapshots")
    private String snapshot; //message详情的简单前一点点

    @NotNull
    @TableField("times")
    private String sendTime; //送信时间（暂时先用string凑活一下，之后再换成datetime）

    @NotNull
    @TableField("detail")
    private String detail; //正文部分
}
