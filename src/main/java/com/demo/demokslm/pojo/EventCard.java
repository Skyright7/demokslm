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
@TableName("event")
public class EventCard {
    //设计控制事件卡片的类
    @TableId(value = "eventId",type = IdType.AUTO)
    private int eventId;

    @NotNull
    @TableField("imageId")
    private int imageId; //静态库中这个头图的image的id


    @NotNull
    @TableField("header")
    private String header; //事件标题


    @NotNull
    @TableField("snapshots")
    private String snapshot; //事件详情的简单前一点点

    @NotNull
    @TableField("times")
    private String holdTime; //举办时间（暂时先用string凑活一下，之后再换成datetime）

    @NotNull
    @TableField("detail")
    private String detail; //正文部分
}
